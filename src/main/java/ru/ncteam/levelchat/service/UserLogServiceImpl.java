package ru.ncteam.levelchat.service;

import javassist.ClassClassPath;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncteam.levelchat.dao.UserLogDAO;
import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private UserLogDAO userLogDAO;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public void setUserLogDAO(UserLogDAO userLogDAO) {
        this.userLogDAO = userLogDAO;
    }


    public boolean checkLogin(String login) {
        return userLogDAO.existUser(login, getQuery("hql/UserInfoByLogin.hql"));
    }

    public String addUser(UserInfo userInfo) {
        return userLogDAO.addUser(userInfo, getQuery("hql/UserInfoByLogin.hql"));
    }

    public String updateUserInfo(UserInfo userInfo) {
        return userLogDAO.updateUserInfo(userInfo);
    }

    public String uploadUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava) {

        StringBuffer fileName = new StringBuffer();
        StringBuffer dirFile = new StringBuffer();
        StringBuffer relativeDir = new StringBuffer();

        if (!photo_ava.isEmpty()) {
            try {
                ClassClassPath cp = new ClassClassPath(this.getClass());
                String projectDir = cp.find(this.getClass().getName()).getPath();
                projectDir = projectDir.substring(0, projectDir.indexOf("WEB-INF"));
                fileName.append(getMD5File(photo_ava));//add filename in var
                fileName.append(getFileExtension(photo_ava));

                relativeDir.append("photo");
                relativeDir.append(File.separator);
                relativeDir.append(fileName.substring(0, 2));
                relativeDir.append(File.separator);
                relativeDir.append(fileName.substring(2, 4));

                dirFile.append(projectDir);

                dirFile.append(relativeDir);
                fileName.delete(0, 4);

                //save photo in file system
                File dir = new File(dirFile.toString());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                byte[] bytes = photo_ava.getBytes();
                File uploadedFile = new File(dirFile.toString() + File.separator + fileName.toString());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                return (relativeDir.toString() + File.separator + fileName);

            } catch (Exception e) {
                return "failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "failed to upload " + photo_ava.getName() + " because the file was empty.";
        }

    }


    public String updateUserInfoPhoto(UserInfo userInfo, String filename) {
        userInfo.setPhoto_ava(filename);
        if (!userLogDAO.updateUserInfoPhoto(userInfo, getQuery("hql/updateUserInfoPhoto.hql")).equals("success")) {
            return "success";
        }
        return "fail";
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userLogDAO.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        try {
            authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<CategoryInterest> getAllCategory() {
        return userLogDAO.getAllCategory(getQuery("hql/allCategory.hql"));
    }

    public List<Interests> getInterestsByCatId(long categoryId) {
        return userLogDAO.getInterestsByCatId(categoryId, getQuery("hql/getInterestsByCatId.hql"));
    }

    public List<Interests> getInterestsByCatName(String categoryName) {

        return userLogDAO.getInterestsByCatName(categoryName, getQuery("hql/getInterestByCatName.hql"));
    }

    public void putInterestsByCatId(long categoryId, List<Interests> interests) throws HibernateException {
        userLogDAO.putInterestsByCatId(categoryId, interests, getQuery("hql/putInterestsByCatId.hql"));
    }

    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException {
        return userLogDAO.putInterests(interests, categoryName, getQuery("hql/CategoryInterestByName.hql"));
    }

    public void deleteInterests(List<Interests> interests, String categoryName) throws HibernateException {
        userLogDAO.deleteInterests(interests, categoryName, getQuery("hql/CategoryInterestByName.hql"));
    }

    public void deleteCategory(String categoryName) throws HibernateException {
        userLogDAO.deleteCategory(categoryName, getQuery("hql/CategoryInterestByName.hql"));
    }

    public void updateInterests(List<Interests> interests) throws HibernateException {
        userLogDAO.updateInterests(interests, getQuery("hql/updateInterests.hql"));
    }

    public void putCategoryInterestByName(String categoryName) throws HibernateException {
        userLogDAO.putCategoryInterestByName(categoryName);
    }

    public UserInfo getUserByLogin(String login) throws HibernateException {
        return userLogDAO.getUserByLogin(login, getQuery("hql/UserInfoByLogin.hql"));
    }

    public List<UserInfo> getUsersChat(String city, String country, String sex, int otAge, int doAge, Long group) {
        return userLogDAO.getUsersForChat( city,  country,  sex,  otAge,  doAge,  group);
    }

    @Override
    public void putInterestList(List<Interests> interestLists, Long groupId) throws HibernateException {
         userLogDAO.putInterestList(interestLists,groupId);
    }
    public Long getId(){
        return userLogDAO.getId();
    }

    /**
     * добавляет интересы конкретного пользователя в dashboard
     * @param userid
     */
    @Override
    public void putDashboard(Long userid) {
        userLogDAO.putDashBoard(userid);
    }


    private String getQuery(String filename) {
        try {
            Resource res = appContext.getResource("classpath:" + filename);
            InputStream is = res.getInputStream();
            File file = res.getFile();
            byte[] fileContent = ByteBuffer.allocate((int) file.length()).array();
            long actualLength = is.read(fileContent, 0, fileContent.length);
            return new String(fileContent, 0, (int) actualLength);
        } catch (Exception ne) {
            ne.getMessage();
        }
        return null;
    }


    private static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }

    private String getMD5File(MultipartFile file) {
        byte[] digest = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            try {
                messageDigest.update(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    public List<String> getUsersInterests(long userId, long categoryId)
    {
        return userLogDAO.getUsersInterests(userId, categoryId);
    }

    public UserInfo getUser(long userId)
    {
        return userLogDAO.getUser(userId);
    }

    public long getUSER_ID(String username)
    {
        return userLogDAO.getUSER_ID(username);
    }

    public Set<Interests> getInterestsByInteresName(List<String> interestsName)
    {
        return userLogDAO.getInterestsByInteresName(interestsName);
    }

    public long getCategoryIDByCatName(String categoryName)
    {    	//getQuery("hql/getCategoryIDByCatName.hql")
        return userLogDAO.getCategoryIDByCatName(categoryName, "");
    }

    public CategoryInterest getCategorie(String name)
    {
        return userLogDAO.getCategorie(name);
    }

    public List<Interests> getListInterests(CategoryInterest name)
    {
        return userLogDAO.getListInterests(name);
    }

    public void putInterestsUser(long UserID, Set<Interests> interests)
    {
        userLogDAO.putInterestsUser(UserID, interests);
    }

    public void addInter(Interests inter)
    {
        System.out.println(userLogDAO.addInterest(inter));
    }

    public void addCategoryInterests(CategoryInterest catInteres)
    {
        System.out.println(userLogDAO.addCategory(catInteres));
    }

    public List<CategoryInterest> getCategories()
    {
        return userLogDAO.getCategory();
    }

}

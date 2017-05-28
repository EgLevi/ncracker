package ru.ncteam.levelchat.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.ncteam.levelchat.entity.*;


import java.util.*;

@Repository
public class UserLogDAOImpl implements UserDetailsService, UserLogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public BCryptPasswordEncoder getBcryptEncoder() {
        return bcryptEncoder;
    }

    public void setBcryptEncoder(BCryptPasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }


    @Transactional
    public String addUser(UserInfo userInfo, String queryString) {

        if (existUser(userInfo, queryString)) {
            userInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
            userInfo.setPhoto_ava("photo/ava.png");
            Role role = new Role();
            role.setRole("ROLE_USER");
            role.setId(1);
            Set<Role> setRole = new HashSet<>();
            setRole.add(role);
            userInfo.setRoles(setRole);
            try {
                sessionFactory.getCurrentSession().save(userInfo);
                return "success";
            } catch (HibernateException e) {
                return e.getMessage();
            }
        } else {
            return "A user with this login already exist";
        }
    }

    @Transactional
    public String updateUserInfo(UserInfo userInfo) {

        try {
            //создаем переменную buf для динамического формирования запроса
            StringBuffer buf = new StringBuffer();
            buf.append("update UserInfo set");
            boolean flag = false;
            if (userInfo.getEmail() != null) {
                buf.append(" email=:email");
                flag = true;
            }
            if (userInfo.getCountry() != null) {
                if (flag) {
                    buf.append(" ,");
                }
                buf.append(" country=:country");
                flag = true;
            }
            if (userInfo.getCity() != null) {
                if (flag) {
                    buf.append(" ,");
                }
                buf.append(" city=:city");
                flag = true;
            }
            if (userInfo.getName() != null) {
                if (flag) {
                    buf.append(" ,");
                }
                buf.append(" name=:name");
                flag = true;
            }
            if (userInfo.getSurname() != null) {
                if (flag) {
                    buf.append(" ,");
                }
                buf.append(" surname=:surname");
                flag = true;
            }
            if (userInfo.getAge() != null) {
                if (flag) {
                    buf.append(" ,");
                }
                buf.append(" age=:age");
                flag = true;
            }
            if (userInfo.getSex() != null) {
                if (flag) {
                    buf.append(" ,");
                }
                buf.append(" sex=:sex");
            }
            buf.append(" where login=:login");
            String queryString = buf.toString();
            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            if (userInfo.getEmail() != null) {
                query.setParameter("email", userInfo.getEmail());
            }
            if (userInfo.getCountry() != null) {
                query.setParameter("country", userInfo.getCountry());
            }
            if (userInfo.getCity() != null) {
                query.setParameter("city", userInfo.getCity());
            }
            if (userInfo.getName() != null) {
                query.setParameter("name", userInfo.getName());
            }
            if (userInfo.getSurname() != null) {
                query.setParameter("surname", userInfo.getSurname());
            }
            if (userInfo.getAge() != null) {
                query.setParameter("age", userInfo.getAge());
            }
            if (userInfo.getSex() != null) {
                query.setParameter("sex", userInfo.getSex());
            }
            query.setParameter("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateUserInfoPhoto(UserInfo userInfo, String queryString) {

        try {
            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("photo_ava", userInfo.getPhoto_ava());
            query.setParameter("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }


    @Transactional
    public boolean existUser(UserInfo userInfo, String queryString) {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("login", userInfo.getLogin());
        return query.getResultList().isEmpty();
    }

    @Transactional
    public boolean existUser(String login, String queryString) {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("login", login);
        return query.getResultList().isEmpty();
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        Set<Role> roles = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from UserInfo where login=:login");
            query.setParameter("login", username);
            userInfo = (UserInfo) query.uniqueResult();
            roles = userInfo.getRoles();
        } catch (HibernateException e) {
        }

        Iterator<Role> it = roles.iterator();
        Collection<GrantedAuthority> collectionGA = new ArrayList<>();
        while (it.hasNext()) {
            collectionGA.add(new SimpleGrantedAuthority(it.next().getRole()));
        }

        return new User(username, userInfo.getPassword(), true, true, true, true, collectionGA);
    }


    @Transactional
    public List<CategoryInterest> getAllCategory(String queryString) {

        try {
            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            return query.getResultList();
        } catch (HibernateException e) {
            return null;

        }
    }

    @Transactional
    public List<Interests> getInterestsByCatId(long categoryId, String queryString) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("categoryId", categoryId);
            return (List<Interests>) query.getResultList();
        } catch (HibernateException e) {
            return null;

        }
    }

    @Transactional
    public List<Interests> getInterestsByCatName(String categoryName, String queryString) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("categoryName", categoryName);
            return (List<Interests>) query.getResultList();
        } catch (HibernateException e) {
            return null;

        }
    }

    @Transactional
    public void putInterestsByCatId(long categoryId, List<Interests> interests, String queryString) throws HibernateException {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        for (Interests interest : interests) {
            query.setParameter("interestName", interest.getInterestName());
            query.setParameter("interestId", interest.getInterestId());
            query.executeUpdate();
        }
    }

    @Transactional
    public void updateInterests(List<Interests> interests, String queryString) throws HibernateException {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        for (Interests interest : interests) {
            query.setParameter("interestName", interest.getInterestName());
            query.setParameter("interestId", interest.getInterestId());
            query.executeUpdate();
        }
    }

    @Transactional
    public List<Long> putInterests(List<Interests> interests, String categoryName, String queryString) throws HibernateException {
        CategoryInterest categoryInterest = getCategoryInterestByName(categoryName, queryString);
        Interests interest;
        List<Long> listId = new ArrayList<>();
        for (Interests interest1 : interests) {
            interest = interest1;
            interest.setCategoryInterest(categoryInterest);
            listId.add((Long) sessionFactory.getCurrentSession().save(interest));
        }
        return listId;
    }

    @Transactional
    public void deleteInterests(List<Interests> interests, String categoryName, String queryString) throws HibernateException {
        CategoryInterest categoryInterest = getCategoryInterestByName(categoryName, queryString);
        Interests interest;
        for (Interests interest1 : interests) {
            interest = interest1;
            interest.setCategoryInterest(categoryInterest);
            sessionFactory.getCurrentSession().delete(interest);
        }
    }

    @Transactional
    public void deleteCategory(String categoryName, String queryString) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        CategoryInterest categoryInterest = getCategoryInterestByName(categoryName, queryString);
        session.delete(categoryInterest);
    }

    @Transactional
    private CategoryInterest getCategoryInterestByName(String categoryName, String queryString) {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("categoryName", categoryName);
        return (CategoryInterest) query.uniqueResult();
    }

    @Transactional
    public void putCategoryInterestByName(String categoryName) throws HibernateException {
        CategoryInterest categoryInterest = new CategoryInterest();
        categoryInterest.setCategoryName(categoryName);
        sessionFactory.getCurrentSession().save(categoryInterest);
    }

    @Transactional
    public UserInfo getUserByLogin(String login, String queryString) throws HibernateException {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("login", login);
        return (UserInfo) query.uniqueResult();
    }

    @Transactional
    public Long getId() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("Select Max(interest_group) FROM interest_list");
        Object result = query.getSingleResult();
        Long id;
        if(result==null)
        {
            id=new Long(0);
        }
        else
        {
            id=Long.parseLong(query.getSingleResult().toString());
        }
        return id;
    }

    @Transactional
    public long getUSER_ID(String username)
    {
        long id;
        List<UserInfo> list = new ArrayList<UserInfo>();
        try
        {
            list = sessionFactory.getCurrentSession().createQuery("from UserInfo where login = '"+username+"'").list();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        if (list.size() != 1)
        {
            return -1;
        }
        else
        {
            id = list.get(0).getUser_id();
            return id;
        }
    }

    @Transactional
    public List<Interests> getListInterests(CategoryInterest name)
    {
        List<Interests> l = new ArrayList<Interests>();
        List<Interests> mylist = new ArrayList<Interests>();
        try{// u where u.CATEGORY_ID="+id
            l = sessionFactory.getCurrentSession().createQuery("from Interests").list();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return l;
        }
        for (Interests curr: l)
        {
            if(curr.getCategoryInterest().getCategoryName().equals(name.getCategoryName()))
            {
                System.out.print(curr.getInterestName() + " ");
                mylist.add(curr);
            }
        }
        return mylist;
    }

    @Transactional
    public CategoryInterest getCategorie(String name)
    {
        //List<CategoryInterest> cat = new ArrayList<CategoryInterest>();
        List<CategoryInterest> exec = new ArrayList<CategoryInterest>();
        try
        {
            exec = sessionFactory.getCurrentSession().createQuery("from CategoryInterest").list();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return exec.get(0);
        }
        for (CategoryInterest curr: exec)
        {
            System.out.println(curr.getCategoryName());
            if(curr.getCategoryName().equals(name))
            {
                return curr;
            }
        }
        return exec.get(0);
    }

    @Transactional
    public String addInterest(Interests inter)
    {
        try
        {
            sessionFactory.getCurrentSession().save(inter);
        }
        catch(Exception e) {
            return e.getMessage();
        }
        return ("success Insert interes");
    }

    @Transactional
    public List<CategoryInterest> getCategory()
    {
        //List<CategoryInterest> myList = new ArrayList<CategoryInterest>();
        //CategoryInterest inter = new CategoryInterest();
        List<CategoryInterest> exec = new ArrayList<CategoryInterest>();
        try
        {
            //Criteria criteria = session.createCriteria(CategoryInterest.class);
            //myList = criteria.add(Restrictions.isNotNull("categoryId")).list();

            //inter = (CategoryInterest)criteria.add(Restrictions.eq("categoryId","1")).uniqueResult();
            //inter = sessionFactory.getCurrentSession().get(CategoryInterest.class, 1);
            Query query = sessionFactory.getCurrentSession().createQuery("from CategoryInterest");
            exec = query.list();

            System.out.println(exec.get(0).getCategoryName());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return exec;
        }
        System.out.println("succes");
        return exec;
    }

    @Transactional
    public String addCategory(CategoryInterest catInteres)
    {
        try
        {
            sessionFactory.getCurrentSession().save(catInteres);
        }
        catch(Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @Transactional
    public String updateUserInfo(UserInfo userInfo,String queryString) {

        try {
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("email", userInfo.getEmail());
            query.setParameter("country", userInfo.getCountry());
            query.setParameter("city", userInfo.getCity());
            query.setParameter("name", userInfo.getName());
            query.setParameter("surname", userInfo.getSurname());
            query.setParameter("age", userInfo.getAge());
            query.setParameter("sex", userInfo.getSex());
            query.setParameter("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Transactional
    public long getCategoryIDByCatName(String categoryName,String queryString)
    {
        try {
            Query query=sessionFactory.getCurrentSession().createQuery("from CategoryInterest where categoryName=:categoryName");
            query.setParameter("categoryName", categoryName);
            List<CategoryInterest> response = query.list();
            return response.get(0).getCategoryId();
        } catch (HibernateException e) {
            return -1;

        }
    }


    @Transactional
    public UserInfo getUser(long userId)
    {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserInfo WHERE user_id=:ParamID");
        query.setParameter("ParamID", userId);
        List<UserInfo> response = query.list();
        UserInfo userInfo = response.get(0);
        return userInfo;
    }

    @Transactional
    public List<String> getUsersInterests(long userId, long categoryId)
    {
        List<String> listInterests = new ArrayList<String>();
        Set<Interests> interests = new HashSet<Interests>();
        UserInfo userInfo = getUser(userId);
        interests = userInfo.getInterests();
        for(Interests inter:interests){
            if(inter.getCategoryInterest().getCategoryId() == categoryId)
            {
                listInterests.add(inter.getInterestName());
            }
        }
        return listInterests;
    }

    @Transactional
    public Set<Interests> getInterestsByInteresName(List<String> interestsName)
    {
        Set<Interests> interestsList = new HashSet<Interests>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Interests where interestName=:Param");
        for(String name:interestsName){
            query.setParameter("Param", name);
            List<Interests> list = query.list();
            interestsList.add(list.get(0));
        }
        return interestsList;
    }

    @Transactional
    public void putInterestsUser(long UserID, Set<Interests> interests)
    {
        UserInfo userinfo = getUser(UserID);
        userinfo.setInterests(interests);
    }

    @Transactional
    @Override
    public void putDashBoard(Long userid) {
        DashboardUrl dashboardUrl = new DashboardUrl();
        Interests interests = new Interests();
        Query query = sessionFactory.getCurrentSession().createQuery("from UserInterest where userId =:userid");
        query.setParameter("userid", userid);
        List<UserInterest> uListInter = (List<UserInterest>) query.list();
        for (UserInterest anUListInter : uListInter) {

            interests.setInterestId(anUListInter.getInterestId());
            dashboardUrl.setBoardId(dashboardUrl.getBoardId() + 1);
            dashboardUrl.setInterests(interests);
            dashboardUrl.setUrlInterest("href");
            sessionFactory.getCurrentSession().save(dashboardUrl);
        }
    }

    /**
     * @param interestLists
     * @param groupid
     */
    @Override
    @Transactional
    public void putInterestList(List<Interests> interestLists, Long groupid) {
        InterestList interestList = new InterestList();
        Interests interests = new Interests();
        for (int i = 0; i < interestLists.size(); i++) {

            interests.setInterestId(interestLists.get(i).getInterestId());

            interestList.setInterest(interests);

            interestList.setInterestGroup(groupid);
            sessionFactory.getCurrentSession().save(interestList);
        }
    }

    /**
     * @param city
     * @param country
     * @param sex
     * @param otAge
     * @param doAge
     * @param group
     * @return listOfUsersForChat
     * @throws HibernateException
     */
    @Override
    @Transactional
    public ArrayList<String> getUsersForChat(String city, String country, String sex, int otAge, int doAge, Long group, Long USERID) throws HibernateException {

        String q = "SELECT t1.user_id\n" +
                "FROM (SELECT\n" +
                "        uint.user_id,\n" +
                "        COUNT(uint.user_id)\n" +
                "      FROM User_interest uint, Interest_list ilist\n" +
                "      WHERE uint.interest_id = ilist.interest_id\n" +
                "            AND ilist.INTEREST_GROUP = :groupId\n" +
                "            AND uint.USER_ID NOT IN (\n" +
                "        SELECT uc1.USER_ID\n" +
                "        FROM USER_CHAT uc1\n" +
                "        WHERE uc1.CHAT_ID IN (SELECT uc2.CHAT_ID\n" +
                "                              FROM USER_CHAT uc2\n" +
                "                              WHERE uc2.USER_ID = :userid\n" +
                "        )\n" +
                "\n" +
                "      )\n" +
                "            AND uint.USER_ID <> :userid\n" +
                "      GROUP BY uint.user_id\n" +
                "      ORDER BY COUNT(uint.user_id) DESC) t1";


        Query sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(q);

        sqlQuery.setParameter("groupId", getId());
        sqlQuery.setParameter("userid", USERID);
//        sqlQuery.setParameter("city", "'"+city+"'");
//
//        sqlQuery.setParameter("country", "'"+country+"'");
//        sqlQuery.setParameter("sex", "'"+sex+"'");
//        sqlQuery.setInteger("otAge", otAge);
//        sqlQuery.setInteger("doAge", doAge);
        System.out.println("Айдишники собеседников");
        System.out.println(sqlQuery.getResultList().toString());

        String q1 = "Select Login From User_info where user_id = :userid";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(q1);
        ArrayList ar = new ArrayList();
        for (Object userid : sqlQuery.list()) {
            query.setParameter("userid", userid);
            System.out.println(query.getSingleResult().toString());
            ar.add(query.getSingleResult().toString());
        }
        /*for (int i = 0; i<sqlQuery.getResultList().size(); i++){
            query.setParameter("userid",sqlQuery.res);
            System.out.println(query.getResultList().toString());
            ar.add(query.getResultList().toString());
        }*/

        return ar;
    }

}

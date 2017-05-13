package ru.ncteam.levelchat.utils;

import javassist.ClassClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class ApplicationUtil {

    public String getStringFromFile(String pathname) {
        File file;
        BufferedReader reader;
        file = new File(getClass().getClassLoader().getResource(pathname).getFile());
        StringBuilder query = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = reader.readLine()) != null) query.append(s);
            return query.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    /**
     *
     * @param file
     * @return ссылку на файл
     */
    public String uploadFile(MultipartFile file) {

        StringBuffer fileName = new StringBuffer();
        StringBuffer dirFile = new StringBuffer();
        StringBuffer relativeDir = new StringBuffer();

        if (!file.isEmpty()) {
            try {
                ClassClassPath cp = new ClassClassPath(this.getClass());
                String projectDir = cp.find(this.getClass().getName()).getPath();
                projectDir = projectDir.substring(0, projectDir.indexOf("WEB-INF"));
                fileName.append(getMD5File(file));//add filename in var
                fileName.append(getFileExtension(file));

                relativeDir.append("uploads");
                relativeDir.append(File.separator);
                relativeDir.append(fileName.substring(0, 2));
                relativeDir.append(File.separator);
                relativeDir.append(fileName.substring(2, 4));

                dirFile.append(projectDir);

                dirFile.append(relativeDir);
                fileName.delete(0, 4);

                File dir = new File(dirFile.toString());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                byte[] bytes = file.getBytes();
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                return relativeDir.toString() + File.separator + fileName;

            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param file
     * @return расширение файла
     */
    private static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }

    /**
     * @param file
     * @return md5 hash файла
     */
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
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

}

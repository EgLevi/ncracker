package ru.ncteam.levelchat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class FileController {


    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String getDemoFile() {
        return "demoFileUp";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        StringBuffer fileName = new StringBuffer();
        StringBuffer dirFile = new StringBuffer();

        if (!file.isEmpty()) {
            try {
                fileName.append(getMD5File(file));//add filename in var
                fileName.append(getFileExtension(file));
                dirFile.append("uploads");
                dirFile.append(File.separator);
                dirFile.append(fileName.substring(0, 2));
                dirFile.append(File.separator);
                dirFile.append(fileName.substring(2, 4));
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

                return "You successfully uploaded file=" + fileName;

            } catch (Exception e) {
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + fileName + " because the file was empty.";
        }
    }

    /**
     * @param file
     * @return extension of file
     */
    private static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }

    /**
     *
     * @param file
     * @return md5 hash of file
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
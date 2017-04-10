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


        StringBuffer filename = new StringBuffer();
        StringBuffer dirfile = new StringBuffer();
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        if (!file.isEmpty()) {
            try {



/*
                MD5 encryption
*/
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.reset();
                    messageDigest.update(file.getBytes());
                    digest = messageDigest.digest();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                BigInteger bigInt = new BigInteger(1, digest);
                String md5Hex = bigInt.toString(16);

                while (md5Hex.length() < 32) {
                    md5Hex = "0" + md5Hex;
                }
/*
                End MD5 encryption
*/

                filename.append(md5Hex);//add filename in var
                filename.append(getFileExtension(file));
                dirfile.append("uploads");
                dirfile.append(File.separator);
                dirfile.append(filename.substring(0,2));
                dirfile.append(File.separator);
                dirfile.append(filename.substring(2, 4));
                filename.delete(0, 4);

                File dir = new File(dirfile.toString());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                byte[] bytes = file.getBytes();
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                return "You successfully uploaded file=" + filename;

            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + filename + " because the file was empty.";
        }
    }

    /**
     *
     * @param file
     * @return extension of file
     */
    private static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }

}
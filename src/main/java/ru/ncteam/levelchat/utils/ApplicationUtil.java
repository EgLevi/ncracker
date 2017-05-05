package ru.ncteam.levelchat.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class ApplicationUtil {

    @Autowired
    private ApplicationContext appContext;

    public String getStringFromFile(String pathname) {
        File file;
        BufferedReader reader;
        file = new File(getClass().getClassLoader().getResource("hql/allMessages.hql").getFile());
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

}

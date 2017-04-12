package ru.ncteam.levelchat.service;

import org.json.simple.JSONObject;
import ru.ncteam.levelchat.entity.Message;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Senter1st on 10.04.2017.
 */
@WebListener
public class FileUploadService implements ServletContextListener {
    public void contextInitialized(final ServletContextEvent sce) {

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}

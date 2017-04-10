/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncteam.levelchat.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author vara
 */
public class UploadService extends HttpServlet {
    private Random random = new Random();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		
                //провер€ем €вл€етс€ ли полученный запрос multipart/form-data
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
                // —оздаЄм класс фабрику 
		DiskFileItemFactory factory = new DiskFileItemFactory();
                
                // ћаксимальный буфера данных в байтах,
		// при его привышении данные начнут записыватьс€ на диск во временную директорию
		// устанавливаем один мегабайт
		factory.setSizeThreshold(1024*1024);
                // устанавливаем временную директорию
		File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(tempDir);
                
                //—оздаЄм сам загрузчик
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//максимальный размер данных который разрешено загружать в байтах
		//по умолчанию -1, без ограничений. ”станавливаем 10 мегабайт. 
		upload.setSizeMax(1024 * 1024 * 10);
                
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			
			while (iter.hasNext()) {
			    FileItem item = (FileItem) iter.next();
 
			    if (item.isFormField()) {
			    	//если принимаема€ часть данных €вл€етс€ полем формы			    	
			        processFormField(item);
			    } else {
			    	//в противном случае рассматриваем как файл
			        processUploadedFile(item);
			    }
			}			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
                
	}
        private void processUploadedFile(FileItem item) throws Exception {
		File uploadetFile = null;
		//выбираем файлу им€ пока не найдЄм свободное
		do{
			String path = getServletContext().getRealPath("/upload/"+random.nextInt() + item.getName());					
			uploadetFile = new File(path);		
		}while(uploadetFile.exists());
		
		//создаЄм файл
		uploadetFile.createNewFile();
		//записываем в него данные
		item.write(uploadetFile);
	}
 
	/**
	 * ¬ыводит на консоль им€ параметра и значение
	 * @param item
	 */
	private void processFormField(FileItem item) {
		System.out.println(item.getFieldName()+"="+item.getString());		
	}

}

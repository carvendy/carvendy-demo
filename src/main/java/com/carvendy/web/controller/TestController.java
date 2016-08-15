package com.carvendy.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	
	@RequestMapping(value="/run/email.do")
	public void test(){
		System.out.println("email");
	}
	
	@RequestMapping(value="/test/dl.do")
	public String dload(HttpServletRequest request,HttpServletResponse response){
		String path="D:\\download\\weibo\\1.jpg";
		download(path, response);
		
		return "";
	}

	 public HttpServletResponse download(String path, HttpServletResponse response) {
	        try {
	            // path是指欲下载的文件的路径。
	            File file = new File(path);
	            // 取得文件名。
	            String filename = file.getName();
	            // 取得文件的后缀名。
	            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

	            // 以流的形式下载文件。
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();

	            // 清空response
	            response.reset();

	            // 设置response的Header
	                          //下载后，提示的默认名称。
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
	            response.addHeader("Content-Length", "" + file.length());// 文件大小
	            response.setContentType("application/octet-stream");// 文件流


	             //文件流输出到  response.getOutputStream() ，浏览器就会弹出框框下载了
	            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return response;
	    }


}

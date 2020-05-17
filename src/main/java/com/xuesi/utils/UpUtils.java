package com.xuesi.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;


public class   UpUtils {

	public static void upfile(MultipartFile file, HttpServletRequest request) {
		//file 是你当前上传的文件 request 当前请求
		//获取当前服务器路径
		//需要先获取文件的后缀   xx.jpg或者xx.mp4
		//获取我们上传的文件的名字
		String orgName = file.getOriginalFilename();
		System.out.println("上传文件的名称："+orgName);
		//String[] split = orgName.split(".");
		//使用字符串的截取  获取最后一个点的下表   取前不取后
		String suffix = orgName.substring(orgName.lastIndexOf(".")+1);
		System.out.println("截取的名称："+suffix);
		String realPath1 ="";
	    String realPath ="";
		if(suffix.equals("mp4")){
			realPath1 = request.getSession().getServletContext().getRealPath("/WEB-INF/video/");
			//realPath="D:\\111\\video_clips\\src\\main\\webapp\\WEB-INF\\video\\";
			realPath="D:\\毕业项目杂编\\毕业设计项开始整合项目\\xuesi\\src\\main\\webapp\\WEB-INF\\video\\";

		}else{
			realPath1 = request.getSession().getServletContext().getRealPath("/WEB-INF/img/");
			realPath="D:\\毕业项目杂编\\毕业设计项开始整合项目\\xuesi\\src\\main\\webapp\\WEB-INF\\img\\";
			//realPath="D:\\111\\video_clips\\src\\main\\webapp\\WEB-INF\\img\\";
		}
		//在本地能够看见你的上传功能成功了-->源代码项目的地址-->只是为了测试是否成功用的
		//获取我们的地址
		File file1 = new File(realPath1);
//		检测当前路径是否存在
		if (!file1.exists()) {
			file1.mkdirs();
		}

		//只是为了完善检测功能的成功-->如果进行项目发布以下内容必须删除
		File file2 = new File(realPath);
		if(!file2.exists()) {
            file2.mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(realPath1+orgName,true);
			FileOutputStream fos1 = new FileOutputStream(realPath+orgName,true);
			fos.write(file.getBytes());
			fos1.write(file.getBytes());
			fos1.flush();
			fos.flush();
			fos1.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}

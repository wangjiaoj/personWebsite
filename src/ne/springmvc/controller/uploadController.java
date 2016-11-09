package ne.springmvc.controller;

import java.io.File;

import javax.annotation.Resource;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ne.springmvc.service.PictureManager;
 
import ne.springmvc.entity.Picture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class uploadController {
	@Resource(name = "pictureManager")
	private PictureManager pictureManager;
	
	 @RequestMapping("/fileUpload")  
	    public void   fileUpload(@RequestParam("file") MultipartFile file,HttpServletResponse response ) {  
	        // 判断文件是否为空  
	        if (!file.isEmpty()) {  
	            try {  	                	 	                                                    	 
	            	File myfile=new File("D:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\personWebsite\\resources\\upload\\",System.currentTimeMillis()+file.getOriginalFilename());
	            	file.transferTo(myfile); // 转存文件     转存到上方目录中去
	                Picture picture=new Picture();
	                picture.setPicUrl(myfile.getName());
	                pictureManager.addPicture(picture);	       	 	            	 	            	                
	                String result=myfile.getName();
	                response.getWriter().write(result);   
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        // 重定向  
	        //return "redirect:/sucess.jsp";  
	    }  

}

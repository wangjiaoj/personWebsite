package ne.springmvc.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
import ne.springmvc.entity.User;
import ne.springmvc.service.UserManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userManager")
	private UserManager userManager;
	 

	@RequestMapping("/login")
	public void getUser(String userName,String pwd,HttpServletResponse response,HttpSession session) throws IOException {
		System.out.println("userName"+userName);
		System.out.println("pwd"+pwd);
		Integer id=1;
		User user=userManager.getUser(id);
		System.out.println(userName.equals(user.getUserName()));		
		System.out.println(pwd.equals(user.getPwd()));
		String result;
		if(pwd.equals(user.getPwd())&& userName.equals(user.getUserName())  ){
			result="true";			 
		}
		else{
			result="false";			 
		}
		session.setAttribute("loginStatus", result);  
		response.getWriter().write(result);   
	}

	@RequestMapping("/exitlogin")
	public String exitLogin(HttpSession session) throws IOException {
		session.setAttribute("loginStatus", null);  
		System.out.println("xxxxxxxxxxx"+session.getAttribute("loginStatus"));
		return "redirect:/index.jsp";
	}
	
	 
	@RequestMapping("/uploadUserInfo")
	public void uploadUserInfo(String userName,String introduce,HttpServletResponse response) throws IOException {
		
		System.out.println("userName"+userName);
		System.out.println("introduce"+introduce);	 
		boolean re=userManager.updateUserInfo(userName, introduce); 
		String result=String.valueOf(re);
		response.getWriter().write(result);   
	}
	
	@RequestMapping("/uploadUserPwd")
	public void uploadUserPwd(String pwd,String newPwd,HttpServletResponse response) throws IOException {
		System.out.println("pwd"+pwd);
		Integer id=1;
		User user=userManager.getUser(id);
		System.out.println("旧密码"+user.getPwd());
		String result=null;
	    if(pwd.equals(user.getPwd())){
	    	boolean re= userManager.updateUserPwd(newPwd); 
			  result=String.valueOf(re);
	 	}
	    else{
	    	  result="旧密码错误";
	    }
		System.out.println(pwd.equals(user.getPwd()));
		response.getWriter().write(result);   
	}
	
	@RequestMapping("/uploadMail")
	public void uploadMail(String email,HttpServletResponse response) throws IOException {		 
	
		boolean re= userManager.updateUserEmail(email);
		String result=String.valueOf(re);
		response.getWriter().write(result);   
	}
	
	  
    /*** 
     * 更新个人头像 
     *  
     * @return 
     */  
	
 @RequestMapping("/uploadPhoto")  
    public void   UploadPhoto(@RequestParam("file") MultipartFile file,HttpServletResponse response ) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  	                	 	                                                    	 
            	File myfile=new File("D:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\personWebsite\\resources\\upload\\",System.currentTimeMillis()+file.getOriginalFilename());
            	file.transferTo(myfile); // 转存文件     转存到上方目录中去            
                String url=myfile.getName();
                userManager.updateUserPhoto(url);	       	 	            	 	            	                
                String result=myfile.getName();
                response.getWriter().write(result);   
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
         
       
    }  
 
 
 @RequestMapping("/getUserInfo")  
 @ResponseBody
 public User  getUserInfo(HttpServletResponse response ) {  
     // 判断文件是否为空  
	 int id=1;
	 User user=userManager.getUser(id);
	 user.setPwd("");
	 System.out.println(user.getEmail()); 
	 return user;
 }  
 
}
package ne.springmvc.service;


import ne.springmvc.dao.UserDao;
import ne.springmvc.entity.User;

public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	private User user;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser(Integer id) {
		return userDao.getUser(id);
	}

	public User  getUserByName(String Name){
		return userDao.getUserByName(Name);
	}
	 

	public boolean UserLogin(String name,String pwd){
	      int id=1;
		  user=userDao.getUser(id);
		  if(name.equals(user.getUserName())&&pwd.equals( user.getPwd())){
				return true;			  
		  }
		  else{
				return false;
		  }		  		
	}
	
   public boolean updateUserInfo(String name,String introduction){
	   int id=1;
	   User  user=new User();
	   user.setUserId(id);
	   user.setUserName(name);
	   user.setIntroduction(introduction);
	   return userDao.updateUserInfo(user);
   };
	
	public boolean updateUserEmail(String email){
		int id=1;
		User  user=new User();
		user.setUserId(id);
		user.setEmail(email);
		System.out.println("xxxxxemail"+email);
		return userDao.updateUserEmail(user);
	};
	
	public boolean updateUserPwd(String pwd){
		int id=1;
		User  user=new User();
		user.setUserId(id);
		user.setPwd(pwd);
		return userDao.updateUserPwd(user);
	};
	
	public boolean updateUserPhoto(String url){
		int id=1;
		 User  user=new User();
		 user.setUserId(id);
		 user.setPhotoURL(url);
		return userDao.updateUserPhoto(user);
	};
	
	
}

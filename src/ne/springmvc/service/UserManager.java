package ne.springmvc.service;

import ne.springmvc.entity.User;

public interface UserManager {

	public User getUser(Integer id);

	public User getUserByName(String Name);

	//用户登录，账号及密码验证
	public boolean UserLogin(String name,String pwd);
	 
	public boolean updateUserInfo(String name,String introduction);
	
	public boolean updateUserEmail(String email);
	
	public boolean updateUserPwd(String pwd);
	
	public boolean updateUserPhoto(String url);
	
}
package ne.springmvc.dao;

 

import ne.springmvc.entity.User;

public interface UserDao {
	public User getUser(Integer id);
	
	public User getUserByName(String userName);
	 
	public boolean updateUserInfo(User user);
	
	public boolean updateUserPwd(User user);
	
	public boolean updateUserEmail(User user);
	
	public boolean updateUserPhoto(User user);
	 
}

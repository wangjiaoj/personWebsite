package ne.springmvc.dao;

import ne.springmvc.entity.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 根据用户id查询用户
	 */
	public User getUser(Integer userId) {

		String hql = "from User u where u.userId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, userId);
		return (User) query.uniqueResult();
	} 
	/**
	 * 根据用户name查询用户
	 */
	public User getUserByName(String userName) {

		String hql = "from User u where u.userName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, userName);
		return (User) query.uniqueResult();
	} 
	 

	/**
	 * 编辑用户姓名、简介
	 */
	public boolean updateUserInfo(User user) {

		String hql = "update User u set u.userName = ?, u.introduction=? where u.userId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getUserName());
		query.setString(1, user.getIntroduction());
		query.setLong(2, user.getUserId());
		return (query.executeUpdate() > 0);
	}
	/**
	 * 修改密码
	 */
	public boolean updateUserPwd(User user){
		String hql = "update User u set  u.pwd=? where u.userId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		query.setString(0, user.getPwd());	 
		query.setLong(1, user.getUserId());
		return (query.executeUpdate() > 0);
	}
	
	/**
	 * 修改用户绑定的邮箱账号
	 */
	public boolean updateUserEmail(User user){
		String hql = "update User u set u.email=? where u.userId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	 
		query.setString(0, user.getEmail());	 
		query.setLong(1, user.getUserId());
		return (query.executeUpdate() > 0);
	}
	
	/**
	 * 修改用户头像
	 */
	public boolean updateUserPhoto(User user){
		String hql = "update User u set u.photoURL=? where u.userId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	 
		query.setString(0, user.getPhotoURL());	 
		query.setLong(1, user.getUserId());
		return (query.executeUpdate() > 0);
	}
}
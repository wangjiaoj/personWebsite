package ne.springmvc.dao;
 
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import ne.springmvc.entity.Picture;

public class PictureDaoImpl implements PictureDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Picture getPicture(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Picture p where p.picId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, id);

		return (Picture) query.uniqueResult();
	}

	 
 
	 
	public void addPicture(Picture picture) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(picture);
	}

	 
	public boolean delPicture(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete Picture p where p.picId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, id);

		return (query.executeUpdate() > 0);
	}

 

}

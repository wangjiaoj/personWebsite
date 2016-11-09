package ne.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import ne.springmvc.entity.Article;
import ne.springmvc.entity.Tag;
import ne.springmvc.entity.TagRelationship;
 
 
public class TagDaoImpl implements TagDao{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Tag getTagByName(String tagName) {
		// TODO Auto-generated method stub
		String hql = "from Tag t where t.tagName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, tagName);	 
		return (Tag) query.uniqueResult();		 
	}

	@Override
	public int addTag(Tag tag) {
		// TODO Auto-generated method stub
		Serializable id = sessionFactory.getCurrentSession().save(tag);
		return (Integer)id;
	}
	public void addTagRelationship(TagRelationship tagRelationship){
		sessionFactory.getCurrentSession().save(tagRelationship);
	}
	public boolean UpdateTagNum(Tag tag){
		String hql = "update Tag t set  t.tagNum=? where t.tagId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		query.setLong(0, tag.getTagId() );	 
		query.setLong(1, tag.getTagNum() );
		return (query.executeUpdate() > 0);
	}
	@Override
	public List<Tag> getTagListByarticleId(int articleId) {
		// TODO Auto-generated method stub
		
		String hql = "select a.tag.tagName from TagRelationship a where a.article.articleId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, articleId);	 
		return query.list();
		 
	}
	public List<Tag> searchTagListByKeyWord(String tagName) {
		// TODO Auto-generated method stub
		String hql = "from Tag a where a.tagName like '%"+tagName+"%' ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		return query.list();
	}

	
	@Override
	public  boolean deleteTagRelationByarticleId(int articleId) {
		// TODO Auto-generated method stub
		String hql = "delete from TagRelationship a where a.article.articleId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, articleId);
		return (query.executeUpdate() > 0);
	}
	
	public List<Tag> topFourTag(){
		String hql = "from Tag a ORDER BY a.tagNum desc limit 0,4";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		return query.list();
		 
	}
}

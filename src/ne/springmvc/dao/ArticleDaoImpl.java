package ne.springmvc.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import ne.springmvc.entity.Article;
import ne.springmvc.entity.Category;
import ne.springmvc.entity.Tag;
 
 
import java.util.List;
public class ArticleDaoImpl implements ArticleDao{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Article getArticle(Integer articleId) {
		// TODO Auto-generated method stub
		String hql = "from Article a where a.articleId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, articleId);
		return (Article) query.uniqueResult();
	}

	@Override
	public int addArticle(Article article) {
		// TODO Auto-generated method stub
		Serializable id =sessionFactory.getCurrentSession().save(article);	 
		return (Integer) id;
	}

	@Override
	public List<Article> getArticleList(Category category) {
		// TODO Auto-generated method stub
		String hql = "from Article a where a.category.categoryId=?   ORDER BY a.writenTime desc";	
		//String hql = "from Article a,Tag b, TagRelationship c where a.category.categoryId=? and c.article.articleId=a.articleId and c.tag.tagId=b.tagId  ORDER BY a.writenTime";		  
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, category.getId());
		return query.list();
	 
	}

	@Override
	public List<Article> getArticleListByDate(Category category, String startDate,String endDate) {
		// TODO Auto-generated method stub
		
		String hql = "from Article a where a.category.categoryId=? and  a.writenTime BETWEEN "+startDate+" AND "+endDate+"  ORDER BY a.writenTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, category.getId());
	 
		return query.list();
	}

	@Override
	public List<Article> getArticleListByTag(Category category, Tag tag) {
		// TODO Auto-generated method stub
		String hql = "select a.articleId from Article a ,TagRelationship b where a.articleId=b.article.articleId  and a.category.categoryId=? and b.tag.tagId=?  ORDER BY a.writenTime desc";		 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, category.getId());
		query.setLong(1, tag.getTagId());				
		return query.list();
		 
	}
	
	
	public List<Article> searchArticleListByKeyWord(String articleTitle) {
		// TODO Auto-generated method stub
		String hql = "select a.articleId , a.articleTitle from Article a where a.articleTitle like '%"+articleTitle+"%' ORDER BY a.writenTime";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		return query.list();
	}
	
	public boolean UpdateCommentNum(Article article){
		String hql = "update Article a set  a.commentsNum=? where a.articleId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		query.setLong(0,article.getCommentsNum());	 
		query.setLong(1,article.getArticleId());
		return (query.executeUpdate() > 0);
	}
	@Override
	public  boolean deleteArticleByArticleId(int articleId) {
		// TODO Auto-generated method stub
		
		String hql = "delete from Article a where a.articleId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, articleId);
		return (query.executeUpdate() > 0);
		 
	}
	public boolean editArticle(Article article){
		String hql = "update Article a set  a.articleTitle=?,a.content=? where a.articleId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		query.setString(0,article.getArticleTitle());	 
		query.setString(1,article.getContent());	
		query.setLong(2,article.getArticleId());
		return (query.executeUpdate() > 0);
	}
	public List getArticleListMinTime() {
		String hql = "select min(writenTime) from Article";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		 
		return query.list();
	   
	}
}

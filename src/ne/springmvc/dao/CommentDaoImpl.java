package ne.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import ne.springmvc.entity.Comment;

public class CommentDaoImpl implements CommentDao{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<Comment> getCommentListByArticleId(Integer articleId) {
		// TODO Auto-generated method stub
		String hql = "select c.commentId as commentId, c.content as content, c.flag as flag,c.reviewerName as reviewerName,c.replayName as replayName from Comment c where c.article.articleId=? ORDER BY c.publishedTime";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, articleId);
		return query.list();
	 
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		Serializable id = sessionFactory.getCurrentSession().save(comment);
		System.out.println("id"+id);
		return (Integer) id;
	}

	@Override
	public boolean delComment(Integer commentId) {
		// TODO Auto-generated method stub
		String hql = "delete Comment c where c.commentId = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, commentId);

		return (query.executeUpdate() > 0);
	}
	
	 public  boolean deleteCommentByArticleId(Integer articleId) {
		 String hql = "delete Comment c where c.article.articleId = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setLong(0, articleId);

			return (query.executeUpdate() > 0);
	 }

}

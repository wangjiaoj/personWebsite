package ne.springmvc.service;

import java.util.Date;
import java.util.List;

import ne.springmvc.dao.ArticleDao;
import ne.springmvc.dao.CommentDao;
import ne.springmvc.entity.Article;
import ne.springmvc.entity.Comment;

public class CommentManagerImpl implements CommentManager{
	private CommentDao commentDao;
	private ArticleDao articleDao;
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	@Override
	public List<Comment> getComment(Integer articleId) {
		// TODO Auto-generated method stub
		 
		return commentDao.getCommentListByArticleId(articleId);
	}

	@Override
	public int addComment(Comment comment,Integer  articleId) {
		// TODO Auto-generated method stub
		Date publishedTimed= new Date();//生成当前日期		 
		comment.setDate(publishedTimed);
		Article article=new Article();
		article.setArticleId(articleId);
		comment.setArticle(article);
		Article article1=articleDao.getArticle(articleId);
	    int num=article1.getCommentsNum()+1;
	    System.out.println("num"+num);
	    article.setCommentsNum(num);
		articleDao.UpdateCommentNum(article);
		
		return commentDao.addComment(comment);
	}

	@Override
	public boolean delComment(Integer articleId,Integer commentId) {
		// TODO Auto-generated method stub	
		Article article=new Article();
		article.setArticleId(articleId);	 
		Article article1=articleDao.getArticle(articleId);
	    int num=article1.getCommentsNum()-1;
	    System.out.println("num"+num);
	    article.setCommentsNum(num);
		articleDao.UpdateCommentNum(article);
		return commentDao.delComment(commentId);
	}

}

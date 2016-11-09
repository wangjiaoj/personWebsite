package ne.springmvc.dao;

import java.util.Date;
import java.util.List;

import ne.springmvc.entity.Article;
import ne.springmvc.entity.Category;
import ne.springmvc.entity.Tag;

public interface ArticleDao {
	
   public Article getArticle(Integer articleId);
   
   public List<Article> getArticleList(Category category);
   
   public List<Article> getArticleListByDate(Category category,String startDate,String endDate) ;
  	 
      
   public List<Article> getArticleListByTag(Category category,Tag tag) ;
  		
  	 
	public int addArticle(Article article);
	
	public boolean UpdateCommentNum(Article article);
	
	public List<Article> searchArticleListByKeyWord(String articleTitle);
	
	public  boolean deleteArticleByArticleId(int articleId);
	
	public boolean editArticle(Article article);
	
	public List getArticleListMinTime();
}

package ne.springmvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ne.springmvc.dao.ArticleDao;
import ne.springmvc.dao.TagDao;
import ne.springmvc.entity.Article;
import ne.springmvc.entity.Category;
import ne.springmvc.entity.Tag;

public interface ArticleManager {	
	
	 
	
	public int addArticle(Article article,ArrayList<String> taglist);
	
	public String deleteArticleByArticleId(int articleId);
	
	public Map getArticle(int articleId) ;
	
	public List<Object> getArticleList(Category category);

	
    public List<Object>  getArticleListByDate(Category category,int year,int month);
    	 
    public List<Object>  getArticleListByTag(Category category,Tag tag) ;
		
	public Map searchArticleListByKeyWord(String KeyWord);
	
	public Map searchTagListByKeyWord(String KeyWord);
	
	public int editArticle(Article article,ArrayList<String> taglist);
	
	public String getArticleListMinTime();
	
	public List<Tag> topFourTag();
}

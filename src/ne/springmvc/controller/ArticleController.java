package ne.springmvc.controller;


 

import java.io.IOException;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
 


import ne.springmvc.entity.Article;
import ne.springmvc.entity.Category;
import ne.springmvc.entity.Tag;
 
import ne.springmvc.service.ArticleManager;
 
 
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 

@Controller
@RequestMapping("/arcticle")
public class ArticleController {
	
	@Resource(name = "articleManager")
	private ArticleManager articleManager;
	
	@RequestMapping("/addArticle")
	@ResponseBody
	public String addArticle(@RequestBody String ArticleInfo, HttpServletRequest request) {
				
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;
		JSONArray tagNameList=Info.getJSONArray("tagNameList");
	 	System.out.println("size：======" +tagNameList.size());
	 	ArrayList tagList = new ArrayList(); 
		for (int i= 0; i<tagNameList.size(); i++) {
            String jo = (String) tagNameList.get(i);
            tagList.add(tagNameList.get(i)); 
            System.out.println("i：======" +tagNameList.get(i)) ;
		}
	 	Article article=new Article();
		article.setArticleTitle(Info.getString("articleTitle"));
		article.setContent(Info.getString("content"));
		Category category=new Category();
		int categoryId=Integer.parseInt(Info.getString("categoryId"));	 
		category.setId(categoryId);
		article.setCategory(category);
	    int articelId= articleManager.addArticle(article, tagList);
		return "articelId:"+articelId+"";
	}
	
	@RequestMapping("/getArticle")
	@ResponseBody
	public Map getArticle(@RequestBody String ArticleInfo, HttpServletRequest request) {		
		     
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		 	 
		int articleId= Integer.parseInt(Info.getString("articleId"));	
		System.out.println("artilceId：======" + articleId) ;	
		Map<String,Object> map=articleManager.getArticle(articleId);
	  
		return map;
	}
	
	@RequestMapping("/getArticleList")
	@ResponseBody
	public List<Object> getArticleList(@RequestBody String ArticleInfo, HttpServletRequest request) {		
		
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;
		Category category=new Category();
		int categoryId=Integer.parseInt(Info.getString("categoryId"));	 
		category.setId(categoryId);
		  
		List<Object> articleList= articleManager.getArticleList(category);
		 
		return articleList;
	}
	
	@RequestMapping("/getArticleByDate")
	@ResponseBody
	public List<Object> getArticleListByDate(@RequestBody String ArticleInfo, HttpServletRequest request) {
		 
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;
	            
		int year= Integer.parseInt(Info.getString("year")); 
		int month=Integer.parseInt( Info.getString("month"));
		 
		Category category=new Category();
		int categoryId=Integer.parseInt(Info.getString("categoryId"));	 
		category.setId(categoryId);	
		    
		List<Object> articleList=  articleManager.getArticleListByDate(category, year, month);		 
		return articleList;
	}
	
	@RequestMapping("/getArticleByTag")
	@ResponseBody
	public	List<Object> getArticleListByTag (@RequestBody String ArticleInfo, HttpServletRequest request) {
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;
	  
	 	Tag tag=new Tag();
	 	int tagId=Integer.parseInt(Info.getString("tagId"));
		
		tag.setTagId(tagId) ;
		Category category=new Category();
		int categoryId=Integer.parseInt(Info.getString("categoryId"));	 
		category.setId(categoryId);
		 
		List<Object> articleList= articleManager.getArticleListByTag(category, tag);
				
		return articleList;
	}
	
	@RequestMapping("/searchArticle")
	@ResponseBody
	public Map searchInfo(@RequestBody String ArticleInfo, HttpServletRequest request) {
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		String KeyWord=Info.getString("key");	
		Map<String,Object> map=articleManager.searchArticleListByKeyWord(KeyWord);
		return map;
	}
	
	
	@RequestMapping("/searchTag")
	@ResponseBody
	public Map searchTag(@RequestBody String ArticleInfo, HttpServletRequest request) {
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		String KeyWord=Info.getString("key");	
		Map<String,Object> map=articleManager.searchTagListByKeyWord(KeyWord);
		return map;
	}
	
	@RequestMapping("/deleteArticleByArticleId")
	@ResponseBody
	public Map deleteArticleByArticleId (@RequestBody String ArticleInfo, HttpServletRequest request) {
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;   
		int articleId= Integer.parseInt(Info.getString("articleId")); 
	    String result=articleManager.deleteArticleByArticleId(articleId);
	    System.out.println("result：======" + result) ;   
	    Map<String,String> map=new HashMap<String,String>();
	    map.put("result",result);
	    return map;
	   
	}
	
	
	@RequestMapping("/editArticle")
	@ResponseBody
	public String editArticle(@RequestBody String ArticleInfo, HttpServletRequest request) {
				
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;
		JSONArray tagNameList=Info.getJSONArray("tagNameList");
	 	System.out.println("size：======" +tagNameList.size());
	 	ArrayList tagList = new ArrayList(); 
		for (int i= 0; i<tagNameList.size(); i++) {
            String jo = (String) tagNameList.get(i);
            tagList.add(tagNameList.get(i)); 
            System.out.println("i：======" +tagNameList.get(i)) ;
		}
		
		int articleId=Integer.parseInt(Info.getString("articleId"));
	 	Article article=new Article();
	 	article.setArticleId(articleId);
		article.setArticleTitle(Info.getString("articleTitle"));
		article.setContent(Info.getString("content"));
	    int articelId= articleManager.editArticle(article, tagList);
		return "articelId:"+articelId+"";
	}
	
	@RequestMapping("/getArticleListMinTime")
	public void getArticleListMinTime(HttpServletResponse response) throws IOException{
		String minTime=articleManager.getArticleListMinTime();		 
		response.getWriter().write(minTime);	 
	}
	
	@RequestMapping("/getTopFourTag")
	@ResponseBody
	public List<Tag> getTopFourTag(){
		return articleManager.topFourTag();
	}
	
}

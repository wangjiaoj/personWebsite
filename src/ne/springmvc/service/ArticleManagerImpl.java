package ne.springmvc.service;

 

 

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ne.springmvc.entity.Article;
import ne.springmvc.entity.Category;
import ne.springmvc.entity.Comment;
import ne.springmvc.entity.Tag;
import ne.springmvc.entity.TagRelationship;
import ne.springmvc.dao.ArticleDao;
import ne.springmvc.dao.CommentDao;
import ne.springmvc.dao.TagDao;
public class ArticleManagerImpl implements ArticleManager{

	private ArticleDao articleDao;
	private TagDao tagDao;
	private CommentDao commentDao;
	private Date minTime;
	
	public void setArticleDao(ArticleDao articleDao){
		this.articleDao=articleDao;
	}
	public void setTagDao(TagDao tagDao){
		this.tagDao=tagDao;
	}
	
	public void setCommentDao(CommentDao commentDao){
		this.commentDao=commentDao;
	}
	
	@Override
	public int addArticle(Article article,ArrayList<String> taglist) {
		// TODO Auto-generated method stub
		Date d = new Date();//生成当前日期		 
		article.setWritenTime(d);
		article.setHits(0);
		article.setCommentsNum(0);
		int articleId =articleDao.addArticle(article);
	
		article.setArticleId(articleId);//补全articleId
		Iterator it1 = taglist.iterator();
	    while(it1.hasNext()){	                				 	 
		      String tagName=(String) it1.next();
			  System.out.println("---"+tagName);  
			  Tag tag = tagDao.getTagByName(tagName);	//查询是否存在该tag	
			  System.out.println("tag---"+tag); 
			  if(tag!=null){					 	
				 tag.setTagNum(tag.getTagNum()+1);//更新num;							 				    		 					 
			  }
			 else{
				tag =new Tag();
				tag.setTagName(tagName);   
				tag.setTagNum(0);
				int tagid=tagDao.addTag(tag);	
				tag.setTagId(tagid);//补全tagid
			}			  			    
			TagRelationship tagRelationship=new TagRelationship();					
			tagRelationship.setArticle(article);
			tagRelationship.setTag(tag);
				  //添加relationship，触发器并将自动更新tag.tagNum
			System.out.println("------addTagRelationship---"); 
			tagDao.addTagRelationship(tagRelationship); 
			 		 			
		}
		if(articleId>0){
			return articleId;
		}
		else{return -1;}
		
	}
        /*根据文章id,删除Article，tagRelationship，comment表中的记录,触发器自动更新tagNUm*/
	public String deleteArticleByArticleId(int articleId){
 
		boolean tagrs=tagDao.deleteTagRelationByarticleId(articleId);
	    boolean comrs=commentDao.deleteCommentByArticleId(articleId);
		boolean Articlers=articleDao.deleteArticleByArticleId(articleId);
		String result="删除成功";		 
		return result;
	}
	
	
	
	public List<Object> getArticleList(Category category) {
		List<Article> list=articleDao.getArticleList(category);
		List<Object> dataList =new  ArrayList<Object>();
		Iterator item = list .iterator();
	    while(item.hasNext()){	
	    	Article article= (Article) item.next();
	    	int articleId=article.getArticleId();
	    	List<Tag> taglist=tagDao.getTagListByarticleId(articleId);
	    	Map<String,Object> map=new HashMap<String,Object>();  
	    	map.put("Article", article);
	    	map.put("tagList", taglist);
	    	dataList.add(map);
	    }
		return dataList ;
	}

	
    public List<Object> getArticleListByDate(Category category,int year,int month) {
    	 
    	Calendar cal = Calendar.getInstance();   	  
    	  // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
    	cal.set(Calendar.YEAR,year);
    	cal.set(Calendar.MONTH, month);
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	cal.add(Calendar.DAY_OF_MONTH, -1);
    	Date lastDate = cal.getTime();
    	  
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	Date firstDate = cal.getTime();
    	
    	Format format = new SimpleDateFormat("yyyyMMdd");
		   
		System.out.println(format.format(firstDate));
		System.out.println(format.format(lastDate));
		String startDate =format.format(firstDate);
		String endDate =format.format(lastDate);
		List<Article> list=articleDao.getArticleListByDate(category, startDate, endDate);
		List<Object> dataList =new  ArrayList<Object>();
		Iterator item = list.iterator();
	    while(item.hasNext()){	
	    	Article article= (Article) item.next();
	    	int articleId=article.getArticleId();
	    	List<Tag> taglist=tagDao.getTagListByarticleId(articleId);
	    	Map<String,Object> map=new HashMap<String,Object>();  
	    	map.put("Article", article);
	    	map.put("tagList", taglist);
	    	dataList.add(map);
	    }
		return dataList ;
    }
    
    public List<Object> getArticleListByTag(Category category,Tag tag) {
    	List<Article> list= articleDao.getArticleListByTag(category, tag);
			List<Object> dataList =new  ArrayList<Object>();
    	  
			Iterator item = list.iterator();
		    while(item.hasNext()){	
		        int articleId=(Integer) item.next();		    	  
		    	System.out.println("-----------articleId"+articleId);
		    	Article	article =articleDao.getArticle(articleId);
		    	List<Tag> taglist=tagDao.getTagListByarticleId(articleId);
		    	Map<String,Object> map=new HashMap<String,Object>();  
		    	map.put("Article", article);
		    	map.put("tagList", taglist);
		    	dataList.add(map);
		    }
			return dataList ;
	}
	@Override
	public Map getArticle(int articleId) {
		// TODO Auto-generated method stub	
		Map<String,Object> map=new HashMap<String,Object>();  
		
		Article article=articleDao.getArticle(articleId);
		map.put("article",article);
		List<Tag> tagList=tagDao.getTagListByarticleId(articleId);
	 	map.put("tagList",tagList);
        if(article.getCommentsNum()>0){
            List<Comment> commentList=commentDao.getCommentListByArticleId(articleId);
        	map.put("CommentList",commentList);
        }
		return map;
	}
	
	public Map searchArticleListByKeyWord(String KeyWord){
		Map<String,Object> map=new HashMap<String,Object>(); 
		List<Article> articleInfoList=articleDao.searchArticleListByKeyWord(KeyWord);
		map.put("articleInfoList",articleInfoList);
		return map;
	}
	
	public Map searchTagListByKeyWord(String KeyWord){
		Map<String,Object> map=new HashMap<String,Object>(); 
		List<Tag> tagList=tagDao.searchTagListByKeyWord(KeyWord);
		map.put("TagList",tagList);
		return map;
	}

	public int editArticle(Article articleInfo,ArrayList<String> taglist){
		
		boolean tagrs=tagDao.deleteTagRelationByarticleId(articleInfo.getArticleId());
		Article article=articleDao.getArticle(articleInfo.getArticleId());
		article.setArticleTitle(articleInfo.getArticleTitle());
		
		article.setContent(articleInfo.getContent());
		articleDao.editArticle(article);
		Iterator it1 = taglist.iterator();
	    while(it1.hasNext()){	                				 	 
		     String tagName=(String) it1.next();
			 System.out.println("---"+tagName);  
			 Tag tag = tagDao.getTagByName(tagName);	//查询是否存在该tag	
			 System.out.println("tag---"+tag); 		 
			 if(tag!=null){					 	
				tag.setTagNum(tag.getTagNum()+1);//更新num;
			 }
			 else{
				tag =new Tag();
				tag.setTagName(tagName);   
				tag.setTagNum(0);
			    int tagid=tagDao.addTag(tag);	
				tag.setTagId(tagid);//补全tagid
			 }			  			    
		    TagRelationship tagRelationship=new TagRelationship();					
		    tagRelationship.setArticle(article);
			tagRelationship.setTag(tag);
				  //添加relationship，触发器并将自动更新tag.tagNum
			System.out.println("------addTagRelationship---"); 
			tagDao.addTagRelationship(tagRelationship); 
		}	
		int id=article.getArticleId();
		return id;
	};
	
	public String getArticleListMinTime(){
		Date minTime=null;
		String ss=null;
		List list=articleDao.getArticleListMinTime();;
		 
		Format format = new SimpleDateFormat("yyyy-MM-dd");
	 
		Iterator item = list.iterator();
	    while(item.hasNext()){	
	        minTime=(Date) item.next();		
	        if(minTime!=null){
	            System.out.println("-----------minTimeminTime"+minTime);   	
	        }
	        else{
	        	 minTime=new Date();
	        }
	        ss=format.format(minTime);
	    	System.out.println("-----------minTime"+ss);
	    }
	    return ss;
	}
	
	public List<Tag> topFourTag(){
		System.out.println("-----------topFourTag-----");
		List<Tag> taglist=tagDao.topFourTag();
		if(taglist.size()>4){
			for (int i = taglist.size()-1; i >=4 ; i--) { 		 		 			 
				taglist.remove(i); 		
				System.out.println("i:"+i);
			} 
		}

		return taglist;
	}
}

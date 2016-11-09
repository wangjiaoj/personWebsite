package ne.springmvc.controller;
  

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  

import ne.springmvc.entity.Article;
import ne.springmvc.entity.Category;
import ne.springmvc.entity.Comment;
import ne.springmvc.entity.Tag;
 
import ne.springmvc.service.CommentManager;
 
 
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Resource(name = "commentManager")
	private CommentManager commentManager;
	
	@RequestMapping("/addComment")
	@ResponseBody
	public String addArticle(@RequestBody String ArticleInfo, HttpServletRequest request) {
				
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;
	 	 
		Comment comment=new Comment();
	 
		comment.setContent(Info.getString("content"));	
		comment.setMail(Info.getString("mail"));
		comment.setName(Info.getString("reviewerName"));
		comment.setReplayName(Info.getString("replayName"));
	    if(Info.getString("reviewerName")==null){
	    	comment.setFlag(0);
	    }
		int articleId= Integer.parseInt(Info.getString("articleId"));	   
	   int commentId= commentManager.addComment(comment,articleId);
	  //  String jsonString = JSON.toJSONString( );  
		return "commentId:"+commentId+"";
	}

	@RequestMapping("/delComment")
	@ResponseBody
	public String delComment(@RequestBody String ArticleInfo, HttpServletRequest request) {
			
		JSONObject Info = new JSONObject().fromObject(ArticleInfo); 
		System.out.println("ArticleInfo：======" + ArticleInfo) ;		
		int articleId= Integer.parseInt(Info.getString("articleId"));	   
		int commentId= Integer.parseInt(Info.getString("commentId"));	   
        boolean flag=  commentManager.delComment(articleId,commentId) ;
       
		return "commentId:"+flag+"";
	}

}

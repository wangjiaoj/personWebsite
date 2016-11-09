package ne.springmvc.service;

import java.util.List;

import ne.springmvc.entity.Article;
import ne.springmvc.entity.Comment;

public interface CommentManager {
	 public List<Comment> getComment(Integer articleId);
		
	 public int addComment(Comment comment,Integer articleId);
	 public boolean delComment(Integer articleId,Integer commentId);
}

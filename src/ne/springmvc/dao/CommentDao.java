package ne.springmvc.dao;
import java.util.List;
import ne.springmvc.entity.Comment;

public interface CommentDao {
	 public List<Comment> getCommentListByArticleId(Integer articleId);
		
	 public int addComment(Comment comment);
	 public boolean delComment(Integer commentId);
	 public  boolean deleteCommentByArticleId(Integer articleId) ;
}

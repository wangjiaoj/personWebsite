package ne.springmvc.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;  
import javax.persistence.ManyToOne;  

import org.hibernate.annotations.GenericGenerator;
/**
 * 此实体的定义应用了springmvc的注解，省去了传统的Hibernate跟数据库之间的映射配置文件
 * 
 * @author will
 *
 */
@Entity
@Table(name = "BEComment")
public class Comment {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "increment")
	private Integer commentId;
	
	@Column(length = 3000)
	private String content;

	@Column 
	private Integer flag;
		
	@Column(length = 32)
	private String mail;
	
	@Column(length = 32)
	private String reviewerName;
 
	@Column(length = 32)
	private String replayName;
	
	@Column 
	private Date publishedTime;
	
	@ManyToOne()
    @JoinColumn(name="articleId") 
	private Article article;
	
	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getContent() {
		return commentId;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFlag() {
		return flag;
	}
	
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return reviewerName;
	}
	
	public void setName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	
	public String getReplayName() {
		return replayName;
	}
	
	public void setReplayName(String replayName) {
		this.replayName = replayName;
	}
	public Date getDate() {
		return publishedTime;
	}
	
	public void setDate(Date publishedTime) {
		this.publishedTime = publishedTime;
	}
	
	public Article getArticle() {
		return article;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	 
	
}

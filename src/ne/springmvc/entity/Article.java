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
@Table(name = "BEArticle")
public class Article {
						
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "increment")
	private Integer articleId;

	@Column(length = 32)
	private String articleTitle;

	@Column(length = 3000)
	private String content;
	
	@Column 
	private Date writenTime;
	
	@Column 
	private Integer commentsNum;
	
	@Column 
	private Integer hits;
	
	@ManyToOne()           
    @JoinColumn(name="categoryId")     //Category类中对应外键的属性：categoryId   
	private Category category;
  
 
	
	public Integer getArticleId() {
		return articleId;
	}
	
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	
	public String getArticleTitle() {
		return articleTitle;
	}
	
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getWritenTime() {
		return writenTime;
	}
	
	public void setWritenTime(Date writenTime) {
		this.writenTime = writenTime;
	}
	
	
	public Integer getCommentsNum() {
		return commentsNum;
	}
	
	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}
	public Integer getHits() {
		return hits;
	}
	
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
 
	 
	
 
}

package ne.springmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 此实体的定义应用了springmvc的注解，省去了传统的Hibernate跟数据库之间的映射配置文件
 * 
 * @author will
 *
 */
@Entity
@Table(name = "DTUser")
public class User {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "increment")
	private Integer userId;

	@Column(length = 32)
	private String userName;

	@Column(length = 32)
	private String pwd;
	
	@Column(length = 32)
	private String email;
	
	@Column(length = 250)
	private String introduction;
	
	@Column(length = 250)
	private String photoURL;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}
	

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	
}
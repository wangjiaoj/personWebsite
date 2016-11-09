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
@Table(name = "DTCategory")
public class Category {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "increment")
	private Integer categoryId;

	@Column(length = 32)
	private String 	categoryName;

	public Integer getId() {
		return categoryId;
	}

	public void setId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}

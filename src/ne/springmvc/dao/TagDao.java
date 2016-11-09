package ne.springmvc.dao;

import java.util.List;

import ne.springmvc.entity.Tag;
import ne.springmvc.entity.TagRelationship;
public interface TagDao {
	public Tag getTagByName(String tagName);
	
	public int addTag(Tag tag);
	
	public List<Tag> getTagListByarticleId(int articleId);
	
	public void addTagRelationship(TagRelationship tagRelationship);
	public boolean UpdateTagNum(Tag tag);
	public List<Tag> searchTagListByKeyWord(String tagName);
	
	public  boolean deleteTagRelationByarticleId(int articleId) ;
	
	public List<Tag> topFourTag();
}

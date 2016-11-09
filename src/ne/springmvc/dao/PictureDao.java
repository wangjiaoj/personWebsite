package ne.springmvc.dao;
 

import ne.springmvc.entity.Picture;
 
 
public interface PictureDao {
	public Picture getPicture(Integer id);
	
	public void addPicture(Picture picture);

	public boolean delPicture(Integer id);
	
}

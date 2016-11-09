package ne.springmvc.service;

 

import ne.springmvc.entity.Picture;

public interface PictureManager {
	
	public Picture getPicture(Integer id);

	public void addPicture(Picture picture);

	public boolean delPicture(Integer id);
 
}

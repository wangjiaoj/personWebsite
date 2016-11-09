package ne.springmvc.service;

import java.util.List;

 
import ne.springmvc.entity.Picture;
import ne.springmvc.dao.PictureDao;
 

public class PictureManagerImpl implements PictureManager{
	private PictureDao pictureDao;
	
	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	public Picture getPicture(Integer id) {
		// TODO Auto-generated method stub
		return pictureDao.getPicture(id);
	}

	 
	 
	public void addPicture(Picture picture) {
		// TODO Auto-generated method stub
		pictureDao.addPicture(picture);
	}

	 
	public boolean delPicture(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}

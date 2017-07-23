package com.kmbt.csa.dav.bo.impl;
/*
Copyright (c) 2014-2015 Konica Minolta
Cloud Services Applications
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmbt.csa.dav.bo.UserBo;
import com.kmbt.csa.dav.dao.UserDao;
import com.kmbt.csa.dav.model.UserDataModel;
import com.kmbt.csa.dav.pojo.User;
/**
 * @author Kallol Das
 * @Description This service layer servlet handles requests coming from controller and interacts directly with the DAO layer.
 * It acts as an interface between controller and DAO layer of the application.
 * @version 1.0
 * 
 */
@Service("userBo")
public class UserBoImpl implements UserBo {

	@Autowired
	UserDao userDao;

	/**
	 * 
	 * @Description This method calls getUserInfo() method of {@link UserDao}
	 *              type objects then with the retrieved result in
	 *              {@link UserDataModel} form object then compares the input
	 *              given by view layer {@link User} bean before final
	 *              authentication
	 * @return {@link boolean}
	 */
	public boolean authenticate(User usr) {

		UserDataModel udm = userDao.getUserInfo(usr.getUserName());
		if(udm!=null){		
		if (usr.getUserName().equalsIgnoreCase(udm.getUsername())
				&& usr.getPassword().equalsIgnoreCase(udm.getPassword()))
			return true;			
	  }
		return false;
	}
}

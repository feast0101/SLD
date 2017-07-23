package com.kmbt.csa.dav.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kmbt.csa.dav.dao.UserDao;
import com.kmbt.csa.dav.model.UserDataModel;
import com.kmbt.csa.dav.util.CustomHibernateDaoSupport;

@Repository("userDao")
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao {

	@Override
	public UserDataModel getUserInfo(String username) {
		List<UserDataModel> userlist = getHibernateTemplate().find(
				"from UserDataModel where username=?", username);
		return (userlist.isEmpty()) ? null : userlist.get(0);

	}

}

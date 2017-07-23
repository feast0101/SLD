package com.kmbt.csa.dav.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kmbt.csa.dav.bo.impl.DataBoImpl;
import com.kmbt.csa.dav.dao.DataDao;
import com.kmbt.csa.dav.model.DBDataModel;
import com.kmbt.csa.dav.util.CustomHibernateDaoSupport;

@Repository("dataDao")
public class DataDaoImpl extends CustomHibernateDaoSupport implements DataDao {
	private static final Logger logger = Logger.getLogger(DataDaoImpl.class);
	@Override
	public List<DBDataModel> getTenantData(String companyname) {
			List<DBDataModel> list = getHibernateTemplate().find("from DBDataModel where companyname=?", companyname);
		return list;
	}

	@Override
	public List<DBDataModel> getAllData() {
		List<DBDataModel> list = getHibernateTemplate().find("from DBDataModel");
		return list;
	}
	@Override
	public List<DBDataModel> getSelectiveData( java.sql.Date startDt,java.sql.Date endDate) {
		logger.info("Start Date :"+startDt);
		logger.info("End Date :"+endDate);
	    DetachedCriteria criteria = DetachedCriteria.forClass(DBDataModel.class)
				.add(Restrictions.between("date",startDt,endDate));
	    List<DBDataModel> list = getHibernateTemplate().findByCriteria(criteria);
	    return list;
     }

}

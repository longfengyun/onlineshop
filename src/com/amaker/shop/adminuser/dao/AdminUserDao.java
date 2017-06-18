package com.amaker.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.adminuser.bean.AdminUser;

/**
 * 后台登录的Dao类
 * @author ChangchunLong
 *
 */
public class AdminUserDao extends HibernateDaoSupport{
	// Dao完成登录的代码
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username =? and password =?";
		System.out.println("hello dao");
		List<AdminUser> list = this.getHibernateTemplate().find(hql, new String[]{adminUser.getUsername(),adminUser.getPassword()});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
   
}

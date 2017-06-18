package com.amaker.shop.adminuser.service;

import com.amaker.shop.adminuser.bean.AdminUser;
import com.amaker.shop.adminuser.dao.AdminUserDao;

/**
 * 后台登录的service类
 * @author ChangchunLong
 *
 */
public class AdminUserService {
    //注入Dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	//业务层登录
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}

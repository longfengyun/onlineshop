package com.amaker.shop.adminuser.service;

import com.amaker.shop.adminuser.bean.AdminUser;
import com.amaker.shop.adminuser.dao.AdminUserDao;

/**
 * ��̨��¼��service��
 * @author ChangchunLong
 *
 */
public class AdminUserService {
    //ע��Dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	//ҵ����¼
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}

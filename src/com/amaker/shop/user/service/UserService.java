package com.amaker.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.amaker.shop.user.bean.User;
import com.amaker.shop.user.dao.UserDao;
import com.amaker.shop.util.MailUtil;
import com.amaker.shop.util.UUIDUtil;

/**
 * 用户模块服务层的代码
 * @author ChangchunLong
 *
 */
@Transactional
public class UserService {
     //注入UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	//按用户名查询用户的方法
	public User findByUsername(String username){
		System.out.println("Service");
		return userDao.findByUsername(username);
	}
    //业务层保存用户
	public void save(User user) {
		user.setState(0);//0代表未激活，1代表已经激活
		String uuid = UUIDUtil.getUUID()+UUIDUtil.getUUID();
		user.setCode(uuid);
		userDao.save(user);
		MailUtil.sendMail(user.getEmail(), uuid);
	}
    //业务层查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
    //业务层更新用户状态
	public void update(User user) {
		userDao.update(user);
	}
    //业务层用户登录
	public User login(User user) {
		return userDao.login(user);
	}
}

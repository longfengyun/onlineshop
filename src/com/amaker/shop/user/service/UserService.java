package com.amaker.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.amaker.shop.user.bean.User;
import com.amaker.shop.user.dao.UserDao;
import com.amaker.shop.util.MailUtil;
import com.amaker.shop.util.UUIDUtil;

/**
 * �û�ģ������Ĵ���
 * @author ChangchunLong
 *
 */
@Transactional
public class UserService {
     //ע��UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	//���û�����ѯ�û��ķ���
	public User findByUsername(String username){
		System.out.println("Service");
		return userDao.findByUsername(username);
	}
    //ҵ��㱣���û�
	public void save(User user) {
		user.setState(0);//0����δ���1�����Ѿ�����
		String uuid = UUIDUtil.getUUID()+UUIDUtil.getUUID();
		user.setCode(uuid);
		userDao.save(user);
		MailUtil.sendMail(user.getEmail(), uuid);
	}
    //ҵ����ѯ�û�
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
    //ҵ�������û�״̬
	public void update(User user) {
		userDao.update(user);
	}
    //ҵ����û���¼
	public User login(User user) {
		return userDao.login(user);
	}
}

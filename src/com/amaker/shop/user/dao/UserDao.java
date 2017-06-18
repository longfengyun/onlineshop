package com.amaker.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.user.bean.User;

/**
 * �û��ĳ־ò����
 * @author ChangchunLong
 *
 */
public class UserDao extends HibernateDaoSupport{
    //�����Ʋ�ѯ�Ƿ��и��û�
	public User findByUsername(String username){
		String hql = "from User u where u.username=?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		System.out.println("UserDao");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
    //�����û������ݿ�
	public void save(User user) {
	   this.getHibernateTemplate().save(user);
	}
	//����code��ѯ�û�
	public User findByCode(String code) {
		String hql ="from User u where u.code=?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//�����û�״̬
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}
	//�û���¼��ѯ
	public User login(User user) {
		String hql = "from User u where u.username=? and u.password=? and u.state=?";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}

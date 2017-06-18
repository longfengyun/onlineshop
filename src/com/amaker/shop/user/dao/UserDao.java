package com.amaker.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.user.bean.User;

/**
 * 用户的持久层代码
 * @author ChangchunLong
 *
 */
public class UserDao extends HibernateDaoSupport{
    //按名称查询是否有该用户
	public User findByUsername(String username){
		String hql = "from User u where u.username=?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		System.out.println("UserDao");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
    //保存用户到数据库
	public void save(User user) {
	   this.getHibernateTemplate().save(user);
	}
	//根据code查询用户
	public User findByCode(String code) {
		String hql ="from User u where u.code=?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//更新用户状态
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}
	//用户登录查询
	public User login(User user) {
		String hql = "from User u where u.username=? and u.password=? and u.state=?";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}

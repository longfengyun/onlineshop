package com.amaker.shop.adminuser.bean;
/**
 * 后台管理者的实体类
 * @author ChangchunLong
 *
 */
public class AdminUser {
    private Integer uid;//管理者的ID
    private String username;//管理者的名字
    private String password;//管理者的密码
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}

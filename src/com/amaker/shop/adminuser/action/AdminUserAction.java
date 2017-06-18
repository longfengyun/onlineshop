package com.amaker.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.adminuser.bean.AdminUser;
import com.amaker.shop.adminuser.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 后台管理登录的Action
 * @author ChangchunLong
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
    //接收模型驱动的AdminUser
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	//注入AdminUserService
    private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	// 后台登录的执行的方法
	public String login() {
		// 调用service方法完成登录
		AdminUser existAdminUser = adminUserService.login(adminUser);
		// 判断
		if (existAdminUser == null) {
			// 用户名或密码错误
			this.addActionError("用户名或密码错误!");
			return "loginFail";
		} else {
			// 登录成功:
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	/**
	 * 管理者退出的方法
	 */
	public String logout(){
		//获取session并销毁
		ServletActionContext.getRequest().getSession().invalidate();
		//返回到首页
		return "logout";
	}
}

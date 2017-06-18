package com.amaker.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.user.bean.User;
import com.amaker.shop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 实现用户注册
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动需要用的
	private User user=new User();
	//获取模型驱动
	public User getModel() {
		return user;
	}
	//注入UserService
	private UserService userService;

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	//接收注册验证码；
	private String checkCode;
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	//接收登录验证码；
	private String checkCode1;
	public void setCheckCode1(String checkCode1){
		this.checkCode1 = checkCode1;
	}
	/**
	 * 跳转到注册页面的执行方法
	 */
	public String registerPage() {

		return "registerPage";
	}
	
	public String register(){
		//判断验证程序：
		//从Session中获取验证码的随机值：
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode.equalsIgnoreCase(checkcode1)){
			//验证码不匹配
			//返回一个错误信信息
			this.addActionError("验证码错误!");
			//返回原注册页面
			return "checkCodeError";
		}
		else{
			//验证码匹配
			userService.save(user);
			this.addActionMessage("注册成功了，请去邮箱激活");
			return "msg";
		}
	}
	/**
	 * AJAX异步校验用户的方法
	 * @throws IOException 
	 * 
	 */
	public String findByName() throws IOException{
		User existUser = userService.findByUsername(user.getUsername());
		//获取response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			//查询到该用户已经存在
			response.getWriter().println("<font color='red'>用户已经存在</font>");
		}
		else{
			response.getWriter().println("<font color='green'>用户可以使用</font>");
		}
		return null;
	}
	/**
	 * 用户激活的方法
	 */
	public String active(){
		//获取code
		User existUser = userService.findByCode(user.getCode());
		System.out.println("hello,active");
		//判断是否有该用户
		if(existUser==null){
			//激活失败，不存在该用户
			this.addActionMessage("激活失败：激活码错误!");
			return "msg";
		}
		else{
			//激活成功
			this.addActionMessage("激活成功：请登录!");
			//改变用户状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			return "msg";
		}
	}
	
	/**
	 * 用户登录页面跳转
	 */
	public String loginPage(){
		return "loginPage";
	}
	/**
	 * 用户登录的方法
	 */
	public String login(){
		//判断验证程序：
		//从Session中获取验证码的随机值：
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		//模型驱动自动接收表单内容
		User existUser = userService.login(user);
		if(existUser==null){
			//用户不存在或未激活
			this.addActionError("用户名或密码错误或未激活,请重新输入!" );
			return "login";
		}
		else{
			//将用户信息存入Session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//页面跳转
			return "loginSuccess";
		}
	}
	
	/**
	 * 用户退出的方法
	 */
	public String logout(){
		//获取session并销毁
		ServletActionContext.getRequest().getSession().invalidate();
		//返回到首页
		return "logout";
	}
}

package com.amaker.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.user.bean.User;
import com.amaker.shop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ʵ���û�ע��
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//ģ��������Ҫ�õ�
	private User user=new User();
	//��ȡģ������
	public User getModel() {
		return user;
	}
	//ע��UserService
	private UserService userService;

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	//����ע����֤�룻
	private String checkCode;
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	//���յ�¼��֤�룻
	private String checkCode1;
	public void setCheckCode1(String checkCode1){
		this.checkCode1 = checkCode1;
	}
	/**
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registerPage() {

		return "registerPage";
	}
	
	public String register(){
		//�ж���֤����
		//��Session�л�ȡ��֤������ֵ��
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode.equalsIgnoreCase(checkcode1)){
			//��֤�벻ƥ��
			//����һ����������Ϣ
			this.addActionError("��֤�����!");
			//����ԭע��ҳ��
			return "checkCodeError";
		}
		else{
			//��֤��ƥ��
			userService.save(user);
			this.addActionMessage("ע��ɹ��ˣ���ȥ���伤��");
			return "msg";
		}
	}
	/**
	 * AJAX�첽У���û��ķ���
	 * @throws IOException 
	 * 
	 */
	public String findByName() throws IOException{
		User existUser = userService.findByUsername(user.getUsername());
		//��ȡresponse������ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			//��ѯ�����û��Ѿ�����
			response.getWriter().println("<font color='red'>�û��Ѿ�����</font>");
		}
		else{
			response.getWriter().println("<font color='green'>�û�����ʹ��</font>");
		}
		return null;
	}
	/**
	 * �û�����ķ���
	 */
	public String active(){
		//��ȡcode
		User existUser = userService.findByCode(user.getCode());
		System.out.println("hello,active");
		//�ж��Ƿ��и��û�
		if(existUser==null){
			//����ʧ�ܣ������ڸ��û�
			this.addActionMessage("����ʧ�ܣ����������!");
			return "msg";
		}
		else{
			//����ɹ�
			this.addActionMessage("����ɹ������¼!");
			//�ı��û�״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			return "msg";
		}
	}
	
	/**
	 * �û���¼ҳ����ת
	 */
	public String loginPage(){
		return "loginPage";
	}
	/**
	 * �û���¼�ķ���
	 */
	public String login(){
		//�ж���֤����
		//��Session�л�ȡ��֤������ֵ��
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		//ģ�������Զ����ձ�����
		User existUser = userService.login(user);
		if(existUser==null){
			//�û������ڻ�δ����
			this.addActionError("�û�������������δ����,����������!" );
			return "login";
		}
		else{
			//���û���Ϣ����Session��
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//ҳ����ת
			return "loginSuccess";
		}
	}
	
	/**
	 * �û��˳��ķ���
	 */
	public String logout(){
		//��ȡsession������
		ServletActionContext.getRequest().getSession().invalidate();
		//���ص���ҳ
		return "logout";
	}
}

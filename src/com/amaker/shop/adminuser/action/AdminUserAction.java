package com.amaker.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.adminuser.bean.AdminUser;
import com.amaker.shop.adminuser.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ��̨�����¼��Action
 * @author ChangchunLong
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
    //����ģ��������AdminUser
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	//ע��AdminUserService
    private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	// ��̨��¼��ִ�еķ���
	public String login() {
		// ����service������ɵ�¼
		AdminUser existAdminUser = adminUserService.login(adminUser);
		// �ж�
		if (existAdminUser == null) {
			// �û������������
			this.addActionError("�û������������!");
			return "loginFail";
		} else {
			// ��¼�ɹ�:
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	/**
	 * �������˳��ķ���
	 */
	public String logout(){
		//��ȡsession������
		ServletActionContext.getRequest().getSession().invalidate();
		//���ص���ҳ
		return "logout";
	}
}

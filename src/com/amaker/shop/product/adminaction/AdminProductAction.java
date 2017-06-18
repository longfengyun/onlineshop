package com.amaker.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.categorysecond.service.CategorySecondService;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.amaker.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨��Ʒ�����Action
 * @author ChangchunLong
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
    //ģ���������յĶ���
	private Product product = new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//ע��productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//ע���������categoryService
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//�ļ��ϴ���Ҫ������������
	private File upload;//�ϴ����ļ�
	private String uploadFileName;//�����ļ��ϴ����ļ���
	private String uploadContextType;//�ļ��ϴ���MIME���͡����ļ���׺
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	//���շ�ҳ��ѯ��Ʒ
	public String findAll(){
		//����pageBean
		PageBean<Product> pageBean = productService.findAll(page);
		//��pageBean����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//��ת�������Ʒ��ҳ��
	public String addPage(){
		//1.�Ȳ�ѯ���еĶ������������
		List<CategorySecond>  csList = categorySecondService.findAll();
		//��csList���õ�ֵջ��
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	//������Ʒ
	public String save() throws IOException, ParseException{
		// ���ύ��������ӵ����ݿ���.
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		product.setPdate(date3);
		// product.setImage(image);
		if(upload != null){
			// ����ƷͼƬ�ϴ�����������.
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products/1");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	//��Ʒɾ��
	public String delete(){
		//�Ȳ�ѯ��Ʒ��ɾ��
        product = productService.findByPid(product.getPid());
		//ɾ���ϴ�����ƷͼƬ
        String path = product.getImage();
        if(path!=null){
        	String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
        	File file =new File(realPath);
        	file.delete();
        }
		//ɾ����Ʒ
		productService.delete(product);
		return "deleteSuccess";
	}
	//ת����Ʒ�޸�ҳ��
	public String edit(){
		//�ȸ�����Ʒid��ѯ��Ʒ
		product = productService.findByPid(product.getPid());
		//��ѯ���еĶ�������
		List<CategorySecond> csList = categorySecondService.findAll();
	    //��list���Ϸŵ�ֵջ��
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	//�޸���Ʒ
	public String update() throws Exception{
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		product.setPdate(date3);
		// product.setImage(image);
		if(upload != null){
			//��ԭ����Ʒ��ͼƬɾ��
			String path1 = product.getImage();
	        if(path1!=null){
	        	String realPath = ServletActionContext.getServletContext().getRealPath("/"+path1);
	        	File file =new File(realPath);
	        	file.delete();
	        }
			// ����ƷͼƬ�ϴ�����������.
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products/1");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);
			//ɾ���ϴ�����ƷͼƬ
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}

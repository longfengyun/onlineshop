package com.amaker.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.dao.ProductDao;
import com.amaker.shop.util.PageBean;

/**
 * ��Ʒ��ҵ������
 * @author ChangchunLong
 *
 */
@Transactional
public class ProductService {
    //ע��productDao
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    //ҵ����ѯ������Ʒ
	public List<Product> findHotProduct() {
		return productDao.findHotProduct();
	}
	//ҵ����ѯ������Ʒ
	public List<Product> findNewProduct() {
		return productDao.findNewProduct();
	}
	//ҵ��������Ʒid��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		System.out.println("findByPidService");
		return productDao.findByPid(pid);
	}
	//ҵ������һ�������ѯ����ҳ����Ʒ
	public PageBean<Product> findByCid(int cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//���÷�ҳ��С
		int limit=8;
		pageBean.setLimit(limit);
		//��ȡ��Ӧһ��������ܼ�¼
		int totalCount = productDao.countCid(cid);
		
		pageBean.setTotalCount(totalCount);
		//��ȡ��Ӧһ���������ҳ��
		int totalPage =0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin =(page-1)*limit;
		//��ȡ��Ӧ��ҳ����Ʒ����
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//ҵ�����ݶ��������ѯ��ҳ����Ʒ
	public PageBean<Product> findByCsid(int csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//���÷�ҳ��С
		int limit=8;
		pageBean.setLimit(limit);
		//��ȡ��Ӧ����������ܼ�¼
		int totalCount = productDao.countCsid(csid);
		pageBean.setTotalCount(totalCount);
		//��ȡ��Ӧ�����������ҳ��
		int totalPage =0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin =(page-1)*limit;
		//��ȡ��Ӧ��ҳ����Ʒ����
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//ҵ����ѯ��Ʒ������ҳ��
	public PageBean<Product> findAll(Integer page) {
		PageBean<Product> pageBean = new PageBean();
		//����page
		pageBean.setPage(page);
		//����limit
		int limit=10;
		pageBean.setLimit(limit);
		//��ȡ����Ʒ��¼��������
		int totalCount = productDao.findCountProduct();
		pageBean.setTotalCount(totalCount);
		//������Ʒ��ҳ��
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//��ȡ��Ʒ����
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//ҵ��㱣����Ʒ
	public void save(Product product) {
		productDao.save(product);
	}
	//ҵ���ɾ����Ʒ
	public void delete(Product product) {
         productDao.delete(product);	
	}
	//ҵ����޸���Ʒ
	public void update(Product product) {
		productDao.update(product);
	}
}

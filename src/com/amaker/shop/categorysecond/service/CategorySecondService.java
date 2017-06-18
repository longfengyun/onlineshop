package com.amaker.shop.categorysecond.service;

import java.util.List;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.categorysecond.dao.CategorySecondDao;
import com.amaker.shop.util.PageBean;

/**
 * ��̨������������Service
 * @author ChangchunLong
 *
 */
public class CategorySecondService {
	//ע��CategorySecondDao
    private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	//ҵ��㰴ҳ��ѯ��������
	public PageBean findByPage(Integer page) {
		PageBean pageBean = new PageBean();
		//����page
		pageBean.setPage(page);
		//����limit
		int limit=10;
		pageBean.setLimit(limit);
		//��ȡ���������ܼ�¼��
		int totalCount = categorySecondDao.findCountCsid();
		pageBean.setTotalCount(totalCount);
		//��ȡ��ҳ��
		int totalPage = 0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		//���ö����������ҳ��
		pageBean.setTotalPage(totalPage);
		//���ö�������ĳҳ�ļ���
		int begin=(page-1)*limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//ҵ��㱣���������
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//ҵ�����ݶ�������id��ѯ
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//ҵ���ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}
	//ҵ����޸Ķ�������
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	//ҵ����ѯ���еĶ�������
	public List<CategorySecond> findAll() {
		List<CategorySecond> list = categorySecondDao.findAll();
		return list;
	}
    
}

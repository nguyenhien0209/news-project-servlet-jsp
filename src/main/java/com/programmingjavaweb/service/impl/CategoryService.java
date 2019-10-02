package com.programmingjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.programmingjavaweb.dao.ICategoryDAO;
import com.programmingjavaweb.model.CategoryModel;
import com.programmingjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {
//	private ICategoryDAO categoryDao;
//	
//	public CategoryService() {
//		categoryDao = new CategoryDAO();
//	}

//	Su dung Servlet Weld de goi den mot interface va su dung cac phuong thuc duoc dinh nghia trong
//  interface do ma khong can phai new class implements interface do va su dung annotation @Inject
	@Inject
	private ICategoryDAO categoryDao;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

}

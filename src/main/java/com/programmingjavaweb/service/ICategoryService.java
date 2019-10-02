package com.programmingjavaweb.service;

import java.util.List;

import com.programmingjavaweb.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
}

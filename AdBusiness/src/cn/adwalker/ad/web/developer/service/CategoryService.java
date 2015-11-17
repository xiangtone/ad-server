package cn.adwalker.ad.web.developer.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.model.common.dao.CategoryDao;
import cn.adwalker.ad.model.common.domain.EscoreCategory;

/**
 * 功能概述：<br>
 * 开发者用户账户服务
 */
@Service("categoryService")
public class CategoryService {
	
	@Resource
	private CategoryDao categoryDao;
	
	public List<EscoreCategory> findECList(Integer type) {
		return categoryDao.findECList(type);
	}

}

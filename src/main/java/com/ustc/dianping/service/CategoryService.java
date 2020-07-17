package com.ustc.dianping.service;

import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.model.CategoryModel;

import java.util.List;

/**
 * 品类服务
 */
public interface CategoryService {

    //创建品类
    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    //根据品类id获取品类
    CategoryModel get(Integer id);

    //后台品类查询管理
    List<CategoryModel> selectAll();

    //统计品类数量
    Integer countAllCategory();
}

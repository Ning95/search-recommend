package com.ustc.dianping.service;

import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.model.SellerModel;

import java.util.List;

/**
 * 商家服务
 */
public interface SellerService {

    //商家创建功能
    SellerModel create(SellerModel sellerModel);

    //商家查询功能
    SellerModel get(Integer id);

    //商家批量查询
    List<SellerModel> selectAll();

    //设置商家状态
    SellerModel changeStatus(Integer id,Integer disabledFlag) throws BusinessException;

    //统计商家数量
    Integer countAllSeller();
}
package com.ustc.dianping.service;

import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.model.OrderModel;

/**
 * 优惠券抢购相关业务
 */
public interface SeckillService {

    void createOptimisticOrder(int userId, int goodsId) throws BusinessException;
}

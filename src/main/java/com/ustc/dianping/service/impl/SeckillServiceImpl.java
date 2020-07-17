package com.ustc.dianping.service.impl;

import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.common.EmBusinessError;
import com.ustc.dianping.mapper.CouponMapper;
import com.ustc.dianping.mapper.OrderMapper;
import com.ustc.dianping.model.CouponModel;
import com.ustc.dianping.model.OrderModel;
import com.ustc.dianping.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ning
 * @since 2020.07.16 16:45
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    
    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public void createOptimisticOrder(int userId, int goodsId) throws BusinessException {
        CouponModel couponModel = couponMapper.selectByPrimaryKey(goodsId);
        int count = couponModel.getStockCount();
        if (count <= 0) {
            throw new BusinessException(EmBusinessError.COUPON_IS_EMPTY);
        }
        int result = couponMapper.updateStockByOptimistic(couponModel);
        if (result == 0){
            throw new BusinessException(EmBusinessError.COUPON_IS_ERROR) ;
        }else {
            orderMapper.insertOrder(userId, goodsId);
        }
    }
}

package com.ustc.dianping.mapper;

import com.ustc.dianping.model.CouponModel;

public interface CouponMapper {
    CouponModel selectByPrimaryKey(int id);

    int updateStockByOptimistic(CouponModel couponModel);
}

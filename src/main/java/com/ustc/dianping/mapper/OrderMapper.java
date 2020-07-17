package com.ustc.dianping.mapper;

import com.ustc.dianping.model.OrderModel;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int insertOrder(@Param("userId") int userId, @Param("goodsId") int goodsId);

    OrderModel selectByGoodsId(int goodsId);
}

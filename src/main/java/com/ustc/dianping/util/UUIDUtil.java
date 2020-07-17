package com.ustc.dianping.util;

import java.util.UUID;

/**
 * 生成uuid的工具类
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}

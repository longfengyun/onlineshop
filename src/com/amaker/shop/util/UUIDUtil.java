package com.amaker.shop.util;

import java.util.UUID;
/**
 * 随机生成字符串的工具类
 * @author ChangchunLong
 *
 */
public class UUIDUtil {
   public static String getUUID(){
	   return UUID.randomUUID().toString().replace("-", "");
   }
}

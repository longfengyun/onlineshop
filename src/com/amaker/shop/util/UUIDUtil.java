package com.amaker.shop.util;

import java.util.UUID;
/**
 * ��������ַ����Ĺ�����
 * @author ChangchunLong
 *
 */
public class UUIDUtil {
   public static String getUUID(){
	   return UUID.randomUUID().toString().replace("-", "");
   }
}

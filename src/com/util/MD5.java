package com.util;

/**
 *MD5密码加密类 
 *将字节数组换成成16进制的字符串
 *
 *使用MessageDigest对字符串进行MD5算法的步骤是，
 *先将字符串转换成字节数组，在进行MD5算法，
 *最后返回的也是一个字节数组，要我们自己转成32位的字符串。
 */

import java.security.*; 
import java.security.spec.*; 
public final class MD5 {
	
	public final static String MD5(String s){ 
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char hexDigits[] = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 
		'e', 'f'}; 
		try { 
			// 输入的字符串转换成字节数组
			byte[] strTemp = s.getBytes(); 
			
			// 拿到一个MD5转换器
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
			
			// strTemp是输入字符串转换得到的字节数组
			mdTemp.update(strTemp); 
			
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] md = mdTemp.digest(); 
			
			int j = md.length; 
			
			// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
			char str[] = new char[j * 2]; 
			
			// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
			int k = 0; 
			for (int i = 0; i < j; i++) { 
				byte byte0 = md[i]; 
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				str[k++] = hexDigits[byte0 & 0xf]; 
			} 
			// 字符数组组合成字符串返回
			return new String(str); 
		} 
		catch (Exception e){ 
			return null; 
		} 
	}
}

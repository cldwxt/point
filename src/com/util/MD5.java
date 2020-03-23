package com.util;

/**
 *MD5��������� 
 *���ֽ����黻�ɳ�16���Ƶ��ַ���
 *
 *ʹ��MessageDigest���ַ�������MD5�㷨�Ĳ����ǣ�
 *�Ƚ��ַ���ת�����ֽ����飬�ڽ���MD5�㷨��
 *��󷵻ص�Ҳ��һ���ֽ����飬Ҫ�����Լ�ת��32λ���ַ�����
 */

import java.security.*; 
import java.security.spec.*; 
public final class MD5 {
	
	public final static String MD5(String s){ 
		// ���ȳ�ʼ��һ���ַ����飬�������ÿ��16�����ַ�
		char hexDigits[] = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 
		'e', 'f'}; 
		try { 
			// ������ַ���ת�����ֽ�����
			byte[] strTemp = s.getBytes(); 
			
			// �õ�һ��MD5ת����
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
			
			// strTemp�������ַ���ת���õ����ֽ�����
			mdTemp.update(strTemp); 
			
			// ת�������ؽ����Ҳ���ֽ����飬����16��Ԫ��
			byte[] md = mdTemp.digest(); 
			
			int j = md.length; 
			
			// newһ���ַ����飬�������������ɽ���ַ����ģ�����һ�£�һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η�����
			char str[] = new char[j * 2]; 
			
			// �����ֽ����飬ͨ��λ���㣨λ����Ч�ʸߣ���ת�����ַ��ŵ��ַ�������ȥ
			int k = 0; 
			for (int i = 0; i < j; i++) { 
				byte byte0 = md[i]; 
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				str[k++] = hexDigits[byte0 & 0xf]; 
			} 
			// �ַ�������ϳ��ַ�������
			return new String(str); 
		} 
		catch (Exception e){ 
			return null; 
		} 
	}
}

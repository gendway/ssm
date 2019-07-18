package com.itheima.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		//81dc9bdb52d04dc20036dbd8313ed055
		//81dc9bdb52d04dc20036dbd8313ed055
		/*String prefix="xiaohong";
		String suffix="xiaohei";
		String str=prefix+"1234"+suffix;
		String e1=md5(str);
		String e2=md5(e1+"1234");
		String e3=md5(e2);
		System.out.println(e3);*/


		//40bd6fed8457fac7b08b227d6451a44a
		//19ca1e2b815c328b506e07da6e0f59bd
		//Md5Hash md5Hash = new Md5Hash("明文","盐1",3);
		//System.out.println(md5Hash.toString());
	}

}
package com.webapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class Password {
	
	/* 
	 * 단방향 암호화 (암호화) ==> MD5(약간의 문제점 발생됨), SHA-256 알고리즘 사용(256비트로 암호화)
	 * 양방향 암호화 (암호화, 복호화) ==> AES-256(개인정보 암호화, 복호화가 가능하도록 해야함)
	 */
	static Log log = LogFactory.getLog(Password.class);
	
	public static String encode(String password){
		
		
		StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest message = MessageDigest.getInstance("SHA-256");
			byte[] digest = message.digest(password.getBytes());
			log.info("degest bytes = " + digest.length);
			for(byte b : digest){
										//2자리 대문자 출력
				buffer.append(String.format("%02X", b));
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("[" + password + "] ==> [" + buffer.toString() + "]");
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		encode("1234567890abcdefgyyy");
		encode("1234567890abcdefgyyy");
		encode("1234567890abcdefg");
	}
}

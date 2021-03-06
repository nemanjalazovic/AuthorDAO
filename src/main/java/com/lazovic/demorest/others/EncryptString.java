package com.lazovic.demorest.others;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.apache.log4j.Logger;

public class EncryptString {
	private final Logger logger = Logger.getLogger(this.getClass());

	public String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("No Such Algorithm Exception", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("UUnsupported Encoding Exception", e);
		}
		return sha1;
	}

	public String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static void main(String[] args) {
		EncryptString enc = new EncryptString();
		String password = "sifra";
		System.out.println(enc.encryptPassword(password));
		String password2 = "sifra";
		System.out.println(enc.encryptPassword(password2));

	}
}

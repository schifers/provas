package br.com.schifers.provas.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.ejb.Stateless;

@Stateless
public class EncoderUtil {
	public String encode(String password) throws NoSuchAlgorithmException {
		MessageDigest digester = MessageDigest.getInstance("SHA-256");
		digester.update(password.getBytes());
		return Base64.getEncoder().encodeToString(digester.digest());
	}
}

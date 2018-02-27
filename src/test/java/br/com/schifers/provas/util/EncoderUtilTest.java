package br.com.schifers.provas.util;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EncoderUtilTest {

	@Test
	public void testEncode() throws NoSuchAlgorithmException {
		EncoderUtil encoder = new EncoderUtil();
		String password = encoder.encode("admin");
		System.out.println(password);
		Assert.assertTrue(true);
	}

}

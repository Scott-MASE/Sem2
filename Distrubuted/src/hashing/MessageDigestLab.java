package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageDigestLab {

	public static void main(String args[]) throws IOException {
//		Q1();
		Qsomething();

	}

	public static void Q1() {
		String s = "Hello World";
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		algorithm.reset();
		algorithm.update(s.getBytes());
		byte[] messageDigest = algorithm.digest();

		System.out.println("length = " + messageDigest.length);
		String encodedDigest = Base64.getEncoder().encodeToString(messageDigest);
		System.out.println("Base54 encoded message digest " + encodedDigest);


		try {
			InputStream is = new FileInputStream("data/test.txt");
			byte[] buffer = new byte[64];
			int bytesRead = 0;
			while ((bytesRead = is.read(buffer)) > 0)
				algorithm.update(buffer,0,bytesRead);
				System.out.println(bytesRead);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Qsomething() throws IOException {
		InputStream is = new FileInputStream("data/test.txt");
		String SHA256 = DigestUtils.sha256Hex(is);
		System.out.println(SHA256);
		
	}

}

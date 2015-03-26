package com.home.autoparts.security.crypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-security.xml")
public class TestEncryption {
	
	private static final String ENCRYPTED_WITH_OTHER_MASTER_PASS = "fgjJAPRn19luNiM4ztoYm1SWpZUxLO3Y";
	
	@Autowired
	private StringEncryptor encryptor;
	
	@Test
	public void testEncryptDecrypt() {
		String password = "mypassword";
		String encrypted = encryptor.encrypt(password);
		Assert.assertNotNull(encrypted);
		String decrypted = encryptor.decrypt(encrypted);
		Assert.assertEquals(password, decrypted);
	}
	
	@Test(expected=EncryptionOperationNotPossibleException.class)
	public void testDecryptWthWrongMasterPassword() {
		encryptor.decrypt(ENCRYPTED_WITH_OTHER_MASTER_PASS);
	}
}

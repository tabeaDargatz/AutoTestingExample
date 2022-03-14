package com.example.autotestingexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CredentialValidatorTest {

    @Mock
    public DatabaseConnection databaseConnection;

    @Test
    public void invalidEmail_isValidEmail_ReturnsFalse() {
        when(databaseConnection.isRegistered("alreadyRegistered@gmail.com")).thenReturn(true);
        CredentialValidator credentialValidator = new CredentialValidator(databaseConnection);
        assertFalse(credentialValidator.isValidEmail(null)); //nothing entered
        assertFalse(credentialValidator.isValidEmail("ICHMAGKEINESONDERZEICHEN.com")); //no @
        assertFalse(credentialValidator.isValidEmail("alreadyRegistered@gmail.com")); //already registered according to db mock
    }

    @Test
    public void validEmail_isValidEmail_ReturnsTrue() {
        when(databaseConnection.isRegistered(any())).thenReturn(true);
        CredentialValidator credentialValidator = new CredentialValidator(databaseConnection);
        assertTrue(credentialValidator.isValidEmail("tabea.dargatz@lfh.de")); //has @ sign & isn't registered according to db mock
    }

    @Test
    public void weakPassword_isValidPassword_ReturnsFalse() {
        CredentialValidator credentialValidator = new CredentialValidator(databaseConnection);
        assertFalse(credentialValidator.isValidPassword(null));
        assertFalse(credentialValidator.isValidPassword("1111111!")); //missing capital letter
        assertFalse(credentialValidator.isValidPassword("AAAAAAAA!")); //missing number
        assertFalse(credentialValidator.isValidPassword("AAAAAAAA1")); //missing special character
        assertFalse(credentialValidator.isValidPassword("AA1!!")); //too short
    }

    @Test
    public void strongPassword_isValidPassword_ReturnsTrue() {
        CredentialValidator credentialValidator = new CredentialValidator(databaseConnection);
        assertTrue(credentialValidator.isValidPassword("123456A!")); //capital letter, number, special character and > 7 chars
    }

}
package com.example.autotestingexample;

import android.content.Context;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class Credential_validator_test {
    @Test
    public void testInvalidEmail(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertFalse(credentialValidator.isValidEmail(null));
        assertFalse(credentialValidator.isValidEmail(""));
        assertFalse(credentialValidator.isValidEmail("ICHMAGKEINESONDERZEICHEN"));
    }
    @Test
    public void testRegisterEmail(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.register("tabea.dargatz@lfh.de", "123456!A");
        assertTrue(databaseConnection.isRegistered("tabea.dargatz@lfh.de"));
    }
    @Test
    public void testValidPassword(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertTrue(credentialValidator.isValidPassword("Geheim!1234"));
    }
    @Test
    public void testInvalidPassword(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertFalse(credentialValidator.isValidPassword("1234567"));
        assertFalse(credentialValidator.isValidPassword(""));
        assertFalse(credentialValidator.isValidPassword(null));
    }
    @Test
    public void testStrongPassword(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertTrue(credentialValidator.isPasswordStrong("123456A!"));
    }
    @Test
    public void testWeakPassword(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertFalse(credentialValidator.isPasswordStrong("1!"));
        assertFalse(credentialValidator.isPasswordStrong("A!"));
        assertFalse(credentialValidator.isPasswordStrong("A1"));
    }
    @Mock
    public DatabaseConnection databaseConnection;
    @Test
    public void EmailAlreadyRegistered(){
        when(databaseConnection.isAvailable()).thenReturn(true);
        when(databaseConnection.isRegistered(any())).thenReturn(true);
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertFalse(credentialValidator.isValidEmail("Test"));
    }


}
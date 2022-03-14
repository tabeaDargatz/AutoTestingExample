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

@RunWith(MockitoJUnitRunner.class)
public class CredentialValidatorTest {

    @Test
    public void invalidEmail_isValidEmail_ReturnsFalse(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertFalse(credentialValidator.isValidEmail(null));
        assertFalse(credentialValidator.isValidEmail(""));
        assertFalse(credentialValidator.isValidEmail("ICHMAGKEINESONDERZEICHEN.com"));
    }

    @Test
    public void validEmail_isValidEmail_ReturnsTrue(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertTrue(credentialValidator.isValidEmail("tabea.dargatz@lfh.de")); //has @ sign
    }

    @Mock
    public DatabaseConnection databaseConnection;

    @Test
    public void validButAlreadyRegisteredEmail_isValidEmail_ReturnsFalse(){
        when(databaseConnection.isRegistered(any())).thenReturn(true);
        CredentialValidator credentialValidator = new CredentialValidator(databaseConnection);
        assertFalse(credentialValidator.isValidEmail("Test@gmail.com"));
    }



    @Test
    public void weakPassword_isValidPassword_ReturnsFalse(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertFalse(credentialValidator.isValidPassword(null));
        assertFalse(credentialValidator.isValidPassword("1111111!")); //missing capital letter
        assertFalse(credentialValidator.isValidPassword("AAAAAAAA!")); //missing number
        assertFalse(credentialValidator.isValidPassword("AAAAAAAA1")); //missing special character
        assertFalse(credentialValidator.isValidPassword("AA1!!")); //too short
    }

    @Test
    public void strongPassword_isValidPassword_ReturnsTrue(){
        CredentialValidator credentialValidator = new CredentialValidator(new DatabaseConnection());
        assertTrue(credentialValidator.isValidPassword("123456A!"));
    }

}
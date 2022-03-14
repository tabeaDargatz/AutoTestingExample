package com.example.autotestingexample;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Credential_validator_test {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.autotestingexample", appContext.getPackageName());
    }
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
}
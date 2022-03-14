package com.example.autotestingexample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialValidator {

    private final DatabaseConnection databaseConnection;

    public CredentialValidator(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public boolean isValidEmail(String email){
        if(email == null || email.isEmpty() || !email.contains("@")){

            return false;
        }
        else return databaseConnection.isAvailable() && !databaseConnection.isRegistered(email);
    }

    public boolean isValidPassword(String password){
        if(password == null || password.isEmpty() || password.length() < 8){
            return false;
        }
        return isPasswordStrong(password);
    }

    public boolean isPasswordStrong(String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

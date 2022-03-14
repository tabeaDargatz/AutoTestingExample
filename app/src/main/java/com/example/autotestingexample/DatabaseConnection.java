package com.example.autotestingexample;
import java.util.ArrayList;
import java.util.List;
public class DatabaseConnection {

    public List<String> registeredEmails = new ArrayList<>();

    public boolean isRegistered(String email){
        return registeredEmails.contains(email);
    }

    public void register(String email, String password){
        registeredEmails.add(email);
    }

}

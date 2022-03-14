package com.example.autotestingexample;
import java.util.ArrayList;
import java.util.List;
public class DatabaseConnection {

    public List<String> registeredEmails = new ArrayList<String>();
    public boolean isAvailable(){
        return true;
    }

    public boolean isRegistered(String email){
        if(registeredEmails.contains(email)){
            return  true;
        }
        else {
            register(email, "");
            return false;
        }
    }

    public void register(String email, String password){

        registeredEmails.add(email);
    }

}

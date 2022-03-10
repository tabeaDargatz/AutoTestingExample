package com.example.autotestingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private DatabaseConnection connection;
    private TextView emailField;
    private TextView passwordField;
    private CredentialValidator validator;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new DatabaseConnection();
        validator = new CredentialValidator(connection);
        emailField = findViewById(R.id.txt_email);
        passwordField = findViewById(R.id.txt_password);

        toast = new Toast(getApplicationContext());
        toast = Toast.makeText(getApplicationContext(),
                "",
                Toast.LENGTH_SHORT);
    }

    @SuppressLint("ShowToast")
    public void registerAccount(View view){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(validator.isValidEmail(email) && validator.isValidPassword(password)){
            connection.register(email, password);
            toast.setText("registration successful.");
        }
        else{
            toast.setText("Oops, something went wrong.");
        }
        toast.show();
    }
}
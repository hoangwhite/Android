package com.ctk43.doancoso.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ctk43.doancoso.Database.DataLocal.DBHelper;
import com.ctk43.doancoso.Database.DataLocal.DataLocalManager;
import com.ctk43.doancoso.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password;
    DBHelper DB;
    Button signupbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        password = (EditText) findViewById(R.id.pass) ;
        username = (EditText) findViewById(R.id.Username);
        signupbutton = (Button) findViewById(R.id.signupbutton);
        DB = new DBHelper(this);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser == false){
                        Boolean insert = DB.insertData(user, pass);
                        if(insert == true){
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                        }else {
                            Toast.makeText(RegisterActivity.this, "Registerion failed", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "User already exists!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        TextView loginLink = findViewById(R.id.Login);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đổi sang hoạt động đăng nh (RegisterActivity)
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
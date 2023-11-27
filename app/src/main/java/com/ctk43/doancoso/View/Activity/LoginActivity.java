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
import com.ctk43.doancoso.R;

public class LoginActivity extends AppCompatActivity {
    EditText username, pass;
    Button LoginButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);

        DB = new DBHelper(this);
        LoginButton = (Button) findViewById(R.id.LoginButton);



        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String password = pass.getText().toString();


                if(user.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DB.checkusernamepass(user, password);
                    if(checkuserpass == true){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView registerLink = findViewById(R.id.signupText);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đổi sang hoạt động đăng ký (RegisterActivity)
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
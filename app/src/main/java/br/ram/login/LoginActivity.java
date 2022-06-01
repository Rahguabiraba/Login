package br.ram.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    protected EditText username;
    protected EditText password;
    protected Button button1;
    protected Button button2;

    protected String user="";
    protected String pass="";
    protected Boolean checkuserpass;
    protected Intent intent;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button1 = (Button) findViewById(R.id.signup);
        button2 = (Button) findViewById(R.id.back);

        //Conection DataBase
        db = new DataBaseSQL(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, R.string.toast1_create_login, Toast.LENGTH_SHORT).show();
                } else {
                    checkuserpass = db.checkusernamepassword(user, pass);
                    if(checkuserpass==true) {
                        Toast.makeText(LoginActivity.this, R.string.toast6_create_login, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.toast7_create_login, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
        //Button Create Login
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, StartActivity.class);
                finish();
                startActivity(intent);
            }
        });


    }
}
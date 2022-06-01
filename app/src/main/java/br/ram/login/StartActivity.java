package br.ram.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected EditText username;
    protected EditText password;
    protected EditText repassword;
    protected Button button1;
    protected Button button2;

    private Intent intent;

    protected String user="";
    protected String pass="";
    protected String repass="";
    protected Boolean checkuser;
    protected Boolean insert;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        button1 = (Button) findViewById(R.id.signup);
        button2 = (Button) findViewById(R.id.signin);

        //Conection DataBase
        db = new DataBaseSQL(this);

        //Button Create Login
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = username.getText().toString();
                pass = password.getText().toString();
                repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(StartActivity.this, R.string.toast1_create_login, Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass)) {
                        checkuser = db.checkusername(user);
                        if (checkuser==false) {
                            insert = db.insertData(user, pass);
                            if(insert==true) {
                                Toast.makeText(StartActivity.this, R.string.toast2_create_login, Toast.LENGTH_SHORT).show();
                                intent = new Intent(StartActivity.this, LoginActivity.class);
                                finish();
                                startActivity(intent);
                                db.close();
                            } else {
                                Toast.makeText(StartActivity.this, R.string.toast3_create_login, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(StartActivity.this, R.string.toast4_create_login, Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(StartActivity.this, R.string.toast5_create_login, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Button SignUp
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(StartActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
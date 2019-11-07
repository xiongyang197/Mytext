package com.example.mytext.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mytext.R;
import com.example.mytext.activity.ForgetPWActivity;
import com.example.mytext.activity.LoginActivity;
import com.example.mytext.activity.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin;
    Button buttonReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin=(Button) this.findViewById(R.id.button);
        buttonReg=(Button) this.findViewById(R.id.button2);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reg();
            }
        });
    }
    private  void  Login(){
        Intent intent=new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }
    private  void Reg(){
        Intent intent=new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }
}

package com.example.mytext.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytext.R;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextName=(EditText) this.findViewById(R.id.editTextName);
        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(editTextName.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this,"length需要大于6",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

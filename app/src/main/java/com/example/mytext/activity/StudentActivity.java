package com.example.mytext.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytext.R;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    private Spinner mSpinner;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String TR="k";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textView=(TextView) findViewById(R.id.textID);
        textView.setText(TR);

    }
}

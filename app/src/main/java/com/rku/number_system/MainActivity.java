package com.rku.number_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 =findViewById(R.id.spinner1);
        spinner2 =findViewById(R.id.spinner2);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.num_system_array, R.layout.spinner_items);
        adapter.setDropDownViewResource(R.layout.dropdown_layout);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

    }
}
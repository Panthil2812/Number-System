package com.rku.number_system;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private  EditText bin_text;
    private  EditText dec_text;
    private  EditText oct_text;
    private  EditText hex_text;
    private  Button Convert;
    private  Button Clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bin_text  = findViewById(R.id.txt_binary);
        dec_text  = findViewById(R.id.txt_decimal);
        oct_text  = findViewById(R.id.txt_octal);
        hex_text  = findViewById(R.id.txt_hexadecimal);
        Convert = findViewById(R.id.btn_Convert);
        Clear = findViewById(R.id.btn_Clear);

        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bin = bin_text.getText().toString();
                String dec =dec_text.getText().toString();
                String oct = oct_text.getText().toString();
                String hex = hex_text.getText().toString();
                 if(!dec.isEmpty())
                {
                    Long value = Long.parseLong(dec);
                    bin_text.setText(String.valueOf(Long.toBinaryString(value)));
                    oct_text.setText(String.valueOf(Long.toOctalString(value)));
                    hex_text.setText(String.valueOf(Long.toHexString(value).toUpperCase()));
                }
                else if(!bin.isEmpty())
                {
                    dec_text.setText(String.valueOf(Long.parseLong(bin,2)));
                    oct_text.setText(String.valueOf(Long.toOctalString(Long.parseLong(bin, 2))));
                    hex_text.setText(String.valueOf(Long.toHexString(Long.parseLong(bin,2)).toUpperCase()));
                }

                else if(!oct.isEmpty())
                {
                    dec_text.setText(String.valueOf(Long.parseLong(oct,8)));
                    bin_text.setText(String.valueOf(Long.toBinaryString(Long.parseLong(oct,8))));
                    hex_text.setText(String.valueOf(Long.toHexString(Long.parseLong(oct,8)).toUpperCase()));
                }
                else if(!hex.isEmpty())
                {
                    dec_text.setText(String.valueOf(Long.parseLong(hex,16)));
                    oct_text.setText(String.valueOf(Long.toOctalString(Long.parseLong(hex,16))));
                    bin_text.setText(String.valueOf(Long.toBinaryString(Long.parseLong(hex,16))));
                }

            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 bin_text.setText("");
                 hex_text.setText("");
                 oct_text.setText("");
                 dec_text.setText("");

            }
        });
    }

}

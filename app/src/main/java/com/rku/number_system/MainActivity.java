package com.rku.number_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private  EditText bin_text;
    private  EditText dec_text;
    private  EditText oct_text;
    private  EditText hex_text;
    private  Button Convert;
    private  Button Clear;
    private  String  value;
    public  Integer  getAnsBin=0,getAnsDec=0,getAnsOct=0,getAnsHex=0;
    private  View.OnClickListener copyListener;
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

        copyListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = null;

                switch (view.getId())
                {
                    case R.id.copy_bin:
                        clipData = ClipData.newPlainText("Copied Data",bin_text.getText().toString());
                        break;
                    case R.id.copy_dec:
                        clipData = ClipData.newPlainText("Copied Data",dec_text.getText().toString());
                        break;
                    case R.id.copy_oct:
                        clipData = ClipData.newPlainText("Copied Data",oct_text.getText().toString());
                        break;
                    case R.id.copy_hex:
                        clipData = ClipData.newPlainText("Copied Data",hex_text.getText().toString());
                        break;
                }
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(),"Copied Data", Toast.LENGTH_LONG).show();
            }
        };
        findViewById(R.id.copy_bin).setOnClickListener(copyListener);
        findViewById(R.id.copy_dec).setOnClickListener(copyListener);
        findViewById(R.id.copy_oct).setOnClickListener(copyListener);
        findViewById(R.id.copy_hex).setOnClickListener(copyListener);

        Clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                 bin_text.setText("");
                 hex_text.setText("");
                 oct_text.setText("");
                 dec_text.setText("");
                bin_text.setError(null);
                dec_text.setError(null);
                oct_text.setError(null);
                hex_text.setError(null);
                getAnsBin = 0;
                getAnsDec = 0;
                getAnsOct = 0;
                getAnsHex = 0;
            }
        });
        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bin = bin_text.getText().toString();
                String dec = dec_text.getText().toString();
                String oct = oct_text.getText().toString();
                String hex = hex_text.getText().toString().toUpperCase();
                int bin_id = bin_text.getId();
                int dec_id = dec_text.getId();
                int oct_id = oct_text.getId();
                int hex_id = hex_text.getId();
                //all edittext empty
                if(bin.isEmpty() && dec.isEmpty() && oct.isEmpty() && hex.isEmpty())
                {
                    ErrorShow(101);
                }
                //decimal text not empty
                else if (bin.isEmpty() && !dec.isEmpty() && oct.isEmpty() && hex.isEmpty())
                {
                    if (ValidDec(dec) == 1) {
                        Convert(dec, dec_id);
                    } else {
                         ErrorShow(dec_id);
                    }

                }
                //binary text not empty
                else if (!bin.isEmpty() && dec.isEmpty() && oct.isEmpty() && hex.isEmpty())
                {
                    if (ValidBin(bin) == 1) {
                        Convert(bin, bin_id);
                    } else {
                        ErrorShow(bin_id);
                    }
                }
                //octal text not empty
                else if (bin.isEmpty() && dec.isEmpty() && !oct.isEmpty() && hex.isEmpty())
                {
                    if (ValidOct(oct) == 1) {
                        Convert(oct, oct_id);
                    } else {
                        ErrorShow(oct_id);
                    }
                }
                //hexadecimal text not empty
                else if (bin.isEmpty() && dec.isEmpty() && oct.isEmpty() && !hex.isEmpty())
                {
                    if (ValidHex(hex) == 1) {
                        Convert(hex, hex_id);
                    } else {
                        ErrorShow(hex_id);
                    }
                }
                else
                {
                    //decimal second time
                    if (!dec.isEmpty() && getAnsDec != dec.length())
                    {
                        if (ValidDec(dec) == 1) {
                            Convert(dec, dec_id);
                        } else {
                            ErrorShow(dec_id);
                        }
                    }
                    //binary second time
                    else if (!bin.isEmpty() && getAnsBin != bin.length())
                    {
                        if (ValidBin(bin) == 1) {
                            Convert(bin, bin_id);
                        } else {
                            ErrorShow(bin_id);
                        }
                    }
                    //octal second time
                    else if (!oct.isEmpty() && getAnsOct < oct.length())
                    {
                        if (ValidOct(oct) == 1) {
                            Convert(oct, oct_id);
                        } else {
                            ErrorShow(oct_id);
                        }
                    }
                    // hexadecimal second time
                    else if (!hex.isEmpty() && getAnsHex < hex.length())
                    {
                        if (ValidHex(hex) == 1) {
                            Convert(hex, hex_id);
                        } else {
                            ErrorShow(hex_id);
                        }

                    }
                }
        }
        });

    }

    private void ErrorShow(int id)   {
        switch (id)
        {

            case 101:
                bin_text.requestFocus();
                bin_text.setError("Please Enter Value");
                break;

            case R.id.txt_binary:
                bin_text.requestFocus();
                bin_text.setError("Invalid Input");
                break;
            case R.id.txt_decimal:
                dec_text.requestFocus();
                dec_text.setError("Invalid Input");
                break;
            case R.id.txt_octal:
                oct_text.requestFocus();
                oct_text.setError("Invalid Input");
                break;
            case R.id.txt_hexadecimal:
                hex_text.requestFocus();
                hex_text.setError("Invalid Input");
                break;
        }
    }
    private int ValidBin(String bin) {
        StringBuilder sbf = new StringBuilder(bin);
        int valid =0;
        if(sbf.length()>1) {
            for (int i = 0; i < sbf.length() - 1; i++) {
                char first = sbf.charAt(0);
                char on = sbf.charAt(i);
                char next = sbf.charAt(i + 1);
                if (on == '1' || on == '0'  || (on == '.' && next != '.')) {
                    valid = 1;
                } else {
                    valid = 0;
                    break;
                }
            }
        }else{
            if(sbf.charAt(0)=='1'||sbf.charAt(0)=='0')
            {
                valid =1;

            }
            else {
                valid = 0;
            }
        }
        System.out.println(valid);
        return valid;
    }
    private int ValidDec(String dec) {

        StringBuilder sbf = new StringBuilder(dec);
        int valid =0;

        if(sbf.length()>1) {
            for (int i=0;i<sbf.length()-1;i++)
            {
                char on = sbf.charAt(i);
                char next =sbf.charAt(i+1);
                if(on == '1'||on =='2'||on == '3'||on =='4'||on == '5'||on =='6'||on == '7'||on =='8'||on == '9'||on =='0'||(on =='.' && next !='.'))
                {
                    valid = 1;
                }
                else{
                    valid =0;
                    break;
                }
            }
        }else {
            char ch = sbf.charAt(0);
            if(ch=='0'||ch =='1'||ch=='3'||ch=='2'||ch =='4'||ch=='7'||ch=='6'||ch =='5'||ch=='8'||ch=='9')
            {
                valid =1;

            }
            else {
                valid = 0;
            }
        }
        return valid;
    }
    private int ValidOct(String oct) {

        StringBuilder sbf = new StringBuilder(oct);
        int valid =0;

        if(sbf.length()>1) {
            for (int i=0;i<sbf.length()-1;i++)
            {
                char first = sbf.charAt(0);
                char on = sbf.charAt(i);
                char next =sbf.charAt(i+1);
                if(first =='.')
                {
                    valid =0;
                    break;
                }
                if(on == '1'||on =='2'||on == '3'||on =='4'||on == '5'||on =='6'||on == '7'||on =='0'||(on =='.' && next !='.'))
                {
                    valid = 1;
                }
                else{
                    valid =0;
                    break;
                }
            }
        }else {
            char ch = sbf.charAt(0);
            if(ch=='0'||ch =='1'||ch=='2'||ch=='3'||ch =='4'||ch=='5'||ch=='6'||ch =='7')
            {
                valid =1;

            }
            else {
                valid = 0;
            }
        }
        return valid;
    }
    private int ValidHex(String hex) {

        StringBuilder sbf = new StringBuilder(hex);
        int valid =0;

        if(sbf.length()>1) {
            for (int i=0;i<sbf.length()-1;i++)
            {
                char first = sbf.charAt(0);
                char on = sbf.charAt(i);
                char next =sbf.charAt(i+1);
                if(first =='.')
                {
                    valid =0;
                    break;
                }
                if(on == '1'||on =='2'||on == '3'||on =='4'||on == '5'||on =='6'||on == '7'||on =='8'||on == '9'||on =='0'||(on =='.'&& next !='.'))
                {
                    valid = 1;
                }else if(on == 'A'||on =='B'||on == 'C'||on =='D'||on == 'E'||on =='F')
                {
                    valid = 1;
                }
                else{
                    valid =0;
                    break;
                }
            }
        }else {
            char ch = sbf.charAt(0);
            if(ch=='0'||ch =='1'||ch=='2'||ch=='3'||ch =='4'||ch=='5'||ch=='6'||ch =='7'||ch=='8'||ch=='9'
                    ||ch =='A'||ch=='B'||ch=='C'||ch =='D'||ch=='E'||ch=='F')
            {
                valid =1;

            }
            else {
                valid = 0;
            }
        }
        return valid;
    }

    private void Convert(String str ,int id)
    {
        String ansBin=null,ansDec=null,ansOct=null,ansHex=null;
        bin_text.setError(null);
        dec_text.setError(null);
        oct_text.setError(null);
        hex_text.setError(null);
        switch (id)
        {

            case R.id.txt_binary:
                ansDec = calculator.BintoDec(str);
                ansBin = str;
                ansOct = calculator.DecToOct(ansDec);
                ansHex = calculator.DecToHex(ansDec);
                break;
            case R.id.txt_decimal:
                ansBin = calculator.DecToBin(str);
                ansDec = str;
                ansOct = calculator.DecToOct(str);
                ansHex = calculator.DecToHex(str);
                break;
            case R.id.txt_octal:
                ansDec = calculator.OcttoDec(str);
                ansBin = calculator.DecToBin(ansDec);
                ansOct = str;
                ansHex = calculator.DecToHex(ansDec);
                break;
            case R.id.txt_hexadecimal:
                ansDec = calculator.HextoDec(str);
                ansBin = calculator.DecToBin(ansDec);
                ansOct = calculator.DecToOct(ansDec);
                ansHex = str;
                break;
        }
        bin_text.setText(ansBin);
        bin_text.setSelection(bin_text.length());

        dec_text.setText(ansDec);
        dec_text.setSelection(dec_text.length());

        oct_text.setText(ansOct);
        oct_text.setSelection(oct_text.length());

        hex_text.setText(ansHex);
        hex_text.setSelection(hex_text.length());
        getAnsBin = bin_text.length();
        getAnsDec = dec_text.length();
        getAnsOct = oct_text.length();
        getAnsHex = hex_text.length();
    }
}

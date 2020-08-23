package com.rku.number_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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



        Clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                 bin_text.setText("");
                 hex_text.setText("");
                 oct_text.setText("");
                 dec_text.setText("");
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
                if (!bin.isEmpty() && dec.isEmpty() && oct.isEmpty() && hex.isEmpty())
                {
//                    if (ValidBin(bin) == 1) {
                        Convert(bin, bin_id);
//                    } else {
//                        System.out.println("panthil");
//                    }
                }
                else if (bin.isEmpty() && !dec.isEmpty() && oct.isEmpty() && hex.isEmpty())
                {
//                    if (ValidDec(dec) == 1) {
                        Convert(dec, dec_id);
//                    } else {
//                        System.out.println("panthil");
//                    }

                }
                else if (bin.isEmpty() && dec.isEmpty() && !oct.isEmpty() && hex.isEmpty())
                {
//                    if (ValidOct(oct) == 1) {
                        Convert(oct, oct_id);
//                    } else {
//                        System.out.println("panthil");
//                    }
                }
                else if (bin.isEmpty() && dec.isEmpty() && oct.isEmpty() && !hex.isEmpty())
                {
//                    if (ValidHex(hex) == 1) {
                        Convert(hex, hex_id);
//                    } else {
//                        System.out.println("panthil");
//                    }
                }
                else
                {
                    if (!dec.isEmpty() && getAnsDec < dec.length())
                    {
//                        if (ValidDec(dec) == 1) {
                            Convert(dec, dec_id);
//                        } else {
//                            System.out.println("panthil");
//                        }
                    }
                    else if (!bin.isEmpty() && getAnsBin < bin.length())
                    {
//                        if (ValidBin(dec) == 1) {
                            Convert(bin, bin_id);
//                        } else {
//                            System.out.println("panthil");
//                        }
                    }
                    else if (!oct.isEmpty() && getAnsOct < oct.length())
                    {
//                        if (ValidOct(oct) == 1) {
                            Convert(oct, oct_id);
//                        } else {
//                            System.out.println("panthil");
//                        }
                    }
                    else if (!hex.isEmpty() && getAnsHex < hex.length())
                    {
//                        if (ValidHex(hex) == 1) {
                            Convert(hex, hex_id);
//                        } else {
//                            System.out.println("panthil");
//                        }

                    }
                }
        }
        });
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
        return valid;
    }
    private int ValidDec(String dec) {
   // System.out.println("binary string : "+dec);
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
        //System.out.println("binary string : "+oct);
        StringBuilder sbf = new StringBuilder(oct);
        int valid =0;

        if(sbf.length()>1) {
            for (int i=0;i<sbf.length()-1;i++)
            {
                char first = sbf.charAt(0);
                char on = sbf.charAt(i);
                char next =sbf.charAt(i+1);
                char last =sbf.charAt(sbf.length()-1);
                if(first==' '||first =='+' ||first =='-'||first =='*'||first =='/'||first =='.'){
                    valid =0;
                    break;
                }
                else if (last =='+' ||last =='-'||last =='*'||last =='/'||last =='.')
                {
                    valid =0;
                    break;
                }
                else if(on == '1'||on =='2'||on == '3'||on =='4'||on == '5'||on =='6'||on == '7'||on =='0'||(on =='.' && next !='.'))
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
        //System.out.println("Hexadecimal string : "+hex);
        StringBuilder sbf = new StringBuilder(hex);
        int valid =0;

        if(sbf.length()>1) {
            for (int i=0;i<sbf.length()-1;i++)
            {
                char first = sbf.charAt(0);
                char on = sbf.charAt(i);
                char next =sbf.charAt(i+1);
                char last =sbf.charAt(sbf.length()-1);

                if(first==' '||first =='+' ||first =='-'||first =='*'||first =='/'||first =='.'){
                    valid =0;
                    break;
                }
                else if (last =='+' ||last =='-'||last =='*'||last =='/'||last =='.')
                {
                    valid =0;
                    break;
                }
                else if(on == '1'||on =='2'||on == '3'||on =='4'||on == '5'||on =='6'||on == '7'||on =='8'||on == '9'||on =='0'||on =='.')
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
        dec_text.setText(ansDec);
        oct_text.setText(ansOct);
        hex_text.setText(ansHex);
        getAnsBin = bin_text.length();
        getAnsDec = dec_text.length();
        getAnsOct = oct_text.length();
        getAnsHex = hex_text.length();
    }
}

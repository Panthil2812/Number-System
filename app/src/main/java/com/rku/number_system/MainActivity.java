package com.rku.number_system;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spinner2;
    private Button Convert;
    private  EditText Value;
    private  TextView Ans;
    private int choose1;
    private int choose2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        Convert = findViewById(R.id.btn_convert);
        Value = findViewById(R.id.txt_user);
        Ans = findViewById(R.id.txt_ans);

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.num_system_array, R.layout.spinner_items);
        adapter.setDropDownViewResource(R.layout.dropdown_layout);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choose1 = spinner1.getSelectedItemPosition();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choose2 = spinner2.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //click to Convert function calling ....
        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str =Value.getText().toString();
                if(str.trim().length()==0){
                    //edit text empty than error
                    Value.setError("Please Enter value");
                    Value.requestFocus();
                }
                else{
                    //binary to other number system convert
                    if(choose1 == 0)
                    {
                        String pattern = "^[0-1]+$";
                        boolean matches = Pattern.matches(pattern, str);
                        if(matches){
                           switch (choose2)
                           {
                               case 0:
                                   //bin to bin
                                   Ans.setText(str);
                                   break;
                               case 1:
                                   //bin to dec
                                   Ans.setText(String.valueOf(Long.parseLong(str,2)));
                                   break;
                               case 2:
                                   //bin to oct
                                   Ans.setText(String.valueOf(Long.toOctalString(Long.parseLong(str, 2))));
                                   break;
                               case 3:
                                   //bin to hex
                                   Ans.setText(String.valueOf(Long.toHexString(Long.parseLong(str,2)).toUpperCase()));
                                   break;
                           }
                        }
                        else{
                            //invaild value enter
                            Value.setError("invaild value");
                            Value.requestFocus();
                        }
                    }
                    //Decimal to other number system
                    else if (choose1 == 1)
                    {
                        String pattern = "^[0-9]+$";
                        boolean matches = Pattern.matches(pattern, str);
                        if(matches){
                            Long value = Long.parseLong(Value.getText().toString());
                            switch (choose2)
                            {

                                case 0:
                                    //dec to bin

                                    Ans.setText(String.valueOf(Long.toBinaryString(value)));
                                    break;
                                case 1:
                                    //dec to dec
                                    Ans.setText(str);
                                    break;
                                case 2:
                                    //dec to oct

                                    Ans.setText(String.valueOf(Long.toOctalString(value)));
                                    break;
                                case 3:
                                    //dec to hex

                                    Ans.setText(String.valueOf(Long.toHexString(value).toUpperCase()));
                                    break;
                            }
                        }
                        else{
                            //invaild value enter
                            Value.setError("invaild value");
                            Value.requestFocus();
                        }
                    }
                    // Octal to other number system
                    else if (choose1 == 2)
                    {
                        String pattern = "^[0-7]+$";
                        boolean matches = Pattern.matches(pattern, str);
                        if(matches){
                            switch (choose2)
                            {
                                case 0:
                                    //oct to bin
                                    Ans.setText(String.valueOf(Long.toBinaryString(Long.parseLong(str,8))));
                                    break;
                                case 1:
                                    //oct to dec
                                    Ans.setText(String.valueOf(Long.parseLong(str,8)));
                                    break;
                                case 2:
                                    //oct to oct
                                    Ans.setText(str);
                                    break;
                                case 3:
                                    //oct to hex
                                    Ans.setText(String.valueOf(Long.toHexString(Long.parseLong(str,8)).toUpperCase()));
                                    break;
                            }
                        }
                        else{
                            //invaild value enter
                            Value.setError("invaild value");
                            Value.requestFocus();
                        }
                    }
                    // Hexadecimal to other number system
                    else if (choose1 == 3)
                    {
                        String pattern = "^[0-9a-fA-F]+$";
                        boolean matches = Pattern.matches(pattern, str);
                        if(matches){
                            switch (choose2)
                            {
                                case 0:
                                    //hex to bin
                                    Ans.setText(String.valueOf(Long.toBinaryString(Long.parseLong(str,16))));
                                    break;
                                case 1:
                                    //hex to dec
                                    Ans.setText(String.valueOf(Long.parseLong(str,16)));
                                    break;
                                case 2:
                                    //hex to oct
                                    Ans.setText(String.valueOf(Long.parseLong(str,16)));
                                    break;
                                case 3:
                                    //hex to hex
                                    Ans.setText(str.toUpperCase());
                                    break;
                            }
                        }
                        else{
                            //invaild value enter
                            Value.setError("invaild value");
                            Value.requestFocus();
                        }
                    }



                }

            }
        });
     }

}
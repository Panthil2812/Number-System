package com.rku.number_system;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.Stack;

public  class calculator {

    //DECIMAL TO BINARY CONVERT
    public static String DecToBin(String str)
    {
        String bin = "";
        Double value = Double.valueOf (str);
        Long l = value.longValue ();
        bin = String.valueOf(Long.toBinaryString (l));
        Double fact = (value - l);
        if(fact != 0.00)
        {
            bin +=".";
            int max = 10;
            while (max-- > 0)
            {

                fact =fact * 2;
                Long bit  = fact.longValue();
                fact -=bit;
                if(fact == 0.00)
                {
                    if(bit== 1)
                    {
                        bin +="1";
                    }else{
                        bin +="0";
                    }
                    break;
                }
                else{
                    if(bit== 1)
                    {
                        bin +="1";
                    }else{
                        bin +="0";
                    }
                }
            }

        }
        return bin;
    }
    // DECIMAL TO OCTAL
    public static String DecToOct(String dec)
    {
        String oct = "";
        Double value = new Double(dec);
        Long l = value.longValue ();
        oct = String.valueOf(Long.toOctalString(l));
        Double fact = (value - l);
        if(fact != 0.00)
        {
            oct +=".";
            int max = 15;
            while (max-- > 0)
            {

                fact =fact * 8;
                Long bit  = fact.longValue();
                fact -=bit;
                if(fact == 0.00)
                {
                    oct +=String.valueOf(bit);
                    break;
                }
                else{
                    oct +=String.valueOf(bit);

                }
            }
        }
        return oct;
    }
    // DECIMAL TO HEXADECIMAL
    public static String DecToHex(String dec)
    {
        String hex = "";
        Double value = new Double(dec);
        Long l = value.longValue ();
        hex = String.valueOf(Long.toHexString(l).toUpperCase());
        Double fact = (value - l);
        if(fact != 0.00)
        {
            hex +=".";
            int max = 15;
            while (max-- > 0)
            {
                fact =fact * 16;
                Long bit  = fact.longValue();
                fact -=bit;
                if(fact == 0.00)
                {
                    hex += String.valueOf(Long.toHexString(bit).toUpperCase());
                    break;
                }
                else{
                    hex += String.valueOf(Long.toHexString(bit).toUpperCase());
                }
            }
        }
        return hex;
    }
    // BINARY TO DECIMAL
   public static String BintoDec(String bin)
    {
        String decimal ="";
        String[] arrOfStr = bin.split("[.]+");
        ArrayList<String> ar = new ArrayList<String>();
        for (String a : arrOfStr)
        {
            ar.add(a);
        }


        if(ar.size() == 2)
        {
            String fa = ar.get(1);
            Double sum =0.0;
            int p=0;
            for(int i=0;i<fa.length();i++)
            {
                p = p-1;
                char ch = (fa.charAt(i));
                int a = Character.getNumericValue(ch);
                sum =(sum + (a*Math.pow(2, p)));
            }
            String sum1 =(String.valueOf(sum));
            BigDecimal bd1 =new BigDecimal(sum1);
            decimal =String.valueOf(Long.parseLong(ar.get(0),2));
            BigDecimal bd2 =new BigDecimal(decimal);
            decimal =String.valueOf(bd1.add(bd2));
        }else{
            decimal =String.valueOf(Long.parseLong(ar.get(0),2));
        }
        return decimal;
    }
    // OCTAL TO DECIMAL
    public static String OcttoDec(String hex)
    {
        String decimal ="";
        String[] arrOfStr = hex.split("[.]+");
        ArrayList<String> ar = new ArrayList<String>();
        for (String a : arrOfStr)
        {
            ar.add(a);
        }
        if(ar.size() == 2)
        {
            String fa = ar.get(1);
            Double sum =0.0;
            int p=0;
            for(int i=0;i<fa.length();i++)
            {
                p = p-1;
                char ch = (fa.charAt(i));
                int a = Character.getNumericValue(ch);
                sum =(sum + (a*Math.pow(8, p)));
            }
            String sum1 =(String.valueOf(sum));
            BigDecimal bd1 =new BigDecimal(sum1);
            decimal =String.valueOf(Long.parseLong(ar.get(0),8));
            BigDecimal bd2 =new BigDecimal(decimal);
            decimal =String.valueOf(bd1.add(bd2));
        }else{
            decimal =String.valueOf(Long.parseLong(ar.get(0),8));
        }
        return decimal;
    }
    // HEXADECIMAL TO DECIMAL
    public static String HextoDec (String hex)
    {

        String decimal ="";
        String[] arrOfStr = hex.split("[.]+");
        ArrayList<String> ar = new ArrayList<String>();
        for (String a : arrOfStr)
        {
            ar.add(a.toUpperCase());
        }
        if(ar.size() == 2)
        {
            String fa = ar.get(1);
            Double sum =0.0;
            int p=0;
            for(int i=0;i<fa.length();i++)
            {
                p = p-1;
                char ch = (fa.charAt(i));
                int a;
                if(ch == 'A'){ a=10; }
                else if(ch == 'B'){ a=11; }
                else if(ch == 'C'){ a=12; }
                else if(ch == 'D'){ a=13; }
                else if(ch == 'E'){ a=14; }
                else if(ch == 'F'){ a=15; }
                else{
                    a=Character.getNumericValue(ch);
                }
                sum =(sum + (a*Math.pow(16, p)));
            }
            String sum1 =(String.valueOf(sum));
            BigDecimal bd1 =new BigDecimal(sum1);
            decimal =String.valueOf(Long.parseLong(ar.get(0),16));
            BigDecimal bd2 =new BigDecimal(decimal);
            decimal =String.valueOf(bd1.add(bd2));
        }
        else
        {
            decimal =String.valueOf(Long.parseLong(ar.get(0),16));
        }
        return decimal;
    }
    // STRING TO INTEGER TO STRING
    public static String intvalue(String ans)
    {
        String[] arrOfStr = ans.split("[.]+");
        ArrayList<String> ar = new ArrayList<String>();
        for (String a : arrOfStr)
        {
            ar.add(a);

        }
        long i = Long.valueOf(ar.get(1));
        if(i==0)
        {
            return ar.get(0);
        }
        else{
            return ans;
        }
    }


}



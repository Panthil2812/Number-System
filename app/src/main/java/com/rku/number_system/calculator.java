package com.rku.number_system;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.Stack;

public  class calculator {
                                                                // CALCULATOR FOR DECIMAL
    public static Double evaluate(String expression,int id)
    {
        char[] tokens = expression.toCharArray();
        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
        String array1[]= expression.split("[+ / - *]+");
        for (String temp: array1)
        {
           switch (id){
               case R.id.txt_decimal:
                   values.push(Double.parseDouble(temp.toString()));
                   break;
           }

        }

        for (int i = 0; i < tokens.length; i++)
        {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
                // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && (hasPrecedence(tokens[i])< hasPrecedence(ops.peek())))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                // Push current token to 'ops'.
                ops.push(tokens[i]);
                System.out.println(tokens[i]);
            }
        }
        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        // Top of 'values' contains result, return it
        return values.pop();
    }

    public static int hasPrecedence(char op1)
    {
        if(op1 == '+' || op1 == '-')
        {
            return 1;
        }
        else if(op1 == '*' || op1 == '/')
        {
            return 2;
        }
        return 0;
    }
    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static double applyOp(char op, double b, double a)
    {
        switch (op)
        {
            case '+':
                //System.out.println("a+b  : "+a +" + "+b);
                return a + b;
            case '-':
              //  System.out.println("a-b  : "+a +" - "+b);
                return a - b;
            case '*':
                //System.out.println("a*b  : "+a +" * "+b);
                return a * b;
            case '/':
               // System.out.println("a/b  : "+a  +" / "+b);
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
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



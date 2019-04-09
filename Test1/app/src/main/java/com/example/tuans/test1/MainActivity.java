package com.example.tuans.test1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText textInput, textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = (EditText) findViewById(R.id.textInput);
        textOutput = (EditText) findViewById(R.id.textOutput);
    }

    @Override
    public void onClick(View v) {
        String inputString = textInput.getText().toString();

        String[] listNumber = splitString(inputString);
        StringBuilder line = new StringBuilder();
        StringBuilder beauti = new StringBuilder();

        for (int i = 0; i < listNumber.length; i++) {
            if (listNumber[i].equals("")) continue;

            List<String> result = checkBeautyNumber(listNumber[i]);
            if (result != null) {
                String type = getType(listNumber[i]);
                for (String s:result) {
                    line.append(listNumber[i]).append(": ").append(s).append("\n");
                }
            }
        }
        textOutput.setText(line);
    }

    private String getType(String s) {
        return null;
    }

    private List<String> checkBeautyNumber(String s) {
        List<String> result = new ArrayList<>();
        s = s.replaceAll("[^0-9.-]","");


        int sum = calSum(s);
        if (sum % 10 == 0) {
            result.add("Tổng bằng 10");
        }
        if (sum % 10 == 9) {
            result.add("Tổng bằng 9");
        }


        int numberResult = checkEndWithDoubleBeautyNumber(s);
        if (numberResult >= 0) {
            if (numberResult == 0) {
                result.add("Cụm số lặp");
            } else if (numberResult == 1) {
                result.add("Kết thúc bằng cụm số lặp");
            }
        }

        int increasing  = checkIncreasingNumber(s);
        if (increasing >=3) {
            result.add("Số tiến tăng tuần tự (" + increasing +" số liên tiếp)");
        }
        return result;
    }

    private int checkIncreasingNumber(String s) {
        char[] charArray = s.toCharArray();
        int count = 1;
        if (charArray.length < 3) return 0;
        for (int i = 0; i < charArray.length-1; i++) {
            if (charArray[i+1] - charArray[i] == 1) {
                count ++;
            } else {
                count = 1;
            }
        }
        return count;
    }

    private int checkEndWithDoubleBeautyNumber(String s) {
        if (s.length() >= 4) {
            s = s.substring(s.length() - 4);
            s = s.replace("6","");
            s = s.replace("8","");
            if(s.isEmpty()) {
                return 1;
            } else {
                char[] charArray = s.toCharArray();
                if (charArray[charArray.length-1] == charArray[charArray.length-2]) {
                    return 0;
                }
            }
        }
        return -1;
    }

    private int calSum(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            int digit = c - '0';
            sum += digit;
        }
        return sum;
    }

    private String[] splitString(String inputString) {
        return inputString.split("\\s+|\\#+|\\,+");
    }
}
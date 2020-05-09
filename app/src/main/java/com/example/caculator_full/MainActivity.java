package com.example.caculator_full;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.edt);
    }

    public void insert(View view){
        String previous = edt.getText().toString();
        if(previous.equals("Syntax Error")){
            previous = "";
            edt.setText("");
        }
        if (previous.equals("Math Error")){
            previous = "";
            edt.setText("");
        }
        switch (view.getId()){
            case (R.id.btn_0):
                edt.setText(previous + "0");
            break;
            case (R.id.btn_1):
                edt.setText(previous + "1");
                break;
            case (R.id.btn_2):
                edt.setText(previous + "2");
                break;
            case (R.id.btn_3):
                edt.setText(previous + "3");
                break;
            case (R.id.btn_4):
                edt.setText(previous + "4");
                break;
            case (R.id.btn_5):
                edt.setText(previous + "5");
                break;
            case (R.id.btn_6):
                edt.setText(previous + "6");
                break;
            case (R.id.btn_7):
                edt.setText(previous + "7");
                break;
            case (R.id.btn_8):
                edt.setText(previous + "8");
                break;
            case (R.id.btn_9):
                edt.setText(previous + "9");
                break;
            case (R.id.btn_plus):
                edt.setText(previous + "+");
                break;
            case (R.id.btn_minus):
                edt.setText(previous + "-");
                break;
            case (R.id.btn_mutiply):
                edt.setText(previous + "x");
                break;
            case (R.id.btn_divide):
                edt.setText(previous + "/");
                break;
            case (R.id.btn_point):
                edt.setText(previous + ".");
                break;
        }
    }

    public void onclickAC(View view){
        edt.setText("");
    }

    public void onclickDE(View view){
        String previous = edt.getText().toString();
        if(previous.length() != 0){
            previous = edt.getText().toString();
            String text = previous.substring(0,previous.length()-1);
            edt.setText(text);
        }
    }

    public int check(String left){
        char[] ch = left.toCharArray();
        char[] key = {'+', '-', 'x', '/'};

        for(int j = 0; j < left.length(); j++){
            if(ch[j] == key[0] || ch[j] == key[1] || ch[j] == key[2] || ch[j] == key[3]){
                if(ch[j+1] == key[0] || ch[j+1] == key[1] || ch[j+1] == key[2] || ch[j+1] == key[3]){
                    return 1;
                }
            }
        }

        return 0;
    }

    public void equal(View view){
        String left = edt.getText().toString();
        char[] ch = left.toCharArray();
        char[] key = {'+', '-', 'x', '/'};
        ArrayList<Float> array = new ArrayList<Float>();
        ArrayList<String> array_1 = new ArrayList<String>();

        if (left.indexOf("+") == -1 || left.indexOf("-") == -1 || left.indexOf("x") == -1 || left.indexOf("/") == -1) {
            String[] string = left.split("\\.");
            if (string.length > 2) {
                edt.setText("Syntax Error");
            } else {
                edt.setText(left);
            }
        }
            int val = 0;
            for (int i = 0; i < left.length(); i++) {
                if (ch[i] == key[0] || ch[i] == key[1] || ch[i] == key[2] || ch[i] == key[3]) {
                    String a = Character.toString(ch[i]);
                    array_1.add(a);
                    String num = left.substring(val, i);
                    float b = Float.parseFloat(num);
                    array.add(b);
                    val = i + 1;
                }
            }

            int l = left.length() - 1;

            for (int j = l; l > 0; j--) {
                if (ch[j] == key[0] || ch[j] == key[1] || ch[j] == key[2] || ch[j] == key[3]) {
                    String num = left.substring(j + 1, l + 1);
                    float b = Float.parseFloat(num);
                    array.add(b);
                    break;
                }
            }

            String tem = "";
            for (String s : array_1) {
                tem += s;
            }
            while (tem.contains("x") || tem.contains("/")) {
                for (int i = 0; i < array_1.size(); i++) {
                    if ("x".equals(array_1.get(i))) {
                        float mutiply = array.get(i) * array.get(i + 1);
                        array.remove(i + 1);
                        array.remove(i);
                        array.add(i, mutiply);
                        array_1.remove(i);
                        tem = "";
                        for (String s : array_1) {
                            tem += s;
                        }
                        break;
                    }
                    if ("/".equals(array_1.get(i))) {
                        float divide = array.get(i) / array.get(i + 1);
                        array.remove(i + 1);
                        array.remove(i);
                        array.add(i, divide);
                        array_1.remove(i);
                        tem = "";
                        for (String s : array_1) {
                            tem += s;
                        }
                        break;
                    }
                }
            }

        float result = 0;
            for (int i = 0; i < array_1.size(); i++) {
                if ("+".equals(array_1.get(i))) {
                    if (result == 0) {
                        result = array.get(i) + array.get(i + 1);
                    } else {
                        result = result + array.get(i + 1);
                    }
                }
                if ("-".equals(array_1.get(i))) {
                    if (result == 0) {
                        result = array.get(i) - array.get(i + 1);
                    } else {
                        result = result - array.get(i + 1);
                    }
                }
                array.set(i, result);
            }

            String s = String.valueOf(result);
            edt.setText(s);
        }
}

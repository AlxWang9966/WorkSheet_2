package com.example.simple_calculator_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView formula, result;
    MaterialButton num_1, num_2, num_3, b_plus, b_mul;
    MaterialButton num_4, num_5, num_6, b_minus, b_div;
    MaterialButton num_7, num_8, num_9, b_sqrt;
    MaterialButton num_0, b_decimal, b_clear, b_eq;
    String formulaString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formula = findViewById(R.id.formula);
        result = findViewById(R.id.result);

        assignID(num_1, R.id.num1);
        assignID(num_2, R.id.num2);
        assignID(num_3, R.id.num3);
        assignID(num_4, R.id.num4);
        assignID(num_5, R.id.num5);
        assignID(num_6, R.id.num6);
        assignID(num_7, R.id.num7);
        assignID(num_8, R.id.num8);
        assignID(num_9, R.id.num9);
        assignID(num_0, R.id.num0);
        assignID(b_plus, R.id.b_plus);
        assignID(b_mul, R.id.b_mul);
        assignID(b_minus, R.id.b_minus);
        assignID(b_div, R.id.b_div);
        assignID(b_sqrt, R.id.b_sqrt);
        assignID(b_decimal, R.id.b_decimal);
        assignID(b_eq, R.id.b_eq);
        assignID(b_clear, R.id.b_clear);

    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    //UNFINISHED!!!
    //method to calculate the formula - parsing through the string
    public float calc( String formString) {
        float answer = 0;
        int i = 0;

        while (formString.length() > 0) {
            if (formString.indexOf('+') > -1) {
                float input = Float.parseFloat(formString.substring(i, formString.indexOf('+')));
                answer = answer + input;
                formString = formString.substring((formString.indexOf('+')) + 1);
                i += formString.indexOf('+');
            }
            System.out.println(formulaString);
            float input = Float.parseFloat(formulaString.substring(i));
        }

        return answer;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        float finalAnswer = 0;
        formulaString = formulaString + buttonText;

        if (buttonText.equals("=")) {
            finalAnswer = calc(formulaString);
            result.setText(String.valueOf(finalAnswer));
        }
        if (buttonText.equals("C")) {
            formulaString = "";
            formula.setText("");
        } else {
            formula.setText(formulaString);
        }
    }

}
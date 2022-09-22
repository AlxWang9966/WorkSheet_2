package com.example.calculatorp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText input;
    MaterialButton zero, dot, ac, equal;
    MaterialButton one, two, three, plus;
    MaterialButton four, five, six, min;
    MaterialButton seven, eight, nine, mul;
    MaterialButton sqrt, plus_min, percentage, div;
    StringBuilder inputAll;
    String preserve, buttonText;
    LinkedList<String> log;
    Boolean waiting, afterEqual, isChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = new LinkedList<>();
        inputAll = new StringBuilder();
        waiting = false;
        afterEqual = false;
        isChanged = false;

        input = findViewById(R.id.input);
        zero = setButtons(R.id.zero);
        dot = setButtons(R.id.dot);
        ac = setButtons(R.id.AC);
        equal = setButtons(R.id.equal);
        one = setButtons(R.id.one);
        two = setButtons(R.id.two);
        three = setButtons(R.id.three);
        four = setButtons(R.id.four);
        five = setButtons(R.id.five);
        six = setButtons(R.id.six);
        seven = setButtons(R.id.seven);
        eight = setButtons(R.id.eight);
        nine = setButtons(R.id.nine);
        plus = setButtons(R.id.plus);
        min = setButtons(R.id.min);
        mul = setButtons(R.id.mul);
        sqrt = setButtons(R.id.sqrt);
        plus_min = setButtons(R.id.plus_min);
        percentage = setButtons(R.id.percentage);
        div = setButtons(R.id.div);
    }

    private MaterialButton setButtons(int id) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        buttonText = button.getText().toString();
        preserve = input.getText().toString();

        if (isNum(buttonText)) isChanged = true;

        //For plus_minus
        if (buttonText.equals("\u00B1")) {
            String temp = preserve.substring(0, 1);
            if (!temp.equals("0") || preserve.length() > 1) {
                if (temp.equals("\u2212") || temp.equals("-")) {
                    input.setText(preserve.substring(1));
                } else {
                    inputAll.setLength(0);
                    inputAll.append("\u2212");
                    inputAll.append(preserve);
                    input.setText(inputAll.toString());
                }
            }
            return;
        }

        //AC clears all input
        if (buttonText.equals("AC")) {
            input.setText("0");
            log.clear();
            isChanged = true;
            waiting = false;
            afterEqual = false;
            return;
        }

        //For plus
        if (buttonText.equals("+")) {
            buttonAction(buttonText);
            return;
        }

        //For minus
        if (buttonText.equals("\u2212")) {
            buttonAction(buttonText);
            return;
        }

        //For mul
        if (buttonText.equals("\u00d7")) {
            buttonAction(buttonText);
            return;
        }

        //For div
        if (buttonText.equals("\u00f7")) {
            buttonAction(buttonText);
            return;
        }

        //For sqrt
        if (buttonText.equals("\u221a")) {
            double temp = Double.parseDouble(preserve);
            temp = Math.sqrt(temp);
            String result = String.valueOf(temp);
            if (result.endsWith(".0")) result = result.replace(".0", "");
            input.setText(result);
            return;
        }

        //For percentage
        if (buttonText.equals("%")) {
            double temp = Double.parseDouble(preserve);
            temp = temp / 100;
            String result = String.valueOf(temp);
            if (result.endsWith(".0")) result = result.replace(".0", "");
            input.setText(result);
            return;
        }

        //Calculate output
        if (buttonText.equals("=")) {
            if (log.size() >= 2) {
                evaluate();
                afterEqual = true;
            }
            return;
        }

        if ((preserve.equals("0") && !buttonText.equals(".")) || waiting) {
            preserve = "";
            waiting = false;
        }
        inputAll.setLength(0);
        inputAll.append(preserve);
        inputAll.append(buttonText);
        input.setText(inputAll.toString());
    }

    private void evaluate() {
        inputAll.setLength(0);
        inputAll.append(log.remove());
        inputAll.append(log.remove());
        inputAll.append(preserve);
        String output = String.valueOf(simpleCalculate(inputAll.toString()));
        if (output.endsWith(".0")) output = output.replace(".0", "");
        input.setText(output);
        log.clear();
        log.add(output);
        waiting = false;
        isChanged = true;
    }

    @SuppressLint("SetTextI18n")
    private double simpleCalculate(String expression) {
        int index = 0;
        int temp;
        System.out.println("Expression: " + expression);
        if (expression.contains("\u2212")) {
            expression = expression.replace((char) 8722, '-');
        }

        for (int i = 1; i < expression.length(); i++) {
            temp = expression.charAt(i);
            if ((temp < 48 || temp > 57) && temp != '.') {
                index = i;
                break;
            }
        }

        try {
            double num1 = Double.parseDouble(expression.substring(0, index));
            String op = expression.substring(index, index + 1);
            double num2 = Double.parseDouble(expression.substring(index + 1));
            double result = 0;
            switch (op) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "\u00d7":
                    result = num1 * num2;
                    break;
                case "\u00f7":
                    result = num1 / num2;
                    break;
            }
            return result;
        } catch (Exception e) {
            input.setText("Invalid input");
        }
        return 0;
    }

    private boolean isNum(String in) {
        try {
            Double.parseDouble(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void buttonAction(String symbol) {
        if (isChanged) {
            if (!afterEqual)
                log.add(preserve);
            if (log.size() == 3) {
                if (isNum(log.peek()))
                    evaluate();
            }
            log.add(symbol);
            waiting = true;
            afterEqual = false;
            isChanged = false;
        } else if (!log.isEmpty() && !log.peek().equals(symbol)) {
            log.removeLast();
            log.add(symbol);
        }
    }
}
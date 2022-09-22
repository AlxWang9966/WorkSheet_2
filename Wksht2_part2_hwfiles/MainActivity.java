package com.example.calculatorp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button add, sub, mul, div, mod;
    TextView input1, input2, result;
    Double first = 0.0, second = 0.0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.plus);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        mod = findViewById(R.id.mod);
        input1 = findViewById(R.id.Num1);
        input2 = findViewById(R.id.Num2);
        result = findViewById(R.id.Ans);

        add.setOnClickListener(view -> {
            try {
                first = Double.parseDouble(input1.getText().toString());
                second = Double.parseDouble(input2.getText().toString());
                result.setText(String.valueOf(first + second));
            } catch (Exception e) {
                result.setText("Invalid input");
            }
        });

        sub.setOnClickListener(view -> {
            try {
                first = Double.parseDouble(input1.getText().toString());
                second = Double.parseDouble(input2.getText().toString());
                result.setText(String.valueOf(first - second));
            } catch (Exception e) {
                result.setText("Invalid input");
            }
        });

        mul.setOnClickListener(view -> {
            try {
                first = Double.parseDouble(input1.getText().toString());
                second = Double.parseDouble(input2.getText().toString());
                result.setText(String.valueOf(first * second));
            } catch (Exception e) {
                result.setText("Invalid input");
            }
        });

        div.setOnClickListener(view -> {
            try {
                first = Double.parseDouble(input1.getText().toString());
                second = Double.parseDouble(input2.getText().toString());
                if (second == 0)
                    result.setText("Divide by Zero not allowed!");
                else
                    result.setText(String.valueOf(first / second));
            } catch (Exception e) {
                result.setText("Invalid input");
            }
        });

        mod.setOnClickListener(view -> {
            try {
                first = Double.parseDouble(input1.getText().toString());
                second = Double.parseDouble(input2.getText().toString());
                result.setText(String.valueOf(first % second));
            } catch (Exception e) {
                result.setText("Invalid input");
            }
        });
    }
}
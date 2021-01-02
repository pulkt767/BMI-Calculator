package com.pulkeet.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton;
    private EditText inchesEdit;
    private EditText feetEdit;
    private EditText weightEdit;
    private EditText ageEdit;
    private RadioButton femaleRadio;
    private RadioButton maleRadio;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        setupbuttonlistner();

    }

    private void findviews()
    {
        resultText = findViewById(R.id.textview_result);
        maleRadio = findViewById(R.id.male_radio);
        femaleRadio = findViewById(R.id.female_radio);
        ageEdit = findViewById(R.id.edittext_age);
        weightEdit = findViewById(R.id.edittext_weight);
        feetEdit = findViewById(R.id.edittext_feet);
        inchesEdit = findViewById(R.id.edittext_inches);
        calculateButton = findViewById(R.id.button_calculate);
    }
    private void setupbuttonlistner() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatebmi();
            }
        });
    }

    private void calculatebmi() {
        try{
            String ageText = ageEdit.getText().toString();
            String feetText = feetEdit.getText().toString();
            String inchesText = inchesEdit.getText().toString();
            String weightText = weightEdit.getText().toString();

            int age = Integer.parseInt(ageText);
            int feet = Integer.parseInt(feetText);
            int inches = Integer.parseInt(inchesText);
            int weight= Integer.parseInt(weightText);

            int totalinches = feet*12 + inches;
            double totalheightinmetres = totalinches*0.024;
            double bmi=weight/(totalheightinmetres*totalheightinmetres);
            double healthyweight_min = 18.5 * (totalheightinmetres*totalheightinmetres);
            double healthyweight_max = 25 * (totalheightinmetres*totalheightinmetres);

            DecimalFormat bmiformatter = new DecimalFormat("0.00");
            String bmiresult = bmiformatter.format(bmi);
            String fullstringresult;
            String weightneeded;
            if(bmi<18.5)
            {
                fullstringresult ="BMI-" +bmiresult ;
                double weightreq= healthyweight_min-weight;
                weightneeded= bmiformatter.format(weightreq);
                fullstringresult = fullstringresult+"\nYou are underweight by " + weightneeded+"KG";
            }
            else if (bmi>25)
            {
                fullstringresult ="BMI-" +bmiresult ;
                double weightreq = weight-healthyweight_max;
                weightneeded= bmiformatter.format(weightreq);
                fullstringresult = fullstringresult+ "\n You are overweight by " + weightneeded+"KG";
            }
            else{
                fullstringresult = bmiresult + " You are healthy";
            }

            resultText.setText(fullstringresult);
        } catch (Exception e){
            resultText.setText("PLease fill all the fields");
        }


    }
}
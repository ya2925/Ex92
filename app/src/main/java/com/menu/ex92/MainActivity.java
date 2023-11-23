package com.menu.ex92;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Yanir Aton
 * @version 1.0
 * @since 2023-09-10
 * This class is the main activity of the application
 * it will get information from the user and will use it to send it to the results activity.
 */
public class MainActivity extends AppCompatActivity {

    int mode = -1;
    String[] modes = {"Arithmetic Series","Geometric series"};

    Button seriesType,done;
    EditText firstIntense,differenceMultiplier;
    TextView dOrQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect the views to the xml widgets
        seriesType = findViewById(R.id.seriesType);
        done = findViewById(R.id.doneButton);
        firstIntense = findViewById(R.id.firstIntense);
        differenceMultiplier = findViewById(R.id.differenceMultiplier);
        dOrQ = findViewById(R.id.dOrQ);
    }

    /**
     * the onclick function of the change mode - change from Arithmetic Series to Invoice series or Invoice series to Arithmetic Series
     *
     * @param v the button that was clicked
     */
    public void changeMode(View v){
        if (mode == -1 || mode == 1){
            mode = 0;
            dOrQ.setText("common difference");
        }
        else{
            mode = 1;
            dOrQ.setText("common ratio");
        }
        seriesType.setText(modes[mode]);
    }

    /**
     * the onclick function of the done button
     * it will go to the results activity with the information from the user input
     *
     * @param v the button that was clicked
     */
    public void goToResults(View v){
        // check the the input fields are not empty
        if (firstIntense.getText().toString().equals("") || differenceMultiplier.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please fill in all the fields",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isDoubleNum(firstIntense.getText().toString()) || !isDoubleNum(differenceMultiplier.getText().toString())){
            Toast.makeText(getApplicationContext(),"Please make sure the numbers you entered are a valid numbers",Toast.LENGTH_SHORT).show();
            return;
        }
        if(mode == -1){
            Toast.makeText(getApplicationContext(),"Choose Series Type!!",Toast.LENGTH_SHORT).show();
            return;
        }else{
            // create the intent
            Intent res = new Intent(this,results.class);

            // add the information to the intent
            res.putExtra("mode",mode);
            res.putExtra("firstIntense",Double.parseDouble(String.valueOf(firstIntense.getText())));
            res.putExtra("differenceMultiplier",Double.parseDouble(String.valueOf(differenceMultiplier.getText())));

            // start the activity
            startActivity(res);
        }
    }

    public void goToCredits(View v){
        Intent credits = new Intent(this, credits.class);
        startActivity(credits);
    }

    /**
     * this method chack if a given string is a double number
     * @param str the string to check
     * @return true if the string is a double number, otherwise false
     */
    public static boolean isDoubleNum(String str){
        if (str == null || str.equals("") || str.isEmpty()){
            return false;
        }
        boolean hasDecimalPoint = false;
        boolean hasDigit = false;
        for(int i = 0; i < str.length(); i++){
            char currentChar = str.charAt(i);
            if(i==0 && (currentChar == '-' || currentChar == '+')){
                continue;
            } else if (currentChar == '.') {
                if (hasDecimalPoint) {
                    return false;
                } else {
                    hasDecimalPoint = true;
                }
            } else if (Character.isDigit(currentChar)) {
                hasDigit = true;
            } else {
                return false;
            }
        }
        return hasDigit;
    }



}
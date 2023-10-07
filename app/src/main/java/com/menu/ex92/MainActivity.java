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
        if(mode == -1){
            Toast.makeText(getApplicationContext(),"Choose Series Type!!",Toast.LENGTH_SHORT).show();
            return;
        }else{
            // create the intent
            Intent res = new Intent(this,results.class);

            // add the information to the intent
            res.putExtra("mode",mode);
            res.putExtra("firstIntense",Integer.parseInt(String.valueOf(firstIntense.getText())));
            res.putExtra("differenceMultiplier",Integer.parseInt(String.valueOf(differenceMultiplier.getText())));

            // start the activity
            startActivity(res);
        }
    }

    public void goToCredits(View v){
        Intent credits = new Intent(this, credits.class);
        startActivity(credits);
    }




}
package com.menu.ex92;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Yanir Aton
 * @version 1.0
 * @since 2023-09-10
 * This class is used to display the results of the calculation
 */
public class results extends AppCompatActivity implements View.OnCreateContextMenuListener {


    ListView seriesListView;

    double resultA1;
    double resultDQ;
    int selectedPosition;
    double sumToN;
    int mode;

    LinearLayout informationLayout;

    TextView infoText;

    Double[] seriesNs = new Double[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // connect the views to the xml widgets
        informationLayout = findViewById(R.id.informationLayout);
        infoText = findViewById(R.id.info);


        // reset the variables and set the layout weight to 0
        sumToN = 0;
        changeLayoutWeight(informationLayout, 0);

        // get the intent and the data from the intent
        Intent gi = getIntent();
        mode = gi.getIntExtra("mode", -1);
        resultA1 = gi.getIntExtra("firstIntense", 0);
        resultDQ = gi.getIntExtra("differenceMultiplier", 0);

        // setup the ListView
        seriesListView = (ListView) findViewById(R.id.Lv_series);
        seriesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        fillSeriesNs(seriesNs, mode); // fill the seriesNs array with values to display in the ListView
        ArrayAdapter<Double> adp = new ArrayAdapter<Double>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, seriesNs);
        seriesListView.setAdapter(adp);
        registerForContextMenu(seriesListView);
    }


    /**
     * calculate the n's value of an arithmetic series
     *
     * @param a1 the first value of the series
     * @param d  the common difference of the series
     * @param n  the n's value to calculate
     * @return the n's value of an arithmetic series'
     */
    public static double ArithmeticSeriesGetNthTerm(double a1, double d, int n) {
        return a1 + (n - 1) * d;
    }

    /**
     * calculate the n's value of a geometric series
     *
     * @param a1 the first value of the series
     * @param q  the common ratio of the series
     * @param n  the n's value to calculate
     * @return the n's value of a geometric series'
     */
    public static double GeometricSeriesGetNthTerm(double a1, double q, int n) {
        return a1 * Math.pow(q, n - 1);
    }

    /**
     * fill the seriesNs array with values to display in the ListView
     *
     * @param fillList the seriesNs array to fill
     * @param mode     the type of series to calculate(0 = arithmetic series, 1 = geometric series)
     */
    public void fillSeriesNs(Double[] fillList, int mode) {
        if (mode == 0) {
            for (int i = 0; i < fillList.length; i++) {
                fillList[i] = ArithmeticSeriesGetNthTerm(resultA1, resultDQ, i + 1);
            }
        } else if (mode == 1) {
            for (int i = 0; i < fillList.length; i++) {
                fillList[i] = GeometricSeriesGetNthTerm(resultA1, resultDQ, i + 1);
            }
        } else {
            for (int i = 0; i < fillList.length; i++) {
                fillList[i] = 0.0;
            }
        }
    }

    /**
     * Change the weight of a given LinearLayout to a given weight
     *
     * @param layout the LinearLayout to change
     * @param weight the weight to change the LinearLayout to
     */
    public void changeLayoutWeight(LinearLayout layout, int weight) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.weight = weight;
        layout.setLayoutParams(params);
    }

    /**
     * will run when you click on any of the listView items
     * it will create a context menu and add two opsions to it
     * @param menu The context menu that is being built
     * @param v The view for which the context menu is being built
     * @param menuInfo Extra information about the item for which the
     *            context menu should be shown. This information will vary
     *            depending on the class of v.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        selectedPosition = info.position;

        menu.add("Sum(Sn)");
        menu.add("Location(n)");
    }

    /**
     *
     * @param item The context menu item that was selected.
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = item.getItemId();
        String oper=item.getTitle().toString();
        changeLayoutWeight(informationLayout, 5);
        if (oper.equals("Sum(Sn)")) {
            sumToN = 0;
            for (int i = 0; i < selectedPosition + 1; i++) {
                sumToN += seriesNs[i];
            }
            infoText.setText("Sn = "+sumToN);
        }
        else if (oper.equals("Location(n)")) {
            infoText.setText("n = "+(selectedPosition+1));
        }

        return true;
    }

}
package zzafa015.uottawa.ca.tipcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        NumberFormat numberFormat = new DecimalFormat("0.00");
        Intent intent = getIntent();
        int person;
        float amount, tip;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        person = intent.getIntExtra("persons",1);
        amount = intent.getFloatExtra("amount", 0);
        tip = intent.getFloatExtra("tip", 13.5f)/100 * amount;
        String currency = preferences.getString("currency_preference","$");

        double amountPerson = amount/person;
        double tipPerson = tip/person;

        StringBuilder builder = new StringBuilder();

        builder.append("Total Amount:\n");
        builder.append(currency+numberFormat.format(amount)+ "\n\n");
        builder.append("Tip Amount:\n");
        builder.append(currency+numberFormat.format(tip)+ "\n\n");
        builder.append("Person(s) paying:\n");
        builder.append(person + "\n\n");
        builder.append("Amount per person:\n");
        builder.append(currency+numberFormat.format(amountPerson )+ "\n\n");
        builder.append("Tip per person:\n");
        builder.append(currency+numberFormat.format(tipPerson) + "\n\n");
        builder.append("Total per person:\n");
        builder.append(currency+numberFormat.format(amountPerson+tipPerson));

        TextView summary = (TextView)findViewById(R.id.lbl_summary_content);

        summary.setText(builder.toString());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

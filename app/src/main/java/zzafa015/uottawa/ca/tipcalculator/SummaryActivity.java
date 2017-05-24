package zzafa015.uottawa.ca.tipcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        int person;
        float amount, tip;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        person = intent.getIntExtra("persons",1);
        amount = intent.getFloatExtra("amount", 0);
        tip = intent.getFloatExtra("name", 13.5f);
        String currency = preferences.getString("currency_preference","$");

        double amountPerson = amount/person;
        double tipPerson = tip/person;

        StringBuilder builder = new StringBuilder();

        builder.append("Total Amount:\t\t\t");
        builder.append(currency+amount + "\n\n");
        builder.append("Tip Amount:\t\t\t");
        builder.append(currency+tip + "\n\n");
        builder.append("Person(s) paying:\t\t\t");
        builder.append(currency+person + "\n\n");
        builder.append("Amount per person:\t\t\t");
        builder.append(currency+amountPerson + "\n\n");
        builder.append("Tip per person:\t\t\t");
        builder.append(currency+tipPerson + "\n\n");
        builder.append("Total per person:\t\t\t");
        builder.append(currency+(amountPerson+tipPerson));

        TextView summary = (TextView)findViewById(R.id.lbl_summary_content);

        summary.setText(builder.toString());

    }
}

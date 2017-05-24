package zzafa015.uottawa.ca.tipcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView labelCurrency, labelSummary;
    EditText editTip, editAmount, editPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        labelCurrency = (TextView) findViewById(R.id.lbl_currency);
        labelCurrency.setText(preferences.getString("currency_preference","$"));
        final MainActivity mainActivity = this;
        editTip =(EditText) findViewById(R.id.edit_tip);
        editTip.setHint(preferences.getString("tip_preference", "13.7f"));
//        editTip.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                mainActivity.onTextChange();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        labelCurrency.setText(preferences.getString("currency_preference","$"));

        labelSummary = (TextView) findViewById(R.id.lbl_Sum);

        editAmount = (EditText) findViewById(R.id.edit_amount);
//        editAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                mainActivity.onTextChange();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        editPerson = (EditText) findViewById(R.id.edit_persons);

    }

    public void onClickSettingButton(){
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS,true);
        intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT,SettingsActivity.GeneralPreferenceFragment.class.getName());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.settings){
            onClickSettingButton();
        }
        return true;
    }

//    public void onTextChange(){
//        float amount, tip;
//        int persons;
//
//        persons = editPerson.getText().toString().equals("") ? Integer.parseInt(editPerson.getHint().toString()) : Integer.parseInt(editPerson.getText().toString());
//
//        amount = Float.parseFloat(editAmount.getText().toString());
//        tip = editTip.getText().toString().equals("") ? Float.parseFloat(editTip.getHint().toString()) : Float.parseFloat(editTip.getText().toString());
//
//        labelSummary.setText(String.valueOf(amount+(amount*tip/100)));
//
//    }

    public void onSummaryClick(View view){
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("amount",Float.parseFloat(editAmount.getText().toString()));
        intent.putExtra("tip",editTip.getText().toString().equals("") ? Float.parseFloat(editTip.getHint().toString()) : Float.parseFloat(editTip.getText().toString()));
        intent.putExtra("persons",editPerson.getText().toString().equals("") ? Integer.parseInt(editPerson.getHint().toString()) : Integer.parseInt(editPerson.getText().toString()));
        startActivity(intent);
    }
}

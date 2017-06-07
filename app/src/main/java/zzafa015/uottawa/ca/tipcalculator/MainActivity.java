package zzafa015.uottawa.ca.tipcalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.widget.*;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView labelCurrency;
    EditText editTip, editAmount, editPerson;
    RatingBar rb;
    View dialogView;
    Button addBtn,removeBtn, tipSuggestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        labelCurrency = (TextView) findViewById(R.id.lbl_currency);
        labelCurrency.setText(preferences.getString("currency_preference","$"));
        editTip =(EditText) findViewById(R.id.edit_tip);
        editTip.setHint(preferences.getString("tip_preference", "13.7f"));

        labelCurrency.setText(preferences.getString("currency_preference","$"));

        editAmount = (EditText) findViewById(R.id.edit_amount);

        editPerson = (EditText) findViewById(R.id.edit_persons);

        addBtn = (Button) findViewById(R.id.btn_add_person) ;
        removeBtn = (Button) findViewById(R.id.btn_subtract_person);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(editPerson.getText().toString());
                editPerson.setText(String.valueOf(num+1));
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(editPerson.getText().toString());
                if(num - 1 > 0)
                editPerson.setText(String.valueOf(num - 1));
            }
        });

        tipSuggestBtn = (Button) findViewById(R.id.btn_suggestTip);


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

    public void onSummaryClick(View view){
        if(editAmount.getText().toString().equals("") || Integer.parseInt(editPerson.getText().toString())<1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Please ensure correct information is entered")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

        }else {
            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtra("amount", Float.parseFloat(editAmount.getText().toString()));
            intent.putExtra("tip", editTip.getText().toString().equals("") ? Float.parseFloat(editTip.getHint().toString()) : Float.parseFloat(editTip.getText().toString()));
            intent.putExtra("persons", editPerson.getText().toString().equals("") ? Integer.parseInt(editPerson.getHint().toString()) : Integer.parseInt(editPerson.getText().toString()));
            startActivity(intent);
        }
    }

    public void onRateSuggestionClick(View view){

        LayoutInflater layoutInflater = this.getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.dialog_xml,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        rb = (RatingBar) dialogView.findViewById(R.id.rate_foodRate);
        final TextView lblRating = (TextView) dialogView.findViewById(R.id.lbl_tipsuggestval);
        lblRating.setText("Tip: "+String.valueOf(10+rb.getRating()*2)+"%");
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                lblRating.setText("Tip: "+String.valueOf(10+v*2) + "%");


            }
        });
        builder.setTitle(R.string.suggest_tip)
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        float val = 10+rb.getRating()*2;
                        editTip.setText(String.valueOf(val));


                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }
}

package com.example.meteo2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {

    public Activity act;
    public Dialog d;
    public Button yes, no;
    public RadioGroup rg;
    public EditText et;


    public CustomDialogClass(Activity a) {
        super(a);
        this.act = a;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        rg = (RadioGroup) findViewById(R.id.rg);
        et = (EditText) findViewById(R.id.villeInput);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                SharedPreferences sharedPref = act.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                if(!et.getText().toString().equals("") && !et.getText().toString().equals("ville"))
                {
                    editor.putString("ville", et.getText().toString());
                }
                if(((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString().equals("Tous les jours"))
                {
                    editor.putString("affichage", "8");
                }
                else
                {
                    editor.putString("affichage", "1");
                }
                editor.apply();
                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}

package com.example.group8.miniagent;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCustomerActivity extends BaseActivity {

    @BindView(R.id.newC_fName) EditText fn;
    @BindView(R.id.newC_lName) EditText ln;
    @BindView(R.id.newC_DOB) EditText dob;
    @BindView(R.id.newC_sAdrs) EditText adrs;
    @BindView(R.id.newC_city) EditText cty;
    @BindView(R.id.newC_state) EditText stte;
    @BindView(R.id.newC_zip) EditText zp;
    @BindView(R.id.newC_email) EditText eml;
    @BindView(R.id.newC_phone) EditText phne;
    @BindView(R.id.newC_cscore) EditText c_scr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.newC_ok)
        public void newC_ok(){
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date mDate;

            try{
                mDate = df.parse(dob.getText().toString());
                Calendar cal = Calendar.getInstance();
                cal.setTime(mDate);

                Intent intent = new Intent("CUSTOMER_INTENT");
                Bundle bundle = new Bundle();
                bundle.putString("f_name", fn.getText().toString());
                bundle.putString("l_name", ln.getText().toString());
                bundle.putInt("year", cal.get(Calendar.YEAR));
                bundle.putInt("month", cal.get(Calendar.MONTH));
                bundle.putInt("day", Calendar.DAY_OF_MONTH);
                bundle.putString("address", adrs.getText().toString());
                bundle.putString("city", cty.getText().toString());
                bundle.putString("state", stte.getText().toString());
                bundle.putInt("zip", Integer.parseInt(zp.getText().toString()));
                bundle.putString("email", eml.getText().toString());
                bundle.putString("phone", phne.getText().toString());
                bundle.putInt("c_score", Integer.parseInt(c_scr.getText().toString()));

                intent.putExtras(bundle);
                LocalBroadcastManager.getInstance(AddCustomerActivity.this).sendBroadcast(intent);
                onBackPressed();
            } catch (ParseException e) {
                //
            }
    }
}

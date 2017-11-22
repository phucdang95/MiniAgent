package com.example.group8.miniagent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public offLineDB testDB;
    private static Agent user;
    SharedPreferences mPrefs;

    @BindView(R.id.nameUser) TextView displayName;
    @BindView(R.id.rollUser) TextView displayRoll;
    @BindView(R.id.searchtext) EditText search;
    @BindView(R.id.agent_pic) ImageView profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPrefs = getPreferences(MODE_PRIVATE);
        Stetho.initializeWithDefaults(this);
        readGSON();

        user = testDB.getAgentDB().get(0);

        int picID = getResources().getIdentifier(user.getAgnt_ID().toLowerCase(), "drawable", this.getPackageName());
        Drawable userPic = getResources().getDrawable(picID, null);
        profile_pic.setBackground(userPic);
        displayName.setText(user.getFirst_name()+" "+user.getLast_name());
        displayRoll.setText(user.printRoll());

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("CUSTOMER_INTENT"));

    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("f_name")) {
                Bundle bundle = intent.getExtras();
                String f_name = bundle.getString("f_name");
                String l_name = bundle.getString("l_name");
                int year = bundle.getInt("year");
                int month = bundle.getInt("month");
                int day = bundle.getInt("day");
                String address = bundle.getString("address");
                String city = bundle.getString("city");
                String state = bundle.getString("state");
                int zip = bundle.getInt("zip");
                String email = bundle.getString("email");
                String phone = bundle.getString("phone");
                int c_score = bundle.getInt("c_score");

                user.addCustomer(testDB,f_name,l_name,year,month,day,address,city,state,zip,"US",email,phone,c_score);
                toastShort("added");
            } else {
                toastShort("no extra");
            }
            saveDB();
        }
    };

    public void readGSON(){
        Gson gson = new Gson();
        String json = mPrefs.getString("testDB", "");
        if (json.isEmpty()){
            LinkedList<Agent> agentList = new LinkedList<Agent>();
            LinkedList<Customer> customerList = new LinkedList<Customer>();
            testDB = new offLineDB(agentList, customerList);
            Admin test = new Admin("Test", "Account", 1980, 1, 1, "TestAddress", "TestCity", "TestState", 1, "US", "TestEmail", "TestPhone", "TestUser", "TestPass");
            test.decrement();
            test.addAgent(testDB, "Test", "Account", 1980, 1, 1, "TestAddress", "TestCity", "TestState", 1, "US", "TestEmail", "TestPhone", "TestUser", "TestPass");
            saveDB();
        }else{
            testDB = gson.fromJson(json, offLineDB.class);
        }
    }

    public void saveDB() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(testDB);
        prefsEditor.putString("testDB", json);
        prefsEditor.commit();
    }

    @OnClick(R.id.cstmr_btn)
        public void readDB(){
            toActivity(AddCustomerActivity.class);
        }

    @OnClick(R.id.menu_btn)
        public void toMenu(){

    }

    @OnClick(R.id.quote_btn)
        public void genQuote(){

    }

    @OnClick(R.id.search_btn)
        public void srchDB(){
            user.lookUp(testDB, search.getText().toString());
            int temp = testDB.getcDBcurrent();
            toastShort(""+testDB.getCustomerDB().get(temp).getFirst_name());
    }

}

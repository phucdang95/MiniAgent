package com.example.group8.miniagent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Stefan Dragic on 11/21/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    };

    public void toActivity(Class name) {
        Intent intent = new Intent(this, name);
        startActivity(intent);
    }

    public void toastShort(String content){
        final Toast a = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        a.show();
    }
}

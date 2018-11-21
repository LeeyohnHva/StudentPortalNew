package com.example.leey_.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity {

    EditText mPortalName;
    EditText mPortalUrl;
    Button mAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        //Init local variables

        mPortalName = findViewById(R.id.editText_name);
        mPortalUrl = findViewById(R.id.editText_url);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mAddButton = findViewById(R.id.button_addPortal);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mPortalName.getText().toString();
                String url = mPortalUrl.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(url)) {
                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();

                    Portal newportal = new Portal(name,url);
                    resultIntent.putExtra(MainActivity.EXTRA_PORTAL, newportal);

                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG);
                }


            }
        });
    }
}

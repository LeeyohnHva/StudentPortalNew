package com.example.leey_.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener{

    private List<Portal> mPortals;
    private PortalAdapter mAdapter;
    private RecyclerView mRecyclerView;

    //Constants used when calling the update activity
    public static final String EXTRA_PORTAL = "Portal";
    public static final int REQUESTCODE = 1234;
    private int mModifyPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPortals = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPortalActivity.class);
                startActivityForResult(intent,REQUESTCODE);
            }
        });

        updateUI();
    }

    public void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalAdapter(mPortals, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void portalOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(EXTRA_PORTAL,  mPortals.get(i));
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {

                Log.d("MyApp","I am here");
                Portal updatedReminder = data.getParcelableExtra(MainActivity.EXTRA_PORTAL);
                mPortals.add(updatedReminder);
                updateUI();
            }else{
                System.out.println("fout bij result_ok "+requestCode+" - "+RESULT_OK);
            }
        }else{
            System.out.println("fout bij requestcode "+requestCode+" - "+REQUESTCODE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

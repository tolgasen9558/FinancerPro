package com.example.android.financerpro.BaseActivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.example.android.financerpro.Activities.ChartViewActivity;
import com.example.android.financerpro.Activities.CheckCalculatorActivity;
import com.example.android.financerpro.Activities.DebtTrackerActivity;
import com.example.android.financerpro.Activities.ExpenseTrackerActivity;
import com.example.android.financerpro.Activities.MainActivity;
import com.example.android.financerpro.R;


@SuppressLint("Registered")
public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;

    protected Toolbar toolbar;
    protected FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        contentFrame = findViewById(R.id.content_frame);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                hideSoftKeyKeyboard();
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void setContent(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, contentFrame);
    }

    private void hideSoftKeyKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Class loadClass = null;
        Class currentClass;
        Intent i;

        switch (id){
            case R.id.item_check_calculator_activity:
                loadClass = CheckCalculatorActivity.class;
                break;
            case R.id.item_expense_tracker_activity:
                loadClass = ExpenseTrackerActivity.class;
                break;
            case R.id.item_debt_tracker_activity:
                loadClass = DebtTrackerActivity.class;
                break;
            case R.id.item_chart_view_activity:
                loadClass = ChartViewActivity.class;
                break;
        }
        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        currentClass = this.getClass();
        if(loadClass != null && currentClass != loadClass){
            i = new Intent(getApplicationContext(), loadClass);
            startActivity(i);
            if (currentClass != MainActivity.class) {
                finish();
            }
        }
        return false;
    }
}

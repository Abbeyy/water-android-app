package assessment.c1714546.c1714546assessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import assessment.c1714546.c1714546assessment.settingsForApp.SettingsForUserActivity;
import assessment.c1714546.c1714546assessment.tipsAboutWaterContent.TipsAboutDrinkingWaterActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.WaterContentRecord;
import assessment.c1714546.c1714546assessment.viewWaterContent.ViewDailyWaterContentActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLout;
    private SharedPreferences date;
    private SharedPreferences.Editor editDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        date = getSharedPreferences("todaysDate", Context.MODE_PRIVATE);
        editDate = date.edit();

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        this.drawerLout = (DrawerLayout)this.findViewById(R.id.nav_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                this.drawerLout,
                myToolbar,
                R.string.open,
                R.string.closed);
        this.drawerLout.addDrawerListener(toggle);
        toggle.syncState();

        android.support.design.widget.NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        // Grabbing ImageView widgets to be
        // able to add a user's onClick to them.
        ImageView launchUpdateWaterContent = (ImageView)findViewById(R.id.water_content_home_button);
        launchUpdateWaterContent.setOnClickListener(this);
        ImageView launchViewDailyWaterContent = (ImageView)findViewById(R.id.daily_content_home_button);
        launchViewDailyWaterContent.setOnClickListener(this);
        ImageView launchTipsAboutWaterContent = (ImageView)findViewById(R.id.info_home_button);
        launchTipsAboutWaterContent.setOnClickListener(this);
        ImageView launchSettingsForUser = (ImageView)findViewById(R.id.settings_home_button);
        launchSettingsForUser.setOnClickListener(this);

        generateDate();
        Log.i("Todays date is: ", date.getString("today", "error"));
    }

    @Override
    public void onClick(View v) {
        int imageID = v.getId();

        switch (imageID) {
            case R.id.water_content_home_button :
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.daily_content_home_button :
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            case R.id.info_home_button :
                Intent launchTipsAboutWaterContentActivity = new Intent(this, TipsAboutDrinkingWaterActivity.class);
                startActivity(launchTipsAboutWaterContentActivity);
                break;
            case R.id.settings_home_button :
                Intent launchSettingsForUserActivity = new Intent(this, SettingsForUserActivity.class);
                startActivity(launchSettingsForUserActivity);
                break;
            default :
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int actionID = item.getItemId();

        switch (actionID) {
            case R.id.home_activity_icon :
                Toast.makeText(this, "Remaining on Home page...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_water_icon :
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.view_water_icon :
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            case R.id.tips_icon :
                Intent launchTipsAboutWaterContentActivity = new Intent(this, TipsAboutDrinkingWaterActivity.class);
                startActivity(launchTipsAboutWaterContentActivity);
                break;
            case R.id.settings_icon :
                Intent launchSettingsForUserActivity = new Intent(this, SettingsForUserActivity.class);
                startActivity(launchSettingsForUserActivity);
                break;
            default :
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();

        switch (itemID) {
            case R.id.home_title :
                Toast.makeText(this, "Remaining on Home page...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_water_content_title :
                Toast.makeText(this, "Launching update water content...", Toast.LENGTH_SHORT).show();
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.view_daily_water_content_title :
                Toast.makeText(this, "Launching view daily water content...", Toast.LENGTH_SHORT).show();
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            case R.id.tips_title :
                Toast.makeText(this, "Launching tips about water content...", Toast.LENGTH_SHORT).show();
                Intent launchTipsAboutWaterContentActivity = new Intent(this, TipsAboutDrinkingWaterActivity.class);
                startActivity(launchTipsAboutWaterContentActivity);
                break;
            case R.id.settings_title :
                Toast.makeText(this, "Launching settings...", Toast.LENGTH_SHORT).show();
                Intent launchSettingsForUserActivity = new Intent(this, SettingsForUserActivity.class);
                startActivity(launchSettingsForUserActivity);
                break;
            default :
                break;
        }

        drawerLout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void generateDate() {
        //Method is key for producing listview of user's
        //water content consumption for just today.

        Date dateToday = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String todaysDate = format.format(dateToday);

        String globalDateToday = date.getString("today", "error");

        if (globalDateToday == null | globalDateToday == "error") {
            editDate.putString("today", todaysDate);
            editDate.apply();
        } else {
            if (!globalDateToday.equals(todaysDate)) {
                editDate.clear();
                editDate.putString("today", todaysDate);
                editDate.apply();
                //reset the number of record objects for today.
                WaterContentRecord.numOfRecs = 0;
            }
            //else, all is well! same date!
        }
    }

}

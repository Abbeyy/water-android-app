package assessment.c1714546.c1714546assessment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
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


/**
 * Launching activity of application. User can
 * navigate to any other part of the application
 * from this page, and freely travel back to it.
 *
 * Created by c1714546 on 5/3/2018.
 *
 * @author Abbey Ross, 04/04/2018.
 * @version 1.0.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLout;
    private SharedPreferences date;
    private SharedPreferences.Editor editDate;
    private SharedPreferences todaysWaterHistory;
    private SharedPreferences.Editor editAddWaterHistory;

    private NotifBroadcastReceiver broadcastReceiver;
    private IntentFilter systemIntent;

    private AppCompatTextView helloUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        // Setup BroadcastReceiver
        this.broadcastReceiver = new NotifBroadcastReceiver();
        this.systemIntent = new IntentFilter(Intent.ACTION_POWER_CONNECTED);

        // Setup Shared Preferences and Editors.
        date = getSharedPreferences("todaysDate", Context.MODE_PRIVATE);
        editDate = date.edit();
        todaysWaterHistory = getSharedPreferences("waterHistory", Context.MODE_PRIVATE);
        editAddWaterHistory = todaysWaterHistory.edit();

        // Grab toolbar and implement.
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Setup navigation drawer.
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

        // Generate date in java.
        generateDate();

        helloUser = (AppCompatTextView)findViewById(R.id.hello_text_textview);
        displayUsersName();
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
                Toast.makeText(this, R.string.remain_home_page, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, R.string.remain_home_page, Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_water_content_title :
                Toast.makeText(this, R.string.launch_update_water, Toast.LENGTH_SHORT).show();
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.view_daily_water_content_title :
                Toast.makeText(this, R.string.launch_view_water, Toast.LENGTH_SHORT).show();
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            case R.id.tips_title :
                Toast.makeText(this, R.string.launch_tips, Toast.LENGTH_SHORT).show();
                Intent launchTipsAboutWaterContentActivity = new Intent(this, TipsAboutDrinkingWaterActivity.class);
                startActivity(launchTipsAboutWaterContentActivity);
                break;
            case R.id.settings_title :
                Toast.makeText(this, R.string.launch_settings, Toast.LENGTH_SHORT).show();
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
        // The purpose of this method is to generate
        // the date and compare this to the one
        // stored in Shared Prefs. If they match,
        // it is the same day and user's data
        // remains. Else, it is cleared because it's a
        // new day (daily water content app).

        Date dateToday = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String todaysDate = format.format(dateToday);

        String globalDateToday = date.getString("today", "error");

        if (globalDateToday == null | globalDateToday == "error") {
            // No date stored globally yet. Store it now.
            editDate.putString("today", todaysDate);
            editDate.apply();
        } else {
            if (!globalDateToday.equals(todaysDate)) {
                editDate.clear();
                editDate.putString("today", todaysDate);
                editDate.apply();
                //reset the number of record objects for today.
                WaterContentRecord.numOfRecs = 0;
                editAddWaterHistory.clear();
                editAddWaterHistory.apply();
            }
            // Else, same date. Nothing needs to be cleated.
        }
    }

    @Override
    protected void onPause() {
        // Stop receiver listening out.
        unregisterReceiver(this.broadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // Reintroduce receiver to listen out.
        registerReceiver(this.broadcastReceiver, this.systemIntent);
        super.onResume();
    }

    public void displayUsersName() {
        SharedPreferences nameOfUser = getSharedPreferences("usersName", Context.MODE_PRIVATE);
        String value = nameOfUser.getString("name", "error");
        if (!value.equals("error")) {
            this.helloUser.setText("Hello " + value +".");
        } else {
            this.helloUser.setEnabled(false);
        }
    }

}

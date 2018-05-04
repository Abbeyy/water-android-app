package assessment.c1714546.c1714546assessment.viewWaterContent;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import assessment.c1714546.c1714546assessment.HomeActivity;
import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.settingsForApp.SettingsForUserActivity;
import assessment.c1714546.c1714546assessment.tipsAboutWaterContent.TipsAboutDrinkingWaterActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.WaterContentRecord;

public class ViewDailyWaterContentActivity extends AppCompatActivity {
    private String timeNow;
    private SharedPreferences todaysWaterHistory;
    private AppCompatTextView genTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily_water_content);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        this.todaysWaterHistory = getSharedPreferences("waterHistory", Context.MODE_PRIVATE);

        int glassesDrunk = this.todaysWaterHistory.getInt("drunk", -1);

        genTime = (AppCompatTextView)findViewById(R.id.gen_time);
        generateTime();

        AppCompatTextView showConsumption = (AppCompatTextView)findViewById(R.id.show_consumption);
        displayConsumption(showConsumption, glassesDrunk);
    }

    // The below annotation rule should mean
    // this method works for devices using API level 26+.
    @TargetApi(26)
    public void generateTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss a");
        this.timeNow = currentTime.format(formattedTime);
        genTime.setText("Time:" + timeNow);
    }

    public void displayConsumption(AppCompatTextView showConsumption, int glassesDrunk) {
        if (glassesDrunk == -1) {
            showConsumption.setText(R.string.drunk_zero);
        }
        showConsumption.setText("You have drunk " + glassesDrunk + " glasses of water so far.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        generateTime();
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
                Intent launchHomeActivity = new Intent(this, HomeActivity.class);
                startActivity(launchHomeActivity);
                break;
            case R.id.update_water_icon :
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.view_water_icon :
                Toast.makeText(this, "Remaining on View Daily Water Content page...", Toast.LENGTH_SHORT).show();
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

}

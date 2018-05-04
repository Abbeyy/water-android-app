package assessment.c1714546.c1714546assessment.viewWaterContent;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import assessment.c1714546.c1714546assessment.R;
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

}

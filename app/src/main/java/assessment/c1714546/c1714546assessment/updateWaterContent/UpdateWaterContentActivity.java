package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.viewWaterContent.ViewDailyWaterContentActivity;

public class UpdateWaterContentActivity extends AppCompatActivity implements View.OnClickListener {
    private WaterContentDatabase wcd;
    private String timeNow;
    private AppCompatEditText getGlasses;
    private SharedPreferences todaysWaterHistory;
    private SharedPreferences.Editor editAddWaterHistory;
    private AppCompatButton submitBtn;
    private AppCompatButton viewActivityBtn;
    private List<WaterContentRecord> recordObjects = new ArrayList<WaterContentRecord>();
    private int totalGlassesDrunk;
    private AppCompatTextView genTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water_content);

        // Setup the database.
        this.wcd = Room.databaseBuilder(
                getApplicationContext(),
                WaterContentDatabase.class,
                "my_database").build();

        // Setup SharedPreferences.
        todaysWaterHistory = getSharedPreferences("waterHistory", Context.MODE_PRIVATE);
        editAddWaterHistory = todaysWaterHistory.edit();

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        genTime = (AppCompatTextView)findViewById(R.id.gen_time);
        generateTime();

        submitBtn = (AppCompatButton)findViewById(R.id.submit_btn);
        submitBtn.setEnabled(true);
        submitBtn.setOnClickListener(this);

        viewActivityBtn = (AppCompatButton)findViewById(R.id.launch_view_activity_btn);
        viewActivityBtn.setEnabled(false);
        viewActivityBtn.setOnClickListener(this);

        getGlasses = (AppCompatEditText)findViewById(R.id.get_glasses);
    }

    // The below annotation rule should mean
    // this method works for devices using API level 26+.
    @TargetApi(26)
    public void generateTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss a");
        this.timeNow = currentTime.format(formattedTime);
        this.genTime.setText("Time:" + timeNow);
    }

    @Override
    public void onClick(View v) {
        int btnId = v.getId();

        switch (btnId) {
            case R.id.submit_btn :
                //Disables button to ensure user can
                //only enter one entry per activity-load
                //due to the time being generated on
                //the loading of the activity.
                submitBtn.setEnabled(false);
                viewActivityBtn.setEnabled(true);

                //Use Room to communicate with SQLite DB
                //to store history of user's water content
                //consumption.
                final int numberOfGlasses = Integer.parseInt(this.getGlasses.getText().toString());
                final String time = this.timeNow;
                final WaterContentRecord record = new WaterContentRecord(numberOfGlasses, time);
                recordObjects.add(record);

                //Store the total number of glasses
                // drunk on sharedprefs for use in other activity...
                updateSharedPrefs(numberOfGlasses);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        wcd.waterContentDao().insertAll(record);

                        List<WaterContentRecord> databaseContents = wcd.waterContentDao().getAllWaterContentRecords();
                        for (WaterContentRecord aRecord : databaseContents) {
                            Log.i("DATABASE", "Record:" + aRecord.getId());
                            Log.i("DATABASE", "Record:" + aRecord.getTimeOfConsumption());
                            Log.i("DATABASE", "Record:" + aRecord.getNumberOfGlasses());
                        }
                    }
                });
                break;
            case R.id.launch_view_activity_btn :
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            default :
                break;
        }


    }

    public void updateSharedPrefs(int numberOfGlasses) {
        int result = todaysWaterHistory.getInt("drunk", -1);
        if (result == -1) {
            editAddWaterHistory.putInt("drunk", numberOfGlasses);
        } else {
            editAddWaterHistory.clear();
            editAddWaterHistory.putInt("drunk", result+numberOfGlasses);
        }
        editAddWaterHistory.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        generateTime();
    }

}

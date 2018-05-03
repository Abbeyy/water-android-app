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
    private SharedPreferences alreadyClicked;
    private SharedPreferences todaysWaterHistory;
    private SharedPreferences.Editor editAddWaterHistory;
    private AppCompatButton submitBtn;
    private AppCompatButton viewActivityBtn;
    private List<WaterContentRecord> recordObjects = new ArrayList<WaterContentRecord>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water_content);

        // Setup the database.
        this.wcd = Room.databaseBuilder(
                getApplicationContext(),
                WaterContentDatabase.class,
                "my_database").fallbackToDestructiveMigration().build();

        //.fallbackToDestructiveMigration()

        // Setup SharedPreferences.
        alreadyClicked = getPreferences(Context.MODE_PRIVATE);
        todaysWaterHistory = getSharedPreferences("waterHistory", Context.MODE_PRIVATE);
        editAddWaterHistory = todaysWaterHistory.edit();

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        AppCompatTextView genTime = (AppCompatTextView)findViewById(R.id.gen_time);
        generateTime(genTime);

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
    public void generateTime(AppCompatTextView genTime) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss a");
        this.timeNow = currentTime.format(formattedTime);
        genTime.setText("Time:" + timeNow);
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

                //Use SharedPrefs to store user's content from this entry.
                updateSharedPreferences(record, recordObjects.size());

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

    public void updateSharedPreferences(WaterContentRecord record, int numberOfRecords) {
        List<String> info = new ArrayList<String>();
        int counter = 1;

        String valueGlasses = todaysWaterHistory.getString("1glasses", "error");
        if (valueGlasses == "error") {
            //nothing yet stored today.
            editAddWaterHistory.putString(Integer.toString(counter)+"glasses", Integer.toString(record.getNumberOfGlasses()));
            editAddWaterHistory.putString(Integer.toString(counter)+"time", record.getTimeOfConsumption());
            editAddWaterHistory.apply();
        } else {
            //Retrieve whatever is in Shared Prefs.
            for (int r = 1; r == numberOfRecords; r++) {
                info.add(todaysWaterHistory.getString(Integer.toString(r)+"glasses", "error"));
                info.add(todaysWaterHistory.getString(Integer.toString(r)+"time", "error"));
            }

            //Write it back
            editAddWaterHistory.clear();
            editAddWaterHistory.apply();
            for (int e = 1; e == (numberOfRecords*2); e = e+2) {
                editAddWaterHistory.putString(Integer.toString(counter)+"glasses", info.get(e));
                editAddWaterHistory.putString(Integer.toString(counter)+"time", info.get(e+1));
                Log.i("LOOKY", todaysWaterHistory.getString(Integer.toString(counter)+"glasses", "error"));
                Log.i("LOOKY", todaysWaterHistory.getString(Integer.toString(counter)+"time", "error"));
                counter++;
            }
            editAddWaterHistory.apply();

            //Add extra info
            editAddWaterHistory.putString(Integer.toString(numberOfRecords)+"glasses", Integer.toString(record.getNumberOfGlasses()));
            editAddWaterHistory.putString(Integer.toString(numberOfRecords)+"time", record.getTimeOfConsumption());
            editAddWaterHistory.apply();
        }

    }

}

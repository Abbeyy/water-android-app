package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import assessment.c1714546.c1714546assessment.R;

public class UpdateWaterContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water_content);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        AppCompatTextView genTime = (AppCompatTextView)findViewById(R.id.gen_time);
        generateTime(genTime);
    }

    // The below annotation rule should mean
    // this method works for devices using API level 26+.
    @TargetApi(26)
    public void generateTime(AppCompatTextView genTime) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss a");
        genTime.setText("Time:" + currentTime.format(formattedTime));
    }
}

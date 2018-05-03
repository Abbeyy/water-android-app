package assessment.c1714546.c1714546assessment.viewWaterContent;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.WaterContentRecord;

public class ViewDailyWaterContentActivity extends AppCompatActivity {

    private SharedPreferences todaysWaterHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily_water_content);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        this.todaysWaterHistory = getSharedPreferences("waterHistory", Context.MODE_PRIVATE);

        int glassesDrunk = this.todaysWaterHistory.getInt("drunk", -1);
        Log.i("drunk: ", glassesDrunk + "!");

        //Display now...
    }

}

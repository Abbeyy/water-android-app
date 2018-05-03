package assessment.c1714546.c1714546assessment.viewWaterContent;

import android.content.Context;
import android.content.SharedPreferences;
//import android.support.v7.widget.ListViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.WaterContentRecord;

public class ViewDailyWaterContentActivity extends AppCompatActivity {
    private List<String> entries = new ArrayList<String>();
//    private ListViewCompat entriesListView;
    private ArrayAdapter<String> basicArrayAdapter;
    private SharedPreferences numberOfRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily_water_content);

        numberOfRecords = getSharedPreferences("numOfRecs", Context.MODE_PRIVATE);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        generateList();
//
//        this.entriesListView = findViewById(R.id.entries_listview);
//
//        this.basicArrayAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                this.entries
//        );
//
//        this.entriesListView.setAdapter(this.basicArrayAdapter);
//        this.entriesListView.setOnItemClickListener(this);
    }

    public void generateList() {
        int listSize = WaterContentRecord.numOfRecs;
        Log.i("NUM: ", listSize+"!");

        if (listSize > 0) {
            for (int r = 0; r<listSize; r++) {
                entries.add("Entry " + r);
            }
        }
    }

}

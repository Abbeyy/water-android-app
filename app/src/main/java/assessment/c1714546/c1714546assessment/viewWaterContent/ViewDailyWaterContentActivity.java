package assessment.c1714546.c1714546assessment.viewWaterContent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.WaterContentRecord;

public class ViewDailyWaterContentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<String> entries = new ArrayList<String>();
    private ListView entriesListView;
    private ArrayAdapter<String> basicArrayAdapter;
    private SharedPreferences numberOfRecords;
    private SharedPreferences entryClicked;
    private SharedPreferences.Editor editEntryClicked;
    private SharedPreferences waterHist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily_water_content);

        numberOfRecords = getSharedPreferences("numOfRecs", Context.MODE_PRIVATE);
        entryClicked = getSharedPreferences("entry", Context.MODE_PRIVATE);
        editEntryClicked = entryClicked.edit();
        waterHist = getSharedPreferences("waterHistory", Context.MODE_PRIVATE);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        generateList();

        this.entriesListView = findViewById(R.id.entries_listview);

        this.basicArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.entries
        );

        this.entriesListView.setAdapter(this.basicArrayAdapter);
        this.entriesListView.setOnItemClickListener(this);
    }

    public void generateList() {
        int listSize = WaterContentRecord.numOfRecs;
        Log.i("NUM: ", listSize+"!");

        if (listSize > 0) {
            for (int r = 0; r<listSize; r++) {
                entries.add("Entry " + r);
            }
        } else if (listSize == 0) {
            Toast.makeText(this, "Please make an entry before viewing your history...", Toast.LENGTH_SHORT).show();
            Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
            startActivity(launchUpdateWaterContentActivity);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = entryClicked.getString("record", "error");
        if ((value != null) && (value != "error")) {
            editEntryClicked.clear();
        }
        String recordNum = Integer.toString(position + 1);
        editEntryClicked.putString("recordId", recordNum);
        String glasses = waterHist.getString(recordNum+"glasses", "error");
        editEntryClicked.putString("glasses", glasses);
        String time = waterHist.getString(recordNum+"time", "error");
        editEntryClicked.putString("time", time);
        editEntryClicked.apply();

        Intent launchViewDailyWaterDetailsActivity = new Intent(this, ViewDailyWaterDetailsActivity.class);
        startActivity(launchViewDailyWaterDetailsActivity);
    }
}

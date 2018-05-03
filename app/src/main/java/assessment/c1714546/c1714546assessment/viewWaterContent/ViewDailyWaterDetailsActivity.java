package assessment.c1714546.c1714546assessment.viewWaterContent;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import assessment.c1714546.c1714546assessment.R;


public class ViewDailyWaterDetailsActivity extends AppCompatActivity {
    private List<String> details = new ArrayList<String>();
    private ListView detailsListView;
    private ArrayAdapter<String> basicArrayAdapter;
    private SharedPreferences entryClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily_water_details);

        this.entryClicked = getSharedPreferences("entry", Context.MODE_PRIVATE);

        generateDetails();

        this.detailsListView = findViewById(R.id.details_listview);

        this.basicArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.details
        );

        this.detailsListView.setAdapter(this.basicArrayAdapter);
    }

    public void generateDetails() {
        this.details.add(entryClicked.getString("recordId", "error"));
        this.details.add(entryClicked.getString("glasses", "error"));
        this.details.add(entryClicked.getString("time", "error"));
    }

}

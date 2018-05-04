package assessment.c1714546.c1714546assessment.settingsForApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import assessment.c1714546.c1714546assessment.R;

public class SettingsForUserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView settingsListView;
    private ArrayAdapter basicAdapter;
    private List<String> settings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_for_user);

        // Grab toolbar and implement.
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        generateSettings();

        this.settingsListView = this.findViewById(R.id.settings_list_view);
        this.basicAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.settings
        );
        this.settingsListView.setAdapter(this.basicAdapter);
        this.settingsListView.setOnItemClickListener(this);
    }

    public void generateSettings() {
        this.settings.add("Name");
        this.settings.add("Goal");
        this.settings.add("Privacy Policy");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "User clicked on item " + (position+1), Toast.LENGTH_SHORT).show();
    }
}

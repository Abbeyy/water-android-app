package assessment.c1714546.c1714546assessment.settingsForApp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class SettingsForUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_for_user);

        FragmentManager fragMgr = getSupportFragmentManager();
        FragmentTransaction fragTrns = fragMgr.beginTransaction();
        fragTrns.add(R.id.settings_fragment_container, new ListSettingsFragment()).commit();
    }

}

package assessment.c1714546.c1714546assessment.settingsForApp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import assessment.c1714546.c1714546assessment.HomeActivity;
import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.tipsAboutWaterContent.TipsAboutDrinkingWaterActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.viewWaterContent.ViewDailyWaterContentActivity;

public class SettingsForUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_for_user);

        // Grab toolbar and implement.
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FragmentManager fragMgr = getSupportFragmentManager();
        FragmentTransaction fragTrns = fragMgr.beginTransaction();
        fragTrns.add(R.id.settings_fragment_container, new ListSettingsFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int actionID = item.getItemId();

        switch (actionID) {
            case R.id.home_activity_icon :
                Intent launchHomeActivity = new Intent(this, HomeActivity.class);
                startActivity(launchHomeActivity);
                break;
            case R.id.update_water_icon :
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.view_water_icon :
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            case R.id.tips_icon :
                Intent launchTipsAboutWaterContentActivity = new Intent(this, TipsAboutDrinkingWaterActivity.class);
                startActivity(launchTipsAboutWaterContentActivity);
                break;
            case R.id.settings_icon :
                Toast.makeText(this, R.string.remain_settings, Toast.LENGTH_SHORT).show();
                break;
            default :
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

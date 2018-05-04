package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import assessment.c1714546.c1714546assessment.HomeActivity;
import assessment.c1714546.c1714546assessment.R;
import assessment.c1714546.c1714546assessment.settingsForApp.SettingsForUserActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.viewWaterContent.ViewDailyWaterContentActivity;

/**
 * This activity inflates fragments dependent on
 * tab functionality. 
 *
 * Created by c1714546 on 5/3/2018.
 *
 * @author Abbey Ross, 04/04/2018.
 * @version 1.0.
 */
public class TipsAboutDrinkingWaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_about_drinking_water);

        // Grab toolbar and implement.
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Needed for backwards compatibility on android's
        // back button for tabs - TipsFragOne and Two.
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        // Replace with the value under MainTabFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.tips_fragment_container, new MainTabFragment())
                .commit();

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
                Toast.makeText(this, "Remaining on Tips About Water Content tabs...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_icon :
                Intent launchSettingsForUserActivity = new Intent(this, SettingsForUserActivity.class);
                startActivity(launchSettingsForUserActivity);
                break;
            default :
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

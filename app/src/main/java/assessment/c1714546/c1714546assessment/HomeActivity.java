package assessment.c1714546.c1714546assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import assessment.c1714546.c1714546assessment.settingsForApp.SettingsForUserActivity;
import assessment.c1714546.c1714546assessment.tipsAboutWaterContent.TipsAboutDrinkingWaterActivity;
import assessment.c1714546.c1714546assessment.updateWaterContent.UpdateWaterContentActivity;
import assessment.c1714546.c1714546assessment.viewWaterContent.ViewDailyWaterContentActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Grabbing ImageView widgets to be
        // able to add a user's onClick to them.
        ImageView launchUpdateWaterContent = (ImageView)findViewById(R.id.water_content_home_button);
        launchUpdateWaterContent.setOnClickListener(this);
        ImageView launchViewDailyWaterContent = (ImageView)findViewById(R.id.daily_content_home_button);
        launchViewDailyWaterContent.setOnClickListener(this);
        ImageView launchTipsAboutWaterContent = (ImageView)findViewById(R.id.info_home_button);
        launchTipsAboutWaterContent.setOnClickListener(this);
        ImageView launchSettingsForUser = (ImageView)findViewById(R.id.settings_home_button);
        launchSettingsForUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int imageID = v.getId();

        switch (imageID) {
            case R.id.water_content_home_button :
                Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
                startActivity(launchUpdateWaterContentActivity);
                break;
            case R.id.daily_content_home_button :
                Intent launchViewDailyWaterContentActivity = new Intent(this, ViewDailyWaterContentActivity.class);
                startActivity(launchViewDailyWaterContentActivity);
                break;
            case R.id.info_home_button :
                Intent launchTipsAboutWaterContentActivity = new Intent(this, TipsAboutDrinkingWaterActivity.class);
                startActivity(launchTipsAboutWaterContentActivity);
                break;
            case R.id.settings_home_button :
                Intent launchSettingsForUserActivity = new Intent(this, SettingsForUserActivity.class);
                startActivity(launchSettingsForUserActivity);
                break;
            default :
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

}

package assessment.c1714546.c1714546assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import assessment.c1714546.c1714546assessment.waterContent.UpdateWaterContentActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView launchUpdateWaterContent = (ImageView)findViewById(R.id.water_content_home_button);
        launchUpdateWaterContent.setOnClickListener(this);
//        ImageView launchViewDailyWaterContent = (ImageView)
    }

    @Override
    public void onClick(View v) {
        Intent launchUpdateWaterContentActivity = new Intent(this, UpdateWaterContentActivity.class);
        startActivity(launchUpdateWaterContentActivity);
    }
}

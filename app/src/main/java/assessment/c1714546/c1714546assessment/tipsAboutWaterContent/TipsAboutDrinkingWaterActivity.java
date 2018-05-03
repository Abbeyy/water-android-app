package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import assessment.c1714546.c1714546assessment.R;

public class TipsAboutDrinkingWaterActivity extends AppCompatActivity {
    private FragmentManager fragManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_about_drinking_water);

        this.fragManager = getSupportFragmentManager();
        this.fragmentTransaction = this.fragManager.beginTransaction();
        this.fragmentTransaction.add(R.id.tips_fragment_container, new TipsFragmentOne()).commit();
    }
}

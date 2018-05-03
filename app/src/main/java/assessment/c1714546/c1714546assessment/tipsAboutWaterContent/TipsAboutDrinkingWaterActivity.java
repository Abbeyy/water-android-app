package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import assessment.c1714546.c1714546assessment.R;

public class TipsAboutDrinkingWaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_about_drinking_water);

        // Needed for backwards compatibility on android's
        // back button for tabs - TipsFragOne and Two.
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        // Replace with the value under MainTabFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.tips_fragment_container, new MainTabFragment())
                .commit();

    }
}

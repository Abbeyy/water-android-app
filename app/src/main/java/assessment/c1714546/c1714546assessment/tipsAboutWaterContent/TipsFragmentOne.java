package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assessment.c1714546.c1714546assessment.R;

/**
 * Created by c1714546 on 5/3/2018.
 */

public class TipsFragmentOne extends Fragment {

    public TipsFragmentOne() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tips_fragment_one_layout, container, false);


        return v;
    }

}

package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assessment.c1714546.c1714546assessment.R;

/**
 * Created by c1714546 on 5/3/2018.
 */

public class MainTabFragment extends Fragment {

    public MainTabFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabs, container, false);

        ViewPager pager = (ViewPager)v.findViewById(R.id.view_pager);
        String[] tabTitles = new String[]{"Tabs Info", "Tabs Code"};

        FragmentViewPagerAdapter fvpa = new FragmentViewPagerAdapter(
                getActivity().getSupportFragmentManager(),
                tabTitles
        );

        pager.setAdapter(fvpa);

        TabLayout tLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        tLayout.setupWithViewPager(pager);

        return v;
    }

    public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
        private String[] titlesOfTabs;

        public FragmentViewPagerAdapter(FragmentManager fragManager, String[] titlesOfTabs) {
            super(fragManager);
            this.titlesOfTabs = titlesOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0 :
                    return new TipsFragmentOne();
                case 1 :
                    return new TipsFragmentTwo();
                default :
                    return new TipsFragmentOne();
            }
        }

        @Override
        public int getCount() {
            return this.titlesOfTabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlesOfTabs[position];
        }

    }

}

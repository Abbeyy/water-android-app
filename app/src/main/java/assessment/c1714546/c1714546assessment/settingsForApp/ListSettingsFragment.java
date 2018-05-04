package assessment.c1714546.c1714546assessment.settingsForApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import assessment.c1714546.c1714546assessment.R;

/**
 * Created by c1714546 on 5/4/2018.
 */

public class ListSettingsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView settingsListView;
    private ArrayAdapter basicAdapter;
    private List<String> settings = new ArrayList<String>(Arrays.asList("Name", "Goals", "Privacy Policy"));
    private FragmentManager fragMgr;
    private FragmentTransaction fragTrns;

    public ListSettingsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_list, container, false);

        // Populate ArrayAdapter with necessary info.
        this.settingsListView = v.findViewById(R.id.settings_list_view);
        this.basicAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                this.settings
        );

        // Apply adapter and it's info to ListView.
        this.settingsListView.setAdapter(this.basicAdapter);
        // Set onItemClickListener for items in Listview.
        this.settingsListView.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Method replaces fragment inflated by Activity
        // dependent on what list item the user clicked on.

        this.fragMgr = getActivity().getSupportFragmentManager();
        this.fragTrns = this.fragMgr.beginTransaction();

        switch (position) {
            case 0 :
                NameSettingsFragment nameFrag = new NameSettingsFragment();
                Toast.makeText(getActivity(), R.string.user_clicked_name, Toast.LENGTH_SHORT).show();
                this.fragTrns.replace(R.id.settings_fragment_container, nameFrag).addToBackStack(NameSettingsFragment.class.toString()).commit();
                break;
            case 1 :
                GoalSettingsFragment goalFrag = new GoalSettingsFragment();
                Toast.makeText(getActivity(), R.string.user_clicked_goal, Toast.LENGTH_SHORT).show();
                this.fragTrns.replace(R.id.settings_fragment_container, goalFrag).addToBackStack(GoalSettingsFragment.class.toString()).commit();
                break;
            case 2 :
                PrivacyPolicyFragment privFrag = new PrivacyPolicyFragment();
                Toast.makeText(getActivity(), R.string.user_clicked_privacy, Toast.LENGTH_SHORT).show();
                this.fragTrns.replace(R.id.settings_fragment_container, privFrag).addToBackStack(PrivacyPolicyFragment.class.toString()).commit();
                break;
            default :
                break;
        }
    }
}

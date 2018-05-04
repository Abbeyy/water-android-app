package assessment.c1714546.c1714546assessment.settingsForApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import assessment.c1714546.c1714546assessment.HomeActivity;
import assessment.c1714546.c1714546assessment.R;

/**
 * Created by c1714546 on 5/4/2018.
 */

public class NameSettingsFragment extends Fragment implements View.OnLongClickListener, View.OnClickListener {
    private AppCompatEditText usersName;
    private SharedPreferences nameOfUser;
    private SharedPreferences.Editor editName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_name_settings, container, false);

        nameOfUser = getActivity().getSharedPreferences("usersName", Context.MODE_PRIVATE);
        editName = nameOfUser.edit();

        usersName = (AppCompatEditText)v.findViewById(R.id.users_name);

        AppCompatButton replaceBtn = (AppCompatButton)v.findViewById(R.id.replace_name_btn);
        replaceBtn.setOnClickListener(this);
        AppCompatButton resetBtn = (AppCompatButton)v.findViewById(R.id.reset_name_btn);
        resetBtn.setOnLongClickListener(this);

        return v;
    }

    @Override
    public boolean onLongClick(View v) {
        usersName.setText("");
        return false;
    }

    @Override
    public void onClick(View v) {
        editName.clear();
        editName.putString("name", usersName.getText().toString());
        editName.apply();
        Toast.makeText(getActivity(), "Name replaced.", Toast.LENGTH_SHORT).show();
        Intent launchHomeActivity = new Intent(getActivity(), HomeActivity.class);
        startActivity(launchHomeActivity);
    }
}

package com.example.saravananthangamari.moviemanager.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.activity.MainActivity;
import com.example.saravananthangamari.moviemanager.models.UserDetails;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    Gson gson = new Gson();
    @BindView(R.id.textView_email)
    TextView textView_email;
    @BindView(R.id.first_name)
    TextInputLayout first_name;
    @BindView(R.id.last_name)
    TextInputLayout last_name;
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.save)
    Button save;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this, v);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.FILE_NAME), 0);
        UserDetails user = gson.fromJson(sharedPreferences.getString(MainActivity.user_email, null), UserDetails.class);
        textView_email.setText(user.getEmailId());
        first_name.getEditText().setText(user.getFirstName());
        first_name.setEnabled(false);
        last_name.getEditText().setText(user.getLastName());
        last_name.setEnabled(false);


        return v;
    }

    @OnClick({R.id.edit, R.id.save})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.edit) {
            first_name.setEnabled(true);
            last_name.setEnabled(true);
            save.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
        } else if (id == R.id.save) {
            save.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.FILE_NAME), 0);
            UserDetails user = gson.fromJson(sharedPreferences.getString(MainActivity.user_email, null), UserDetails.class);
            SharedPreferences.Editor edited=sharedPreferences.edit();
            user.setFirstName(first_name.getEditText().getText().toString());
            user.setLastName(last_name.getEditText().getText().toString());
            edited.putString(MainActivity.user_email,gson.toJson(user));
            first_name.setEnabled(false);
            last_name.setEnabled(false);
        }
    }
}

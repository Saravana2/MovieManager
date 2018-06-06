package com.example.saravananthangamari.moviemanager.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {


    @BindView(R.id.textView_email)
    TextView textView_email;
    @BindView(R.id.first_name)
    TextInputLayout first_name;
    @BindView(R.id.last_name)
    TextInputLayout last_name;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this,v);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(MainActivity.user_email,0);

        textView_email.setText(sharedPreferences.getString(getString(R.string.EMAIL_ID),null));
        first_name.getEditText().setText(sharedPreferences.getString(getString(R.string.FIRST_NAME),null));
        first_name.setEnabled(false);
        last_name.getEditText().setText(sharedPreferences.getString(getString(R.string.LAST_NAME),null));
        last_name.setEnabled(false);
        return v;
    }

}

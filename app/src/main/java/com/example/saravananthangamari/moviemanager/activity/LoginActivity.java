package com.example.saravananthangamari.moviemanager.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.fragment.LoginFragment;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showfragment(LoginFragment.class);
    }
     public void showfragment(Class fragment){
        Fragment fragment1=null;
        try {
            fragment1=(Fragment)fragment.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.login_frame_id,fragment1)
                .commit();
    }
}

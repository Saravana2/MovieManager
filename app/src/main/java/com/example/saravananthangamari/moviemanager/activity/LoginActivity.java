package com.example.saravananthangamari.moviemanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.FILE_NAMES),0);
        if(sharedPreferences.contains(getString(R.string.LAST_USER))){
            SharedPreferences userPref=getSharedPreferences(sharedPreferences.getString(getString(R.string.LAST_USER),null),0);
            if(userPref.getBoolean(getString(R.string.LOGIN_STATUS),false)){
                gotoMovieManager(sharedPreferences.getString(getString(R.string.LAST_USER),null));
            }else{
                showfragment(LoginFragment.class);
            }
        }else{
            showfragment(LoginFragment.class);
        }
    }

    private void gotoMovieManager(String emailid){
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("user_email",emailid);
        startActivity(intent);
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

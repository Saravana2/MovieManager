package com.example.saravananthangamari.moviemanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.fragment.*;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
public static String user_email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        Bundle info = getIntent().getExtras();
        if(info!=null){
            user_email=(String)info.getSerializable("user_email");
        }
        SharedPreferences s=getSharedPreferences(user_email,0);
        SharedPreferences.Editor editor=s.edit();
        editor.putBoolean(getString(R.string.LOGIN_STATUS),true);
        editor.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View vi= navigationView.getHeaderView(0);
        TextView username=(TextView)vi.findViewById(R.id.username);
        TextView userEmail=(TextView)vi.findViewById(R.id.userEmail);
        username.setText(s.getString(getString(R.string.FIRST_NAME),null)+" "+s.getString(getString(R.string.LAST_NAME),null));
        userEmail.setText(s.getString(getString(R.string.EMAIL_ID),null));


        showfragment(NowPlayingFragment.class);
    }



    public String getSharedPreferencesValue(String user_email, String fieldName){
        SharedPreferences preferences=this.getSharedPreferences(user_email,0);
        return preferences.getString(fieldName,null);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            Class fragment=null;
        if (id == R.id.nav_nowplaying) {
            fragment= NowPlayingFragment.class;
            showfragment(fragment);
        } else if (id == R.id.nav_upcoming) {
            fragment=UpcomingFragment.class;
            showfragment(fragment);
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_profile) {
            fragment=MyProfileFragment.class;
            showfragment(fragment);
            /*Fragment fragment1=null;
            try {
                 fragment1=(Fragment) MyProfileFragment.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_id,fragment1).commit();
            */
        } else if (id == R.id.nav_logout) {
            SharedPreferences.Editor s=getSharedPreferences(user_email,0).edit();
            s.putBoolean(getString(R.string.LOGIN_STATUS),false);
            s.commit();
            gotoLoginActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoLoginActivity() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void showfragment(Class fragment){
        Fragment fragment1=null;
        try {
            fragment1=(Fragment)fragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_id,fragment1)
                .commit();
    }
}

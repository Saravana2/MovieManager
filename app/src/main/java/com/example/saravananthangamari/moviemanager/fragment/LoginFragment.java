package com.example.saravananthangamari.moviemanager.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.activity.LoginActivity;
import com.example.saravananthangamari.moviemanager.activity.MainActivity;
import com.example.saravananthangamari.moviemanager.models.UserDetails;
import com.google.gson.Gson;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
@BindView(R.id.user_name)
TextInputLayout u_name;
@BindView(R.id.password)
TextInputLayout pwd;
Button login_btn1;
TextView tvcreate;

    public LoginFragment() {
        // Required empty public constructor
    }

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   // Toast.makeText(getContext(),"hifff",Toast.LENGTH_LONG).show();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        login_btn1=(Button) view.findViewById(R.id.login_btn);
        tvcreate=(TextView)view.findViewById(R.id.tvcreate);

        tvcreate.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v)
            {
        //Toast.makeText(getContext(),"inside",Toast.LENGTH_LONG).show();
                gotoSignUp();
            }});

            login_btn1.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v)
            {
                Context context=getActivity();
                SharedPreferences userPref=context.getSharedPreferences(getString(R.string.FILE_NAME),0);
               // Toast.makeText(getContext(),FileNames.getStringSet(getString(R.string.FILE_LIST),null).toString(),Toast.LENGTH_LONG).show();
                if(userPref.contains(u_name.getEditText().getText().toString())){
                    String userdetails=userPref.getString(u_name.getEditText().getText().toString(),null);
                    if(userdetails!=null) {
                        Gson gson=new Gson();
                        UserDetails user =gson.fromJson(userdetails,UserDetails.class);
                        if (pwd.getEditText().getText().toString().equals(user.getPassword())) {
                            SharedPreferences.Editor edit=userPref.edit();
                            edit.putString(getString(R.string.LAST_USER), u_name.getEditText().getText().toString());
                            edit.commit();
                            gotoMovieManager(u_name.getEditText().getText().toString());
                        } else {
                            Toast.makeText(getContext(), "password wrong", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "SignUp again", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getContext(),"SignUp First",Toast.LENGTH_LONG).show();
                }

            }});

        return view;
    }
    private void gotoMovieManager(String emailid){
        Intent intent=new Intent(getContext(),MainActivity.class);
        intent.putExtra("user_email",emailid);
        getContext().startActivity(intent);
    }

private void gotoSignUp(){
    Fragment fragment=null;
    try {
        fragment=(Fragment)SignUpFragment.class.newInstance();
    } catch (java.lang.InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace(); }
    FragmentManager fragmentManager=getFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.login_frame_id,fragment).addToBackStack(null).commit();
}


}

package com.example.saravananthangamari.moviemanager.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
@BindView(R.id.user_name)
TextInputEditText u_name;
@BindView(R.id.password)
TextInputEditText pwd;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);

        login_btn1=(Button) view.findViewById(R.id.login_btn);
        tvcreate=(TextView)view.findViewById(R.id.tvcreate);

        tvcreate.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v)
            {
        //Toast.makeText(getContext(),"inside",Toast.LENGTH_LONG).show();
                Fragment fragment=null;
                try {
                     fragment=(Fragment)SignUpFragment.class.newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); }
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.login_frame_id,fragment).addToBackStack(null).commit();
            }});

        login_btn1.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v)
            {

            }});

        return view;
    }




}

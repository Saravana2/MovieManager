package com.example.saravananthangamari.moviemanager.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saravananthangamari.moviemanager.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    Button signUp;
    TextView hv_account;
    @BindView(R.id.first_name)
    TextInputLayout first_name;
    @BindView(R.id.last_name)
    TextInputLayout last_name;
    @BindView(R.id.user_name)
    TextInputLayout user_name;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.c_password)
    TextInputLayout c_password;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,view);
        signUp = (Button) view.findViewById(R.id.signup_btn);
        hv_account = (TextView) view.findViewById(R.id.hv_account);

        first_name.getEditText().addTextChangedListener(generalTextWatcher);
        last_name.getEditText().addTextChangedListener(generalTextWatcher);

        hv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"inside",Toast.LENGTH_LONG).show();
                Fragment fragment = null;
                try {
                    fragment = (Fragment) LoginFragment.class.newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.login_frame_id, fragment).commit();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private TextWatcher generalTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }
        @Override
        public void afterTextChanged(Editable s) {
            if(s.hashCode()==first_name.getEditText().getText().hashCode()){
                if(checkName(first_name.getEditText().getText().toString())){
                    first_name.setErrorEnabled(false);
                }else{
                    first_name.setErrorEnabled(true);
                    first_name.setError(getString(R.string.e_name));
                }
            }else if(s.hashCode()==last_name.getEditText().getText().hashCode()){
                if(checkName(last_name.getEditText().getText().toString())){
                    last_name.setErrorEnabled(false);
                }else{
                    last_name.setErrorEnabled(true);
                    last_name.setError(getString(R.string.e_name));
                }

            }else if(s.hashCode()==user_name.getEditText().getText().hashCode()){

            }else if(s.hashCode()==email.getEditText().getText().hashCode()){

            }else if(s.hashCode()==password.getEditText().getText().hashCode()){

            }else if(s.hashCode()==c_password.getEditText().getText().hashCode()){

            }
        }
    };
    private boolean checkName(String text){
     boolean flag=false;
        if(text.length()>0){
        if(Pattern.matches("[a-zA-Z]+",text)){
            flag=true;
        }else{
            flag=false;
        }
        }else{
            flag=false;
        }
     return flag;
    }

}

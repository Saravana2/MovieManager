package com.example.saravananthangamari.moviemanager.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.HashSet;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    boolean firstName=false,lastName=false,emailId=false,userName=false,pwd_flag=false,c_pwd_flag=false;


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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,view);
        signUp = (Button) view.findViewById(R.id.signup_btn);
        hv_account = (TextView) view.findViewById(R.id.hv_account);

        first_name.getEditText().addTextChangedListener(generalTextWatcher);
        last_name.getEditText().addTextChangedListener(generalTextWatcher);
        user_name.getEditText().addTextChangedListener(generalTextWatcher);
        email.getEditText().addTextChangedListener(generalTextWatcher);
        password.getEditText().addTextChangedListener(generalTextWatcher);
        c_password.getEditText().addTextChangedListener(generalTextWatcher);

        hv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"inside",Toast.LENGTH_LONG).show();
               gotoLogin();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firstName && lastName && emailId && userName && pwd_flag && c_pwd_flag){
                    Context context=getActivity();

SharedPreferences pref=context.getSharedPreferences(getString(R.string.FILE_NAMES),0);
SharedPreferences.Editor edit=pref.edit();
HashSet<String> set= (HashSet<String>) pref.getStringSet(getString(R.string.FILE_LIST),new HashSet<String>());
set.add(email.getEditText().getText().toString());
edit.putStringSet(getString(R.string.FILE_LIST),set);
edit.commit();
                    SharedPreferences preferences=context.getSharedPreferences(email.getEditText().getText().toString(),0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean(getString(R.string.LOGIN_STATUS),false);
                    editor.putString(getString(R.string.FIRST_NAME),first_name.getEditText().getText().toString());
                    editor.putString(getString(R.string.LAST_NAME),last_name.getEditText().getText().toString());
                    editor.putString(getString(R.string.EMAIL_ID),email.getEditText().getText().toString());
                    editor.putString(getString(R.string.USER_NAME),user_name.getEditText().getText().toString());
                    editor.putString(getString(R.string.PASSWORD),password.getEditText().getText().toString());
                    editor.putString(getString(R.string.C_PASSWORD),c_password.getEditText().getText().toString());

                    editor.commit();
                    gotoLogin();

                }else{
                    Toast.makeText(getContext(),"Please fill all the details",Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }

    private void gotoLogin(){
        Fragment fragment=null;
        try {
            fragment=(Fragment)LoginFragment.class.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace(); }
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.login_frame_id,fragment).commit();
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
                    firstName=true;
                }else{
                    first_name.setErrorEnabled(true);
                    first_name.setError(getString(R.string.e_name));
                    firstName=false;
                }
            }else if(s.hashCode()==last_name.getEditText().getText().hashCode()){
                if(checkName(last_name.getEditText().getText().toString())){
                    lastName=true;
                    last_name.setErrorEnabled(false);
                }else{
                    lastName=false;
                    last_name.setErrorEnabled(true);
                    last_name.setError(getString(R.string.e_name));
                }
            }else if(s.hashCode()==user_name.getEditText().getText().hashCode()){
                if(user_name.getEditText().getText().length()>0){
                    user_name.setErrorEnabled(false);
                    userName=true;
                }else {
                    user_name.setErrorEnabled(true);
                    user_name.setError(getString(R.string.e_username));
                    userName = false;
                }

            }else if(s.hashCode()==email.getEditText().getText().hashCode()){
                if(checkEmail(email.getEditText().getText().toString())){
                    email.setErrorEnabled(false);
                    emailId=true;
                }else{
                    emailId=false;
                    email.setErrorEnabled(true);
                    email.setError(getString(R.string.e_email));
                }

            }else if(s.hashCode()==password.getEditText().getText().hashCode()){
                if(password.getEditText().getText().length()>0){
                    password.setErrorEnabled(false);
                    pwd_flag=true;
                }else {
                    password.setErrorEnabled(true);
                    password.setError(getString(R.string.e_pwd_null));
                    pwd_flag = false;
                }

            }else if(s.hashCode()==c_password.getEditText().getText().hashCode()){
                if(checkPassword(password.getEditText().getText().toString(),c_password.getEditText().getText().toString())){
                    c_password.setErrorEnabled(false);
                    c_pwd_flag=true;
                }else{
                    c_pwd_flag=false;
                    c_password.setErrorEnabled(true);
                    c_password.setError(getString(R.string.e_pwdMisMatch));
                }
            }
        }
    };
    private boolean checkName(String text){
     boolean flag=false;
        if(text.length()>0){
        if(Pattern.matches("[a-zA-Z]+",text)){
            flag=true;
        }
        }
     return flag;
    }


    private boolean checkEmail(String text){
        boolean flag=false;
if(text.length()>0){
if(Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",text)){
flag=true;
    }
}
return flag;
    }
private boolean checkPassword(String pwd1,String pwd2){
        boolean flag=false;
        if(pwd1.length()==pwd2.length()){
            if(pwd1.equals(pwd2)){
                flag=true;
            }
        }

        return flag;
}

}

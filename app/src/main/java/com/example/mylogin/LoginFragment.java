package com.example.mylogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {
    Button buttonLogin,buttonRegister;
    EditText email, password;
    CallbackFragment callbackFragment;
    String userName,pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public LoginFragment() {
    }

    @Override
    public void onAttach(Context context) {
        sharedPreferences=context.getSharedPreferences("userfile",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_login, container, false);
        email= view.findViewById(R.id.et_email);
        password=view.findViewById(R.id.et_password);

        buttonLogin=view.findViewById(R.id.btn_login);
        buttonRegister=view.findViewById(R.id.btn_register);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=email.getText().toString();
                pass=password.getText().toString();

                String uName,uPass;
                uName=sharedPreferences.getString("userName",null);
                uPass=sharedPreferences.getString("pass",null);
            if (userName.equals(uName) && pass.equals(uPass)){
                Toast.makeText(getContext(),"Login",Toast.LENGTH_SHORT).show();
                     }
            else {
                Toast.makeText(getContext(),"Login failed",Toast.LENGTH_SHORT).show();
            }

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          if (callbackFragment!=null){
              callbackFragment.changeFragment();

            }
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }
}

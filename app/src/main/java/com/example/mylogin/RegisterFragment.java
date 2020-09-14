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


public class RegisterFragment extends Fragment {
    Button buttonRegister;
    EditText etName,etEmail, etPassword;
    CallbackFragment callbackFragment;
    String userName,name,pass;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(Context context) {
        SharedPreferences sharedPreferences=context.getSharedPreferences("userfile",Context.MODE_PRIVATE);
         editor=sharedPreferences.edit();
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_register, container, false);
        etName= view.findViewById(R.id.et_name);
        etEmail= view.findViewById(R.id.et_email);
        etPassword=view.findViewById(R.id.et_password);


        buttonRegister=view.findViewById(R.id.btn_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etName.getText().toString();
                userName=etEmail.getText().toString();
                pass=etPassword.getText().toString();

                editor.putString("name",name);
                editor.putString("userName",userName);
                editor.putString("pass",pass);
                editor.apply();
                Toast.makeText(getContext(),"Register",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

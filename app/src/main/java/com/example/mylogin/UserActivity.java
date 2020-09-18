package com.example.mylogin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    DBHelper myDb;
    EditText editId, editName, editAge, editPlace, editDesignation;
    Button btnAdd;
    RecyclerView recyclerView;
    FloatingActionButton actionButton;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        myDb = new DBHelper(this);
        editId = findViewById(R.id.et_id);
        editName = findViewById(R.id.et_name);
        editAge = findViewById(R.id.et_age);
        editPlace = findViewById(R.id.et_place);
        editDesignation = findViewById(R.id.et_Designation);
        actionButton = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recycler_view);
        displayNotes();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });




    }
    public void displayNotes () {
            arrayList = new ArrayList<>(myDb.getAllData());
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            NotesAdapter adapter = new NotesAdapter(getApplicationContext(), this, arrayList);
            recyclerView.setAdapter(adapter);
        }
        public void showDialog () {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            dialog.setContentView(R.layout.activity_user);
            params.copyFrom(dialog.getWindow().getAttributes());
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.gravity = Gravity.CENTER;
            dialog.getWindow().setAttributes(params);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            btnAdd = dialog.findViewById(R.id.btn_add);
            editId = dialog.findViewById(R.id.et_id);
            editName = dialog.findViewById(R.id.et_name);
            editAge = dialog.findViewById(R.id.et_age);
            editPlace = dialog.findViewById(R.id.et_place);
            editDesignation = dialog.findViewById(R.id.et_Designation);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = myDb.insertData(editId.getText().toString(), editName.getText().toString(),
                            editAge.getText().toString(),
                            editPlace.getText().toString(), editDesignation.getText().toString());
                    Log.i("Log", "insert" + isInserted);
                    if (isInserted == true) {
                        dialog.cancel();
                        displayNotes();
                        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Employee id already exist", Toast.LENGTH_LONG).show();
                    }

                }
            });


        }

        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){

            int id = item.getItemId();
            if (id == R.id.log_out) {
                Intent logoutintent = new Intent(this, MainActivity.class);
                startActivity(logoutintent);
                Log.i("Log", "logout:" + logoutintent);
                SharedPreferences loginSharedPreferences;
                loginSharedPreferences = getSharedPreferences(
                        "login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = loginSharedPreferences.edit();
                editor.putBoolean("userlogin", false);
                editor.clear();
                editor.apply();
                finish();
                return true;
            }
            if (id == R.id.profile) {

                Intent profile = new Intent(this, ProfileImage.class);
                startActivity(profile);

            }
            return super.onOptionsItemSelected(item);
        }


}

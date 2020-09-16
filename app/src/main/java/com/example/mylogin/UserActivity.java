package com.example.mylogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {
    DBHelper myDb;
    EditText editId, editName, editAge, editPlace, editDesignation;
    Button btnAdd, btnDelete, btnUpdate, btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        myDb = new DBHelper(this);
        editId = findViewById(R.id.et_id);
        editName = findViewById(R.id.et_name);
        editAge = findViewById(R.id.et_age);
        editPlace = findViewById(R.id.et_place);
        editDesignation = findViewById(R.id.et_Designation);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        btnGet = findViewById(R.id.btn_get);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editId.getText().toString(), editName.getText().toString(),
                        editAge.getText().toString(),
                        editPlace.getText().toString(), editDesignation.getText().toString());
                Log.i("Log", "insert" + isInserted);
                if (isInserted == true)
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Data not Inserted", Toast.LENGTH_LONG).show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editId.getText().toString(), editName.getText().toString(),
                        editAge.getText().toString(),
                        editPlace.getText().toString(), editDesignation.getText().toString());
                Log.i("Log", "update" + isUpdate);
                if (isUpdate == true)
                    Toast.makeText(getApplicationContext(), "Data Update", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Data not Updated", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editId.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Data not Deleted", Toast.LENGTH_LONG).show();
            }
        });


        btnGet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Age :" + res.getString(2) + "\n");
                            buffer.append("place :" + res.getString(3) + "\n");
                            buffer.append("Designation :" + res.getString(4) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );


    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.log_out) {
            Intent logoutintent = new Intent(this, MainActivity.class);
            startActivity(logoutintent);
            SharedPreferences loginSharedPreferences;
            loginSharedPreferences = getSharedPreferences(
                    "userfile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loginSharedPreferences.edit();
            editor.putBoolean("userlogin", false);
            editor.apply();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

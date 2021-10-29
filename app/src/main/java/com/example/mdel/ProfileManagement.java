package com.example.mdel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mdel.database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,pass;
    Button add,updateProfile;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.user_pm);
        dob = findViewById(R.id.dob);
        pass = findViewById(R.id.pass_pm);
        add = findViewById(R.id.add_btn);
        updateProfile= findViewById(R.id.update_btn);
        male = findViewById(R.id.male_radio);
        female = findViewById(R.id.female_radio);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Edit_Profile.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male.isChecked()){
                    gender="Male";
                }else{
                    gender ="Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newId= dbHandler.addInfo(username.getText().toString(),dob.getText().toString(),pass.getText().toString(),gender);

                Toast.makeText(ProfileManagement.this, "User added"+newId, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),Edit_Profile.class);
                startActivity(intent);
            }
        });
    }


}
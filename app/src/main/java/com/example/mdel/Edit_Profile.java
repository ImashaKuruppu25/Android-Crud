package com.example.mdel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mdel.database.DBHandler;

import java.util.List;

public class Edit_Profile extends AppCompatActivity {

    EditText username,dob,pass;
    Button search,edit,delete;
    RadioButton male,female;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.username_ep);
        dob = findViewById(R.id.dof_ep);
        pass = findViewById(R.id.pass_ep);
        edit = findViewById(R.id.edit_btn_ep);
        search = findViewById(R.id.search_btn);
        delete= findViewById(R.id.delete_btn_ep);
        male = findViewById(R.id.male_ep);
        female = findViewById(R.id.female_ep);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(username.getText().toString());

                if(user.isEmpty()){
                    Toast.makeText(Edit_Profile.this, "No user!", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }else{
                    Toast.makeText(Edit_Profile.this, "User Found.User: "+username, Toast.LENGTH_SHORT).show();

                     username.setText(user.get(0).toString());
                     dob.setText(user.get(1).toString());
                     pass.setText(user.get(2).toString());
                     if(user.get(3).toString().equals("Male")){
                         male.setChecked(true);
                     }else{
                         female.setChecked(true);
                     }
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked()){
                    gender="Male";
                }else{
                    gender ="Female";
                }
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                boolean status = dbHandler.updateInfo(username.getText().toString(),dob.getText().toString(),pass.getText().toString(),gender);

                if(status){
                    Toast.makeText(Edit_Profile.this, "User updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Edit_Profile.this, "User not updated: ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(username.getText().toString());
                Toast.makeText(Edit_Profile.this, "User Deleted!: ", Toast.LENGTH_SHORT).show();

                username.setText(null);
                dob.setText(null);
                pass.setText((null));
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }
}
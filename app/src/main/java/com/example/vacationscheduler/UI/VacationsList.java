package com.example.vacationscheduler.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vacationscheduler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VacationsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacations_list);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationsList.this, VacationDetails.class);
                startActivity(intent);
            }
        });

        System.out.println(getIntent().getStringExtra("test"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId()==R.id.sample){
            Toast.makeText(VacationsList.this, "Put in sample data", Toast.LENGTH_LONG).show();
            return true;
        }
        if(menuItem.getItemId()==android.R.id.home){
            this.finish();
            return  true;
        }
        return true;
    }
}
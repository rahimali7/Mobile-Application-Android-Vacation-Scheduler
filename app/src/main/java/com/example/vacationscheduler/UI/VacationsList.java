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
import com.example.vacationscheduler.database.Repository;
import com.example.vacationscheduler.entities.Excursion;
import com.example.vacationscheduler.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VacationsList extends AppCompatActivity {

    private Repository repository;

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
            repository = new Repository(getApplication());
            //Toast.makeText(VacationsList.this, "Put in vacation details", Toast.LENGTH_LONG).show();
            Vacation vacation1 = new Vacation(0, "Italy", "La comma", "06/10/2024", "06/20/2024");
            repository.insert(vacation1);
            Excursion excursion1 = new Excursion(0, "Snorkeling", "11/06/2-24", 1);
            repository.insert(excursion1);



            return true;
        }
        if(menuItem.getItemId()==android.R.id.home){
            this.finish();
            return  true;
        }
        return true;
    }
}
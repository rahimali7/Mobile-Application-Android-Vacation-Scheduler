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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationscheduler.R;
import com.example.vacationscheduler.database.Repository;
import com.example.vacationscheduler.entities.Excursion;
import com.example.vacationscheduler.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

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
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        repository = new Repository(getApplication());
        List<Vacation> allVacations = repository.getmAllVacations();
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(allVacations);
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
            Vacation vacation1 = new Vacation(0, "Italy", "La comma", "10/06/2024", "20/06/2024");
            repository.insert(vacation1);
            vacation1 = new Vacation(0, "Greece", "Hamilton", "13/07/2024", "23/07/2024");
            repository.insert(vacation1);
            vacation1 = new Vacation(0, "USA", "Blues", "15/07/2024", "30/07/2024");
            repository.insert(vacation1);
            Excursion excursion1 = new Excursion(0, "Snorkeling", "11/06/2024", 1);
            repository.insert(excursion1);
            excursion1 = new Excursion(0, "Swimming", "10/06/2024", 2);
            repository.insert(excursion1);
            excursion1 = new Excursion(0, "Hiking", "15/06/2024", 3);
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
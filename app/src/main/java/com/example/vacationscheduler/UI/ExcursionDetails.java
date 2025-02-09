package com.example.vacationscheduler.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationscheduler.R;
import com.example.vacationscheduler.database.Repository;
import com.example.vacationscheduler.entities.Excursion;
import com.example.vacationscheduler.entities.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExcursionDetails extends AppCompatActivity {
    String title;
    String date;
    int excursionID;
    int vacationID;
    EditText editTitle;
    EditText editNote;
    TextView editDate;
    Excursion currentExcursion;
    Repository repository;
    Vacation currentVacation;
    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);
        repository=new Repository(getApplication());
        title = getIntent().getStringExtra("title");
        editTitle = findViewById(R.id.excursionTitle);
        editTitle.setText(title);
        excursionID = getIntent().getIntExtra("id", -1);
        vacationID = getIntent().getIntExtra("vacationID", -1);
        date = getIntent().getStringExtra("date");
        editNote=findViewById(R.id.note);
        editDate=findViewById(R.id.date);
        editDate.setText(date);

        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ArrayList<Vacation> vacationArrayList = new ArrayList<>();
        vacationArrayList.addAll(repository.getmAllVacations());
        ArrayList<String> vacationTitleList = new ArrayList<>();
        for (Vacation vacation:vacationArrayList){
            vacationTitleList.add(vacation.getTitle());
        }
        ArrayAdapter<String> vacationTitleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vacationTitleList);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(vacationTitleAdapter);

        startDate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

            }
        };

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Date date = new Date();
                String info=editDate.getText().toString();
                //Vacation vacationDate;
                if(info.equals("")) info = date.toString();
                try{
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(ExcursionDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabelStart() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_excursiondetails, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }


        if (item.getItemId() == R.id.excursionsave) {
            Excursion excursion;
            if (excursionID == -1) {
                if (repository.getAllExcursions().size() == 0)
                    excursionID = 1;
                else
                    excursionID = repository.getAllExcursions().get(repository.getAllExcursions().size() - 1)
                            .getExcursionID() + 1;
                excursion = new Excursion(excursionID, editTitle.getText().toString(),
                        editDate.getText().toString(), vacationID);


                for (Vacation vacation:repository.getmAllVacations()){
                    if (vacation.getVacationID()==excursion.getVacationID()){
                        currentVacation = vacation;
                    }
                }
                String dateFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

                try{
                    Date vacStartDate = sdf.parse(currentVacation.getStartDate());
                    Date vacEndDate = sdf.parse(currentVacation.getEndDate());
                    Date excursionDate = sdf.parse(editDate.getText().toString());
                    if (excursionDate.before(vacStartDate)) {
                        Toast.makeText(this, "Excursion date is before vacation start date.", Toast.LENGTH_LONG).show();
                        return false;
                    } else if (excursionDate.after(vacEndDate)) {
                        Toast.makeText(this, "Excursion date is after vacation end date.", Toast.LENGTH_LONG).show();
                        return false;
                    } else if (excursionDate.after(vacStartDate) && (excursionDate.before(vacEndDate))){
                        repository.insert(excursion);
                        this.finish();
                        return true;
                    }
                }
                catch (ParseException e){
                    e.printStackTrace();
                }

            } else {
                excursion = new Excursion(excursionID, editTitle.getText().toString(), editDate.getText().toString(), vacationID);
                for (Vacation vacation:repository.getmAllVacations()){
                    if (vacation.getVacationID()==excursion.getVacationID()){
                        currentVacation = vacation;
                    }
                }
                String dateFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                try{
                    Date vacStartDate = sdf.parse(currentVacation.getStartDate());
                    Date vacEndDate = sdf.parse(currentVacation.getEndDate());
                    Date excursionDate = sdf.parse(editDate.getText().toString());
                    if (excursionDate.before(vacStartDate)) {
                        Toast.makeText(this, "Excursion date is before vacation start date.", Toast.LENGTH_LONG).show();
                        return false;
                    } else if (excursionDate.after(vacEndDate)) {
                        Toast.makeText(this, "Excursion date is after vacation end date.", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        repository.update(excursion);
                        this.finish();
                        return true;
                    }
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
            }
            return true;
        }


        if (item.getItemId()==R.id.excursiondelete) {
            for (Excursion excursion:repository.getAllExcursions()){
                if (excursion.getExcursionID()==excursionID){
                    currentExcursion = excursion;
                    repository.delete(currentExcursion);
                    this.finish();
                }
            }
        }






        if (item.getItemId() == R.id.shareNote) {
            Intent sentIntent= new Intent();
            sentIntent.setAction(Intent.ACTION_SEND);
            sentIntent.putExtra(Intent.EXTRA_TEXT, "Excursion Title: " + editTitle.getText().toString() + "\n" +
            "Excursion Date: " + editDate.getText().toString());
            //sentIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
            sentIntent.setType("text/plain");
            Intent shareIntent=Intent.createChooser(sentIntent,null);
            startActivity(shareIntent);
            return true;
        }
        if (item.getItemId() == R.id.notify) {
            String dateFromScreen = editDate.getText().toString();
            String myFormat = "MM/dd/yyyy"; //In which you need put here
            SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf1.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();
            //if
            Intent intent = new Intent(ExcursionDetails.this, MyReceiver.class);
            intent.putExtra("key", "Excursion Date: " + myDate);
            PendingIntent sender=PendingIntent.getBroadcast(ExcursionDetails.this,++MainActivity.numAlert,
                    intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger,sender);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
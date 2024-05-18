package com.example.vacationscheduler.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Excursions")
public class Excursion {

    @PrimaryKey(autoGenerate = true)
    private int excursionID;
    private String title;
    private String date;
    private int vacationID;

    public Excursion(int excursionID, String title, String date, int vacationID) {
        this.excursionID = excursionID;
        this.title = title;
        this.date = date;
        this.vacationID = vacationID;
    }

    public int getExcursionID() {
        return excursionID;
    }

    public void setExcursionID(int excursionID) {
        this.excursionID = excursionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }
}

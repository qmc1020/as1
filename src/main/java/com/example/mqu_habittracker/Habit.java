package com.example.mqu_habittracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mc on 16/9/24.
 */
public class Habit {
    protected String habitName;
    protected String data;


    public Habit(String habitName) {
        this.habitName = habitName;
        data = "";
    }
    public Habit(String habitName, String data) {
        this.habitName = habitName;
        this.data = data;
    }

    // complete once, add the string of time into data (String type)
    public void complete(){
        DateFormat df = new SimpleDateFormat("\nMM/dd/yyyy HH:mm:ss ");
        Calendar cal=Calendar.getInstance();

        this.data += df.format(new Date()) + new SimpleDateFormat("EEE").format(cal.getTime());
    }

    // return the title and records
    public String toString(){

        return habitName + data;
    }

    public void setHabitName(String habitName){
        this.habitName = habitName;
    }

    public String getName() {
        return this.habitName;
    }

    public void cleanData() {
        data = "";
    }
}

package com.example.mqu_habittracker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mc on 16/9/24.
 */
public class HabitList {
    protected ArrayList<Habit> habitList;

    public HabitList() {
        this.habitList = new ArrayList<Habit>();
    }

    public Collection<Habit> getHabits() {
        return habitList;
    }


    public void addHabit(Habit testHabit) {
        if (habitList.contains(testHabit) == Boolean.FALSE){
            habitList.add(testHabit);
        }
    }

    // get arraylist of Habit
    public ArrayList<Habit> getList(){
        return habitList;
    }


    public void removeHabit(Habit testHabit) {
        habitList.remove(testHabit);
    }

    public Habit chooseHabits() {
        return habitList.get(0);
    }

    public int size() {
        return habitList.size();
    }

    public boolean contains(Habit testHabit) {
        return habitList.contains(testHabit);
    }

    // delete everything
    public void deleteAll() {
        habitList.clear();
    }

    // clean all the record
    public void cleanHabit() {
        for (Habit habit:habitList) {
            habit.cleanData();
        }
    }

    public void complete(int position) {
        habitList.get(position).complete();
    }

    // load an arraylist of habit
    public void inputList(ArrayList<Habit> inputList) {
        habitList = inputList;
    }
}

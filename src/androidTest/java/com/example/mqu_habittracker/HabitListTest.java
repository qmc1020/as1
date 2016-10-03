package com.example.mqu_habittracker;

import junit.framework.TestCase;

import java.util.Collection;

/**
 * Created by mc on 16/9/24.
 */
public class HabitListTest extends TestCase {
    public void testHabitList(){
        HabitList habitList = new HabitList();
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("Empty Habit List",habits.size() == 0);
    }

    public void testAddHabitList(){
        HabitList habitList = new HabitList();
        String habitName = "A habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);assertTrue("habit list size", habitList.size() == 1);
        assertTrue("test habit not contained", habitList.contains(testHabit));

    }

    public void testGetHabit(){
        HabitList habitList = new HabitList();
        String habitName = "A habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);
        assertTrue("habit list size", habitList.size() == 1);
        assertTrue("test habit not contained", habitList.contains(testHabit));

    }

    public void testRemoveHabitList(){
        HabitList habitList = new HabitList();
        String habitName = "A habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("habit list size", habits.size() == 1);
        assertTrue("", habits.contains(testHabit));
        habitList.removeHabit(testHabit);
        habits = habitList.getHabits();
        assertTrue("habit list size is not small enough", habits.size() == 0);
        assertFalse("test student still contained", habits.contains(testHabit));
    }

    public void testChooseHabitList(){
        HabitList habitList = new HabitList();
        String habitName = "A habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);

        Habit habit = habitList.chooseHabits();
        assertTrue("did not choose the right students", habit.equals(testHabit));

    }


}

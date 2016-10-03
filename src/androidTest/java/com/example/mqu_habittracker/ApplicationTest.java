package com.example.mqu_habittracker;

import android.app.Application;
import android.test.ApplicationTestCase;
import com.example.mqu_habittracker.Habit;

import junit.framework.TestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends TestCase {
    public void testHabit() {
        String habitName = "A habit";
        Habit habit = new Habit(habitName);
        assertTrue("Habit Name is not equal",habitName.equals(habit.getName()));
    }
}
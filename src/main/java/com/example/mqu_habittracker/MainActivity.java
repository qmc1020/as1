/*
Habbit Packer: storing habbits and complete them

Copyright (C) 2014 Mingcheng Qu mqu@ualberta.ca

This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */


package com.example.mqu_habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{
    private static final String FILENAME = "file3.sav";
    private HabitList habitList;
    private EditText bodyText;
    private ListView oldHabitsList;
    private ArrayAdapter<Habit> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the button
        bodyText = (EditText) findViewById(R.id.habitEditText);
        Button addButton = (Button) findViewById(R.id.addHabitButton);
        Button cleanButton = (Button) findViewById(R.id.cleanButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        // set listview
        oldHabitsList = (ListView) findViewById(R.id.habitListView);
        oldHabitsList.setOnItemClickListener(MainActivity.this);

        // to add a new habit
        addButton.setOnClickListener((v) -> {
            setResult(RESULT_OK);
            String text = bodyText.getText().toString();
            Habit newHabit = new Habit(text);
            if (habitList == null) {
                habitList = new HabitList();
            }
            //newHabit.complete();
            habitList.addHabit(newHabit);
            adapter.notifyDataSetChanged();
            saveInFile();

        });

        // delete everything
        deleteButton.setOnClickListener((v) -> {
            setResult(RESULT_OK);
            if (habitList == null) {
                habitList = new HabitList();
            }
            habitList.deleteAll();
            adapter.notifyDataSetChanged();
            saveInFile();
        });

        // clean the record of all the habits
        cleanButton.setOnClickListener((v) -> {
            setResult(RESULT_OK);
            if (habitList == null) {
                habitList = new HabitList();
            }
            habitList.cleanHabit();
            adapter.notifyDataSetChanged();
            saveInFile();
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, habitList.getList());
        oldHabitsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            if (habitList == null){habitList = new HabitList();}
            habitList.inputList(gson.fromJson(in, listType));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //throw new RuntimeException();
            habitList = new HabitList();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {

        try {

            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList.getList(), writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


    // on click for listview, when i click the item in the list view =  complete once
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(MainActivity.this, "completed once", Toast.LENGTH_LONG).show();
        habitList.complete(position);

        saveInFile();
        adapter.notifyDataSetChanged();
    }
}

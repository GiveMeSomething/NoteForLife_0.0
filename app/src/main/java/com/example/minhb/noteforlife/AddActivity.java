package com.example.minhb.noteforlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class AddActivity extends AppCompatActivity {
    String[] dayList = {
            "","1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };
    String[] monthList = {
            "","1","2","3","4","5","6","7","8","9","10","11","12"
    };
    String[] yearList = {
            "","2018","2019",
            "2020","2021","2022","2023","2024","2025","2026","2027","2028","2029",
            "2030"
    };
    String[] hourList = {
            "","00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23"
    };
    String[] minuteList = {
            "","00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31","32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59"
    };
    ArrayList<String> dayL = new ArrayList<>();
    ArrayList<String> monthL = new ArrayList<>();
    ArrayList<String> yearL = new ArrayList<>();
    ArrayList<String> hourL = new ArrayList<>();
    ArrayList<String> minuteL = new ArrayList<>();

    public Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        SharedPreferences prefs = getSharedPreferences("dateList",0);

        int dayAdd = prefs.getInt("day",0);
        int monthAdd = prefs.getInt("month",0);
        int yearAdd = prefs.getInt("year",0);

        int hourAdd = cal.get(Calendar.HOUR_OF_DAY);
        int minuteAdd = cal.get(Calendar.MINUTE);

        getSupportActionBar().setTitle("Note Edit");

        final Spinner daySpinner = findViewById(R.id.daySpinner);
        final Spinner monthSpinner = findViewById(R.id.monthSpinner);
        final Spinner yearSpinner = findViewById(R.id.yearSpinner);
        final Spinner hourSpinner = findViewById(R.id.hourSpinner);
        final Spinner minuteSpinner = findViewById(R.id.minuteSpinner);

        final EditText dayText = findViewById(R.id.dayText);
        final EditText monthText = findViewById(R.id.monthText);
        final EditText yearText = findViewById(R.id.yearText);

        final EditText hourText = findViewById(R.id.hourText);
        final EditText minuteText = findViewById(R.id.minuteText);


        dayL.addAll(Arrays.asList(dayList));
        monthL.addAll(Arrays.asList(monthList));
        yearL.addAll(Arrays.asList(yearList));
        hourL.addAll(Arrays.asList(hourList));
        minuteL.addAll(Arrays.asList(minuteList));

        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_text,dayL);
        daySpinner.setAdapter(adapterDay);

        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_text,monthL);
        monthSpinner.setAdapter(adapterMonth);

        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_text,yearL);
        yearSpinner.setAdapter(adapterYear);

        ArrayAdapter<String> adapterHour = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_text,hourL);
        hourSpinner.setAdapter(adapterHour);

        ArrayAdapter<String> adapterMinute = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_text,minuteL);
        minuteSpinner.setAdapter(adapterMinute);

        daySpinner.setSelection(dayAdd);
        dayText.setText(daySpinner.getSelectedItem().toString());
        daySpinner.setSelection(0);

        monthSpinner.setSelection(monthAdd + 1);
        monthText.setText(monthSpinner.getSelectedItem().toString());
        monthSpinner.setSelection(0);

        yearSpinner.setSelection(yearAdd - 2017);
        yearText.setText(yearSpinner.getSelectedItem().toString());
        yearSpinner.setSelection(0);

        hourSpinner.setSelection(hourAdd);
        hourText.setText(hourSpinner.getSelectedItem().toString());
        hourSpinner.setSelection(0);

        minuteSpinner.setSelection(minuteAdd);
        minuteText.setText(minuteSpinner.getSelectedItem().toString());
        minuteText.setSelection(0);

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String selected = adapterView.getItemAtPosition(pos).toString();
                if(pos != 0) {
                    dayText.setText(selected);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selected = adapterView.getItemAtPosition(pos).toString();
                if(pos != 0) {
                    monthText.setText(selected);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selected = adapterView.getItemAtPosition(pos).toString();
                if(pos != 0 ) {
                    yearText.setText(selected);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selected = adapterView.getItemAtPosition(pos).toString();
                if (pos != 0) {
                    hourText.setText(selected);
                }
                hourSpinner.setSelection(0);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selected = adapterView.getItemAtPosition(pos).toString();
                if(pos != 0) {
                    minuteText.setText(selected);
                }
                minuteSpinner.setSelection(0);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        EditText titleNote = findViewById(R.id.noteTitle);

        EditText dayNote = findViewById(R.id.dayText);
        EditText monthNote = findViewById(R.id.monthText);
        EditText yearNote = findViewById(R.id.yearText);

        EditText hourNote = findViewById(R.id.hourText);
        EditText minuteNote = findViewById(R.id.minuteText);

        EditText noteText = findViewById(R.id.noteArea);

        int day = Integer.parseInt(dayNote.getText().toString());
        int month = Integer.parseInt(monthNote.getText().toString());
        int year = Integer.parseInt(yearNote.getText().toString());

        int hour = Integer.parseInt(hourNote.getText().toString());
        int minute = Integer.parseInt(minuteNote.getText().toString());
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            int id = item.getItemId();
            if(id == R.id.doneButton)
            {
                Finish(titleNote, noteText, day, month, year, hour, minute);
            }
        }
        if(month == 4 || month == 6 || month == 9 || month == 11)
        {
            if(day == 31)
            {
                CharSequence error = "Invalid date or time.";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT ;
                Toast toast = Toast.makeText(context,error,duration);
                toast.show();
            }
            else
            {
                int id = item.getItemId();
                if(id == R.id.doneButton)
                {
                    Finish(titleNote, noteText, day, month, year, hour, minute);
                }
            }
        }
        if(month == 2){
            if(year % 4 == 0)
            {
                if(day == 30 || day == 31 )
                {
                    CharSequence error = "Invalid date or time.";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT ;
                    Toast toast = Toast.makeText(context,error,duration);
                    toast.show();
                }
                else
                {
                    int id = item.getItemId();
                    if(id == R.id.doneButton)
                    {
                        Finish(titleNote, noteText, day, month, year, hour, minute);
                    }
                }
            }
            else{
                if(day == 29 || day == 30 || day == 31)
                {
                    CharSequence error = "Invalid date or time.";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT ;
                    Toast toast = Toast.makeText(context,error,duration);
                    toast.show();
                }
                else
                {
                    int id = item.getItemId();
                    if(id == R.id.doneButton)
                    {
                        Finish(titleNote, noteText, day, month, year, hour, minute);
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void Finish(EditText titleNote, EditText noteText, int day, int month, int year, int hour, int minute) {
        SharedPreferences preferences = getSharedPreferences("objFile",0);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        NoteObj note = new NoteObj();

        String titleList = titleNote.getText().toString();
        String dateList = day + "/" + month + "/" + year;
        String timeList = hour + ":" + minute;
        String textList = noteText.getText().toString();

        int position = preferences.getAll().size() + 1;
        String objID = Integer.toString(position);

        note.setDateText(dateList);
        note.setTimeText(timeList);
        note.setTitleText(titleList);
        note.setNoteText(textList);
        note.setId(position);

        String json = gson.toJson(note);
        editor.putString(objID,json);
        editor.apply();

        Intent intent = new Intent(this,NoteActivity.class);
        startActivity(intent);
    }

}

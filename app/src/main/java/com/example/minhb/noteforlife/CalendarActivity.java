package com.example.minhb.noteforlife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    public Calendar cal = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        getSupportActionBar().setTitle("Calendar");

        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        SharedPreferences preferences = getSharedPreferences("dateList",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        editor.putInt("day",day);
        editor.putInt("month",month);
        editor.putInt("year", year);

        editor.apply();

        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                SharedPreferences preferences = getSharedPreferences("dateList",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();

                editor.putInt("day",d);
                editor.putInt("month",m );
                editor.putInt("year", y);

                editor.apply();
            }
        });
    }
    public void noteView(View view)
    {
        ImageButton addButton = findViewById(R.id.addButtonCal);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar_menu,menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem title = menu.findItem(R.id.calendarDay);

        int dayShow = cal.get(Calendar.DATE);
        int monthShow = cal.get(Calendar.MONTH) + 1;
        int yearShow = cal.get(Calendar.YEAR);
        String date = dayShow + "/" + monthShow + "/" + yearShow;
        title.setTitle(date);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.noteButton)
        {
            Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.minhb.noteforlife;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;


public class NoteActivity extends AppCompatActivity {
    public int day;
    public int month;
    public int year;

    public int modeEdit;

    public int numberNote = 0;

    public Calendar cal = Calendar.getInstance();

    ArrayList<String> noteViewDate = new ArrayList<>();
    ArrayList<String> noteViewText = new ArrayList<>();
    ArrayList<Integer> checkedPosition = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        getSupportActionBar().setTitle("Note");
        modeEdit = 1;

        showLayout();
        //KIEM TRA IMAGE BUTTON
        if(modeEdit == 1) {
            ImageButton addButton = findViewById(R.id.addButtonNote);
            addButton.setImageResource(R.drawable.ic_add_circle_black_24dp);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem title = menu.findItem(R.id.noteDay);
        MenuItem option = menu.findItem(R.id.options);
        MenuItem done = menu.findItem(R.id.doneButton);
        MenuItem returnButton = menu.findItem(R.id.returnButton);

        day = cal.get(Calendar.DATE);
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);

        String date = day + "/" + month + "/" + year;

        title.setTitle(date);

        if(modeEdit == 1)
        {
            title.setVisible(true);
            option.setVisible(true);
            done.setVisible(false);
            returnButton.setVisible(false);
        }else
        {
            title.setVisible(false);
            option.setVisible(false);
            done.setVisible(true);
            returnButton.setVisible(true);
        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.noteDay:
                Intent intent = new Intent (getApplicationContext(),CalendarActivity.class);
                startActivity(intent);
                return true;
            case R.id.delete:
                if(numberNote == 0){
                    Toast.makeText(this, "Note is empty. Create some", Toast.LENGTH_SHORT).show();
                }else
                    {
                    modeEdit = 2;

                    final ListView noteList = findViewById(R.id.noteList);
                    final SharedPreferences preferences = getSharedPreferences("objFile",0);

                    final CustomAdapter adapter = new CustomAdapter(this,noteViewDate,noteViewText, modeEdit);
                    noteList.setAdapter(adapter);

                    noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View item, int position, long id) {

                        }
                    });


                    ImageButton button = findViewById(R.id.addButtonNote);
                    button.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    //XOA DATA KHOI LIST
                        button.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
                                builder.setTitle("Delete");
                                builder.setMessage("Do you want delete this item?");
                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which)
                                    {
                                        checkedPosition = adapter.getCheckedList();
                                        int removePosition = checkedPosition.get(0);
                                        noteViewDate.remove(removePosition);
                                        noteViewText.remove(removePosition);
                                        CustomAdapter newAdapter = new CustomAdapter(getApplicationContext(),noteViewDate,noteViewText, modeEdit);
                                        noteList.setAdapter(newAdapter);
                                    }
                                });
                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        dialogInterface.dismiss();
                                        Toast toast = Toast.makeText(NoteActivity.this,"No changes were made",Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                                builder.show();
                            }
                        });
                }
                return true;

            case R.id.checker:
                if(numberNote == 0)
                {
                    Toast.makeText(this, "Note is empty. Create some!", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    modeEdit = 3;

                    final ListView noteList = findViewById(R.id.noteList);
                    final SharedPreferences preferences = getSharedPreferences("objFile", 0);

                    final CustomAdapter adapter = new CustomAdapter(this,noteViewDate,noteViewText, modeEdit);
                    noteList.setAdapter(adapter);

                    ImageButton button = findViewById(R.id.addButtonNote);
                    button.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    //XOA DATA KHOI LIST
                    button.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
                            builder.setTitle("Finish");
                            builder.setMessage("Have you finished this work?");
                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which)
                                {

                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which)
                                {
                                    dialogInterface.dismiss();
                                    Toast toast = Toast.makeText(NoteActivity.this,"No changes were made",Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                            builder.show();
                        }
                    });
                }
                return true;

            case R.id.returnButton:
                modeEdit = 1;

                SharedPreferences preferences = getSharedPreferences("objFile",0);
                ListView noteList = findViewById(R.id.noteList);

                final CustomAdapter adapter = new CustomAdapter(this,noteViewDate,noteViewText, modeEdit);
                noteList.setAdapter(adapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showLayout()
    {
        ListView noteList = findViewById(R.id.noteList);

        SharedPreferences preferences = getSharedPreferences("objFile",0);
        int size = preferences.getAll().size();

        // LAY DATA CHO LISTVIEW
        for (int i = 0; i < size; i++)
        {
            Gson gson = new Gson();
            int pos = i + 1;
            String objId = Integer.toString(pos);
            String json = preferences.getString(objId,"");

            NoteObj note = gson.fromJson(json, NoteObj.class);

            String title = note.getTitleText();
            String date = note.getDateText();
            String time = note.getTimeText();

            String noteDate = time + "  " + date;

            noteViewDate.add(i,noteDate);
            noteViewText.add(i,title);

            numberNote = size;
        }
        final CustomAdapter adapter = new CustomAdapter(this,noteViewDate,noteViewText, modeEdit);
        noteList.setAdapter(adapter);
    }
}

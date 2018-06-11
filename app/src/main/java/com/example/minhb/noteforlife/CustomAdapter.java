package com.example.minhb.noteforlife;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;

    private int modeEdit;

    private ArrayList<String> noteDate;
    private ArrayList<String> noteText;

    private ArrayList<Integer> itemPosition = new ArrayList<>();

    private boolean [] isChecked;

    public CustomAdapter(Context context,ArrayList<String> date, ArrayList<String> text ,int modeEdit)
    {
        this.context = context;
        this.modeEdit = modeEdit;
        this.noteDate = date;
        this.noteText = text;
    }

    @Override
    public int getCount() {
        return noteDate.size();
    }

    //Trả về vị trí của mảng chứa nội dung list item
    @Override
    public Object getItem(int position) {
        return noteDate.get(position);
    }

    //Trả về vị trí của mảng image list item
    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_style, parent, false);

        final CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView date = convertView.findViewById(R.id.noteText1);
        TextView text = convertView.findViewById(R.id.noteText2);

        isChecked = new boolean[noteDate.size()];

        if (modeEdit == 1) {
            checkBox.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    checkBox.getLayoutParams();
            params.weight = 0.0f;
            checkBox.setLayoutParams(params);
        }
        if (modeEdit == 2) {
            checkBox.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    checkBox.getLayoutParams();
            params.weight = 1.0f;
            checkBox.setLayoutParams(params);
        }
        if (modeEdit == 3) {
            checkBox.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    checkBox.getLayoutParams();
            params.weight = 1.0f;checkBox.setLayoutParams(params);
        }
        date.setText(noteDate.get(position));
        text.setText(noteText.get(position));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked())
                {
                    isChecked[position] = true;
                }
                else
                {
                    isChecked[position] = false;
                }
            }
        });
        for(int i = 0; i < isChecked.length; i++)
        {
            if(isChecked[i]) {
                itemPosition.add(i, i);
            }
        }
        Log.d("length",Integer.toString(isChecked.length));


        return convertView;
    }
    public ArrayList<Integer> getCheckedList()
    {
        return itemPosition;
    }
}

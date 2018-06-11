package com.example.minhb.noteforlife;
public class NoteObj {
    private String titleText;
    private String dateText;
    private String timeText;
    private String noteText;
    private int id;
    
    private NoteObj ()
    {
        this.titleText = "";
        this.dateText = "";
        this.timeText = "";
        this.noteText = "";
    }
    public NoteObj(String titleText, String dateText, String timeText, String noteText, int id) {
        this.titleText = titleText;
        this.dateText = dateText;
        this.timeText = timeText;
        this.noteText = noteText;
        this.id = id;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }
    public String getTitleText() {
        return titleText;
    }

    public  void setDateText(String dateText) {
        this.dateText = dateText;
    }
    public String getDateText() {
        return dateText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }
    public String getTimeText() {
        return timeText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    public String getNoteText() {
        return noteText;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
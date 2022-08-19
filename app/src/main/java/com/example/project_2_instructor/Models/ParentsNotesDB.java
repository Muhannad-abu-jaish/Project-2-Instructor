package com.example.project_2_instructor.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ParentsNotesDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "parents_notes";
    private static final String PARENT_NOTES_TABLE  ="parentsnotetable";


    //columns name for database tables
    private static final String KEY_ID = "parents_note_id" ;
    private static final String KEY_MESSAGE = "parents_note_message" ;
    private static final String KEY_STUDENT_NAME = "parents_note_student_name" ;
    private static final String KEY_START_DATE = "parents_note_start_date" ;

    public ParentsNotesDB(@Nullable Context context) {
        super(context , DATABASE_NAME , null , DATABASE_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String query = "CREATE TABLE " + PARENT_NOTES_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY ,"+
                KEY_STUDENT_NAME + " TEXT,"+
                KEY_MESSAGE + " TEXT," +
                KEY_START_DATE + " TEXT" + ")";
        //For execute the query and create the table
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //To upgrade the data base version

        if (oldVersion >= newVersion)
            return;

        db.execSQL(" DROP TABLE IF EXISTS " + PARENT_NOTES_TABLE);

        onCreate(db);
    }

    public long addParentsNote(ParentsNotes parentsNotes)
    {
        //For write in the data base
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID , parentsNotes.getId());
        contentValues.put(KEY_STUDENT_NAME , parentsNotes.getUsername());
        contentValues.put(KEY_MESSAGE , parentsNotes.getMessage());
        contentValues.put(KEY_START_DATE , parentsNotes.getStart_date());


        long ID = sqLiteDatabase.insert(PARENT_NOTES_TABLE,null,contentValues);
        Log.d("inserted", "ID -> " + ID);

        return ID;
    }


    public boolean isExists(int id )
    {
        //select * from databaseTable where id = 1
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(PARENT_NOTES_TABLE , new String[]{KEY_ID , KEY_STUDENT_NAME , KEY_MESSAGE , KEY_START_DATE }, KEY_ID +"=?" ,
                new String[]{ String.valueOf(id)},null,null,null);

        if (cursor !=null) {
            cursor.moveToFirst();
            return true;
        }else
            return false ;

    }
    public void deleteTable()
    {
        SQLiteDatabase db = getWritableDatabase() ;
        db.execSQL(" DROP TABLE IF EXISTS " + PARENT_NOTES_TABLE);
        onCreate(db);
    }

    public ParentsNotes getParentsNote(long id)
    {
        //select * from databaseTable where id = 1
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(PARENT_NOTES_TABLE , new String[]{KEY_ID , KEY_STUDENT_NAME , KEY_MESSAGE , KEY_START_DATE }, KEY_ID +"=?" ,
                new String[]{ String.valueOf(id)},null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        return new ParentsNotes(cursor.getInt(0),cursor.getString(1) , cursor.getString(2) , cursor.getString(3));
    }

    public ArrayList<ParentsNotes> getAllParentNotes()
    {
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        ArrayList<ParentsNotes> allPrivateNotes =new ArrayList<>();

        //select * from databaseName
        String query = "SELECT * FROM " + PARENT_NOTES_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do {

                ParentsNotes parentsNote = new ParentsNotes();
                parentsNote.setId(cursor.getInt(0));
                parentsNote.setMessage(cursor.getString(1));
                parentsNote.setStart_date(cursor.getString(2));
                parentsNote.setUsername(cursor.getString(3));

                allPrivateNotes.add(parentsNote);

            }while (cursor.moveToNext());

        }

        cursor.close();
        return allPrivateNotes;
    }

   /* public long addAbsence(AbsencesStudent absencesStudent)
    {
        //For write in the data base
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SECTION_ID , absencesStudent.getSecID());
        contentValues.put(KEY_STUDENT_NAME , absencesStudent.getName());
        contentValues.put(KEY_CHECK , absencesStudent.isAbsence());


        long ID = sqLiteDatabase.insert(ABSENCE_TABLE,null,contentValues);
        Log.d("inserted", "ID -> " + ID);

        return ID;
    }*/
}

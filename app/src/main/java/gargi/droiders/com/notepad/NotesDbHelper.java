package gargi.droiders.com.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 01-09-2017.
 */

public class NotesDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "NotepadDb";
    static final int DATABASE_VERSION = 1;


    public NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WAITLIST_TABLE = " CREATE TABLE " +
                NotesContractClass.NoteListEntry.TABLE_NAME + " ( " +
                NotesContractClass.NoteListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesContractClass.NoteListEntry.COLUMN_NOTES_TITLE + " TEXT NOT NULL, " +
                NotesContractClass.NoteListEntry.COLUMN_NOTES_TEXT + " TEXT NOT NULL " +
                " ); ";
        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + NotesContractClass.NoteListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    long addNote(String title, String text) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesContractClass.NoteListEntry.COLUMN_NOTES_TITLE, title);
        values.put(NotesContractClass.NoteListEntry.COLUMN_NOTES_TEXT, text);


        long id = sqLiteDatabase.insert(NotesContractClass.NoteListEntry.TABLE_NAME, null, values);
        sqLiteDatabase.close();

        return id;
    }

    long updateNote(String title, String text, long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesContractClass.NoteListEntry.COLUMN_NOTES_TITLE, title);
        values.put(NotesContractClass.NoteListEntry.COLUMN_NOTES_TEXT, text);
        sqLiteDatabase.update(NotesContractClass.NoteListEntry.TABLE_NAME, values,
                "_id=" + id, null);

        return id;
    }


    int deleteNote(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

       return sqLiteDatabase.delete(NotesContractClass.NoteListEntry.TABLE_NAME, "_id= " + id , null) ;

        // sqLiteDatabase.close();

    }

    long getNotesCount() {
        String countQuery = "SELECT * FROM " + NotesContractClass.NoteListEntry.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


}


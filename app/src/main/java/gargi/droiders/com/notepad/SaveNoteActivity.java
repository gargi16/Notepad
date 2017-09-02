package gargi.droiders.com.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.google.android.gms.common.api.Status.sq;
import static gargi.droiders.com.notepad.NotesContractClass.NoteListEntry.COLUMN_USER_NAME;

public class SaveNoteActivity extends AppCompatActivity {
    private EditText title_editText, content_editText;
    private Button buttonSave;
    private TextInputLayout title_textInputLayout, content_textInputLayout;
    private NotesDbHelper mDbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_content);
        title_editText = (EditText) findViewById(R.id.et_notes_title);
        content_editText = (EditText) findViewById(R.id.et_notes_content);
        buttonSave = (Button) findViewById(R.id.save_btn);
        mDbhelper = new NotesDbHelper(this);


          Intent intent = getIntent() ;
        final long id = intent.getLongExtra("identity",-1);
        Toast.makeText(this , "id =" + id , Toast.LENGTH_SHORT).show();

        if(id!= -1){
            SQLiteDatabase database = mDbhelper.getReadableDatabase();

            String selection = NotesContractClass.NoteListEntry._ID + " = ?";

            // selection argument
            String[] selectionArgs = {Long.toString(id)};

           Cursor cursor = database.query(NotesContractClass.NoteListEntry.TABLE_NAME ,null,
                    selection,selectionArgs,null ,null,null);
            cursor.moveToFirst() ;
            String title , text ;

            title = cursor.getString(cursor.getColumnIndex(NotesContractClass.NoteListEntry.COLUMN_NOTES_TITLE));
            text = cursor.getString(cursor.getColumnIndex(NotesContractClass.NoteListEntry.COLUMN_NOTES_TEXT));

            title_editText.setText(title);
            content_editText.setText(text);
            database.close();
        }

            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String title = title_editText.getText().toString();
                    String contentText = content_editText.getText().toString();


                    if(id == -1)
                    {
                      long returnID= mDbhelper.addNote(title,contentText);

                    }
                    else
                       mDbhelper.updateNote(title,contentText , id) ;


                    Toast.makeText(SaveNoteActivity.this, "Note id :" + id, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SaveNoteActivity.this, MainActivity.class);

                    startActivity(intent);
                    finish();




                }
            });


        }
    }


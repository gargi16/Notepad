package gargi.droiders.com.notepad;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
       //  Bundle bundle = getIntent().getExtras();

          Intent intent = getIntent() ;
        long id = intent.getLongExtra("identity",-1);
        Toast.makeText(this , "id =" + id , Toast.LENGTH_SHORT).show();

            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = title_editText.getText().toString();
                    String contentText = content_editText.getText().toString();
                    long id = mDbhelper.addNote(title, contentText);

                    Toast.makeText(SaveNoteActivity.this, "Note id :" + id, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SaveNoteActivity.this, MainActivity.class);

                    startActivity(intent);
                    finish();


                }
            });


        }
    }


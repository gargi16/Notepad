package gargi.droiders.com.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.google.android.gms.common.api.Status.sq;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton openNoteBtn ;
    private NotesListAdapter mAdapter ;
    private RecyclerView mRecyclerView ;
    NotesDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openNoteBtn = (FloatingActionButton) findViewById(R.id.fab2) ;
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_numbers);
        dbHelper = new NotesDbHelper(this);

        // to get cursor , notesDbHelper instance


        openNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SaveNoteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mAdapter = new NotesListAdapter(getNotes());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);



    }

    private Cursor getNotes(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return database.query(NotesContractClass.NoteListEntry.TABLE_NAME ,
                null,null,null,null ,null,null,null);
    }

}

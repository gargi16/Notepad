package gargi.droiders.com.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements NotesListAdapter.ListItemClickListener {

    NotesDbHelper dbHelper;
    private FloatingActionButton openNoteBtn;
    private NotesListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openNoteBtn = (FloatingActionButton) findViewById(R.id.fab2);
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

        mAdapter = new NotesListAdapter(this, getNotes());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


    }


    private Cursor getNotes() {

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return database.query(NotesContractClass.NoteListEntry.TABLE_NAME,
                null, null, null, null, null, null, null);
    }


    @Override
    public void onListItemClick(int clickedItemIndex, long id) {
        Intent intent = new Intent(MainActivity.this, SaveNoteActivity.class);
        intent.putExtra("identity", id);

        startActivity(intent);

    }
}

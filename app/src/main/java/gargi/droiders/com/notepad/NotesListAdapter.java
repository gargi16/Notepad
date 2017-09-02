package gargi.droiders.com.notepad;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 02-09-2017.
 */

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder> {


    private Cursor mCursor;

    public NotesListAdapter(Cursor cursor) {
        this.mCursor = cursor;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_list_item,parent,false);
        NotesViewHolder viewHolder = new NotesViewHolder(view);
        viewHolder.noteTextView.setText("default6");
        viewHolder.titleTextView.setText("default");



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Log.e("checkBool", String.valueOf(mCursor.getCount()));

        if(!mCursor.moveToPosition(position))
            return;
        Log.e("abc",mCursor.getString(mCursor.getColumnIndex(NotesContractClass.NoteListEntry.COLUMN_NOTES_TITLE)));
        Log.e("efg",mCursor.getString(mCursor.getColumnIndex(NotesContractClass.NoteListEntry.COLUMN_NOTES_TEXT)));
        String noteTitle = mCursor.getString(mCursor.getColumnIndex(NotesContractClass.NoteListEntry.COLUMN_NOTES_TITLE));
        String noteContent = mCursor.getString(mCursor.getColumnIndex(NotesContractClass.NoteListEntry.COLUMN_NOTES_TEXT));
        holder.titleTextView.setText(noteTitle);
        holder.noteTextView.setText(noteContent);
        long id =(long) mCursor.getLong(mCursor.getColumnIndex(NotesContractClass.NoteListEntry._ID));
        holder.itemView.setTag(id);

    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView;
        TextView noteTextView;

        public NotesViewHolder(View itemView) {
            super(itemView);
            titleTextView =(TextView) itemView.findViewById(R.id.tv_notes_title);
            noteTextView = (TextView) itemView.findViewById(R.id.tv_notes_content);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            long id = (long) view.getTag();
        }
    }
    void swapCursor(Cursor cursor){
        if(cursor==null)
            return;
        mCursor = cursor;
        if(mCursor!=null)
            this.notifyDataSetChanged();

    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex, long id);
    }

}

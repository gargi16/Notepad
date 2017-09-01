package gargi.droiders.com.notepad;

import android.provider.BaseColumns;

/**
 * Created by hp on 01-09-2017.
 */

public final class NotesContractClass {

    public static final class NoteListEntry implements BaseColumns{
        public static final String TABLE_NAME = "noteslist";
        public static final String COLUMN_USER_NAME = "username";
        public static final String COLUMN_NOTES_TEXT = "notetext";
        public static final String COLUMN_NOTES_TITLE = "notetitle";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }



}

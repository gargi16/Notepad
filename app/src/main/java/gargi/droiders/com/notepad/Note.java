package gargi.droiders.com.notepad;

import android.support.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by hp on 02-09-2017.
 */

public class Note {
    private final String mId;
    @Nullable
    private final String mTitle;
    @Nullable
    private final String mDescription;
    @Nullable
    private final String mImageUrl;


    public Note(@Nullable String title, @Nullable String description, @Nullable String imageUrl) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;
    }

    public Note(@Nullable String title, @Nullable String description){
        this(title,description,null);
    }

    public boolean isEmpty(){
        return (mTitle==null || "".equals(mTitle))&&
                (mDescription==null || "".equals(mDescription));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass()!=this.getClass()) return false;
        Note note = (Note) obj;
        return Objects.equals(mId, note.mId) &&
                Objects.equals(mTitle, note.mTitle) &&
                Objects.equals(mDescription, note.mDescription) &&
                Objects.equals(mImageUrl, note.mImageUrl);
    }

    public String getmId() {
        return mId;
    }

    @Nullable
    public String getmTitle() {
        return mTitle;
    }

    @Nullable
    public String getmDescription() {
        return mDescription;
    }

    @Nullable
    public String getmImageUrl() {
        return mImageUrl;
    }

}


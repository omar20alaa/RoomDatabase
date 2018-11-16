package com.room.room.RoomDataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.room.room.Model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    // insert method
    @Insert
    void insert(Note note);

    // update method
    @Update
    void update(Note note);

    // delete one note method
    @Delete
    void delete(Note note);

    // delete all notes
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    // return all notes in a recyclerview
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}

package com.room.room.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.room.room.Model.Note;
import com.room.room.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    // vars
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    } // constructor


    public void insert(Note note)
    {
        repository.insert(note);
    } // insert method

    public void update(Note note)
    {
        repository.update(note);
    } // update method

    public void delete(Note note)
    {
        repository.delete(note);
    } // delete method

    public void deleteAll()
    {
        repository.deleteAllNotes();
    } // deleteAll method

    public LiveData<List<Note>> getAllNotes()
    {
        return allNotes;
    } // get all notes


}

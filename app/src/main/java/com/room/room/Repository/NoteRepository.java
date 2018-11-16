package com.room.room.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.room.room.Model.Note;
import com.room.room.RoomDataBase.NoteDao;
import com.room.room.RoomDataBase.NoteDatabase;

import java.util.List;

public class NoteRepository {

    // vars
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    } // constructor

    public void insert(Note note) {
        new InsertNoteAscyncTask(noteDao).execute(note);
    } // insert function

    public void update(Note note) {
        new UpdateNoteAscyncTask(noteDao).execute(note);

    } // update function

    public void delete(Note note) {
        new DeleteNoteAscyncTask(noteDao).execute(note);

    } // delete function

    public void deleteAllNotes() {
        new DeleteAllNoteAscyncTask(noteDao).execute();

    } // deleteAllNotes function

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    } // return live data function

    private static class InsertNoteAscyncTask extends AsyncTask<Note, Void, Void> {
        // vars
        private NoteDao noteDao;

        public InsertNoteAscyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        } // constructor

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        } // insert notes
    } // insert notes using async task

    private static class UpdateNoteAscyncTask extends AsyncTask<Note, Void, Void> {
        // vars
        private NoteDao noteDao;

        public UpdateNoteAscyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        } // constructor

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        } // insert notes
    } // update notes using async task

    private static class DeleteNoteAscyncTask extends AsyncTask<Note, Void, Void> {
        // vars
        private NoteDao noteDao;

        public DeleteNoteAscyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        } // constructor

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        } // insert notes
    } // insert notes using async task

    private static class DeleteAllNoteAscyncTask extends AsyncTask<Void, Void, Void> {
        // vars
        private NoteDao noteDao;

        public DeleteAllNoteAscyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        } // constructor

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        } // insert notes
    } // insert notes using async task

}

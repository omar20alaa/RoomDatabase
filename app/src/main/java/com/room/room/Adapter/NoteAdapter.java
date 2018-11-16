package com.room.room.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.room.room.Model.Note;
import com.room.room.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    // vars
    List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.tv_description.setText(currentNote.getDescription());
        holder.tv_title.setText(currentNote.getTitle());
        holder.tv_priority.setText(String.valueOf(currentNote.getPriority()));
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    } // set note method

    public Note getNoteAt(int position) {
        return notes.get(position);
    } // get note position from here

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // bind views
        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_priority)
        TextView tv_priority;

        @BindView(R.id.tv_description)
        TextView tv_description;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

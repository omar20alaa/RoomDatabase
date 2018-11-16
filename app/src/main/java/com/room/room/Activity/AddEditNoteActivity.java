package com.room.room.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.room.room.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditNoteActivity extends AppCompatActivity {

    // Bind Views
    @BindView(R.id.et_title)
    EditText et_title;

    @BindView(R.id.et_description)
    EditText et_description;

    @BindView(R.id.number_picker)
    NumberPicker number_picker;

    // vars
    public static final String EXTRA_ID = "com.room.room.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.room.room.EXTRA_TITLE";
    public static final String EXTRA_DESRIPTION = "com.room.room.EXTRA_DESRIPTION";
    public static final String EXTRA_PRIORITY = "com.room.room.EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        setNumber();
    }

    public void setNumber() {
        number_picker.setMinValue(1);
        number_picker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit  Note");
            et_title.setText(intent.getStringExtra(EXTRA_TITLE));
            et_description.setText(intent.getStringExtra(EXTRA_DESRIPTION));
            number_picker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {

            setTitle("Add  Note");
        }
    } // set number picker

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    } // on create option menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } // on item selected

    private void saveNote() {
        String title = et_title.getText().toString();
        String description = et_description.getText().toString();
        int priority = number_picker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    } // save note method
}

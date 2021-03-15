package uz.wiut.mad.StudentAttendanceManagementSystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText id_input, date_input;
    Spinner module_input;
    Button update_button, delete_button;
    String id_id, id, date, module;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id_input = findViewById(R.id.id_input2);
        date_input = findViewById(R.id.date_input2);
        module_input = findViewById(R.id.module_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(id);
        }

        update_button.setOnClickListener((view) -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            id = id_input.getText().toString().trim();
            date = date_input.getText().toString().trim();
            module = module_input.getSelectedItem().toString().trim();
            if (id.length() == 0){
                id_input.requestFocus();
                id_input.setError("FIELD CANNOT BE EMPTY");
            }else if (date.length() == 0){
                date_input.requestFocus();
                date_input.setError("FIELD CANNOT BE EMPTY");
            }
            else {
                myDB.updateData(id_id,id,date,module);
            }

        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });





    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id_id") && getIntent().hasExtra("id") &&
        getIntent().hasExtra("date") && getIntent().hasExtra("module")){
            //getting data
            id_id = getIntent().getStringExtra("id_id");
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            module = getIntent().getStringExtra("module");
            //setting data
            id_input.setText(id);
            date_input.setText(date);
        }else{
            Toast.makeText(this, "No data",Toast.LENGTH_SHORT).show();

        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Delete "+ id + " ? ");
        builder.setMessage(" Are you sure you want to delete "+ id + " ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id_id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
package uz.wiut.mad.StudentAttendanceManagementSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {
    EditText id_input, date_input;
    Spinner module_input;
    Button submit_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        id_input = findViewById(R.id.id_input);
        date_input = findViewById(R.id.date_input);
        module_input = findViewById(R.id.module_input);
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                if (id_input.length() == 0){
                    id_input.requestFocus();
                    id_input.setError("FIELD CANNOT BE EMPTY");
                }else if (date_input.length() == 0){
                    date_input.requestFocus();
                    date_input.setError("FIELD CANNOT BE EMPTY");
                }
                else {
                    myDB.AddUser(Integer.valueOf(id_input.getText().toString().trim()),
                            date_input.getText().toString().trim(),
                            module_input.getSelectedItem().toString().trim());
                }

            }
        });
    }
}
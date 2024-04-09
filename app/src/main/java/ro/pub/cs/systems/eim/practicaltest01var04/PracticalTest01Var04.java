package ro.pub.cs.systems.eim.practicaltest01var04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04 extends AppCompatActivity {

    Button navigate_to_secondary_activity_button, display_information_button;
    CheckBox checkbox_name_student, checkbox_group_student;
    EditText name_student, group_student;
    TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);


        navigate_to_secondary_activity_button = findViewById(R.id.navigate_to_secondary_activity_button);
        display_information_button = findViewById(R.id.display_information_button);
        checkbox_name_student = findViewById(R.id.checkbox_name_student);
        checkbox_group_student = findViewById(R.id.checkbox_group_student);
        name_student = findViewById(R.id.text_name_student);
        group_student = findViewById(R.id.text_group_student);
        text_view = findViewById(R.id.textView2);

        display_information_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                if (checkbox_name_student.isChecked()) {

                    if (name_student.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    text += name_student.getText().toString();
                }
                if (checkbox_group_student.isChecked()) {

                    if (group_student.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Group is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    text += " ";
                    text += group_student.getText().toString();
                }
                text_view.setText(text);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name_student.getText().toString());
        outState.putString("group", group_student.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("name")) {
            name_student.setText(savedInstanceState.getString("name"));
        }
        if (savedInstanceState.containsKey("group")) {
            group_student.setText(savedInstanceState.getString("group"));
        }
    }
}
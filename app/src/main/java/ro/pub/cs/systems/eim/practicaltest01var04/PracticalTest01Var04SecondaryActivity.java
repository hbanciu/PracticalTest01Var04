package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    EditText name, group;
    Button ok, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        name = findViewById(R.id.sent_name);
        group = findViewById(R.id.sent_group);

        ok = findViewById(R.id.ok_button);
        cancel = findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        String received_name = intent.getStringExtra("SENT_NAME_INTENT");
        String received_group = intent.getStringExtra("SENT_GROUP_INTENT");


        name.setText(received_name);
        group.setText(received_group);

// Intorc rezultate cand apas pe cele 2 butoane

        ok.setOnClickListener(v -> {
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        });

        cancel.setOnClickListener(v -> {
            Intent result = new Intent();
            setResult(RESULT_CANCELED, result);
            finish();
        });

    }
}
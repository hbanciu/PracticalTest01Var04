package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class PracticalTest01Var04 extends AppCompatActivity {

    Button navigate_to_secondary_activity_button, display_information_button;
    CheckBox checkbox_name_student, checkbox_group_student;
    EditText name_student, group_student;
    TextView text_view;

    private IntentFilter intentFilter = new IntentFilter();

    private int serviceStatus = 0;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    private final MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private static class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, Objects.requireNonNull(intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA)));
        }
    }

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

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Toast.makeText(this, "The activity returned with OK ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The activity returned with CANCEL ", Toast.LENGTH_LONG).show();
            }
        });

        navigate_to_secondary_activity_button.setOnClickListener(v -> {
            String sent_name_intent = name_student.getText().toString();
            String sent_group_intent = group_student.getText().toString();

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra("SENT_NAME_INTENT", sent_name_intent);
            intent.putExtra("SENT_GROUP_INTENT", sent_group_intent);
            activityResultLauncher.launch(intent);
            //startActivity(intent);
        });

        display_information_button.setOnClickListener(v -> {
            if (!name_student.getText().toString().equals("") && !name_student.getText().toString().equals("")
                    && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
                intent.putExtra("SERVICE_SENT_NAME", name_student.getText().toString());
                intent.putExtra("SERVICE_SENT_GROUP", group_student.getText().toString());

                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;


            }
        });

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

    }


        @Override
        protected void onSaveInstanceState (@NonNull Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putString("name", name_student.getText().toString());
            outState.putString("group", group_student.getText().toString());
        }

        @Override
        protected void onRestoreInstanceState (@NonNull Bundle savedInstanceState){
            super.onRestoreInstanceState(savedInstanceState);
            if (savedInstanceState.containsKey("name")) {
                name_student.setText(savedInstanceState.getString("name"));
            }
            if (savedInstanceState.containsKey("group")) {
                group_student.setText(savedInstanceState.getString("group"));
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(messageBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(messageBroadcastReceiver, intentFilter);
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(intent);
        super.onDestroy();
    }

    }

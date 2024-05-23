package com.example.payrollgenerator;

import static android.app.DownloadManager.COLUMN_ID;

import static com.example.payrollgenerator.DatabaseHelper.COLUMN_BS;
import static com.example.payrollgenerator.DatabaseHelper.COLUMN_HRA;
import static com.example.payrollgenerator.DatabaseHelper.COLUMN_NAME;
import static com.example.payrollgenerator.DatabaseHelper.COLUMN_OTHERS;
import static com.example.payrollgenerator.DatabaseHelper.COLUMN_PERQUISITES;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private EditText idEdit, nameEdit, basicpayEdit, hraEdit, perquisitesEdit, othersEdit;
    private Button addButton, exitButton;
    DatabaseHelper dbHelper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        // Initialize EditTexts
        idEdit = findViewById(R.id.idEdit);
        nameEdit = findViewById(R.id.nameEdit);
        basicpayEdit = findViewById(R.id.basicpayEdit);
        hraEdit = findViewById(R.id.hraEdit);
        perquisitesEdit = findViewById(R.id.perquisitesEdit);
        othersEdit = findViewById(R.id.othersEdit);

        // Initialize Buttons
        addButton = findViewById(R.id.add);
        exitButton = findViewById(R.id.exit);

        // Set click listeners
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve text from EditText fields
                String idStr = idEdit.getText().toString().toUpperCase();
                String name = nameEdit.getText().toString();
                String basicPayStr = basicpayEdit.getText().toString();
                String hraStr = hraEdit.getText().toString();
                String perquisitesStr = perquisitesEdit.getText().toString();
                String othersStr = othersEdit.getText().toString();

// Convert Strings to appropriate data types
                Integer id = null;
                Double basicPay = null;
                Double hra = null;
                Double perquisites = null;
                Double others = null;

                try {
                    id = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    // Handle the case where the id text cannot be parsed as an integer
                    e.printStackTrace();
                }

                try {
                    basicPay = Double.parseDouble(basicPayStr);
                } catch (NumberFormatException e) {
                    // Handle the case where the basic pay text cannot be parsed as a double
                    e.printStackTrace();
                }

                try {
                    hra = Double.parseDouble(hraStr);
                } catch (NumberFormatException e) {
                    // Handle the case where the HRA text cannot be parsed as a double
                    e.printStackTrace();
                }

                try {
                    perquisites = Double.parseDouble(perquisitesStr);
                } catch (NumberFormatException e) {
                    // Handle the case where the perquisites text cannot be parsed as a double
                    e.printStackTrace();
                }

                try {
                    others = Double.parseDouble(othersStr);
                } catch (NumberFormatException e) {
                    // Handle the case where the others text cannot be parsed as a double
                    e.printStackTrace();
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_ID, id); // Provide a specific value for the ID
                values.put(DatabaseHelper.COLUMN_NAME, name);
                values.put(DatabaseHelper.COLUMN_BS, basicPay);
                values.put(DatabaseHelper.COLUMN_PERQUISITES, perquisites);
                values.put(DatabaseHelper.COLUMN_HRA, hra);
                values.put(DatabaseHelper.COLUMN_OTHERS, others);

// Inserting Row
                long newRowId = db.insert("employees", null, values);



                //finish();
                Toast.makeText(AddActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();


                // Save the data or perform any desired action
                // For now, let's just return to the main activity
                // You can save this data using SharedPreferences, SQLite, or any other storage mechanism

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(AddActivity.this, "action cancelled", Toast.LENGTH_SHORT).show();
                // Just return to the main activity without saving anything

            }
        });
    }

}

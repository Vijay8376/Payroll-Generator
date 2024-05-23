package com.example.payrollgenerator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    EditText editText;
    Button saveButton, againButton, exitButton;
    ImageView topBanner, bannerGrey, bannerMagenta, videoButtons, bottomBorder, bottomVideo;
    TextView titleText, addTitle, idText, nameText, basicpayText, hraText, perquisitesText, othersText;
    TextView idEdit, nameEdit, basicpayEdit, hraEdit, perquisitesEdit, othersEdit;

    String savedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        // Initialize views
        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.button);
        againButton = findViewById(R.id.again);
        exitButton = findViewById(R.id.exit);
        topBanner = findViewById(R.id.topBanner);
        bannerGrey = findViewById(R.id.bannerGrey);
        bannerMagenta = findViewById(R.id.bannerMagenta);
        videoButtons = findViewById(R.id.videoButtons);
        bottomBorder = findViewById(R.id.bottomBorder);
        bottomVideo = findViewById(R.id.bottomVideo);
        titleText = findViewById(R.id.titleText);
        addTitle = findViewById(R.id.addTitle);
        idText = findViewById(R.id.idText);
        nameText = findViewById(R.id.nameText);
        basicpayText = findViewById(R.id.basicpayText);
        hraText = findViewById(R.id.hraText);
        perquisitesText = findViewById(R.id.perquisitesText);
        othersText = findViewById(R.id.othersText);
        idEdit = findViewById(R.id.idEdit);
        nameEdit = findViewById(R.id.nameEdit);
        basicpayEdit = findViewById(R.id.basicpayEdit);
        hraEdit = findViewById(R.id.hraEdit);
        perquisitesEdit = findViewById(R.id.perquisitesEdit);
        othersEdit = findViewById(R.id.othersEdit);

        // Set OnClickListener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text entered in the EditText
                String savedValue = editText.getText().toString();
                Integer id = null;

                try {
                    id = Integer.parseInt(savedValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // Create an instance of your DatabaseHelper class
                DatabaseHelper dbHelper = new DatabaseHelper(DisplayActivity.this);

                // Get a readable database instance
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                // Define the columns you want to retrieve
                String[] projection = {
                        DatabaseHelper.COLUMN_NAME,
                        DatabaseHelper.COLUMN_BS,
                        DatabaseHelper.COLUMN_HRA,
                        DatabaseHelper.COLUMN_PERQUISITES,
                        DatabaseHelper.COLUMN_OTHERS
                };

                // Define the selection criteria
                String selection = DatabaseHelper.COLUMN_ID + " = ?";
                String[] selectionArgs = {String.valueOf(id)};

                // Query the database
                Cursor cursor = db.query(
                        "employees",   // The table to query
                        projection,                 // The array of columns to return (null to return all)
                        selection,                  // The columns for the WHERE clause
                        selectionArgs,              // The values for the WHERE clause
                        null,                       // don't group the rows
                        null,                       // don't filter by row groups
                        null                        // don't order
                );

                // Check if the cursor has any rows
                if (cursor != null && cursor.moveToFirst()) {
                    // Retrieve values from the cursor
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                    double basicPay = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BS));
                    double hra = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_HRA));
                    double perquisites = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PERQUISITES));
                    double others = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_OTHERS));

                    // Update the TextViews with the retrieved values
                    idEdit.setText(String.valueOf(id));
                    nameEdit.setText(name);
                    basicpayEdit.setText(String.valueOf(basicPay));
                    hraEdit.setText(String.valueOf(hra));
                    perquisitesEdit.setText(String.valueOf(perquisites));
                    othersEdit.setText(String.valueOf(others));
                } else {
                    // If no row found, display a message
                    Toast.makeText(DisplayActivity.this, "No data found for ID: " + id, Toast.LENGTH_SHORT).show();
                }

                // Close the cursor and database
                if (cursor != null) {
                    cursor.close();
                }
                db.close();
            }
        });


        // Set OnClickListener for the again button
        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restart the activity to allow the user to enter a new value
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(DisplayActivity.this, "cleared", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for the exit button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the main activity

                finish();
                Toast.makeText(DisplayActivity.this, "Action cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

package com.example.payrollgenerator;

import android.annotation.SuppressLint;
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

public class GenerateActivity extends AppCompatActivity {

    EditText editText;
    TextView nameEdit, basicpayEdit, hraEdit, perquisitesEdit, addTitle, titleText, nameText, basicpayText, hraText, perquisitesText;
    Button button, again, exit;
    SQLiteDatabase mDatabase;
    String name;
    double bs, hra, perquisites, others, grossSalary, netSalary;
    ImageView topBanner, bannerGrey, bannerMagenta, videoButtons, bottomBorder, bottomVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_activity);

        editText = findViewById(R.id.editText);
        nameEdit = findViewById(R.id.nameEdit);
        basicpayEdit = findViewById(R.id.basicpayEdit);
        hraEdit = findViewById(R.id.hraEdit);
        perquisitesEdit = findViewById(R.id.perquisitesEdit);
        addTitle = findViewById(R.id.addTitle);
        titleText = findViewById(R.id.titleText);
        nameText = findViewById(R.id.nameText);
        basicpayText = findViewById(R.id.basicpayText);
        hraText = findViewById(R.id.hraText);
        perquisitesText = findViewById(R.id.perquisitesText);
        button = findViewById(R.id.button);
        again = findViewById(R.id.again);
        exit = findViewById(R.id.exit);
        topBanner = findViewById(R.id.topBanner);
        bannerGrey = findViewById(R.id.bannerGrey);
        bannerMagenta = findViewById(R.id.bannerMagenta);
        videoButtons = findViewById(R.id.videoButtons);
        bottomBorder = findViewById(R.id.bottomBorder);
        bottomVideo = findViewById(R.id.bottomVideo);

        // Initialize your SQLiteDatabase instance here
        // mDatabase = ...

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String savedValue = editText.getText().toString();
                Integer id = null;

                try {
                    id = Integer.parseInt(savedValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // Create an instance of your DatabaseHelper class
                DatabaseHelper dbHelper = new DatabaseHelper(GenerateActivity.this);

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

                    // Update EditTexts
                    nameEdit.setText(name);
                    basicpayEdit.setText(String.valueOf(basicPay));

                    // Calculate Gross Salary and Net Salary
                    grossSalary = basicPay + hra + perquisites + others;
                    netSalary = grossSalary - 12000;

                    // Update EditTexts
                    hraEdit.setText(String.valueOf(grossSalary));
                    perquisitesEdit.setText(String.valueOf(netSalary));
                } else {
                    Toast.makeText(GenerateActivity.this, "Employee not found", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restart the activity
                recreate();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the main activity
                finish();
            }
        });
    }

}

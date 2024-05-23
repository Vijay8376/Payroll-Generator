package com.example.payrollgenerator;

import android.content.ContentValues;
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

public class EditActivity extends AppCompatActivity {private EditText editText, nameEdit, basicpayEdit, perquisitesEdit, hraEdit, othersEdit;
    private Button button, again, exit;
    private ImageView topBanner, bannerGrey, bannerMagenta, videoButtons, bottomBorder, bottomVideo;
    private TextView titleText, addTitle, nameText, basicpayText, perquisitesText, hraText, othersText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        dbHelper = new DatabaseHelper(EditActivity.this);

        // Initialize EditTexts
        editText = findViewById(R.id.editText);
        nameEdit = findViewById(R.id.nameEdit);
        basicpayEdit = findViewById(R.id.basicpayEdit);
        perquisitesEdit = findViewById(R.id.perquisitesEdit);
        hraEdit = findViewById(R.id.hraEdit);
        othersEdit = findViewById(R.id.othersEdit);

        // Initialize Buttons
        button = findViewById(R.id.button);
        again = findViewById(R.id.again);
        exit = findViewById(R.id.exit);

        // Initialize ImageViews
        topBanner = findViewById(R.id.topBanner);
        bannerGrey = findViewById(R.id.bannerGrey);
        bannerMagenta = findViewById(R.id.bannerMagenta);
        videoButtons = findViewById(R.id.videoButtons);
        bottomBorder = findViewById(R.id.bottomBorder);
        bottomVideo = findViewById(R.id.bottomVideo);

        // Initialize TextViews
        titleText = findViewById(R.id.titleText);
        addTitle = findViewById(R.id.addTitle);
        nameText = findViewById(R.id.nameText);
        basicpayText = findViewById(R.id.basicpayText);
        perquisitesText = findViewById(R.id.perquisitesText);
        hraText = findViewById(R.id.hraText);
        othersText = findViewById(R.id.othersText);

        // Button click listeners
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to main activity
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
                //Toast.makeText(this,"action cancelled",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkData() {
        // Retrieve ID from EditText
        String idStr = editText.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter an ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert ID to integer
        int id = Integer.parseInt(idStr);

        // Get a readable database instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Check if data exists in database
        Cursor cursor = db.rawQuery("SELECT * FROM employees WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Data located", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No such data found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void editData() {
        // Retrieve values from EditTexts

        String idStr = editText.getText().toString().trim();
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

        DatabaseHelper dbHelper = new DatabaseHelper(EditActivity.this);

        // Get a writable database instance
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define the where clause with the ID column
        String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
        // Define the selection arguments
        String[] whereArgs = {String.valueOf(id)};

        // Delete the row from the database
        db.delete("employees",whereClause,whereArgs);


        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, id); // Provide a specific value for the ID
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_BS, basicPay);
        values.put(DatabaseHelper.COLUMN_PERQUISITES, perquisites);
        values.put(DatabaseHelper.COLUMN_HRA, hra);
        values.put(DatabaseHelper.COLUMN_OTHERS, others);

// Inserting Row
        long newRowId = db.insert("employees", null, values);
        finish();

        Toast.makeText(this,"edit successful",Toast.LENGTH_SHORT).show();
    }


}

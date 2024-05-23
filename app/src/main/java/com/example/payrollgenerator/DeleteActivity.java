package com.example.payrollgenerator;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {
    EditText idEdit;
    Button clearButton, exitButton;
    ImageView topBanner, bannerGrey, bannerMagenta, bottomBorder, bottomVideo;
    TextView titleText, addTitle, idText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);

        // Initialize views
        idEdit = findViewById(R.id.idEdit);
        clearButton = findViewById(R.id.clear);
        exitButton = findViewById(R.id.exit);
        topBanner = findViewById(R.id.topBanner);
        bannerGrey = findViewById(R.id.bannerGrey);
        bannerMagenta = findViewById(R.id.bannerMagenta);
        bottomBorder = findViewById(R.id.bottomBorder);
        bottomVideo = findViewById(R.id.bottomVideo);
        titleText = findViewById(R.id.titleText);
        addTitle = findViewById(R.id.addTitle);
        idText = findViewById(R.id.idText);


        // Set OnClickListener for the clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text entered in the EditText
                String idStr = idEdit.getText().toString();

                if (idStr.isEmpty()) {
                    // If the EditText is empty, show a toast message and return
                    Toast.makeText(DeleteActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the ID string to an integer
                int id = Integer.parseInt(idStr);

                // Create an instance of your DatabaseHelper class
                DatabaseHelper dbHelper = new DatabaseHelper(DeleteActivity.this);

                // Get a writable database instance
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Define the where clause with the ID column
                String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
                // Define the selection arguments
                String[] whereArgs = {String.valueOf(id)};

                // Delete the row from the database
                int rowsDeleted = db.delete("employees",whereClause,whereArgs);

                if (rowsDeleted > 0) {
                    // If rows were deleted, show a toast message indicating success
                    Toast.makeText(DeleteActivity.this, "Row with ID " + id + " deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // If no rows were deleted, show a toast message indicating failure
                    Toast.makeText(DeleteActivity.this, "No row found with ID " + id, Toast.LENGTH_SHORT).show();
                }

                // Close the database connection
                db.close();

                // Return to the main activity
                //finish();
            }
        });


        // Set OnClickListener for the exit button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message
                finish();
                Toast.makeText(DeleteActivity.this, "Action cancelled", Toast.LENGTH_SHORT).show();

                // Return to the main activity

            }
        });
    }

}

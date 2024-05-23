# Payroll Generation Database Management System

This project is an Android application developed as a mini-project for the Database Management System (DBMS) laboratory. The app serves as a payroll generation system where employee details can be added, viewed, edited, deleted, and payrolls can be generated.

## Features

- **Add Employee Details**: Add new employee information including Employee ID, Name, Basic Pay, HRA, Perquisites, and Other Allowances.
- **View Employee Details**: View existing employee details by entering the Employee ID.
- **Edit Employee Details**: Edit existing employee information.
- **Delete Employee Details**: Delete employee information from the database.
- **Generate Payslip**: Generate a payslip that calculates the Gross Salary and Net Pay based on the entered details.

## Technologies Used

- **Front-end**: XML for designing the user interface.
- **Back-end**: Java for application logic.
- **Database**: SQLite for data storage and management.

## Layout Files

- `main_activity.xml`: The main screen of the app.
- `add_activity.xml`: Screen to add employee details to the database.
- `display_activity.xml`: Screen to view employee details from the database.
- `edit_activity.xml`: Screen to edit employee details.
- `delete_activity.xml`: Screen to delete employee details from the database.
- `generate_activity.xml`: Screen to generate the payslip.

## Classes

- `MainActivity`: Handles the main screen functionalities with image buttons redirecting to specific activities.
- `AddActivity`: Allows users to enter and add employee details. It ensures no duplicate Employee ID exists before adding data to the database.
- `ViewActivity`: Prompts user to enter Employee ID to view details. Displays employee details if found.
- `EditActivity`: Allows editing of employee details after searching by Employee ID.
- `DeleteActivity`: Deletes employee details based on the entered Employee ID.
- `GenerateActivity`: Generates a payslip showing Basic Pay, Gross Salary, and Net Pay based on the entered Employee ID.
- `DatabaseHelper`: Manages database operations such as adding, updating, deleting, and querying employee details.

## UI Elements

- **ImageViews**: Used across different activities for display purposes.
- **TextViews**: Used to display static text in the UI.
- **EditTexts**: Used for user input in various activities.
- **ImageButtons**: Present in `MainActivity` to navigate to other activities.
- **Buttons**: For actions like Add, Edit, Delete, Search, Generate, Clear, and Exit.

## Functionality Details

### Main Activity

- **Navigation Buttons**: Redirects to Add, View, Edit, Delete, and Generate activities.

### Add Activity

- **Inputs**: Employee ID, Name, Basic Pay, HRA, Perquisites, Other Allowances.
- **Buttons**: 
  - `Add`: Validates input and adds employee details to the database if no duplicate Employee ID exists.
  - `Exit`: Returns to the main activity.

### View Activity

- **Inputs**: Employee ID.
- **Buttons**: 
  - `Search`: Retrieves and displays employee details if found.
  - `Clear`: Clears the input fields.
  - `Exit`: Returns to the main activity.

### Edit Activity

- **Inputs**: Employee ID.
- **Buttons**: 
  - `Search`: Checks if the Employee ID exists and allows editing of details if found.
  - `Edit`: Updates the employee details in the database.
  - `Exit`: Returns to the main activity.

### Delete Activity

- **Inputs**: Employee ID.
- **Buttons**: 
  - `Delete`: Deletes the employee details from the database if found.
  - `Exit`: Returns to the main activity.

### Generate Activity

- **Inputs**: Employee ID.
- **Buttons**: 
  - `Generate`: Calculates and displays the Gross Salary and Net Pay based on the employee details.
  - `Again`: Clears the input fields for a new search and calculation.
  - `Exit`: Returns to the main activity.

## Installation and Usage

1. Clone the repository to your local machine.
   ```bash
   git clone https://github.com/your-username/payroll-generation-dbms.git
   ```
2. Open the project in Android Studio.
3. Build and run the application on an emulator or physical device.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

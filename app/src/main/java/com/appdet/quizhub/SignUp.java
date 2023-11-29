package com.appdet.quizhub;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUp extends AppCompatActivity {

    private EditText etUsername, etFirstname, etLastname, etPassword, etSignUpConfirmPassword, etBirthdate;
    private Button btnCreateAccount;
    private DBHelper dbHelper;
    private SystemBars systemBars;
    private AutoCompleteTextView etGender, etGradeLevel;
    ArrayAdapter<String> gender_adapter, gradelevel_adapter;
    String[] GradeLevel_Options = {
            "Kindergarten",
            "Elementary",
            "Junior High",
            "Senior High",
            "College",
            "Professional"
    };
    String[] GenderOptions = {
            "Prefer not to say",
            "Male",
            "Female"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // to call the SystemBars class that immerse the screen and reveal if swiped up
        systemBars = new SystemBars(this);
        systemBars.enableSwipeToToggleSystemBars();

        etUsername = findViewById(R.id.etSignUpUsername);
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etBirthdate = findViewById(R.id.etBirthdate);
        etGradeLevel = findViewById(R.id.etGradeLevel);
        etGender = findViewById(R.id.etGender);
        etPassword = findViewById(R.id.etSignUpPassword);
        etSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        gender_List();
        gradeLevel_List();
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }

    public void gender_List(){
        gender_adapter = new ArrayAdapter<String>(this, R.layout.list_item,GenderOptions);
        etGender.setAdapter(gender_adapter);

        etGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gender_options = parent.getItemAtPosition(position).toString();
            }
        });

    }
    public void gradeLevel_List(){
        gradelevel_adapter = new ArrayAdapter<String>(this, R.layout.list_item, GradeLevel_Options);
        etGradeLevel.setAdapter(gradelevel_adapter);

        etGradeLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String grade_options = parent.getItemAtPosition(position).toString();
            }
        });
    }
    public void showDatePicker(View v) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) -> {
                    // Handle the selected date
                    String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                    etBirthdate.setText(selectedDate);
                },
                currentYear,
                currentMonth,
                currentDay
        );

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    private void registerAccount() {
        // Retrieve the input values
        String username = etUsername.getText().toString().trim();
        String firstname = etFirstname.getText().toString().trim();
        String lastname = etLastname.getText().toString().trim();
        String dateOfBirth = etBirthdate.getText().toString().trim();
        String gradeLevel = etGradeLevel.getText().toString().trim();
        String gender = etGender.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etSignUpConfirmPassword.getText().toString().trim();

        // Perform input validation
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname)
                || TextUtils.isEmpty(dateOfBirth) || TextUtils.isEmpty(gradeLevel) || TextUtils.isEmpty(gender)
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the age based on the birthdate
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = sdf.parse(dateOfBirth);
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }
            // Set the age value in the User object
            User user = new User();
            user.setAge(age);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a new User object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(firstname + " " + lastname);
        user.setDateOfBirth(dateOfBirth);
        user.setGradeLevel(gradeLevel);
        user.setGender(gender);

        // Insert the user into the database
        dbHelper = new DBHelper(this);
        boolean isInserted = dbHelper.InsertUser(user);
        if (isInserted) {
            Toast.makeText(this, "Account registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to register account", Toast.LENGTH_SHORT).show();
        }

        // Clear the input fields
        etUsername.setText("");
        etFirstname.setText("");
        etLastname.setText("");
        etBirthdate.setText("");
        etGradeLevel.setText("");
        etGender.setText("");
        etPassword.setText("");
        etSignUpConfirmPassword.setText("");
    }
}

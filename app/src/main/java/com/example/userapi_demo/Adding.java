package com.example.userapi_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.userapi_demo.model.Dog;
import com.example.userapi_demo.retrofit.DogApi;
import com.example.userapi_demo.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        initializeComponents();
    }

    private void initializeComponents(){
        EditText nameInput = findViewById(R.id.nameField);
        EditText ageInput = findViewById(R.id.ageField);
        EditText breedInput = findViewById(R.id.breedField);
        RadioGroup sexInput = findViewById(R.id.sexBtnGroup);
        RadioGroup sizeInput = findViewById(R.id.sizeBtnGroup);


        EditText descriptionInput = findViewById(R.id.descriptionField);
        EditText birthdayInput = findViewById(R.id.birthdayField);
        Button submitButton = findViewById(R.id.submitBtn);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        submitButton.setOnClickListener(view -> {
            String name = String.valueOf(nameInput.getText());
            int age = Integer.parseInt(String.valueOf(ageInput.getText()));
            String breed = String.valueOf(breedInput.getText());


            // Get the checked radio button id from the RadioGroup
            int sexRadioButtonId = sexInput.getCheckedRadioButtonId();
            int sizeRadioButtonId = sizeInput.getCheckedRadioButtonId();

            // Find the radio button by id
            RadioButton sexRadioButton = findViewById(sexRadioButtonId);
            RadioButton sizeRadioButton = findViewById(sizeRadioButtonId);

            String sex = sexRadioButton.getText().toString();
            String size = sizeRadioButton.getText().toString();

            String description = String.valueOf(descriptionInput.getText());
            String birthday = String.valueOf(birthdayInput.getText());

            Dog dog = new Dog();

            dog.setName(name);
            dog.setAge(age);
            dog.setBreed(breed);
            dog.setSex(sex);
            dog.setSize(size);
            dog.setDescription(description);
            dog.setBirthday(birthday);

            dogApi.save(dog).enqueue(new Callback<Dog>() {
                @Override
                public void onResponse(Call<Dog> call, Response<Dog> response) {
                    if (response.isSuccessful()) {
                        String successMessage = "Success";
                        showToast(successMessage);
                    } else {
                        String errorMessage = "Failed: " + response.message();
                        showErrorDialog(errorMessage);
                    }
                }

                @Override
                public void onFailure(Call<Dog> call, Throwable t) {
                    String errorMessage = "Failed: " + t.getMessage();
                    showErrorDialog(errorMessage);
                    Logger.getLogger(Adding.class.getName()).log(Level.SEVERE, "Error Occurred!", t);
                }
            });
        });
    }

    private void showToast(String message) {
        Toast.makeText(Adding.this, message, Toast.LENGTH_SHORT).show();
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(Adding.this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


}
package com.naimur.aliensdiary.feature.verification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.naimur.aliensdiary.R;
import com.naimur.aliensdiary.feature.main.MainActivity;
import com.naimur.aliensdiary.util.Utils;

public class VerificationActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, passcodeEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verification);

        nameEditText = findViewById(R.id.nameEditText);
        passcodeEditText = findViewById(R.id.passcodeEditText);

        findViewById(R.id.goButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().trim().equals("")) {
                    ((TextInputLayout) findViewById(R.id.nameInputLayout)).setError("Enter name!");
                    return;
                }
                if (passcodeEditText.getText().toString().trim().equals("")) {
                    ((TextInputLayout) findViewById(R.id.passcodeInputLayout)).setError("Enter passcode!");
                    return;
                }
                Utils.setBasePath("t");
                startActivity(new Intent(VerificationActivity.this, MainActivity.class));
            }
        });
    }
}

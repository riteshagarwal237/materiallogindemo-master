package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText etForName;
    EditText etForEmail;
    EditText etForPwd;
    Button btnForSignup;
    TextView tvForLoginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etForName = (EditText) findViewById(R.id.input_name);
        etForEmail = (EditText) findViewById(R.id.input_email);
        etForPwd = (EditText) findViewById(R.id.input_password);
        btnForSignup = (Button) findViewById(R.id.btn_signup);
        tvForLoginLink = (TextView) findViewById(R.id.link_login);

        btnForSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        tvForLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        btnForSignup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = etForName.getText().toString();
        String email = etForEmail.getText().toString();
        String password = etForPwd.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        btnForSignup.setEnabled(true);
        //  setResult(RESULT_OK, null);
        Intent intent = new Intent(getApplicationContext(), HomeDrawerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnForSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = etForName.getText().toString();
        String email = etForEmail.getText().toString();
        String password = etForPwd.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            etForName.setError("at least 3 characters");
            valid = false;
        } else {
            etForName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etForEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etForEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etForPwd.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            etForPwd.setError(null);
        }

        return valid;
    }
}
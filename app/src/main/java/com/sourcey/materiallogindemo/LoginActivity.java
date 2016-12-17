package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText etForEmail;
    EditText etForPwd;
    Button btnForLogin;
    TextView tvForSignUp;
    String email, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etForEmail = (EditText) findViewById(R.id.input_email);
        etForPwd = (EditText) findViewById(R.id.input_password);
        btnForLogin = (Button) findViewById(R.id.btn_login);
        tvForSignUp = (TextView) findViewById(R.id.link_signup);

        btnForLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvForSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) { //validating the form
            onLoginFailed();
            return;
        }

        btnForLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }*/

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btnForLogin.setEnabled(true);
        Intent intent = new Intent(this, HomeDrawerActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnForLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        email = etForEmail.getText().toString();
        password = etForPwd.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etForEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etForEmail.setError(null);//dismissing the error text
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
//hello


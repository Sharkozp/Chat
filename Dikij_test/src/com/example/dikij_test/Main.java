package com.example.dikij_test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Activity implements OnClickListener {
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passText);
        Button loginButton = (Button) findViewById(R.id.signinButton);

        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // if input data correct change screen
        if (correctLogin()) {
            changeScreen(this, Chat.class);
        }
    }

    private void changeScreen(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);

        // transmission login to next screen
        intent.putExtra("login", email.getText().toString());
        Activity activity = (Activity) context;
        activity.startActivity(intent);
        activity.finish();
    }

    /*
     * Method for check inputed value
     */
    private boolean correctLogin() {
        boolean correct = true;
        StringBuilder error = new StringBuilder();
        if (!isEmail(email.getText().toString())) {

            error.append(getResources().getString(R.string.error_email))
                    .append("\n");
            correct = false;
        }

        if (!isValidPass(password.getText().toString())) {
            error.append(getResources().getString(R.string.error_pass)).append(
                    "\n");
            correct = false;
        }

        // if password less 6 letters set error
        if (password.getText().toString().length() < 6) {
            error.append(getResources().getString(R.string.error_password_len))
                    .append("\n");
            correct = false;
        }

        if (!correct) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder
                    .setTitle(R.string.error_title)
                    .setMessage(error)
                    .setPositiveButton(R.string.ok_button,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.dismiss();
                                }
                            }
                    );

            AlertDialog alert = alertBuilder.create();
            alert.show();
        }

        return correct;
    }

    /*
     * Check email on valid
     */
    private boolean isEmail(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /*
     * Check password on valid
     */
    private boolean isValidPass(String pass) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+");
        Matcher m = p.matcher(pass);
        return m.matches();
    }

}

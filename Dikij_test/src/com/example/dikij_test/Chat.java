package com.example.dikij_test;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Chat extends Activity {

	private String login;
	private EditText messageHistory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);

		// get email from user
		login = getIntent().getExtras().getString("login");

		messageHistory = (EditText) findViewById(R.id.messageHistory);
		final EditText message = (EditText) findViewById(R.id.message);
		Button backButton = (Button) findViewById(R.id.backButton);
		final Button sendButton = (Button) findViewById(R.id.sendButton);

		message.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				// if input text null set button disabled
				if (message.length() > 0) {
					sendButton.setEnabled(true);
				} else {
					sendButton.setEnabled(false);
				}
			}
		});

		//set back button action
		backButton.setOnClickListener(new ChangeScreen().changeScreen(this,
				Main.class));
		
		//set send button action
		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StringBuilder newMessage = new StringBuilder();
				newMessage.append(login).append(" (").append(getCurrentDate())
						.append("):\n").append(message.getText().toString())
						.append("\n");

				messageHistory.append(newMessage);

				// clearing message editText
				message.setText("");

				// Robot answered
				setAnswer();

			}
		});

	}

	/*
	 * Set new robot answer
	 */
	private void setAnswer() {
		Robot robot = new Robot();
		StringBuilder newMessage = new StringBuilder();
		newMessage.append(robot.getName()).append(" (")
				.append(getCurrentDate()).append("):\n")
				.append(robot.getPhrase()).append("\n");
		messageHistory.append(newMessage);
	}

	private String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd.MM.yyyy hh:mm:ss");
		return dateFormat.format(new Date());
	}

}

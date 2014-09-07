package com.example.dikij_test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.dikij_test.system.CustomActionBarActivity;
import com.example.dikij_test.system.Robot;

/**
 * Created by Oleksandr Dykyi.
 */
public class Chat extends CustomActionBarActivity implements
		View.OnClickListener {
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	Robot robot = new Robot();
	private Map<String, String> data;
	private SimpleAdapter adapter;
	private ListView dataList;
	private EditText message;
	private String login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);

		// get email from user
		login = getIntent().getExtras().getString("login");
		dataList = (ListView) findViewById(R.id.messageHistory);
		message = (EditText) findViewById(R.id.message);
		Button backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(this);
		final Button sendButton = (Button) findViewById(R.id.sendButton);
		sendButton.setOnClickListener(this);
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backButton:
			changeScreen(Chat.this, Main.class);
			break;
		case R.id.sendButton:
			updateMessageScreen();
			message.setText("");
			break;
		}
	}

	/*
	 * Set message and robot answer
	 */
	private void updateMessageScreen() {
		// add user message
		addMessage(login, getCurrentDate(), message.getText().toString());

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// add robot answer after 1,5s
				addMessage(robot.getName(), getCurrentDate(), robot.getPhrase());
			}
		}, 1500);
	}

	private void addMessage(String login, String date, String message) {
		data = new HashMap<String, String>();
		data.put("login", login);
		data.put("date", date);
		data.put("message", message);
		list.add(data);
		String[] from = new String[] { "login", "date", "message" };
		int[] to = new int[] { R.id.autorName, R.id.date, R.id.messageText };
		adapter = new SimpleAdapter(this, list, R.layout.item, from, to);
		dataList.setAdapter(adapter);
		scrollListViewToBottom();
	}

	private String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"(yyyy.MM.dd HH:mm:ss):", Locale.getDefault());
		return dateFormat.format(new Date());
	}

	private void changeScreen(Context context, Class<?> clazz) {
		Intent intent = new Intent(context, clazz);
		Activity activity = (Activity) context;
		activity.startActivity(intent);
		activity.finish();
	}

	private void scrollListViewToBottom() {
		dataList.post(new Runnable() {
			@Override
			public void run() {
				// Select the last row so it will scroll into view...
				dataList.setSelection(adapter.getCount() - 1);
			}
		});
	}
}

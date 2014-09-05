package com.example.dikij_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ChangeScreen implements OnClickListener {
	private Context context;
	private Class<?> clazz;
	private Activity activity;

	public OnClickListener changeScreen(Context context, Class<?> clazz) {
		this.context = context;
		this.clazz = clazz;
		activity = (Activity) context;
		return this;
	}

	

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, clazz);
		activity.startActivity(intent);
		activity.finish();
	}

}

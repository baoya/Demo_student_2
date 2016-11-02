package com.demo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class zhuce extends Activity {
	private EditText re_user_name, re_user_pass, re_user_pass_2;
	private Button re_register, re_return;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);

		re_user_name = (EditText) findViewById(R.id.re_user_name);
		re_user_pass = (EditText) findViewById(R.id.re_user_pass);
		re_user_pass_2 = (EditText) findViewById(R.id.re_user_pass_2);
		re_register = (Button) findViewById(R.id.re_register);
		re_return = (Button) findViewById(R.id.re_return);

		re_return.setOnClickListener(new OnClickListener() {
			// ���������һ����½����
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(zhuce.this, denglu.class));
			}
		});

		re_register.setOnClickListener(new OnClickListener() {
			// ���ע���˺ź�����
			@Override
			public void onClick(View arg0) {
				String username = re_user_name.getText().toString();
				String userpass = re_user_pass.getText().toString();
				String userpass_2 = re_user_pass_2.getText().toString();
				if (username.length() != 0 && userpass.length() < 6
						&& userpass_2.length() < 6) {
					Toast.makeText(zhuce.this, "���볤�ȹ��̣���������ڵ�¼6������",
							Toast.LENGTH_SHORT).show();
				} else if (username.length() != 0 && userpass.length() >= 6
						&& userpass_2.length() >= 6) {
					if (userpass.equals(userpass_2)) {
						saveusername(username, userpass_2);
						Toast.makeText(zhuce.this, "ע��ɹ���", Toast.LENGTH_SHORT)
								.show();
						Intent intent = new Intent(zhuce.this, denglu.class);
						startActivity(intent);
					} else {
						Toast.makeText(zhuce.this, "��������������벻һ�������������룡",
								Toast.LENGTH_SHORT).show();
					}

				}
			}
		});
	}

	public void saveusername(String username, String userpass) {
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = openFileOutput("username", Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(username + "\r\n" + userpass);// w-i_n_d_o-sƽ̨���ı��ļ��Ļ��з�ʽ
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}

}

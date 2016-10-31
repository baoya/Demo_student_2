package com.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class denglu extends Activity {
	private EditText user_name,user_pass;
	private CheckBox remember_pass;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private Button entry;
	private TextView tvregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.denglu_menu);
       
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = (EditText) findViewById(R.id.user_name);
        user_pass = (EditText) findViewById(R.id.user_pass);
        remember_pass = (CheckBox) findViewById(R.id.remember_pass);
        entry = (Button) findViewById(R.id.entry);
        tvregister = (TextView) findViewById(R.id.tvregister);
        
        SpannableString spannableString = new SpannableString("没有账号？注册");
        spannableString.setSpan(new ClickableSpan() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(denglu.this,zhuce.class));
				
			}
		}, 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        tvregister.setText(spannableString);
        tvregister.setMovementMethod(LinkMovementMethod.getInstance());
        
        boolean isremember = pref.getBoolean("user_pass", false);
        if(isremember){
        	String account = pref.getString("account", "");
        	String password = pref.getString("password", "");
        	user_name.setText(account);
        	user_pass.setText(password);
        	remember_pass.setChecked(true);
        }
        //登录按钮单击事件
        entry.setOnClickListener(new OnClickListener() {
        	//返回用户名
        	public String loadname(){
        		int count = 0;
        		FileInputStream in = null;
        		BufferedReader reader = null;
        		StringBuilder content_name = new StringBuilder();
        		
        		try {
					in = openFileInput("username");
					reader = new BufferedReader(new InputStreamReader(in));
					
					String name = "";
					while((name = reader.readLine()) != null){
						if(count == 0)
						{
							content_name.append(name);
						}
						count++;
					}

					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content_name.toString();
				
			}
        	//返回密码
        	public String loadpass(){
        		int count = 0;
        		FileInputStream in = null;
        		BufferedReader reader = null;
				StringBuilder content_pass = new StringBuilder();
        		
        		try {
					in = openFileInput("username");
					reader = new BufferedReader(new InputStreamReader(in));
					
					String pass = "";
					while((pass = reader.readLine()) != null){
						if(count == 1)
						{
							content_pass.append(pass);
						}
						count++;
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content_pass.toString();
				
			}
        	
			@Override
			public void onClick(View arg0) {
				
				
				String account = user_name.getText().toString();
				String password = user_pass.getText().toString();
				if(account.equals(loadname())&&password.equals(loadpass())){
					editor = pref.edit();
					if(remember_pass.isChecked()){
						editor.putBoolean("user_pass", true);
						editor.putString("account", account);
						editor.putString("password", password);
					}
					else{
						editor.clear();
					}
					editor.commit();
				Intent intent = new Intent(denglu.this,student.class);
				startActivity(intent);
				}
				else{
					Toast.makeText(denglu.this, "输入错误", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }

	

}

package com.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_student extends Activity {
	private Button btnadd_1,btnreturn;
	private EditText etstuid,etname,etdepar,etclass,etsex,etage,etphone,etqq,etadress;
	private SQLiteDatabase dbwrite;
	private student_sql db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_stu);
		
		etstuid = (EditText) findViewById(R.id.etstuid);
		etname = (EditText) findViewById(R.id.etname);
		etdepar = (EditText) findViewById(R.id.etdepar);
		etclass = (EditText) findViewById(R.id.etclass);
		etsex = (EditText) findViewById(R.id.etsex);
		etage = (EditText) findViewById(R.id.etage);
		etphone = (EditText) findViewById(R.id.etphone);
		etqq = (EditText) findViewById(R.id.etqq);
		etadress = (EditText) findViewById(R.id.etadress);
		
		btnreturn = (Button) findViewById(R.id.btnreturn);
		btnadd_1 = (Button) findViewById(R.id.btnadd_1);
		
		db = new student_sql(this);
		dbwrite = db.getReadableDatabase();
		
		btnadd_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ContentValues cv = new ContentValues();
				cv.put("stuid", etstuid.getText().toString());
				cv.put("name", etname.getText().toString());
				cv.put("depar", etdepar.getText().toString());
				cv.put("class", etclass.getText().toString());
				cv.put("sex", etsex.getText().toString());
				cv.put("age", etage.getText().toString());
				cv.put("phone", etphone.getText().toString());
				cv.put("qq", etqq.getText().toString());
				cv.put("adress", etadress.getText().toString());
				
				dbwrite.insert("user", null, cv);
				Toast.makeText(add_student.this, "Ìí¼Ó³É¹¦", Toast.LENGTH_SHORT).show();
			}
		});
		btnreturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(add_student.this,student.class);
				startActivity(intent);
			}
		});
	}
}

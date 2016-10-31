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

public class updata_stu extends Activity {

	private Button up_btnup,up_btnreturn;
	private EditText etstuid,etname,etdepar,etclass,etsex,etage,etphone,etqq,etadress;
	private SQLiteDatabase dbwrite;
	private student_sql stu_db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.update_stu);
		
		etstuid = (EditText) findViewById(R.id.up_etstuid);
		etname = (EditText) findViewById(R.id.up_etname);
		etdepar = (EditText) findViewById(R.id.up_etdepar);
		etclass = (EditText) findViewById(R.id.up_etclass);
		etsex = (EditText) findViewById(R.id.up_etsex);
		etage = (EditText) findViewById(R.id.up_etage);
		etphone = (EditText) findViewById(R.id.up_etphone);
		etqq = (EditText) findViewById(R.id.up_etqq);
		etadress = (EditText) findViewById(R.id.up_etadress);
		
		up_btnup = (Button) findViewById(R.id.up_btnup);
		up_btnreturn = (Button) findViewById(R.id.up_btnreturn);
		
		stu_db = new student_sql(this);
		stu_db.getWritableDatabase();
		
		
		final Intent intent = getIntent();
		String tvstuid = intent.getStringExtra("tvstuid");
		String tvname = intent.getStringExtra("tvname");
		String tvdepar = intent.getStringExtra("tvdepar");
		String tvclass = intent.getStringExtra("tvclass");
		String tvsex = intent.getStringExtra("tvsex");
		String tvage = intent.getStringExtra("tvage");
		String tvphone= intent.getStringExtra("tvphone");
		String tvqq = intent.getStringExtra("tvqq");
		String tvadress = intent.getStringExtra("tvadress");
		
		etstuid.setText(tvstuid);
		etname.setText(tvname);
		etdepar.setText(tvdepar);
		etclass.setText(tvclass);
		etsex.setText(tvsex);
		etage.setText(tvage);
		etphone.setText(tvphone);
		etqq.setText(tvqq);
		etadress.setText(tvadress);
		
		up_btnup.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				dbwrite = stu_db.getWritableDatabase();
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
				
				
				int itemid = intent.getIntExtra("itemid", 0);
				
				dbwrite.update("user", cv,"_id=?",new String[]{itemid+""} );

				Toast.makeText(updata_stu.this, "ÐÞ¸Ä³É¹¦", Toast.LENGTH_SHORT).show();
			}
		});
		
		up_btnreturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(updata_stu.this,student.class);
				startActivity(intent);	
			}
		});
		
		
	}
}

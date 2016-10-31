package com.demo;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class fr_dianhua extends Fragment{
	private SQLiteDatabase dbread;
	private student_sql db;
	private SimpleCursorAdapter adapter;
	private ListView phone_list;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db = new student_sql(getActivity());
		dbread = db.getReadableDatabase();
	}
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.dianhua, null);
		phone_list = (ListView) view.findViewById(R.id.phone_list);
		
		Cursor c1 = dbread.query("user", new String[]{"_id,stuid,name,phone"}, null, null, null, null, "stuid ASC");
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.p_list, c1, 
				new String[]{"stuid","name","phone"},
				new int[]{R.id.p_id,R.id.p_name,R.id.p_phone});

		phone_list.setAdapter(adapter);
		
		phone_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View v, int position,
					long id) {
				final Cursor c = adapter.getCursor();
				c.moveToPosition(position);
				String name = c.getString(c.getColumnIndex("name"));
				new AlertDialog.Builder(getActivity()).setTitle("提醒").setMessage("您确定拨打"+name+"的电话吗?")
				.setNegativeButton("取消", null).setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						String phone = c.getString(c.getColumnIndex("phone"));
						Toast.makeText(getActivity(), phone, Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
						startActivity(intent);
					}
				}).show();
			}
		});
		return view;
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}

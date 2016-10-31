package com.demo;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class fr_dianhua extends Fragment{
	private SQLiteDatabase dbread,dbwrite;
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
		
//		Cursor c = dbread.query("user", null, null, null, null, null, "stuid ASC");
//		adapter = new SimpleCursorAdapter(getActivity(), R.layout.p_list, c, 
//				new String[]{"stuid","name",null,null,null,null,"phone",null,null},
//				new int[]{R.id.p_id,R.id.p_name,(Integer) null,(Integer) null,
//				(Integer) null,(Integer) null,R.id.p_phone,(Integer) null,(Integer) null});
//
//		phone_list.setAdapter(adapter);
		
		return view;
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}

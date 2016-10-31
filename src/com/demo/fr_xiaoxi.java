package com.demo;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class fr_xiaoxi extends Fragment{
	private SQLiteDatabase dbread,dbwrite;
	private student_sql db;
	private SimpleCursorAdapter adapter;
	private Button btnadd,btnabout;
	private ListView listView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db = new student_sql(getActivity());
		dbread = db.getReadableDatabase();
		dbwrite = db.getWritableDatabase();
	}
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.xiaoxi, null);
		
		listView = (ListView) view.findViewById(R.id.list);
		btnadd = (Button) view.findViewById(R.id.btnadd);
		btnabout = (Button) view.findViewById(R.id.btnabout);
		
		//添加按钮
		btnadd.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),add_student.class);
				startActivity(intent);
			}
		});
		//关于按钮
		btnabout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Toast.makeText(getActivity(), "感谢您安装此APP!", Toast.LENGTH_SHORT).show();
			}
		});
		
		//加载数据库
		Cursor c = dbread.query("user", null, null, null, null, null, "stuid ASC");
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.stu_list, c, 
				new String[]{"stuid","name","depar","class","sex","age","phone","qq","adress"},
				new int[]{R.id.tvstuid,R.id.tvname,R.id.tvdepar,R.id.tvclass,
				R.id.tvsex,R.id.tvage,R.id.tvphone,R.id.tvqq,R.id.tvadress});

		listView.setAdapter(adapter);
		
		//点击
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View v, final int position,
					long id) {
				new AlertDialog.Builder(getActivity()).setTitle("提醒").setMessage("您确定要修改该项吗?")
				.setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
				TextView tvstuid = (TextView)v.findViewById(R.id.tvstuid);
				TextView tvname = (TextView)v.findViewById(R.id.tvname);
				TextView tvdepar = (TextView)v.findViewById(R.id.tvdepar);
				TextView tvclass = (TextView)v.findViewById(R.id.tvclass);
				TextView tvsex = (TextView)v.findViewById(R.id.tvsex);
				TextView tvage = (TextView)v.findViewById(R.id.tvage);
				TextView tvphone = (TextView)v.findViewById(R.id.tvphone);
				TextView tvqq = (TextView)v.findViewById(R.id.tvqq);
				TextView tvadress = (TextView)v.findViewById(R.id.tvadress);
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						Cursor c = adapter.getCursor();
						c.moveToPosition(position);
						int itemid = c.getInt(c.getColumnIndexOrThrow("_id"));
						
						Intent intent = new Intent(getActivity(),updata_stu.class);
						intent.putExtra("itemid", itemid);
						intent.putExtra("tvstuid", tvstuid.getText().toString());
						intent.putExtra("tvname", tvname.getText().toString());
						intent.putExtra("tvdepar", tvdepar.getText().toString());
						intent.putExtra("tvclass", tvclass.getText().toString());
						intent.putExtra("tvsex", tvsex.getText().toString());
						intent.putExtra("tvage", tvage.getText().toString());
						intent.putExtra("tvphone", tvphone.getText().toString());
						intent.putExtra("tvqq", tvqq.getText().toString());
						intent.putExtra("tvadress", tvadress.getText().toString());
						startActivity(intent);
						
					}
				}).show();
				
			}
		});
		
		//长按ListView
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				new AlertDialog.Builder(getActivity()).setTitle("提醒").setMessage("您确定要删除该项吗?")
				.setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {		
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Cursor c = adapter.getCursor();
						c.moveToPosition(position);
						
						int itemid = c.getInt(c.getColumnIndexOrThrow("_id"));
						dbwrite.delete("user", "_id=?", new String[]{itemid+""});
						refreshListView();	
						Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
					}
				}).show();
				return true;
			}
		});
		return view;
	}
	public void refreshListView(){
		Cursor c = dbread.query("user", null, null, null, null, null, "stuid ASC");
		adapter.changeCursor(c);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}

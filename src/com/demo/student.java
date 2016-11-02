package com.demo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class student extends Activity /*implements OnClickListener*/{
	private RadioGroup rg;
	
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private SlidingMenu slidingMenu;
	
	private Button rc_set;
	private TextView text;
    @SuppressWarnings({ })
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
        
        
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
        rc_set = (Button) findViewById(R.id.rc_set);
        text = (TextView) findViewById(R.id.text);
        
        rc_set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				text.setText("从https://github.com/baoya上下载源码," +
						"项目：Demo_student_2，类库：ActionBraSherlock，类库：SlidingMenu");
			}
		});
         
        	
        rg = (RadioGroup) findViewById(R.id.rg);
        //加载信息界面
        transaction = manager.beginTransaction();
        fr_xiaoxi xiaoxi = new fr_xiaoxi();
		transaction.replace(R.id.fragment_down, xiaoxi, "xiaoxi");
		transaction.addToBackStack("xiaoxi");
		transaction.commit();
 
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				transaction = manager.beginTransaction();
				
				switch (checkedId) {
				case R.id.rb_1:
					fr_xiaoxi xiaoxi = new fr_xiaoxi();
					transaction.replace(R.id.fragment_down, xiaoxi, "xiaoxi");
					transaction.addToBackStack("xiaoxi");
					break;

				case R.id.rb_2:
					fr_dianhua dianhua = new fr_dianhua();
					transaction.replace(R.id.fragment_down, dianhua, "dianhua");
					transaction.addToBackStack("dianhua");
					break;
				}
				transaction.commit();
			}
		});
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
}

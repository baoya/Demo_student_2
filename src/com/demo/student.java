package com.demo;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class student extends Activity /*implements OnClickListener*/{
	private RadioGroup rg;
	
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private SlidingMenu slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
        
        manager = getFragmentManager(); 
        	
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

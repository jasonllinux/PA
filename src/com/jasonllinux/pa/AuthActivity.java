package com.jasonllinux.pa;

import com.jasonllinux.app.db.UserAuthDataSource;
import com.jasonllinux.app.user.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AuthActivity extends Activity {
	
	private Button button_login ;
	private Button button_exit;
	private Button button_reg;
	private EditText edit_user;
	private EditText edit_passwd;
	
	private UserAuthDataSource userAuthDataBase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //widget
        button_login = (Button) findViewById(R.id.button_login);
        button_exit = (Button) findViewById(R.id.button_exit);
        button_reg = (Button) findViewById(R.id.button_register);
        edit_user = (EditText) findViewById(R.id.edit_username);
        edit_passwd = (EditText) findViewById(R.id.edit_username);
        
        //db -> new
        
        //绑定监听器 
        button_login.setOnClickListener(loginClickListener);
        button_exit.setOnClickListener(exitOnClickListener);
        
        //db
        userAuthDataBase = new UserAuthDataSource(this);
        userAuthDataBase.open();
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private OnClickListener loginClickListener = new OnClickListener() {
		
    	public void onClick(View v) {
			String name = edit_user.getText().toString();
			String passwd = edit_passwd.getText().toString();
			if(name==null || passwd==null) {
				//TODO 提示 输入
			}else {
				//验证 Query
				//连接数据库
				System.out.println("name: "+name);
				System.out.println("passwd: "+passwd);
				//验证成功
				//TODO 跳转到首页-----传输 UserName---------------------
				Intent intent = new Intent();              
	            intent.setClass(AuthActivity.this, HomeActivity.class);   
	            //传递数据
	            Bundle mBundle = new Bundle();  
	            mBundle.putString("name", "jasonllinux");//压入数据  
	            intent.putExtras(mBundle);  
	                //调用一个新的Activity
	            startActivity(intent);
	            //------------------------------
				//验证失败
				//TODO 
			}
		}
	};
	
	//退出程序Button监听器
	private OnClickListener exitOnClickListener =  new OnClickListener() {
		public void onClick(View arg0) {
			//exit
			System.exit(0);
			//finish
//			finish();
		}
		
	};
    
    @Override
    protected void onResume() {
      userAuthDataBase.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      userAuthDataBase.close();
      super.onPause();
    }
    
    
    
}

package com.jasonllinux.pa;

import com.jasonllinux.app.db.DBHelper;
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
	private Button button_cancel;
	private Button button_reg;
	private EditText edit_user;
	private EditText edit_passwd;
	
	private DBHelper dbHelper;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //widget
        button_login = (Button) findViewById(R.id.button_login);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_reg = (Button) findViewById(R.id.button_register);
        edit_user = (EditText) findViewById(R.id.edit_username);
        edit_passwd = (EditText) findViewById(R.id.edit_username);
        
        //db -> new
        
        //add listener 
        button_login.setOnClickListener(loginClickListener);
        
        
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
			//TODO 调试一下
			if(name==null || passwd==null) {
				//TODO 提示 输入
			}else {
				//验证
				//连接数据库
				System.out.println("name: "+name);
				System.out.println("passwd: "+passwd);
				//验证成功
				//TODO 跳转到首页--------------------------
				Intent intent = new Intent();              
	            intent.setClass(AuthActivity.this, HomeActivity.class);          
	                //调用一个新的Activity
	            startActivity(intent);
	            //------------------------------
				//验证失败
				//TODO 
			}
		}
	};
    
    //TODO add user
    private void addUser(User user) {
    	
    }
    
    //TODO update user
    private void updateUser(User user) {
    	
    }
    
    
    
}

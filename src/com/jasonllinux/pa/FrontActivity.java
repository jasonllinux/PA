package com.jasonllinux.pa;

import com.jasonllinux.app.db.UserAuthDataSource;
import com.jasonllinux.app.face.FaceActivity;
import com.jasonllinux.app.social.SinaAuthorizeActivity;
import com.jasonllinux.app.user.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FrontActivity extends Activity {
	
	private Button button_login ;
	private Button button_exit;
	private Button button_reg;
	private Button button_test_sina;
	private Button button_test_face;
	
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
        button_test_sina = (Button) findViewById(R.id.button_test);
        button_test_face = (Button) findViewById(R.id.button_testface);
        
        edit_user = (EditText) findViewById(R.id.edit_username);
        edit_passwd = (EditText) findViewById(R.id.edit_username);
        
        //db -> new
        
        //绑定监听器 
        button_login.setOnClickListener(loginClickListener);
        button_exit.setOnClickListener(exitOnClickListener);
        button_test_sina.setOnClickListener(testOnClickListener);
        button_test_face.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(FrontActivity.this, FaceActivity.class);
				startActivity(intent);
			}
		});
        
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
//				System.out.println("name: "+name);
//				System.out.println("passwd: "+passwd);
				//若验证成功
				//TODO 跳转到首页-----传输 UserName---------------------
				Intent intent = new Intent();              
	            intent.setClass(FrontActivity.this, HomeActivity.class);   
	            //添加传递数据
	            Bundle mBundle = new Bundle();  
	            mBundle.putString("name", "jasonllinux");//压入数据  
	            intent.putExtras(mBundle);  
	            FrontActivity.this.startActivity(intent);
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
		}
		
	};
	
	private OnClickListener testOnClickListener =  new OnClickListener() {
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(FrontActivity.this, SinaAuthorizeActivity.class);
			FrontActivity.this.startActivity(intent);
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
    
    
    //按下back键，退出确认
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
           if (keyCode == KeyEvent.KEYCODE_BACK){  
        	   
        	   showExitDialog(this);
                   return true;
           }
           return super.onKeyDown(keyCode, event);
    }
    

    //TODO 修改布局文件
    private void showExitDialog(Context context) {
//    	System.out.println("show Dialog");
    	AlertDialog.Builder builder = new AlertDialog.Builder(context); 
    	builder.setTitle(R.string.alter_exit_title);  
        builder.setMessage(R.string.alter_exit_message);  
        
        builder.setPositiveButton(R.string.alter_exit_cancel, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				
			} });
        
        builder.setNegativeButton(R.string.alter_exit_leave, new DialogInterface.OnClickListener() {

    			public void onClick(DialogInterface arg0, int arg1) {
    				System.exit(0);
    				
    			} });
        
        builder.show(); 
    	
    }
    
    
}

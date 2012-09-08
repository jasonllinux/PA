package com.jasonllinux.app.social;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jasonllinux.pa.R;
import com.weibo.net.AccessToken;
import com.weibo.net.DialogError;
import com.weibo.net.Oauth2AccessToken;
import com.weibo.net.RequestToken;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;

public class SinaAuthorizeActivity extends Activity {
//	/** Called when the activity is first created. */
	private Button mLogin;
	private TextView mToken;
//
	private static final String URL_ACTIVITY_CALLBACK = "weiboandroidsdk://TimeLineActivity";
	private static final String FROM = "xweibo";
//	
//	// 设置appkey及appsecret，如何获取新浪微博appkey和appsecret请另外查询相关信息，此处不作介绍
	private static final String CONSUMER_KEY = "2320561935";// 替换为开发者的appkey，例如"1646212960";
	private static final String CONSUMER_SECRET = "2320561935";// 替换为开发者的appkey，例如"94098772160b6f8ffc1315374d8861f9";
//	
	private String username = "";
	private String password = "";
//
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sina_auth);
//		
		mToken = (TextView) this.findViewById(R.id.sina_text);
		mLogin = (Button) this.findViewById(R.id.sina_button);
		mLogin.setText("oauth!");
		mLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (v == mLogin) {
					Weibo weibo = Weibo.getInstance();
					weibo.setupConsumerConfig(CONSUMER_KEY, CONSUMER_SECRET);

					// Oauth2.0
					// 隐式授权认证方式
					weibo.setRedirectUrl("------");// 此处回调页内容应该替换为与appkey对应的应用回调页
					// 对应的应用回调页可在开发者登陆新浪微博开发平台之后，
					// 进入我的应用--应用详情--应用信息--高级信息--授权设置--应用回调页进行设置和查看，
					// 应用回调页不可为空

					weibo.authorize(SinaAuthorizeActivity.this,
							new AuthDialogListener());

					 try {
					 // Oauth2.0 认证方式
					 Weibo.setSERVER("https://api.weibo.com/2/");
					 Oauth2AccessToken at =
					 weibo.getOauth2AccessToken(SinaAuthorizeActivity.this,
					 Weibo.getAppKey(), Weibo.getAppSecret(), username,
					 password);
					 // xauth认证方式
					 /*
					 * Weibo.setSERVER("http://api.t.sina.com.cn/");
					 * AccessToken at =
					 * weibo.getXauthAccessToken(TextActivity.this,
					 * Weibo.APP_KEY, Weibo.APP_SECRET, "", "");
					 * mToken.setText(at.getToken());
					 */
					 RequestToken requestToken =
					 weibo.getRequestToken(SinaAuthorizeActivity.this,
					 Weibo.getAppKey(), Weibo.getAppSecret(),
					 SinaAuthorizeActivity.URL_ACTIVITY_CALLBACK);
					 mToken.setText(requestToken.getToken());
					 Uri uri =
					 Uri.parse(SinaAuthorizeActivity.URL_ACTIVITY_CALLBACK);
					 startActivity(new Intent(Intent.ACTION_VIEW, uri));
					
					 } catch (WeiboException e) {
					 e.printStackTrace();
					 } // mToken.setText(at.getToken());
					

				}
			}
		});

	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
//
	
	private OnClickListener listenser = new OnClickListener() {
		
		public void onClick(View view) {
			
		}
	}; 
	
	
	class AuthDialogListener implements WeiboDialogListener {

		public void onComplete(Bundle values) {
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			mToken.setText("access_token : " + token + "  expires_in: "
					+ expires_in);
			AccessToken accessToken = new AccessToken(token, CONSUMER_SECRET);
			accessToken.setExpiresIn(expires_in);
			Weibo.getInstance().setAccessToken(accessToken);
			Intent intent = new Intent();
			intent.setClass(SinaAuthorizeActivity.this, TestSinaAuthActivity.class);
			startActivity(intent);
		}

		public void onError(DialogError e) {
			Toast.makeText(getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Auth cancel",
					Toast.LENGTH_LONG).show();
		}

		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}
//
}

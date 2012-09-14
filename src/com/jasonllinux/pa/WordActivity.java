package com.jasonllinux.pa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WordActivity extends Activity   implements OnClickListener {
	
	private Button button_prev ;
	private Button button_done ;
	private Button button_next ;
	
	private TextView word_word;
	private TextView word_tran;
	
	private int currentWordNo; //当前单词计数
	private int totalWordCount; //总共单词数量
	private ArrayList<HashMap<String, Object>> thisWordList;
	private Map<String, Object> WordMap;
	
	  // 记录当前单词数据
    private String thisWord = null;
    private String thisPhonetics = null;
    private String thisTranslation = null;
    
    private String currentWord;
    private String currentPhonetics;
    private String currentTranslation;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_word);
		
		this.init();
	}
	
	private void init() {
		init_widget();
		WordMap = new HashMap<String, Object>();
	}
	
	//初始化控件
	private void init_widget() {
		button_done = (Button) findViewById(R.id.button_dict_done);
		button_next = (Button) findViewById(R.id.button_dict_next);
		button_prev = (Button) findViewById(R.id.button_dict_prev);
		
		button_done.setOnClickListener(this);
		button_next.setOnClickListener(this);
		button_prev.setOnClickListener(this);
		
		word_word = (TextView) findViewById(R.id.word_word);
		word_tran = (TextView) findViewById(R.id.word_tran);
		
	}
	
	//初始化配置文件，读取配置文件
	private void init_config() {
		
	}
	

	
	private void showWord() {
		this.setTitle("Lesson");
		//取字典中的单词
		
		//显示在文本框中
		
	}


	//Button时间监听
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_dict_prev :
			showPrev();
			break;
		case R.id.button_dict_next :
			showNext();
			break ;
		case R.id.button_dict_done :
			break ;
		default :
			break ;
		
		}
	}
	
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//            switch (keyCode) {
//            case KeyEvent.KEYCODE_DPAD_UP:
//                    showPrev();
//                    break;
//            case KeyEvent.KEYCODE_DPAD_DOWN:
//                    showNext();
//                    break;
////            case KeyEvent.KEYCODE_BACK: {
//                    // new AlertDialog.Builder(this)
//                    // .setIcon(android.R.drawable.ic_dialog_alert)
//                    // .setTitle(R.string.dialog_title)
//                    // .setMessage(R.string.learn_word_exit)
//                    // .setNegativeButton(R.string.dialog_negativeButton, new
//                    // DialogInterface.OnClickListener() {
//                    // @Override
//                    // public void onClick(DialogInterface dialog, int which) {
//                    // }
//                    // })
//                    // .setPositiveButton(R.string.dialog_positiveButton, new
//                    // DialogInterface.OnClickListener() {
//                    // public void onClick(DialogInterface dialog, int whichButton) {
////                    finish();
//                    // }
//                    // }).show();
////                    break;
////            	}
//            }
//            return true;
//    }
    
    
	
	@Override
	protected void onDestroy() {
		this.saveConfig();
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		this.saveConfig();
		super.onStop();
	}


	private void showPrev() {
//		System.out.println("Shwo prev");  
		int no = currentWordNo;
		  //如果还有单词
          if (--no >= 0) {
                  currentWordNo--;
                  showWord();
          } else {
        	  //如果没有单词
        	  Toast.makeText(this, "没有单词了,亲！", Toast.LENGTH_SHORT).show();
          }
		
		
	}
	
	private void showNext() {
//		System.out.println("Show next");
		//减1
		int no = currentWordNo;
		//如果还有单词
        if (++no < totalWordCount) {
                currentWordNo++;
                showWord();
        }else {
        	//TODO 如果没有单词 
        	 Toast.makeText(this, "没有单词了,亲！", Toast.LENGTH_SHORT).show();
        }
	}
	
	//TODO 保存
	//保存历史记录
	private void saveConfig() {
		
	}

	
}

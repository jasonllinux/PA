package com.jasonllinux.pa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jasonllinux.app.dict.DbAdapter;
import com.jasonllinux.app.dict.DbAdapter.WordBook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WordActivity extends Activity implements OnClickListener {

	private Button button_prev;
	private Button button_done;
	private Button button_next;

	private TextView word_word;
	private TextView word_phone;
	private TextView word_tran;

	private int currentWordNo; // 当前单词计数
	private int totalWordCount; // 总共单词数量
	private ArrayList<HashMap<String, Object>> words;
	private Map<String, Object> currentWord;

	// 记录当前单词数据
	private String thisWord = null;
	private String thisPhonetics = null;
	private String thisTranslation = null;

	//标签
	private String WORD = "word"; 
	private String PHONETICS = "phonetics";
	private String TRANSLATION = "translation";

	private DbAdapter mDbHelper; // 数据库适配器
	private Cursor mDiaryCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_word);

		this.init();
		
		//TODO 获取LessonNo
	}

	private void init() {
		init_widget();
		init_words();
//		init_config();

	}

	// 初始化控件
	private void init_widget() {
		button_done = (Button) findViewById(R.id.button_dict_done);
		button_next = (Button) findViewById(R.id.button_dict_next);
		button_prev = (Button) findViewById(R.id.button_dict_prev);

		button_done.setOnClickListener(this);
		button_next.setOnClickListener(this);
		button_prev.setOnClickListener(this);

		word_word = (TextView) findViewById(R.id.word_word); //单词
		word_phone = (TextView) findViewById(R.id.word_phone); //音标
		word_tran = (TextView) findViewById(R.id.word_tran);  //翻译

	}

	// 初始化配置文件，读取配置文件
	private void init_config() {

	}

	//TODO  初始化数据变量
	private void init_words() {
		this.mDbHelper = new DbAdapter(this);
		this.currentWord = new HashMap<String, Object>();
		this.words = new ArrayList<HashMap<String, Object>>();

		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("Wait");
		pd.setMessage("Waiting!......");
		pd.setIcon(android.R.drawable.ic_dialog_info);
		pd.setOnDismissListener(new OnDismissListener() {

			public void onDismiss(DialogInterface arg0) {
				if (words != null && words.size() > 0) {
					totalWordCount = words.size();
					// currentWordNo = 0;
//					currentWordNo = Config.init().getPreviewWordIndex(
//							currentLessonNo);// 获取上次记忆的单词位置
					showWord();
				}
			}
		});
		pd.show();
		//线程操作
		new Thread() {
			@Override
			public void run() {
				//TODO 获得所有的单词映射链表和个数
//				words = 
				totalWordCount =  words.size();
				pd.dismiss();
			}
		}.start();
		
		
	}


	//显示单词
	private void showWord() {
		//TODO 详细化 
		this.setTitle("Lesson");
		// 取字典中的单词
		currentWord = words.get(currentWordNo);
		thisWord = (String) currentWord.get(WORD);
		thisPhonetics = (String) currentWord.get(PHONETICS);
		thisTranslation = (String) currentWord.get(TRANSLATION);
		
		//显示
		this.word_word.setText(thisWord);
		this.word_phone.setText(thisPhonetics);
		this.word_tran.setText(thisTranslation);
		

		// 显示在文本框中

	}

	// Button时间监听
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_dict_prev:
			showPrev();
			break;
		case R.id.button_dict_next:
			showNext();
			break;
		case R.id.button_dict_done:
			break;
		default:
			break;

		}
	}

	//
	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// switch (keyCode) {
	// case KeyEvent.KEYCODE_DPAD_UP:
	// showPrev();
	// break;
	// case KeyEvent.KEYCODE_DPAD_DOWN:
	// showNext();
	// break;
	// // case KeyEvent.KEYCODE_BACK: {
	// // new AlertDialog.Builder(this)
	// // .setIcon(android.R.drawable.ic_dialog_alert)
	// // .setTitle(R.string.dialog_title)
	// // .setMessage(R.string.learn_word_exit)
	// // .setNegativeButton(R.string.dialog_negativeButton, new
	// // DialogInterface.OnClickListener() {
	// // @Override
	// // public void onClick(DialogInterface dialog, int which) {
	// // }
	// // })
	// // .setPositiveButton(R.string.dialog_positiveButton, new
	// // DialogInterface.OnClickListener() {
	// // public void onClick(DialogInterface dialog, int whichButton) {
	// // finish();
	// // }
	// // }).show();
	// // break;
	// // }
	// }
	// return true;
	// }

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
		// System.out.println("Shwo prev");
		int no = currentWordNo;
		// 如果还有单词
		if (--no >= 0) {
			currentWordNo--;
			showWord();
		} else {
			// 如果没有单词
			Toast.makeText(this, "没有单词了,亲！", Toast.LENGTH_SHORT).show();
		}

	}

	private void showNext() {
		// System.out.println("Show next");
		// 减1
		int no = currentWordNo;
		// 如果还有单词
		if (++no < totalWordCount) {
			currentWordNo++;
			showWord();
		} else {
			// TODO 如果没有单词
			Toast.makeText(this, "没有单词了,亲！", Toast.LENGTH_SHORT).show();
		}
	}

	// TODO 保存
	// 保存历史记录
	private void saveConfig() {

	}

}

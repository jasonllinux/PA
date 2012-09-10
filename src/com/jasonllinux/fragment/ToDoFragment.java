package com.jasonllinux.fragment;

import com.jasonllinux.pa.R;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ToDoFragment extends ListFragment{
	
	private List<Map<String, Object>> mData;
	
	private Button update;
	
    String[] presidents = {
            "Dwight D. Eisenhower",
            "John F. Kennedy",
            "Lyndon B. Johnson",
            "Richard Nixon",
            "Gerald Ford",
            "Jimmy Carter",
            "Ronald Reagan",
            "George H. W. Bush",
            "Bill Clinton",
            "George W. Bush",
            "Barack Obama"
        };
	
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mData = getData();
        
        
//        setListAdapter(new ArrayAdapter<String>(
//					        		getActivity(),
//					                android.R.layout.simple_expandable_list_item_2,
//					                presidents)
//                					);
        //TODO 改变布局文件
        
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.unit_listview_todo, new String[]{"time","todo_content"}, 
        		new int[] {R.id.time, R.id.todo_content});
        setListAdapter(adapter);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
    	View view = inflater.inflate(R.layout.listview_todo, container, false);
    	update = (Button) view.findViewById(R.id.button_update_todo);
    	update.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				System.out.println("Update todo data");
			}
		});
    	
    	return view;
    }

   
    
//    @Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		super.onCreateOptionsMenu(menu, inflater);
//		
//	}
    
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	
//      MenuItem add = menu.add(0, 1, 0, "Save");  
//      MenuItem open = menu.add(0, 2, 1, "Open");  
//      MenuItem close = menu.add(0, 3, 2, "Close");  
//      add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
//      open.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
//      close.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
//		return true;
//    }

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
////		super.onCreateOptionsMenu(menu, inflater);
//		System.out.println("修改");
//		inflater.inflate(R.menu.todo_menu, menu);
//	}

	public void onListItemClick(ListView parent, View v, int position, long id) {          
        Toast.makeText(getActivity(), 
            "You have selected " + mData.get(position).get("time"), 
            Toast.LENGTH_SHORT).show();
        } 
    
    @Override
    public void onStop()
    {
        super.onStop();
    }
    
    //TODO 获取显示数据
    //从数据库获取
    private List<Map<String, Object>> getData() {
    	 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	 
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("time", "2011");
         map.put("todo_content", "eat");
         list.add(map);
         
         map = new HashMap<String, Object>();
         map.put("time", "2012");
         map.put("todo_content", "look");
         list.add(map);
         
         map = new HashMap<String, Object>();
         map.put("time", "2013");
         map.put("todo_content", "Here");
         list.add(map);
         
         map = new HashMap<String, Object>();
         map.put("time", "2014");
         map.put("todo_content", "Jasonllinux");
         list.add(map);
         
         map = new HashMap<String, Object>();
         map.put("time", "2015");
         map.put("todo_content", "look");
         list.add(map);
         
         map = new HashMap<String, Object>();
         map.put("time", "2016");
         map.put("todo_content", "哈哈");
         list.add(map);
         
         return list;
    }

}

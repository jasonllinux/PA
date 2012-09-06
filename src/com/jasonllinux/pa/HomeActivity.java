package com.jasonllinux.pa;

import com.jasonllinux.fragment.HomeFragment;
import com.jasonllinux.fragment.MoneyFragment;
import com.jasonllinux.fragment.TestFragment;
import com.jasonllinux.fragment.ToDoFragment;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 // setup Action Bar for tabs
	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    // remove the activity title to make space for tabs
	    actionBar.setDisplayShowTitleEnabled(false);
	    
	    // instantiate fragment for the tab
	    Tab tab = actionBar.newTab().setText(R.string.fragment_1)
								    .setTabListener(new TabListener<HomeFragment>(
                                    this, "frag_todo", HomeFragment.class)
                                    );
	    actionBar.addTab(tab);
	    
	    tab = actionBar.newTab().setText(R.string.fragment_2)
			    .setTabListener(new TabListener<ToDoFragment>(
                this, "frag2", ToDoFragment.class)
                );
        actionBar.addTab(tab);
        
        tab = actionBar.newTab().setText(R.string.fragment_3)
			    .setTabListener(new TabListener<MoneyFragment>(
                this, "frag3", MoneyFragment.class)
                );
        actionBar.addTab(tab);
	    
	}
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
    	
//        MenuItem add = menu.add(0, 1, 0, "Save");  
//        MenuItem open = menu.add(0, 2, 1, "Open");  
//        MenuItem close = menu.add(0, 3, 2, "Close");  
//        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
//        open.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
//        close.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
        return true;  
    }
	
	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
		
	    private Fragment mFragment;
	    private final Activity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;

	    /** Constructor used each time a new tab is created.
	      * @param activity  The host Activity, used to instantiate the fragment
	      * @param tag  The identifier tag for the fragment
	      * @param clz  The fragment's Class, used to instantiate the fragment
	      */
	    public TabListener(Activity activity, String tag, Class<T> clz) {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	    }


	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	        // Check if the fragment is already initialized
	        if (mFragment == null) {
	            // If not, instantiate and add it to the activity
	            mFragment = Fragment.instantiate(mActivity, mClass.getName());
	            ft.add(android.R.id.content, mFragment, mTag);
	        } else {
	            // If it exists, simply attach it in order to show it
	            ft.attach(mFragment);
	        }
	    	
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	        if (mFragment != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragment);
	        }
	    	
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	    
	    }
	}
	
	
}
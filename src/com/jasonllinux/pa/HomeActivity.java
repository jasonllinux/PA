package com.jasonllinux.pa;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;


public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("HomeActivity onCreate");
		 // setup Action Bar for tabs
	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    // remove the activity title to make space for tabs
	    actionBar.setDisplayShowTitleEnabled(false);
	    
	    // instantiate fragment for the tab
	    Tab tab = actionBar.newTab().setText(R.string.fragment_1)
								    .setTabListener(new TabListener<TestFragment>(
                                    this, "frag1", TestFragment.class)
                                    );
	    actionBar.addTab(tab);
	    
	    tab = actionBar.newTab().setText(R.string.fragment_2)
			    .setTabListener(new TabListener<TestFragment>(
                this, "frag2", TestFragment.class)
                );
        actionBar.addTab(tab);
        
        tab = actionBar.newTab().setText(R.string.fragment_3)
			    .setTabListener(new TabListener<TestFragment>(
                this, "frag3", TestFragment.class)
                );
        actionBar.addTab(tab);
	    
	}
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
    	System.out.println("HomeActivity onCreateOptioinMenu");
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
	    	
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	    	
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	    
	    }
	}
	
	
}
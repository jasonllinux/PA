package com.jasonllinux.pa;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;

import android.os.Bundle;


public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 // setup Action Bar for tabs
	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    // remove the activity title to make space for tabs
	    actionBar.setDisplayShowTitleEnabled(false);
	    
//	 // instantiate fragment for the tab
//	    Fragment artistsFragment = new ArtistsFragment();
//	    // add a new tab and set its title text and tab listener
//	    actionBar.addTab(actionBar.newTab().setText(R.string.tab_artists)
//	            .setTabListener(new TabListener(artistsFragment)));
//
//	    Fragment albumsFragment = new AlbumsFragment();
//	    actionBar.addTab(actionBar.newTab().setText(R.string.tab_albums)
//	            .setTabListener(new TabListener(albumsFragment)));
	}
	
	
	private class tabListener implements ActionBar.TabListener {

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	
}
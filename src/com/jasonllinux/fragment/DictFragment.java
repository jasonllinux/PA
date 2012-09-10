package com.jasonllinux.fragment;

import com.jasonllinux.app.social.SinaAuthorizeActivity;
import com.jasonllinux.pa.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DictFragment extends Fragment{
	

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);

    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
    	View view  =  inflater.inflate(R.layout.fragment_dict, container, false);

    	return view;
    	
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }



}

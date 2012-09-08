package com.jasonllinux.fragment;

import com.jasonllinux.app.social.SinaAuthorizeActivity;
import com.jasonllinux.pa.HomeActivity;
import com.jasonllinux.pa.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeFragment extends Fragment{
	
	private Button button_toSinaAuth;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
//        System.out.println("TODO--->onCreate");
    	
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        System.out.println("TODO--->onCreateView");
    	View view  =  inflater.inflate(R.layout.fragment_home, container, false);
    	button_toSinaAuth =  (Button) view.findViewById(R.id.button_toSinaAuth);
    	button_toSinaAuth.setOnClickListener(toSinaAuthlistener);
    	return view;
    	
    }

    @Override
    public void onStop()
    {
//        System.out.println("TODO--->onStop");
        super.onStop();
    }

    
    private OnClickListener toSinaAuthlistener = new OnClickListener() {
		
		public void onClick(View view) {
			System.out.println("to Sina Auth Button");
			Intent intent = new Intent();
			intent.setClass(view.getContext(), SinaAuthorizeActivity.class );
			startActivity(intent);
		}
	};
	

}

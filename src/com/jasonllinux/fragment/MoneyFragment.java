package com.jasonllinux.fragment;

import com.jasonllinux.pa.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoneyFragment extends Fragment {
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.out.println("TODO--->onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        System.out.println("TODO--->onCreateView");
        return inflater.inflate(R.layout.fragment_money, container, false);
    }

    @Override
    public void onStop()
    {
        System.out.println("TODO--->onStop");
        super.onStop();
    }

}

package com.jmheart.adapter;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

	List<Fragment> list;
    public TabPagerAdapter(FragmentManager fm,List<Fragment> listfrgament) {
        super(fm);
        if (listfrgament!=null) {
        	 this.list=listfrgament;
		}
        else
        {
        	listfrgament=new ArrayList<Fragment>();
        }
       
    }

    @Override
    public Fragment getItem(int index) {
        
        return list.get(index);
    }

    @Override
    public int getCount() {
    	
        return list.size();
    }
}
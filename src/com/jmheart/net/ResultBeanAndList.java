package com.jmheart.net;

import java.util.ArrayList;

public class ResultBeanAndList<T> {
	 public Object bean;
	    
	    public ArrayList<T> list;
	    
	    
	    public Object getBean() {
	        return bean;
	    }
	    public void setBean(Object bean) {
	        this.bean = bean;
	    }
	    public ArrayList<T> getList() {
	        return list;
	    }
	    public void setList(ArrayList<T> list) {
	        this.list = list;
	    }
	    @Override
	    public String toString() {
	        return "ResultBeanAndList [bean=" + bean + ", list=" + list + "]";
	    }
}

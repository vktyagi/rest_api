package com.demo.console.utils;

import java.util.Comparator;

public class AlphaNumericComparator<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
	String obj1 = null;
	String obj2 = null;
	try {
	    if (null == o1 || null == o2) {
		return 1;
	    }

	    obj1 = (String) o1;
	    obj2 = (String) o2;
	    Double val1 = new Double(obj1);
	    Double val2 = new Double(obj2);
	    return val1.compareTo(val2);
	} catch (Exception e) {
	    return obj1.compareTo(obj2);
	}
    }
}

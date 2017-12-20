package com.yt.cms.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合工具类
 * @author admin
 *
 */
public class CollectionUtils<T> {
	/**
	 * 在t1中找出不在t2的元素
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static<T> List<T> find(T[] t1, T[] t2) {
		List<T> result = new ArrayList<T>();
		for(T t : t1) {
			if(Arrays.binarySearch(t2, t, null) < 0) {
				// 不在t2
				result.add(t);
			}
		}
		return result;
	}
}

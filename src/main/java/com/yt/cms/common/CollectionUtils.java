package com.yt.cms.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
	
	public static boolean isNotEmpty(Collection<?> c) {
		if(c != null && c.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isEmpty(Collection<?> c) {
		return !isNotEmpty(c);
	}
	
	public static<T> List<T> changeForSet(Set<T> set){
		if(isEmpty(set)) {
			return new ArrayList<>();
		}
		List<T> list = new ArrayList<>();
		list.addAll(set);
		return list;
	}
	
	static class Web {
		
		private Integer webId;
		
		private List<Integer> channelId;

		public Integer getWebId() {
			return webId;
		}

		public void setWebId(Integer webId) {
			this.webId = webId;
		}

		public List<Integer> getChannelId() {
			return channelId;
		}

		public void setChannelId(List<Integer> channelId) {
			this.channelId = channelId;
		}
		
	}
	/*
	 * 
	 * [
    {
        "channelId": [
            2,
            3
        ],
        "webId": 1
    },
    {
        "channelId": [
            6,
            7
        ],
        "webId": 5
    }
]
	 * 
	 */
	public static void main(String[] args) {
		List<Web> list = new ArrayList<>();
		Web web = new Web();
		web.setWebId(1);
		List<Integer> channel1 = new ArrayList<>();
		channel1.add(2);
		channel1.add(3);
		web.setChannelId(channel1);
		list.add(web);
		
		Web web2 = new Web();
		web2.setWebId(5);
		List<Integer> channel2 = new ArrayList<>();
		channel2.add(6);
		channel2.add(7);
		web2.setChannelId(channel2);
		list.add(web2);
		
		String s = JSON.toJSONString(list);
		System.out.println(s);
		
		JSONArray l = JSONObject.parseArray(s);
		System.out.println(l.size());
	}
}

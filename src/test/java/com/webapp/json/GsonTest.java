package com.webapp.json;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.model.Member;

public class GsonTest {
	
	public static void main(String[] args) {
		
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		/*
		 * Primitive 타입
		 */
		String json = g.toJson("Hello");
		System.out.println(json);
		
		/*
		 * Array
		 */
		json = g.toJson(new Object[]{"Hello", 1234, "C++"});
		System.out.println(json);
		
		/*
		 * Object(Bean)
		 */
		Member m = new Member();
		m.setId(100); m.setEmail("xxx@webapp.com");
		m.setName("홍길동"); m.setPassword("1234");
		m.setRegdate(new Date());
		
		json = g.toJson(m);
		System.out.println(json);
		
		/*
		 * Map
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aaa", "Hello");
		map.put("bbb", m);
		map.put("ccc", new Object[] {"seoul", "pusan"});
		
		json = g.toJson(map);
		System.out.println(json);
		
		
	}

}

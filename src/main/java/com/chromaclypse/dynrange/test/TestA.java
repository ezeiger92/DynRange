package com.chromaclypse.dynrange.test;

import java.util.Map;

public class TestA extends TestClass {
	
	public TestA(){}
	
	public TestA(Map<String, Object> map) {
		super(map);
		b = (int) map.get("b");
	}

	public int b = 3;
	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = super.serialize();
		map.put("b", b);
		
		return map;
	}

}

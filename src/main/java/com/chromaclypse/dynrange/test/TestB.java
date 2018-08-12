package com.chromaclypse.dynrange.test;

import java.util.Map;

public class TestB extends TestClass {
	
	public TestB(){}
	
	public TestB(Map<String, Object> map) {
		super(map);
		flarg = (String) map.get("flarg");
		tt = (TestA) map.get("tt");
	}
	public TestA tt  = new TestA();
	public String flarg = "hello";
	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = super.serialize();
		map.put("flarg", flarg);
		map.put("tt", tt);
		
		return map;
	}
}

package com.chromaclypse.dynrange.test;

import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.chromaclypse.api.Defaults;

public abstract class TestClass implements ConfigurationSerializable {
	public TestClass(){}
	
	public TestClass(Map<String, Object> map) {
		a = (int) map.get("a");
	}
	
	public int a = 1;
	public abstract void startup();
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = Defaults.Keys("a").Values(a);
		return data;
	}
}

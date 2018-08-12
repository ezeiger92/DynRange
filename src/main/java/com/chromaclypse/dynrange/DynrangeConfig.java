package com.chromaclypse.dynrange;

import com.chromaclypse.api.config.ConfigObject;

public class DynrangeConfig extends ConfigObject {
	public Range range = new Range();
	public static class Range {
		public double x = 160.0;
		public double y = 256.0;
		public double z = 160.0;
		public long interval = 80;
	}
	
}

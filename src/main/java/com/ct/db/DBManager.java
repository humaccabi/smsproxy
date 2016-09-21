package com.ct.db;

import com.speedment.Speedment;

public class DBManager {

	static Speedment speedment;

	public static Speedment Instatnce() {

		if (speedment == null) {
			speedment = new DbApplication().withPassword("UALBmkSbCFN9").build();
		}

		return speedment;
	}
}

package com.model.db;

import com.speedment.Speedment;

public class DBManager {

	static Speedment speedment;

	public static Speedment Instatnce() {

		if (speedment == null) {
			//speedment = new DbApplication().withPassword("PHjCLJ9o9zJB").build();
		}

		return speedment;
	}
}

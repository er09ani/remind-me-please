package com.remindermeapp.utils;

import java.util.UUID;

public class MyUUIDGenerator {
	static String getUniqueId() {
		return UUID.randomUUID().toString();
	}
}

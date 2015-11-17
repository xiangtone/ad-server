package cn.adwalker.core.util;

import java.util.Random;
import java.util.UUID;

public final class ToolUtil {

	/**
	 * 生成32位UUID 并去掉"-"
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	public static String generateUID() {
		Random randomForUid = new Random(System.currentTimeMillis());
		long current = randomForUid.nextLong();
		byte[] timeBytes = new byte[8];
		for (int i = 0; i < 8; i++) {
			timeBytes[i] = (byte) (current >> (i * 8));
		}
		UUID uuid = UUID.nameUUIDFromBytes(timeBytes);
		return uuid.toString();
	}
}

package org.gdkm.weixin.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.gdkm.commons.domain.InMessage;
import org.gdkm.commons.domain.event.EventInMessage;
import org.gdkm.commons.domain.text.TextInMessage;

public class MessageTypeRegister {

	// 使用一个Map来记录类型和类之间的关系
	private static Map<String, Class<? extends InMessage>> typeMap = new ConcurrentHashMap<>();

	static {
		// 使用静态代码块直接完成类型和类之间的映射
		register("text", TextInMessage.class);
		register("event", EventInMessage.class);
		register("location", TextInMessage.class);
		register("image", TextInMessage.class);
		register("video", TextInMessage.class);
		register("shortvideo", TextInMessage.class);
		register("voice", TextInMessage.class);
	}

	public static void register(String type, Class<? extends InMessage> cla) {
		typeMap.put(type, cla);
	}

	public static Class<? extends InMessage> getClass(String type) {
		return typeMap.get(type);
	}
}

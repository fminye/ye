package org.gdkm.weixin.menu;

import org.gdkm.commons.EventListenerConfig;
import org.gdkm.commons.domain.event.EventInMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.gdkm")
@EntityScan("org.gdkm")
public class SelfMenuApplication implements EventListenerConfig {

	public static void main(String[] args) {
		SpringApplication.run(SelfMenuApplication.class, args);
	}

	@Override
	public void handleEvent(EventInMessage event) {
		// 不处理事件，方法留空
	}
}

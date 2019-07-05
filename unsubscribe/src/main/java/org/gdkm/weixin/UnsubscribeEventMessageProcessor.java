package org.gdkm.weixin;

import javax.transaction.Transactional;

import org.gdkm.commons.domain.User;
import org.gdkm.commons.domain.event.EventInMessage;
import org.gdkm.commons.processors.EventMessageProcessor;
import org.gdkm.commons.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("unsubscribeMessageProcessor")
public class UnsubscribeEventMessageProcessor implements EventMessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(UnsubscribeEventMessageProcessor.class);

	@Autowired
	private UserRepository userRepository;

	@Transactional 
	public void onMessage(EventInMessage event) {

		if (!event.getEvent().equals("unsubscribe")) {
			return;
		}
		LOG.trace("取消关注事件处理器被调用，收到的消息:\n {} ", event);

		User user = this.userRepository.findByOpenId(event.getFromUserName());
		user.setStatus(User.Status.IS_UNSUBSCRIBED);
	}
}

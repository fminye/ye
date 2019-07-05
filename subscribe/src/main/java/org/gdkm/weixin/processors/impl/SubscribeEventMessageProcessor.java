package org.gdkm.weixin.processors.impl;

import org.gdkm.commons.domain.User;
import org.gdkm.commons.domain.event.EventInMessage;
import org.gdkm.commons.processors.EventMessageProcessor;
import org.gdkm.commons.repository.UserRepository;
import org.gdkm.commons.service.WeiXinProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 把对象加入Spring容器，并且根据事件处理查找Bean的规则给一个名字
@Service("subscribeMessageProcessor")
public class SubscribeEventMessageProcessor implements EventMessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(SubscribeEventMessageProcessor.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WeiXinProxy proxy;

	@Override
	public void onMessage(EventInMessage event) {
		LOG.trace("关注事件处理器被调用，收到的消息:\n {} ", event);

		if (!event.getEvent().equals("subscribe")) {
			// 只处理关注事件
			return;
		}

		
		String openId = event.getFromUserName();
		User user = this.userRepository.findByOpenId(openId);
 
		// 2.如果用户已经关注，直接忽略不处理消息
		if (user != null) {
			if (user.getStatus() == User.Status.IS_SUBSCRIBED) {
				return;
			}

			// 3.如果用户之前曾经关注，但是已经取消，现在重新关注，把状态修改成【已关注】即可，并且重新获取用户信息
		}
		// 4.如果用户之前没有关注，新增关注记录，并且获取用户信息

		User wxUser = this.proxy.getWxUser(openId);// 调用远程接口获取用户信息
		if (user != null) {
			wxUser.setId(user.getId());
		}
		wxUser.setStatus(User.Status.IS_SUBSCRIBED);
		// 保存用户信息
		this.userRepository.save(wxUser);

		// 5.通过客服接口，返回一个关注后的欢迎消息给用户
		this.proxy.sendText(openId, "欢迎关注公众号，然后你就可以滚了！");
	}
}

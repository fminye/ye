package org.gdkm.weixin.service;

import org.gdkm.commons.domain.InMessage;
import org.gdkm.commons.domain.OutMessage;

public interface MessageService {

	OutMessage onMessage(InMessage msg);
}

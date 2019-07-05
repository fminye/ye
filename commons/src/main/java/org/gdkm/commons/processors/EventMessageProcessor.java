package org.gdkm.commons.processors;

import org.gdkm.commons.domain.event.EventInMessage;

public interface EventMessageProcessor {

	void onMessage(EventInMessage event);

}

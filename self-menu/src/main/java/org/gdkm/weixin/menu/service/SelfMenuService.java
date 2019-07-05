package org.gdkm.weixin.menu.service;

import org.gdkm.weixin.menu.domain.SelfMenu;

public interface SelfMenuService {

	SelfMenu findMenus();

	void save(SelfMenu menu);

}

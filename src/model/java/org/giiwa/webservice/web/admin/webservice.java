package org.giiwa.webservice.web.admin;

import org.giiwa.core.bean.X;

import org.giiwa.core.conf.Local;
import org.giiwa.core.json.JSON;
import org.giiwa.webservice.base.Web;

public class webservice extends org.giiwa.app.web.admin.setting {

	@Override
	public void get() {

		this.set("list", Web.getServices());

		this.settingPage("/admin/webservice.setting.html");

	}

	@Override
	public void set() {

		Local.setConfig("webservice.enabled", X.isSame(this.getString("webservice.enabled"), "on") ? 1 : 0);
		Local.setConfig("webservice.port", this.getString("webservice.port"));
		Local.setConfig("webservice.host", this.getString("webservice.host"));

		this.response(JSON.create().append(X.STATE, 201).append(X.MESSAGE, lang.get("save.success")));
		return;
	}

}

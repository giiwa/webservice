package org.giiwa.webservice.web;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.giiwa.app.web.admin.setting;
import org.giiwa.framework.bean.License;
import org.giiwa.framework.web.IListener;
import org.giiwa.framework.web.Module;
import org.giiwa.webservice.base.Web;
import org.giiwa.webservice.service.demo;
import org.giiwa.webservice.web.admin.webservice;

public class WebserviceListener implements IListener {

	static Log log = LogFactory.getLog(WebserviceListener.class);

	@Override
	public void onInit(Configuration conf, Module m) {
		// TODO Auto-generated method stub
		log.info("webwebservice is initing ...");

		m.setLicense(License.LICENSE.licensed,
				"Y/FDoHKn6inG79eB98Yg6+zD3fZJlZ4NDtKK9D3RCoM34PFFz9S7o/V//e/wgdfi+YUaaQKK4hVMSokNVqXTuWMR/7LS86lGFhlmaNYM5jMtpWAoWBH6KGie+LA9V/JxhuK34sKjPGH/HsXadLlZ/NNKcxXUmbRWHcCE+4BAacI=");

		setting.register("webservice", webservice.class);
		Web.bind("/service/demo", new demo());

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		log.info("webwebservice is stopping ...");

	}

	@Override
	public void uninstall(Configuration conf, Module m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void upgrade(Configuration conf, Module m) {
		// TODO Auto-generated method stub

	}

}

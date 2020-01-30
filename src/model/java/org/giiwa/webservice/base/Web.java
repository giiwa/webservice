package org.giiwa.webservice.base;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.ws.Endpoint;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.giiwa.core.conf.Local;
import org.giiwa.framework.bean.GLog;

public class Web {

	public static final Web inst = new Web();

	private static Log log = LogFactory.getLog(Web.class);

	public static boolean bind(String uri, Object o) {

		if (Local.getInt("webservice.enabled", 0) == 0)
			return false;

		if (!uri.startsWith("/")) {
			uri = "/" + uri;
		}

		String server = "http://" + Local.getString("webservice.host", "0.0.0.0") + ":"
				+ Local.getString("webservice.port", "9000");

		if (services.containsKey(uri)) {
			Exception e = new Exception(uri + " already bind");
			log.error(uri + " already bind", e);
			GLog.applog.error("webservice", "bind", uri + " already bind", e, null, null);
			return false;
		}

		String url = server + uri;

		try {
			Endpoint.publish(url, o);

			services.put(uri, o);

			log.info("url=" + url + ", o=" + o);

			GLog.applog.info("webservice", "bind", "url=" + url + ", o=" + o, null, server);

		} catch (Exception e) {
			log.error(url, e);

			GLog.applog.error("webservice", "bind", "url=" + url + ", o=" + o, e, null, server);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T> T call(String wsdl, String namespace, String name, String[] paramsName, Object[] paramsValue,
			Class<T> clazz) throws Exception {

		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(wsdl);

		call.setOperationName(new QName(namespace, name));

		for (int i = 0; i < paramsName.length; i++) {
			call.addParameter(paramsName[i], org.apache.axis.Constants.XSD_ANY, ParameterMode.IN);
		}

		call.setReturnClass(clazz);
		T result = (T) call.invoke(paramsValue);
		return result;

	}

	private static Map<String, Object> services = new TreeMap<String, Object>();

	public static Map<String, Object> getServices() {
		return services;
	}

	public static Object getService(String uri) {
		return services.get(uri);
	}

	public static void main(String[] args) throws Exception {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		content += "<url><yljgdm>320000000000000</yljgdm><ysgh>1234</ysgh><ysxm>张三</ysxm><ksmc>内科</ksmc><ip>11.111.111.11</ip><zjhm >320111111111111111</zjhm><hzxm>张三</hzxm><ywxt>XXXX</ywxt></url>";

		String r = Web.call("http://11.42.30.12:7001/ehrEncryyzm/ehrencry/service/outerEncry?wsdl",
				"http://webservice.encry.ehrview.wondersgroup.com/", "encryXmlUrl", new String[] { "content" },
				new String[] { content }, String.class);

		System.out.println(r);

	}

}

package org.giiwa.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * web api: /demo
 * 
 * @author joe
 * 
 */
@WebService()
public class demo {

	@WebMethod()
	public String echo(@WebParam(name = "ss") String ss) {
		return "hello: " + ss;
	}

	public static void main(String[] args) {

		String wsdl = "http://tg-web:9000/service/demo?wsdl";

		try {

			String namespace = "http://service.webservice.giiwa.org/";

			String result = org.giiwa.webservice.base.Web.call(wsdl, namespace, "echo", new String[] { "ss" },
					new Object[] { "kj" }, String.class);

			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

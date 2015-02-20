package org.ojbc.bundles.prototypes.bundleb.beans;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ojbc.bundles.prototypes.shared.interfaces.LogMessageHelper;

public class BundleBBean1 implements LogMessageHelper {
	
	private String message4;
	private String message5;
	private String message6;
	
	public void setMessage4(String message4) {
		this.message4 = message4;
	}

	public void setMessage5(String message5) {
		this.message5 = message5;
	}

	public void setMessage6(String message6) {
		this.message6 = message6;
	}

	private static final Log LOGGER = LogFactory.getLog(BundleBBean1.class);

	@Override
	public void writeLogMessage(Exchange e, String message) throws Exception {
		LOGGER.info("Bundle B Bean 1 writing message: " + message);
		LOGGER.info("Bundle B Bean 1 Exchange=" + e);
		LOGGER.info("Bundle B Bean 1 message4=" + message4);
		LOGGER.info("Bundle B Bean 1 message5=" + message5);
		LOGGER.info("Bundle B Bean 1 message6=" + message6);
		Message m = e.getIn();
		String body = m.getBody(String.class);
		StringBuffer messages = new StringBuffer();
		messages.append("4=").append(message4).append("|");
		messages.append("5=").append(message5).append("|");
		messages.append("6=").append(message6).append("|");
		m.setBody(body + "\nBundle B Bean 1:" + messages);
		
	}

}
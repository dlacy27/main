/*
 * Unless explicitly acquired and licensed from Licensor under another license, the contents of
 * this file are subject to the Reciprocal Public License ("RPL") Version 1.5, or subsequent
 * versions as allowed by the RPL, and You may not copy or use this file in either source code
 * or executable form, except in compliance with the terms and conditions of the RPL
 *
 * All software distributed under the RPL is provided strictly on an "AS IS" basis, WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH
 * WARRANTIES, INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RPL for specific language
 * governing rights and limitations under the RPL.
 *
 * http://opensource.org/licenses/RPL-1.5
 *
 * Copyright 2012-2015 Open Justice Broker Consortium
 */
package org.ojbc.bundles.adapters.fbi.ebts.processor;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.ojbc.intermediaries.sn.util.SubscriptionResponseBuilderUtil;
import org.w3c.dom.Document;


public class FbiEbtsUnsubscribeProcessor {
	
	private static final Logger logger = Logger.getLogger(FbiEbtsUnsubscribeProcessor.class.getName());
	
	public void sendResponse(Exchange exchange){
		
		logger.info("\n\n\n FBI EBTS Sending Unsubscribe Response... \n\n\n");
		
		Document unsubscribeResponse = SubscriptionResponseBuilderUtil.createUnsubscribeResponse();
		
		exchange.getOut().setBody(unsubscribeResponse);
	}

}
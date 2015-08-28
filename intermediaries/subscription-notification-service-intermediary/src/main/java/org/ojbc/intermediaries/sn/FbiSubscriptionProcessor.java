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
package org.ojbc.intermediaries.sn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.ojbc.util.xml.OjbcNamespaceContext;
import org.ojbc.util.xml.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class FbiSubscriptionProcessor {
	
	private static final Logger logger = Logger.getLogger(FbiSubscriptionProcessor.class.getName());
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
	
	public Document processSubscription(Document subscriptionDoc) throws Exception{
		
		logger.info("processSubscription...");
					
		boolean fbiSubscriptionExists = isFbiSubscriptionExisting("sid(TODO)", "purpose(TODO)");
		
		Document subscriptionMessage = null;
		
		if(!fbiSubscriptionExists){
		
			FbiSubscription fbiSubscription = getFbiSubscriptionFromRapbackDataStore("sid(TODO)", "reasonCode(TODO");
			
			subscriptionMessage = appendFbiDataToSubscriptionDoc(subscriptionDoc, fbiSubscription);
			
		}else{
			
			subscriptionMessage = subscriptionDoc;
		}
						
		return subscriptionMessage;
	}
	

	public Document appendFbiDataToSubscriptionDoc(Document subscriptionDoc, FbiSubscription fbiSubscription) throws Exception{
				
		logger.info("appendFbiDataToSubscriptionDoc...");
		
		Element relatedFBISubscriptionElement = subscriptionDoc.createElementNS(OjbcNamespaceContext.NS_SUB_MSG_EXT, "RelatedFBISubscription");
		relatedFBISubscriptionElement.setPrefix(OjbcNamespaceContext.NS_PREFIX_SUB_MSG_EXT);						
		
		Node subMsgNode = XmlUtils.xPathNodeSearch(subscriptionDoc, "//submsg-exch:SubscriptionMessage");
				
		subMsgNode.appendChild(relatedFBISubscriptionElement);		
				
		Element dateRangeElement = XmlUtils.appendElement(relatedFBISubscriptionElement, OjbcNamespaceContext.NS_NC, "DateRange");				
		
		Date startDate = fbiSubscription.getStartDate();
		if(startDate != null){
			Element startDateElement = XmlUtils.appendElement(dateRangeElement, OjbcNamespaceContext.NS_NC, "StartDate");		
			Element startDateValElement = XmlUtils.appendElement(startDateElement, OjbcNamespaceContext.NS_NC, "Date");					
			String sStartDate = sdf.format(startDate);			
			startDateValElement.setTextContent(sStartDate);			
		}
						
		Date endDate = fbiSubscription.getEndDate();
		if(endDate != null){
			Element endDateElement = XmlUtils.appendElement(dateRangeElement, OjbcNamespaceContext.NS_NC, "EndDate");
			Element endDateValElement = XmlUtils.appendElement(endDateElement, OjbcNamespaceContext.NS_NC, "Date");			
			String sEndDate = sdf.format(endDate);
			endDateValElement.setTextContent(sEndDate);			
		}
							
		String fbiId = fbiSubscription.getFbiId();
		
		if(StringUtils.isNotEmpty(fbiId)){
			Element subFbiIdElement = XmlUtils.appendElement(relatedFBISubscriptionElement, OjbcNamespaceContext.NS_SUB_MSG_EXT, "SubscriptionFBIIdentification");
			Element fbiIdValElement = XmlUtils.appendElement(subFbiIdElement, OjbcNamespaceContext.NS_NC, "IdentificationID");
			fbiIdValElement.setTextContent(fbiId);			
		}		
				
		String reasonCode = fbiSubscription.getCrimSubReasonCode();		
		if(StringUtils.isNotEmpty(reasonCode)){
			Element reasonCodeElement = XmlUtils.appendElement(relatedFBISubscriptionElement, OjbcNamespaceContext.NS_SUB_MSG_EXT, "CriminalSubscriptionReasonCode");
			reasonCodeElement.setTextContent(reasonCode);			
		}
				
		String subTerm = fbiSubscription.getTermDuration();
		if(StringUtils.isNotEmpty(subTerm)){
			Element subscriptionTermElement = XmlUtils.appendElement(relatedFBISubscriptionElement, OjbcNamespaceContext.NS_SUB_MSG_EXT, "SubscriptionTerm");			
			Element termDurationElement = XmlUtils.appendElement(subscriptionTermElement, OjbcNamespaceContext.NS_JXDM_41, "TermDuration");		
			termDurationElement.setTextContent(subTerm);			
		}		
								
		OjbcNamespaceContext ojbNsCtxt = new OjbcNamespaceContext();
		ojbNsCtxt.populateRootNamespaceDeclarations(subscriptionDoc.getDocumentElement());
						
		return subscriptionDoc;
	}

	
	// TODO query rabpack datastore
	private FbiSubscription getFbiSubscriptionFromRapbackDataStore(String sid, String reasonCode){
		
		FbiSubscription fbiSubscription = new FbiSubscription();
		
		fbiSubscription.setCrimSubReasonCode("CI");
		fbiSubscription.setFbiId("1234");
		fbiSubscription.setStartDate(new Date());
		fbiSubscription.setEndDate(new Date());
		
		return fbiSubscription;
	}
	
	
	private boolean isFbiSubscriptionExisting(String string, String string2) {

		//TODO reference actual sub. msg to see if fbi subscription exists
		return false;
	}	

}

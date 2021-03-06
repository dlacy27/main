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
package org.ojbc.web;

import org.apache.camel.CamelContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class WebPortalApplicationContextStartupListener implements
		ApplicationListener<ContextRefreshedEvent> {

	private static final Log LOG = LogFactory.getLog(WebPortalApplicationContextStartupListener.class);
	
	@Autowired(required=false)
    private CamelContext camelContext; 

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		LOG.debug("Enter ContextRefreshedEvent listener");
		
		Environment environment = event.getApplicationContext().getEnvironment();
		
		String defaultProfilesAsString = StringUtils.join(environment.getDefaultProfiles(), ',');
		
		LOG.debug("Current default profiles: "  + defaultProfilesAsString);
		
		if (camelContext != null)
		{	
			try {
				camelContext.start();
				
				for (String profile : environment.getDefaultProfiles())
			    {           
					LOG.debug("Current profile: " + profile);
				
					if (profile.equals("person-search"))
					{	
						camelContext.startRoute("personMergeSearchResultsHandlerRoute");
						continue;
					}

					if (profile.equals("incident-search"))
					{	
						camelContext.startRoute("incidentSearchResultsHandlerRoute");
						continue;
					}

					if (profile.equals("vehicle-search"))
					{	
						camelContext.startRoute("vehicleSearchResultsHandlerRoute");
						continue;
					}

					if (profile.equals("firearms-search"))
					{	
						camelContext.startRoute("firearmSearchResultsHandlerRoute");
						continue;
					}

					if (profile.equals("person-vehicle-to-incident-search"))
					{	
						camelContext.startRoute("personVehicleToIncidentSearchResultsHandlerRoute");
						continue;
					}

					if (profile.equals("warrants-query"))
					{	
						camelContext.startRoute("personQueryResultsHandlerWarrantsServiceRoute");
						continue;
					}

					if (profile.equals("criminal-history-query"))
					{	
						camelContext.startRoute("personQueryResultsHandlerCriminalHistoryServiceRoute");
						continue;
					}

					if (profile.equals("incident-report-query"))
					{	
						camelContext.startRoute("incidentReportResultsHandlerServiceRoute");
						continue;
					}

					if (profile.equals("firearms-query"))
					{	
						camelContext.startRoute("firearmRegistrationQueryResultsHandlerRoute");
						continue;
					}

					if (profile.equals("subscriptions"))
					{	
						camelContext.startRoute("subscriptionSearchResultsHandlerRoute");
						camelContext.startRoute("subscriptionQueryResultsHandlerRoute");
						continue;
					}

					if (profile.equals("access-control"))
					{	
						camelContext.startRoute("identityBasedAccessControlResultsHandlerRoute");
						continue;
					}

					if (profile.equals("policy-acknowledgement"))
					{	
						camelContext.startRoute("policyAcknowledgementRecordingResultsHandlerRoute");
						continue;
					}
				
                    if (profile.equals("juvenile-query"))
                    {   
                        camelContext.startRoute("juvenileOffenseHistoryResultsHandlerRoute");
                        camelContext.startRoute("juvenileCasePlanHistoryResultsHandlerRoute");
                        camelContext.startRoute("juvenilePlacementHistoryResultsHandlerRoute");
                        camelContext.startRoute("juvenileReferralHistoryResultsHandlerRoute");
                        camelContext.startRoute("juvenileIntakeHistoryResultsHandlerRoute");
                        camelContext.startRoute("juvenileHearingHistoryResultsHandlerRoute");
                        continue;
                    }
                    if (profile.equals("rapback-search"))
                    {   
                    	camelContext.startRoute("rapbackSearchResultsHandlerRoute");
                    	continue;
                    }
                    if (profile.equals("initial-results-query"))
                    {   
                    	camelContext.startRoute("identificationResultsQueryResultsHandlerRoute");
                    	continue;
                    }
                    if (profile.equals("identification-results-modification"))
                    {   
                    	camelContext.startRoute("identificationResultsModificationResultsHandlerRoute");
                    	continue;
                    }
			    }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
	}

}

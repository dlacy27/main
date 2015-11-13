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
package org.ojbc.bundles.intermediaries.identificationreporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.ojbc.util.camel.helper.OJBUtils;
import org.ojbc.util.xml.XsltTransformer;
import org.xml.sax.SAXException;

public class ArrestReportTransformTest {
	
	private XsltTransformer xsltTransformer;
	
	@Before
	public void init(){
		
		XMLUnit.setIgnoreWhitespace(true);
    	XMLUnit.setIgnoreAttributeOrder(true);
    	XMLUnit.setIgnoreComments(true);
    	XMLUnit.setXSLTVersion("2.0");
    	
		xsltTransformer = new XsltTransformer();
	}	
	
	@Test
	public void arrestReportTransform() throws IOException, SAXException{
		
		InputStream intputFileStream = new FileInputStream("src/test/resources/xmlInstances/identificationReport/person_identification_search_results_state_criminal.xml");
		Source inputFileSource = OJBUtils.createSaxSource(intputFileStream);
								
		InputStream xsltFileInStream = new FileInputStream("src/main/resources/xsl/arrestReportTransform.xsl"); 				
		Source xsltSource = OJBUtils.createSaxSource(xsltFileInStream);
		
		Map<String, Object> xsltParamMap = getXsltParamMap();
			
		String actualTransformedXml = xsltTransformer.transform(inputFileSource, xsltSource, xsltParamMap);		
				
		String expectedXmlString = FileUtils.readFileToString(
				new File("src/test/resources/xmlInstances/arrestReport/arrestReport.xml"));
							
		compareXml(expectedXmlString, actualTransformedXml);							
	}
	
	
	private Map<String, Object> getXsltParamMap(){
	
		Map<String, Object> xsltParamMap = new HashMap<String, Object>();
		
//		xsltParamMap.put("rapBackTransactionDate", "2015-07-14");		
//		xsltParamMap.put("rapBackNotificatonFormat", 3);
//		xsltParamMap.put("rapBackInStateOptOutIndicator", true);
//		xsltParamMap.put("rapBackTriggeringEvent", 1);		
//		xsltParamMap.put("destinationOrganizationORI", "WVIAFIS0Z");
//		xsltParamMap.put("originatorOrganizationORI", "HI002595Y");
//		xsltParamMap.put("controlID", "9876500000");
//		xsltParamMap.put("domainVersion", "EBTS 10.0");
//		xsltParamMap.put("domainName", "NORAM");
//		xsltParamMap.put("transactionMajorVersion", "05");
//		xsltParamMap.put("transactionMinorVersion", "00");
//		xsltParamMap.put("rapSheetRequestIndicator", "true");
//		xsltParamMap.put("rapBackRecipient", "HI002595Y");
//		xsltParamMap.put("controllingAgencyID", "HI002595Y");
//		xsltParamMap.put("originatingAgencyCaseNumber", "HCJDC-CASE");
//		xsltParamMap.put("nativeScanningResolution", "00.00");
//		xsltParamMap.put("nominalTransmittingResolution", "00.00");
//		xsltParamMap.put("transactionContentSummaryContentFirstRecordCategoryCode", "1");
//		xsltParamMap.put("transactionContentSummaryContentRecordCountCriminal", "01");					
//		xsltParamMap.put("transactionContentSummaryContentRecordCountCivil", "03");				
//		xsltParamMap.put("rapBackDisclosureIndicator", "false");
//		xsltParamMap.put("civilRapBackSubscriptionTerm", "L");
		
		return xsltParamMap;
	}
	
	private static void compareXml(String expectedXmlString, String actualTransformedXml) throws SAXException, IOException{
		
		Diff diff = XMLUnit.compareXML(expectedXmlString, actualTransformedXml);		
		
		DetailedDiff detailedDiff = new DetailedDiff(diff);
		
		Assert.assertEquals(detailedDiff.toString(), 0, detailedDiff.getAllDifferences().size());
	}	
}

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
package org.ojbc.adapters.rapbackdatastore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ojbc.adapters.rapbackdatastore.processor.IdentificationReportingResponseProcessorTest.assertAsExpected;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.junit4.CamelSpringJUnit4ClassRunner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.headers.Header;
import org.apache.cxf.message.MessageImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ojbc.adapters.rapbackdatastore.processor.IdentificationRequestReportProcessor;
import org.ojbc.util.camel.helper.OJBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/camel-context.xml",
        "classpath:META-INF/spring/spring-context.xml",
        "classpath:META-INF/spring/cxf-endpoints.xml",      
        "classpath:META-INF/spring/properties-context.xml",
        "classpath:META-INF/spring/dao.xml",
        })
@DirtiesContext
public class TestIdentficationRecordingAndResponse {
	
	private static final Log log = LogFactory.getLog( TestIdentficationRecordingAndResponse.class );
	
	@Autowired
	IdentificationRequestReportProcessor identificationRequestReportProcessor;
	
    @Resource
    private ModelCamelContext context;
    
    @Produce
    protected ProducerTemplate template;
    
	@EndpointInject(uri = "mock:failedInvocation")
    protected MockEndpoint failedInvocationEndpoint;
	
    @EndpointInject(uri = "mock:bean:identificationReportingResultMessageProcessor")
    protected MockEndpoint identificationReportingResultMessageProcessor;
    
	@Test
	public void contextStartup() {
		assertTrue(true);
	}

	@Before
	public void setUp() throws Exception {
		
		assertNotNull(identificationRequestReportProcessor);

    	//We replace the 'from' web service endpoint with a direct endpoint we call in our test
    	context.getRouteDefinition("identification_recording_service").adviceWith(context, new AdviceWithRouteBuilder() {
    	    @Override
    	    public void configure() throws Exception {
    	    	// The line below allows us to bypass CXF and send a message directly into the route
    	    	replaceFromWith("direct:identificationRecordingServiceEndpoint");
    	    }              
    	});

    	context.getRouteDefinition("send_identification_reporting_response_route").adviceWith(context, new AdviceWithRouteBuilder() {
    	    @Override
    	    public void configure() throws Exception {
    	    	// The line below allows us to bypass CXF and send a message directly into the route
    	    	mockEndpointsAndSkip("bean:identificationReportingResultMessageProcessor*");
    	    }              
    	});
    	context.start();
	}	
	
	
	@Test
	@DirtiesContext
	public void testIdentificationRecordingServiceError() throws Exception
	{
    	Exchange senderExchange = createSenderExchange("src/test/resources/xmlInstances/identificationReport/person_identification_fbi_request.xml");
	    
	    //Send the one-way exchange.  Using template.send will send an one way message
		Exchange returnExchange = template.send("direct:identificationRecordingServiceEndpoint", senderExchange);
		
		//Use getException to see if we received an exception
		if (returnExchange.getException() != null)
		{	
			throw new Exception(returnExchange.getException());
		}	
		
		identificationReportingResultMessageProcessor.expectedMessageCount(1);
		
		identificationReportingResultMessageProcessor.assertIsSatisfied();
		
		Exchange receivedExchange = identificationReportingResultMessageProcessor.getExchanges().get(0);
		String body = OJBUtils.getStringFromDocument(receivedExchange.getIn().getBody(Document.class));
		assertAsExpected(body, "src/test/resources/xmlInstances/identificationReportingResponse/person_identification_report_failure_response.xml");

	}
	
	@Test
	@DirtiesContext
	public void testIdentificationRecordingRequestServiceSuccess() throws Exception
	{
		Exchange senderExchange = createSenderExchange("src/test/resources/xmlInstances/identificationReport/person_identification_fbi_request.xml");
		
		senderExchange.getIn().setHeader("operationName", "RecordPersonFederalIdentificationRequest");
		
		//Send the one-way exchange.  Using template.send will send an one way message
		Exchange returnExchange = template.send("direct:identificationRecordingServiceEndpoint", senderExchange);
		
		//Use getException to see if we received an exception
		if (returnExchange.getException() != null)
		{	
			throw new Exception(returnExchange.getException());
		}	
		
		identificationReportingResultMessageProcessor.expectedMessageCount(1);
		
		identificationReportingResultMessageProcessor.assertIsSatisfied();
		
		Exchange receivedExchange = identificationReportingResultMessageProcessor.getExchanges().get(0);
		String body = OJBUtils.getStringFromDocument(receivedExchange.getIn().getBody(Document.class));
		assertAsExpected(body, "src/test/resources/xmlInstances/identificationReportingResponse/person_identification_report_success_response.xml");
		
	}
	
	@Test
	@DirtiesContext
	public void testIdentificationRecordingResultServiceSuccess() throws Exception
	{
		Exchange senderExchange = createSenderExchange("src/test/resources/xmlInstances/identificationReport/person_identification_results_fbi_identification.xml");
		
		senderExchange.getIn().setHeader("operationName", "RecordPersonFederalIdentificationResults");
		
		//Send the one-way exchange.  Using template.send will send an one way message
		Exchange returnExchange = template.send("direct:identificationRecordingServiceEndpoint", senderExchange);
		
		//Use getException to see if we received an exception
		if (returnExchange.getException() != null)
		{	
			throw new Exception(returnExchange.getException());
		}	
		
		identificationReportingResultMessageProcessor.expectedMessageCount(1);
		
		identificationReportingResultMessageProcessor.assertIsSatisfied();
		
		Exchange receivedExchange = identificationReportingResultMessageProcessor.getExchanges().get(0);
		String body = OJBUtils.getStringFromDocument(receivedExchange.getIn().getBody(Document.class));
		assertAsExpected(body, "src/test/resources/xmlInstances/identificationReportingResponse/person_identification_report_success_response.xml");
		
	}
	
	
	protected Exchange createSenderExchange(String pathToInputFile) throws Exception, IOException {
		//Create a new exchange
    	Exchange senderExchange = new DefaultExchange(context);

    	//Set the WS-Address Message ID
		Map<String, Object> requestContext = OJBUtils.setWSAddressingMessageID("123456789");
		
		//Set the operation name and operation namespace for the CXF exchange
		senderExchange.getIn().setHeader(Client.REQUEST_CONTEXT , requestContext);
		
		Document doc = createDocument();
		List<SoapHeader> soapHeaders = new ArrayList<SoapHeader>();
		soapHeaders.add(makeSoapHeader(doc, "http://www.w3.org/2005/08/addressing", "MessageID", "12345"));
		senderExchange.getIn().setHeader(Header.HEADER_LIST , soapHeaders);
		
		org.apache.cxf.message.Message message = new MessageImpl();

		senderExchange.getIn().setHeader(CxfConstants.CAMEL_CXF_MESSAGE, message);
    	
	    //Read the firearm search request file from the file system
	    File inputFile = new File(pathToInputFile);
	    String inputStr = FileUtils.readFileToString(inputFile);

	    assertNotNull(inputStr);
	    
	    //Set it as the message message body
	    senderExchange.getIn().setBody(inputStr);
		return senderExchange;
	}
	
	private SoapHeader makeSoapHeader(Document doc, String namespace, String localName, String value) {
		Element messageId = doc.createElementNS(namespace, localName);
		messageId.setTextContent(value);
		SoapHeader soapHeader = new SoapHeader(new QName(namespace, localName), messageId);
		return soapHeader;
	}	
	
	public static Document createDocument() throws Exception{

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().newDocument();

		return doc;
	}
	
}
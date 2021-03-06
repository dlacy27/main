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
package org.ojbc.h2.mock.database.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:META-INF/spring/test-application-context.xml",
		"classpath:META-INF/spring/h2-mock-database-application-context.xml",
		"classpath:META-INF/spring/h2-mock-database-context-auditlog.xml",
		"classpath:META-INF/spring/h2-mock-database-context-subscription.xml",
		"classpath:META-INF/spring/h2-mock-database-context-rapback-datastore.xml",
		"classpath:META-INF/spring/h2-mock-database-context-policy-acknowledgement.xml",
		"classpath:META-INF/spring/h2-mock-database-context-incident-reporting-state-cache.xml"
		})
@DirtiesContext
public class TestDatabaseLoad {
	
	private static final Log log = LogFactory.getLog(TestDatabaseLoad.class);
	
    @Resource  
    private DataSource auditLogTestDataSource; 
    
    @Resource  
    private DataSource subscriptionDataSource;  
	
    @Resource  
    private DataSource rapbackDataSource;  
    
    @Resource  
    private DataSource policyAcknowledgementDataSource;  

    @Resource  
    private DataSource incidentReportingStateCacheDataSource;  

	@Test
	public void testAuditlog() throws Exception {
		
		Connection conn = auditLogTestDataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery("select * from AuditLog");
		assertFalse(rs.next());
		
	}

	@Test
	public void testSubscription() throws Exception {
		
		Connection conn = subscriptionDataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery("select * from subscription");
		assertTrue(rs.next());
		assertEquals(62720,rs.getInt("id"));
		
	}

	@Test
	public void testRapbackDatastore() throws Exception {
		
		Connection conn = rapbackDataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery("select * from subscription");
		assertTrue(rs.next());
		assertEquals(62720,rs.getInt("id"));
		
		rs = conn.createStatement().executeQuery("select * from IDENTIFICATION_SUBJECT");
		assertTrue(rs.next());
	}
	
	@Test
	public void testPolicyAcknowledgement() throws Exception {
		
		Connection conn = policyAcknowledgementDataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery("select * from policy where id=1");
		assertTrue(rs.next());
		assertEquals(1,rs.getInt("id"));
		assertEquals("http://ojbc.org/policies/privacy/hawaii/ManualSubscriptionPolicy",rs.getString("policy_uri"));
		
	}

	@Test
	public void testIncidentReportingStateCache() throws Exception {
		
		Connection conn = incidentReportingStateCacheDataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery("select * from Person_Involvement_State");
		assertFalse(rs.next());
		
	}

}

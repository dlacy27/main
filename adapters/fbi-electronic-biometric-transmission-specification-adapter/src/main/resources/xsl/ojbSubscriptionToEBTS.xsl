<!--

    Unless explicitly acquired and licensed from Licensor under another license, the contents of
    this file are subject to the Reciprocal Public License ("RPL") Version 1.5, or subsequent
    versions as allowed by the RPL, and You may not copy or use this file in either source code
    or executable form, except in compliance with the terms and conditions of the RPL

    All software distributed under the RPL is provided strictly on an "AS IS" basis, WITHOUT
    WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH
    WARRANTIES, INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RPL for specific language
    governing rights and limitations under the RPL.

    http://opensource.org/licenses/RPL-1.5

    Copyright 2012-2015 Open Justice Broker Consortium

-->
<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ebts="http://cjis.fbi.gov/fbi_ebts/10.0" 
    xmlns:ansi-nist="http://niem.gov/niem/biometrics/1.0" 
    xmlns:itl="http://biometrics.nist.gov/standard/2011" 
    xmlns:nc20="http://niem.gov/niem/niem-core/2.0" 
    xmlns:j="http://niem.gov/niem/domains/jxdm/4.1"
    xmlns:submsg-doc="http://ojbc.org/IEPD/Exchange/SubscriptionMessage/1.0"
    xmlns:submsg-ext="http://ojbc.org/IEPD/Extensions/Subscription/1.0">
	
	<xsl:output indent="yes" method="xml"/>
	
	<!-- These are implementation-specific parameters that must be passed in when calling this stylesheet -->	
		
	<xsl:param name="rapBackTransactionDate"/>
	
	<!-- RBNF (2.2062)-->
	<xsl:param name="rapBackNotificatonFormat" />
	
	<!-- RBC 2.2065-->
	<xsl:param name="recordRapBackCategoryCode" />
		
	<!-- RBOO (2.2063) -->
	<xsl:param name="rapBackInStateOptOutIndicator" />
	
	<!-- This field corresponds to RBT 2.2040 and specifies which Events will result in notifications sent to the subscriber -->
	<xsl:param name="rapBackTriggeringEvent" />
	
	<!-- DAI 1.007 -->
	<xsl:param name="destinationOrganizationORI" />
	
	<!-- ORI 1.007 -->
	<xsl:param name="originatorOrganizationORI" />
	
	<!-- TCN 1.009 -->
	<xsl:param name="controlID" />
	
	<!-- DOM 1.013 -->
	<xsl:param name="domainVersion" />
	<xsl:param name="domainName" />
	
	<!-- VER 1.002 -->
	<xsl:param name="transactionMajorVersion"/>
	<xsl:param name="transactionMinorVersion"/>
	
	<!-- RAP 2.070 -->
	<xsl:param name="rapSheetRequestIndicator"/>
	
	<!-- RBR 2.020 -->
	<xsl:param name="rapBackRecipient"/>
	
	<!-- CRI 2.073 -->
	<xsl:param name="controllingAgencyID"/>
	
	<!-- OCA 2.009 -->
	<xsl:param name="originatingAgencyCaseNumber"/>
	
	<!-- Native Scanning Resolution (NSR 1.011) -->
	<xsl:param name="nativeScanningResolution"/>
	
	<!--  Nominal Transmitting Resolution (NTR 1.012 -->
	<xsl:param name="nominalTransmittingResolution"/>
	
	<!-- CNT 1.003 -->
	<xsl:param name="transactionContentSummaryContentFirstRecordCategoryCode"/>
	<xsl:param name="transactionContentSummaryContentRecordCountCriminal"/>
	<xsl:param name="transactionContentSummaryContentRecordCountCivil"/>
	
	<!-- IDC 2.002 -->
	<xsl:param name="imageReferenceID"/>
	
	<xsl:template match="/submsg-doc:SubscriptionMessage">
	
		<itl:NISTBiometricInformationExchangePackage>
		
			<!-- Record Type 1 -->
			<itl:PackageInformationRecord>
				<ansi-nist:RecordCategoryCode>01</ansi-nist:RecordCategoryCode>
				 
				 <ansi-nist:Transaction>
				 	<ansi-nist:TransactionDate>
				 		<nc20:Date>
				 			<xsl:value-of select="$rapBackTransactionDate"/>
				 		</nc20:Date>
				 	</ansi-nist:TransactionDate>
				 	<ansi-nist:TransactionDestinationOrganization>
               			 <nc20:OrganizationIdentification>
                    		<nc20:IdentificationID>
                    			<xsl:value-of select="$destinationOrganizationORI"/>
                    		</nc20:IdentificationID>
               			 </nc20:OrganizationIdentification>
          		 	</ansi-nist:TransactionDestinationOrganization>
          		 	<ansi-nist:TransactionOriginatingOrganization>
               			 <nc20:OrganizationIdentification>
                   			 <!--ORI 1.008-->
                    		<nc20:IdentificationID>
                    			<xsl:value-of select="$originatorOrganizationORI"/>
                    		</nc20:IdentificationID>
                		</nc20:OrganizationIdentification>
            		</ansi-nist:TransactionOriginatingOrganization>
            		<ansi-nist:TransactionControlIdentification>
                		<nc20:IdentificationID>
                			<xsl:value-of select="$controlID"/>
                		</nc20:IdentificationID>
            		</ansi-nist:TransactionControlIdentification>
            		<ansi-nist:TransactionDomain>
               			 <ansi-nist:DomainVersionNumberIdentification>
                    		<nc20:IdentificationID>
                    			<xsl:value-of select="$domainVersion"/>
                    		</nc20:IdentificationID>
               			 </ansi-nist:DomainVersionNumberIdentification>
               			 <ansi-nist:TransactionDomainName>
               			 	<xsl:value-of select="$domainName"/>
               			 </ansi-nist:TransactionDomainName>
           			</ansi-nist:TransactionDomain>
           			<ansi-nist:TransactionImageResolutionDetails>
           				 <ansi-nist:NativeScanningResolutionValue>
           				 	<xsl:value-of select="$nativeScanningResolution"/>
           				 </ansi-nist:NativeScanningResolutionValue>
               			 <ansi-nist:NominalTransmittingResolutionValue>
               			 	<xsl:value-of select="$nominalTransmittingResolution"/>
               			 </ansi-nist:NominalTransmittingResolutionValue>
           			</ansi-nist:TransactionImageResolutionDetails>
           			<ansi-nist:TransactionMajorVersionValue>
           				<xsl:value-of select="$transactionMajorVersion"/>
           			</ansi-nist:TransactionMajorVersionValue>
           			<ansi-nist:TransactionMinorVersionValue>
           				<xsl:value-of select="$transactionMinorVersion"/>
           			</ansi-nist:TransactionMinorVersionValue>
				 	<!-- Transaction Category TOT 1.004 -->
				 	<xsl:apply-templates select="/submsg-doc:SubscriptionMessage/submsg-ext:CriminalSubscriptionReasonCode[. != '']" mode="transactionCategory"/>
				 	
				 	 <ansi-nist:TransactionContentSummary>
				 	 	<ansi-nist:ContentFirstRecordCategoryCode>
				 	 		<xsl:value-of select="$transactionContentSummaryContentFirstRecordCategoryCode"/>
				 	 	</ansi-nist:ContentFirstRecordCategoryCode>
				 	 	<ansi-nist:ContentRecordQuantity>
				 	 		<xsl:choose>
								<xsl:when test="/submsg-doc:SubscriptionMessage/submsg-ext:CriminalSubscriptionReasonCode = 'CI'">
									<xsl:value-of select="$transactionContentSummaryContentRecordCountCriminal"/>
								</xsl:when>
								<xsl:when test="/submsg-doc:SubscriptionMessage/submsg-ext:CriminalSubscriptionReasonCode = 'CS'">
									<xsl:value-of select="$transactionContentSummaryContentRecordCountCriminal"/>
								</xsl:when>
							</xsl:choose>
				 	 	</ansi-nist:ContentRecordQuantity>
				 	 </ansi-nist:TransactionContentSummary>
				 </ansi-nist:Transaction>
				 
			</itl:PackageInformationRecord>
			
			<!-- Record Type 2 -->
			<itl:PackageInformationRecord>
			
				<ansi-nist:RecordCategoryCode>02</ansi-nist:RecordCategoryCode>
				<ansi-nist:ImageReferenceIdentification>
            		<nc20:IdentificationID>
            			<xsl:value-of select="$imageReferenceID"/>
            		</nc20:IdentificationID>
     		   </ansi-nist:ImageReferenceIdentification>
				<itl:UserDefinedDescriptiveDetail>
					<ebts:DomainDefinedDescriptiveFields>
		
						<ansi-nist:RecordRapSheetRequestIndicator>
							<xsl:value-of select="$rapSheetRequestIndicator"/>
						</ansi-nist:RecordRapSheetRequestIndicator>
						<ebts:RecordRapBackData>
													
							<ebts:RecordRapBackActivityNotificationFormatCode>
								<xsl:value-of select="$rapBackNotificatonFormat" />
							</ebts:RecordRapBackActivityNotificationFormatCode>
							
							<!--Rap Back Category RBC 2.2065-->
							<xsl:apply-templates select="/submsg-doc:SubscriptionMessage/submsg-ext:CriminalSubscriptionReasonCode[. != '']" mode="rapBackCategory"/>
							<xsl:apply-templates select="/submsg-doc:SubscriptionMessage/submsg-ext:CivilSubscriptionReasonCode[. != '']" mode="rapBackCategory"/>
							<xsl:apply-templates select="/submsg-doc:SubscriptionMessage/nc20:DateRange/nc20:EndDate/nc20:Date" mode="expirationDate"/>
												
							<ebts:RecordRapBackInStateOptOutIndicator>
								<xsl:value-of select="$rapBackInStateOptOutIndicator" />
							</ebts:RecordRapBackInStateOptOutIndicator>
							
							<ansi-nist:RecordForwardOrganizations>
		                        <nc20:OrganizationIdentification>
		                            <nc20:IdentificationID>
		                            	<xsl:value-of select="$rapBackRecipient"/>
		                            </nc20:IdentificationID>
		                        </nc20:OrganizationIdentification>
		                     </ansi-nist:RecordForwardOrganizations>
														
							<ebts:RecordRapBackTriggeringEventCode>
								<xsl:value-of select="$rapBackTriggeringEvent" />
							</ebts:RecordRapBackTriggeringEventCode>							
														
							<xsl:apply-templates select="submsg-ext:Subject/j:PersonAugmentation/j:PersonStateFingerprintIdentification[nc20:IdentificationID !='']"/>
																				
						</ebts:RecordRapBackData>
						
						<ebts:RecordTransactionActivity>
							<nc20:CaseTrackingID>
								<xsl:value-of select="$originatingAgencyCaseNumber"/>
							</nc20:CaseTrackingID>
							<ebts:RecordControllingAgency>
                       			 <nc20:OrganizationIdentification>
                            			<nc20:IdentificationID>
                            				<xsl:value-of select="$controllingAgencyID"/>
                            			</nc20:IdentificationID>
                       			 </nc20:OrganizationIdentification>
                   			</ebts:RecordControllingAgency>
						</ebts:RecordTransactionActivity>
						<xsl:apply-templates select="submsg-ext:Subject"/>	
					</ebts:DomainDefinedDescriptiveFields>
				</itl:UserDefinedDescriptiveDetail>
			</itl:PackageInformationRecord>
		</itl:NISTBiometricInformationExchangePackage>
	</xsl:template>
	<xsl:template match="submsg-ext:Subject">
		<ebts:RecordSubject>			
			<xsl:apply-templates select="nc20:PersonBirthDate"/>			
			<xsl:apply-templates select="nc20:PersonName"/>												
			<xsl:apply-templates select="j:PersonAugmentation" />					
		</ebts:RecordSubject>
	</xsl:template>
	
	<xsl:template match="nc20:PersonBirthDate">
		<xsl:copy-of select="." copy-namespaces="no"/>
	</xsl:template>
	
	<xsl:template match="nc20:PersonName">
		<ebts:PersonName>
			<xsl:apply-templates select="nc20:PersonGivenName"/>
			<xsl:apply-templates select="nc20:PersonSurName"/>
		</ebts:PersonName>
	</xsl:template>
	
	<xsl:template match="nc20:PersonGivenName">
		<xsl:copy-of select="." copy-namespaces="no"/>
	</xsl:template>
	
	<xsl:template match="nc20:PersonSurName">
		<xsl:copy-of select="." copy-namespaces="no"/>
	</xsl:template>
	
	<xsl:template match="j:PersonAugmentation">
		<xsl:apply-templates select="j:PersonFBIIdentification"/>			
	</xsl:template>
	
	<xsl:template match="j:PersonFBIIdentification">		
		<xsl:copy-of select="." copy-namespaces="no" />
	</xsl:template>
		
	<xsl:template match="j:PersonStateFingerprintIdentification">
		<ebts:RecordRapBackUserDefinedElement>
			<ebts:UserDefinedElementName>State Fingerprint ID</ebts:UserDefinedElementName>
			<ebts:UserDefinedElementText>
				<xsl:value-of select="normalize-space(.)"/>
			</ebts:UserDefinedElementText>
		</ebts:RecordRapBackUserDefinedElement>
	</xsl:template>
	
	<xsl:template match="submsg-ext:CriminalSubscriptionReasonCode" mode="transactionCategory">
		<ebts:TransactionCategoryCode>
			<xsl:choose>
				<xsl:when test=". = 'CI'">RBSCRM</xsl:when>
				<xsl:when test=". = 'CS'">RBSCRM</xsl:when>
			</xsl:choose>
		</ebts:TransactionCategoryCode>
	</xsl:template>
	<xsl:template match="submsg-ext:CriminalSubscriptionReasonCode" mode="rapBackCategory">
		<ebts:RecordRapBackCategoryCode>
			<xsl:value-of select="."/>
		</ebts:RecordRapBackCategoryCode>
	</xsl:template>
	<xsl:template match="submsg-ext:CivilSubscriptionReasonCode" mode="rapBackCategory">
		<ebts:RecordRapBackCategoryCode>
			<xsl:value-of select="."/>
		</ebts:RecordRapBackCategoryCode>
	</xsl:template>
	
	<xsl:template match="nc20:Date" mode="expirationDate">
		<ebts:RecordRapBackExpirationDate>
			<nc20:Date>								
				<xsl:value-of select="." />
			</nc20:Date>
		</ebts:RecordRapBackExpirationDate>		
	</xsl:template>
	
</xsl:stylesheet>
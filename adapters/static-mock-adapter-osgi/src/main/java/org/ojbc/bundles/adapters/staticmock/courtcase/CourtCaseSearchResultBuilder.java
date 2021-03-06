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
package org.ojbc.bundles.adapters.staticmock.courtcase;

import org.apache.commons.lang.StringUtils;
import org.ojbc.util.xml.OjbcNamespaceContext;
import org.ojbc.util.xml.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CourtCaseSearchResultBuilder {

	public static CourtCaseDetail getCourtCaseDetail(Document courtCaseDetailDoc) throws Exception{
	
		CourtCaseDetail courtCaseDetail = new CourtCaseDetail();
		
		String caseGenCatTxt = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Case/nc30:CaseGeneralCategoryText");		
		courtCaseDetail.setCaseGeneralCategoryText(caseGenCatTxt);
				
		String caseTrackingId = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Case/nc30:CaseTrackingID");	
		courtCaseDetail.setCaseTrackingID(caseTrackingId);	
		
		String sDocketIdVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Case/nc30:CaseDocketID");	
		courtCaseDetail.setCaseDocketID(sDocketIdVal);
		
		String sJurisdictionTxtVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//jxdm51:CaseCourt/jxdm51:OrganizationAugmentation/jxdm51:OrganizationJurisdiction/nc30:JurisdictionText");	
		courtCaseDetail.setJurisdictionText(sJurisdictionTxtVal);
		
		String caseOtherIdCatDescTxt = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//jxdm51:CaseOtherIdentification/nc30:IdentificationCategoryDescriptionText");		
		courtCaseDetail.setCaseOtherInfoIdCatDescTxt(caseOtherIdCatDescTxt);		
		
		String dobVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Identity/nc30:IdentityPersonRepresentation/nc30:PersonBirthDate/nc30:Date");
		courtCaseDetail.setPersonBirthDate(dobVal);
		
		String eyeColorVal =  XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/jxdm51:PersonEyeColorCode");		
		courtCaseDetail.setPersonEyeColor(eyeColorVal);
		
		String hairColorVal =  XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/jxdm51:PersonHairColorCode");
		courtCaseDetail.setPersonHairColor(hairColorVal);
		
		String heightTxt = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/nc30:PersonHeightMeasure/nc30:MeasureValueText");
		courtCaseDetail.setPersonHeight(heightTxt);
		
		String heightUnitTxt = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/nc30:PersonHeightMeasure/nc30:MeasureUnitText");
		courtCaseDetail.setPersonHeightUnits(heightUnitTxt);
		
		String givenNameVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:PersonName/nc30:PersonGivenName");
		courtCaseDetail.setPersonGivenName(givenNameVal);
		
		String middleNameVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:PersonName/nc30:PersonMiddleName");
		courtCaseDetail.setPersonMiddleName(middleNameVal);
		
		String personSurNameVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:PersonName/nc30:PersonSurName");
		courtCaseDetail.setPersonSurName(personSurNameVal);
		
		String raceVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/nc30:PersonRaceText");
		courtCaseDetail.setPersonRaceCode(raceVal);
		
		String personSexVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/nc30:PersonSexText");
		courtCaseDetail.setPersonSexCode(personSexVal);
		
		String weightVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//nc30:Person/nc30:PersonWeightMeasure/nc30:MeasureValueText");			
		courtCaseDetail.setPersonWeight(weightVal);
		
		String drivLicVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//jxdm51:DriverLicense/jxdm51:DriverLicenseIdentification/nc30:IdentificationID");
		courtCaseDetail.setDriverLicenseId(drivLicVal);
		
		String dlSourceTxt = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//jxdm51:DriverLicense/jxdm51:DriverLicenseIdentification/nc30:IdentificationSourceText");		
		courtCaseDetail.setDriverLicenseIdSourceTxt(dlSourceTxt);
		
		String fbiId = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//jxdm51:PersonFBIIdentification/nc30:IdentificationID");
		courtCaseDetail.setPersonFBIId(fbiId);
		
		String sidVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Person/nc30:PersonStateIdentification");				
		sidVal = sidVal == null ? null : sidVal.trim();		
		courtCaseDetail.setPersonSid(sidVal);
		
		String srcSysNameTxtVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//ccq-res-ext:SourceSystemNameText");
		courtCaseDetail.setSourceSystemNameText(srcSysNameTxtVal);
		
		String sysIdVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//intel31:SystemIdentification/nc30:IdentificationID");
		courtCaseDetail.setSystemId(sysIdVal);
		
		String sysName = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//intel31:SystemIdentification/nc30:SystemName");		
		courtCaseDetail.setSystemName(sysName);
		
		String srchResCatTxtVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//ccq-res-ext:QueryResultCategoryText");			
		courtCaseDetail.setSearchResultCategoryText(srchResCatTxtVal);
		
		String orgBranchNameVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, 
				"//ccq-res-ext:InformationOwningOrganization/nc30:OrganizationBranchName");			
		courtCaseDetail.setOrganizationBranchName(orgBranchNameVal);
		
		String sOrgName = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//ccq-res-ext:InformationOwningOrganization/nc30:OrganizationName");
		courtCaseDetail.setOrganizationName(sOrgName);
		
		String lastUpdatedDateVal = XmlUtils.xPathStringSearch(courtCaseDetailDoc, "//nc30:Metadata/nc30:LastUpdatedDate/nc30:Date");
		courtCaseDetail.setLastUpdatedDate(lastUpdatedDateVal);
		
		return courtCaseDetail;
	}
	
	
	public static Element buildCourtCaseSearchResultElement(Document courtCaseSearchResultsDocument, Document courtCaseDetailDoc, String resultId) throws Exception{
		
		CourtCaseDetail courtCaseDetail = getCourtCaseDetail(courtCaseDetailDoc);
		
		Element courtCaseSearchResultElement = courtCaseSearchResultsDocument.createElementNS(OjbcNamespaceContext.NS_COURT_CASE_SEARCH_RESULTS, "CourtCaseSearchResult");	
		
		courtCaseSearchResultElement.setPrefix(OjbcNamespaceContext.NS_PREFIX_COURT_CASE_SEARCH_RESULTS);
		
		XmlUtils.addAttribute(courtCaseSearchResultElement, OjbcNamespaceContext.NS_STRUCTURES_30, "id", "Result_" + resultId);
												
		Element caseElement = XmlUtils.appendElement(courtCaseSearchResultElement, OjbcNamespaceContext.NS_NC_30, "Case");
		
		XmlUtils.addAttribute(caseElement, OjbcNamespaceContext.NS_STRUCTURES_30, "id", "Case_" + resultId);		
		
				
		String courtCaseGeneralCategory = courtCaseDetail.getCaseGeneralCategoryText();
		
		if(StringUtils.isNotBlank(courtCaseGeneralCategory)){

			Element caseGenCatTxtElement = XmlUtils.appendElement(caseElement, OjbcNamespaceContext.NS_NC_30, "CaseGeneralCategoryText");
			
			courtCaseGeneralCategory = courtCaseGeneralCategory.trim();
			
			caseGenCatTxtElement.setTextContent(courtCaseGeneralCategory);			
		}		

								
		
		String sTrackingId = courtCaseDetail.getCaseTrackingID();
		
		if(StringUtils.isNotBlank(sTrackingId)){
		
			Element caseTrackId = XmlUtils.appendElement(caseElement, OjbcNamespaceContext.NS_NC_30, "CaseTrackingID");		
			
			sTrackingId = sTrackingId.trim();
			
			caseTrackId.setTextContent(sTrackingId);			
		}
		
		
		String sDocketId = courtCaseDetail.getCaseDocketID();
		
		if(StringUtils.isNotBlank(sDocketId)){

			Element caseDocketIdElement = XmlUtils.appendElement(caseElement, OjbcNamespaceContext.NS_NC_30, "CaseDocketID");
			
			caseDocketIdElement.setTextContent(sDocketId);				
		}		
				
		
		Element caseAugment =  XmlUtils.appendElement(caseElement, OjbcNamespaceContext.NS_JXDM_51, "CaseAugmentation");		
		
		String sJurisdictionTxt = courtCaseDetail.getJurisdictionText();
		
		if(StringUtils.isNotBlank(sJurisdictionTxt)){
			
			Element caseCourt = XmlUtils.appendElement(caseAugment, OjbcNamespaceContext.NS_JXDM_51, "CaseCourt");			
			
			Element orgAugment = XmlUtils.appendElement(caseCourt, OjbcNamespaceContext.NS_JXDM_51, "OrganizationAugmentation");
			
			Element orgJurisdiction = XmlUtils.appendElement(orgAugment, OjbcNamespaceContext.NS_JXDM_51, "OrganizationJurisdiction");
			
			Element jurisdictionTxtElement = XmlUtils.appendElement(orgJurisdiction, OjbcNamespaceContext.NS_NC_30, "JurisdictionText");
			
			sJurisdictionTxt = sJurisdictionTxt.trim();
			
			jurisdictionTxtElement.setTextContent(sJurisdictionTxt);			
		}
		

		String sCaseOtherInfoIdCatDescTxt = courtCaseDetail.getCaseOtherInfoIdCatDescTxt();
		
		if(StringUtils.isBlank(sCaseOtherInfoIdCatDescTxt)){

			Element caseOtherIdElement = XmlUtils.appendElement(caseAugment, OjbcNamespaceContext.NS_JXDM_51, "CaseOtherIdentification");
			
			Element idCatDescTxtElement = XmlUtils.appendElement(caseOtherIdElement, OjbcNamespaceContext.NS_NC_30, "IdentificationCategoryDescriptionText"); 	
			
			sCaseOtherInfoIdCatDescTxt = sCaseOtherInfoIdCatDescTxt.trim();
			
			idCatDescTxtElement.setTextContent(sCaseOtherInfoIdCatDescTxt);			
		}						

				
		Element person =  XmlUtils.appendElement(courtCaseSearchResultElement, OjbcNamespaceContext.NS_NC_30, "Person");			
		XmlUtils.addAttribute(person, OjbcNamespaceContext.NS_STRUCTURES_30, "id", "Person_" + resultId);		
		XmlUtils.addAttribute(person, OjbcNamespaceContext.NS_STRUCTURES_30, "metadata", "M" + resultId);		
		
		String personDob = courtCaseDetail.getPersonBirthDate();
		
		if(StringUtils.isNotBlank(personDob)){
			
			Element personDobElement = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_NC_30, "PersonBirthDate");
			
			Element personDobValueElement = XmlUtils.appendElement(personDobElement, OjbcNamespaceContext.NS_NC_30, "Date");		
			
			personDob = personDob.trim();
			
			personDobValueElement.setTextContent(personDob);						
		}
		
		
		String sEyeColor = courtCaseDetail.getPersonEyeColor();
		
		if(StringUtils.isNotBlank(sEyeColor)){

			Element eyeColorElement = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_NC_30, "PersonEyeColorText");
			
			sEyeColor = sEyeColor.trim();
			
			eyeColorElement.setTextContent(sEyeColor);
		}
		
		String sHairColor = courtCaseDetail.getPersonHairColor();
		
		if(StringUtils.isNotBlank(sHairColor)){
			
			Element hairColor = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_NC_30, "PersonHairColorText");	
			
			sHairColor = sHairColor.trim();
			
			hairColor.setTextContent(sHairColor);			
		}

		
		String sHeight = courtCaseDetail.getPersonHeight();		
		
		if(StringUtils.isNotBlank(sHeight)){

			Element height = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_NC_30, "PersonHeightMeasure");
			
			Element measureValueTxt = XmlUtils.appendElement(height, OjbcNamespaceContext.NS_NC_30, "MeasureValueText");
			
			sHeight = sHeight.trim();
			
			measureValueTxt.setTextContent(sHeight);	
						
			String sHeightUnits = courtCaseDetail.getPersonHeightUnits();
			
			if(StringUtils.isNotBlank(sHeightUnits)){

				Element lengthUnitCode = XmlUtils.appendElement(height, OjbcNamespaceContext.NS_NC_30, "LengthUnitCode");
				
				lengthUnitCode.setTextContent(sHeightUnits);				
			}									
		}

		
		String personGivenName = courtCaseDetail.getPersonGivenName();		
		
		boolean hasPersonGivenName = StringUtils.isNotBlank(personGivenName);
				
		String sPersonMiddleName = courtCaseDetail.getPersonMiddleName();
		
		boolean hasPersonMiddleName = StringUtils.isNotBlank(sPersonMiddleName);
		
		String personSurName = courtCaseDetail.getPersonSurName();
		
		boolean hasPersonSurName = StringUtils.isNotBlank(personSurName);
		

		if(hasPersonGivenName || hasPersonMiddleName || hasPersonSurName){
			
			Element personNameElement = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_NC_30, "PersonName");	

			if(hasPersonGivenName){

				Element personGivenNameElement = XmlUtils.appendElement(personNameElement, OjbcNamespaceContext.NS_NC_30, "PersonGivenName");
				
				personGivenName = personGivenName.trim();
				
				personGivenNameElement.setTextContent(personGivenName);
			}
			
			if(hasPersonMiddleName){
				
				Element personMiddleNameElement = XmlUtils.appendElement(personNameElement, OjbcNamespaceContext.NS_NC_30, "PersonMiddleName");
				
				sPersonMiddleName = sPersonMiddleName.trim();
				
				personMiddleNameElement.setTextContent(sPersonMiddleName);				
			}
			
			if(hasPersonSurName){

				Element personSurNameElement = XmlUtils.appendElement(personNameElement, OjbcNamespaceContext.NS_NC_30, "PersonSurName");		
			
				personSurName = personSurName.trim();
				
				personSurNameElement.setTextContent(personSurName);					
			}			
		}

		
		String sRaceCode = courtCaseDetail.getPersonRaceCode();
		
		if(StringUtils.isNotBlank(sRaceCode)){
			
			Element personRaceCodeElement = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_JXDM_51, "PersonRaceCode");
			
			sRaceCode = sRaceCode.trim();
			
			personRaceCodeElement.setTextContent(sRaceCode);			
		}
				
		
		String sPersonSexCode = courtCaseDetail.getPersonSexCode();
		
		if(StringUtils.isNotBlank(sPersonSexCode)){

			Element personSexCode = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_JXDM_51, "PersonSexCode");
			
			sPersonSexCode = sPersonSexCode.trim();
			
			personSexCode.setTextContent(sPersonSexCode);			
		}
		
		String sPersonWeight = courtCaseDetail.getPersonWeight();
		
		if(StringUtils.isNotBlank(sPersonWeight)){
			
			Element personWeightMeasure = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_NC_30, "PersonWeightMeasure");
			
			Element measureValTxt = XmlUtils.appendElement(personWeightMeasure, OjbcNamespaceContext.NS_NC_30, "MeasureValueText");
						
			sPersonWeight = sPersonWeight.trim();
			
			measureValTxt.setTextContent(sPersonWeight);					
		}
		
		
		Element personAugmentation = XmlUtils.appendElement(person, OjbcNamespaceContext.NS_JXDM_51, "PersonAugmentation");
		
		
		String sDriversLicenseId = courtCaseDetail.getDriverLicenseId();
		
		if(StringUtils.isNotBlank(sDriversLicenseId)){

			Element driverLicense = XmlUtils.appendElement(personAugmentation, OjbcNamespaceContext.NS_JXDM_51, "DriverLicense");
			
			Element driverLicenseId = XmlUtils.appendElement(driverLicense, OjbcNamespaceContext.NS_JXDM_51, "DriverLicenseIdentification");
			
			Element driverLicenseIdVal = XmlUtils.appendElement(driverLicenseId, OjbcNamespaceContext.NS_NC_30, "IdentificationID"); 					
			
			sDriversLicenseId = sDriversLicenseId.trim();
			
			driverLicenseIdVal.setTextContent(sDriversLicenseId);	
			
			String driversLicenseSourceTxt = courtCaseDetail.getDriverLicenseIdSourceTxt();
			
			if(StringUtils.isNotBlank(driversLicenseSourceTxt)){
				
				Element drivLicIdSrcTxtElement = XmlUtils.appendElement(driverLicenseId, OjbcNamespaceContext.NS_NC_30, "IdentificationSourceText");
				
				driversLicenseSourceTxt = driversLicenseSourceTxt.trim();
				
				drivLicIdSrcTxtElement.setTextContent(driversLicenseSourceTxt);								
			}
		}
		
		
		String sFbiId = courtCaseDetail.getPersonFBIId();
		
		if(StringUtils.isNotBlank(sFbiId)){
			
			Element personFBIId = XmlUtils.appendElement(personAugmentation, OjbcNamespaceContext.NS_JXDM_51, "PersonFBIIdentification");
			
			Element personFBIIdVal = XmlUtils.appendElement(personFBIId, OjbcNamespaceContext.NS_NC_30, "IdentificationID");
			
			sFbiId = sFbiId.trim();
			
			personFBIIdVal.setTextContent(sFbiId);			
		}

		
		String sPersonSid = courtCaseDetail.getPersonSid();
		
		if(StringUtils.isNotBlank(sPersonSid)){
			
			Element personSid = XmlUtils.appendElement(personAugmentation, OjbcNamespaceContext.NS_JXDM_51, "PersonStateFingerprintIdentification");
			
			Element personSidVal = XmlUtils.appendElement(personSid, OjbcNamespaceContext.NS_NC_30, "IdentificationID");
							
			sPersonSid = sPersonSid.trim();
			
			personSidVal.setTextContent(sPersonSid);				
		}

		Element personCaseAssociation = XmlUtils.appendElement(courtCaseSearchResultElement, OjbcNamespaceContext.NS_CYFS, "PersonCaseAssociation");			
		
		Element personAssoc = XmlUtils.appendElement(personCaseAssociation, OjbcNamespaceContext.NS_NC_30, "Person");
		XmlUtils.addAttribute(personAssoc, OjbcNamespaceContext.NS_STRUCTURES_30, "ref", "Person_" + resultId);
		
		Element caseAssoc = XmlUtils.appendElement(personCaseAssociation, OjbcNamespaceContext.NS_NC_30, "Case");
		XmlUtils.addAttribute(caseAssoc, OjbcNamespaceContext.NS_STRUCTURES_30, "ref", "Case_" + resultId);
		
		
		String sourceSystemNameText = courtCaseDetail.getSourceSystemNameText();
		
		if(StringUtils.isNotBlank(sourceSystemNameText)){

			Element srcSysNameTxtElement = XmlUtils.appendElement(courtCaseSearchResultElement, 
					OjbcNamespaceContext.NS_COURT_CASE_SEARCH_RESULTS_EXT, "SourceSystemNameText");
							
			sourceSystemNameText = sourceSystemNameText.trim();
			
			srcSysNameTxtElement.setTextContent(sourceSystemNameText);			
		}
		
		
		String sSystemId = courtCaseDetail.getSystemId();
		
		String sSystemName = courtCaseDetail.getSystemName();
		
		boolean hasSystemId = StringUtils.isNotBlank(sSystemId);
		
		boolean hasSystemName = StringUtils.isNotBlank(sSystemName);
		
		if(hasSystemId || hasSystemName){
			
			Element sysIdElement = XmlUtils.appendElement(courtCaseSearchResultElement, OjbcNamespaceContext.NS_INTEL_31, "SystemIdentification");
			
			if(hasSystemId){

				Element sysIdValElement = XmlUtils.appendElement(sysIdElement, OjbcNamespaceContext.NS_NC_30, "IdentificationID");
				
				sSystemId = sSystemId.trim();
				
				sysIdValElement.setTextContent(sSystemId);				
			}
			
			if(hasSystemName){
				
				Element sysName = XmlUtils.appendElement(sysIdElement, OjbcNamespaceContext.NS_NC_30, "SystemName");
				
				sSystemName = sSystemName.trim();
				
				sysName.setTextContent(sSystemName);				
			}
		}

		
		
		String sSrchResCatTxt = courtCaseDetail.getSearchResultCategoryText();
		
		if(StringUtils.isNotBlank(sSrchResCatTxt)){
			
			Element srchResCatTxtElement = XmlUtils.appendElement(courtCaseSearchResultElement, 
					OjbcNamespaceContext.NS_COURT_CASE_SEARCH_RESULTS_EXT, "SearchResultCategoryText");
					
			sSrchResCatTxt = sSrchResCatTxt.trim();
			
			srchResCatTxtElement.setTextContent(sSrchResCatTxt);
		}
		
		String sOrgBranchName = courtCaseDetail.getOrganizationBranchName();
		
		String sOrgName = courtCaseDetail.getOrganizationName();
		
		boolean hasOrgBranchName = StringUtils.isNotBlank(sOrgBranchName);
		
		boolean hasOrgName = StringUtils.isNotBlank(sOrgName);
						
		if(hasOrgBranchName || hasOrgName){
			
			Element infoOwnOrgElement =  XmlUtils.appendElement(courtCaseSearchResultElement, 
					OjbcNamespaceContext.NS_COURT_CASE_SEARCH_RESULTS_EXT, "InformationOwningOrganization");
			
			if(hasOrgBranchName){
				
				Element orgBranchNameElement = XmlUtils.appendElement(infoOwnOrgElement, OjbcNamespaceContext.NS_NC_30, "OrganizationBranchName");
				
				sOrgBranchName = sOrgBranchName.trim();
				
				orgBranchNameElement.setTextContent(sOrgBranchName);				
			}
			
			if(hasOrgName){
				
				Element orgNameElement = XmlUtils.appendElement(infoOwnOrgElement, OjbcNamespaceContext.NS_NC_30, "OrganizationName");
				
				sOrgName = sOrgName.trim();
				
				orgNameElement.setTextContent(sOrgName);				
			}
		}
		
		String sLastUpdatedDate = courtCaseDetail.getLastUpdatedDate();
		
		if(StringUtils.isNotBlank(sLastUpdatedDate)){
			
			Element metadataElement = XmlUtils.appendElement(courtCaseSearchResultElement, OjbcNamespaceContext.NS_NC_30, "Metadata");
			
			XmlUtils.addAttribute(metadataElement, OjbcNamespaceContext.NS_STRUCTURES_30, "id", "M" + resultId);		
			
			Element lastUpdatedDateElement = XmlUtils.appendElement(metadataElement, OjbcNamespaceContext.NS_NC_30, "LastUpdatedDate");
			
			Element lastUpdateValElement = XmlUtils.appendElement(lastUpdatedDateElement, OjbcNamespaceContext.NS_NC_30, "Date");									
							
			sLastUpdatedDate = sLastUpdatedDate.trim();
			
			lastUpdateValElement.setTextContent(sLastUpdatedDate);				
		}
				
		return courtCaseSearchResultElement;
	}	

}


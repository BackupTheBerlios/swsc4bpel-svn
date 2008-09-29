<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:po="http://www.sws-challenge.org/schemas/rnet/POR" 
xmlns:poc="http://www.sws-challenge.org/schemas/rnet/POC" 
xmlns:dict="http://www.sws-challenge.org/schemas/rnet/dictionary" 
xmlns:core="http://www.sws-challenge.org/schemas/rnet/coreElements" 
xmlns:rosetta="http://localhost:8080/ontologies/RosettaNetOntology.owl#"
xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#" 
xmlns:anchor="http://localhost:8080/ontologies/ServiceAnchor.owl#"
xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
xmlns:owl="http://www.w3.org/2002/07/owl#"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/">
<poc:Pip3A4PurchaseOrderConfirmation>
	<core:fromRole>
		<core:PartnerRoleDescription>
			<core:ContactInformation>
				<core:contactName>
					<core:FreeFormText>Michael Moon</core:FreeFormText>
				</core:contactName>
				<core:EmailAddress>michael.moon@moon.ie</core:EmailAddress>
				<core:telephoneNumber>
					<core:CommunicationsNumber>+353(86)3882777</core:CommunicationsNumber>
				</core:telephoneNumber>
			</core:ContactInformation>
			<dict:GlobalPartnerRoleClassificationCode>Product Provider</dict:GlobalPartnerRoleClassificationCode>
			<core:PartnerDescription>
				<core:BusinessDescription>
					<core:businessName>
						<core:FreeFormText>Moon Company</core:FreeFormText>
					</core:businessName>
				</core:BusinessDescription>
					<core:ContactInformation>
					<core:contactName>
						<core:FreeFormText>Elizabeth Brown</core:FreeFormText>
					</core:contactName>
					<core:EmailAddress>elizabeth.brown@moon.ie</core:EmailAddress>
					<core:telephoneNumber>
                                    <core:CommunicationsNumber>+353(86)3882777</core:CommunicationsNumber>
                                 </core:telephoneNumber>
				</core:ContactInformation>
				<core:PhysicalLocation>
					<core:PhysicalAddress>
						<core:addressLine1>
							<core:FreeFormText>Moon Company, IDEA Business Park</core:FreeFormText>
						</core:addressLine1>
						<core:cityName>
							<core:FreeFormText>Galway</core:FreeFormText>
						</core:cityName>
						<dict:GlobalCountryCode>IE</dict:GlobalCountryCode>
						<core:NationalPostalCode>N/A</core:NationalPostalCode>
					</core:PhysicalAddress>
				</core:PhysicalLocation>
			</core:PartnerDescription>
		</core:PartnerRoleDescription>
	</core:fromRole>
	<poc:PurchaseOrder>
		<dict:GlobalPurchaseOrderStatusCode>
		<xsl:choose>
			<xsl:when test="//rosetta:hasGlobalPurchaseOrderStatusCode='Reject'">Reject</xsl:when>
			<xsl:when test="//rosetta:hasGlobalPurchaseOrderStatusCode='Pending'">Pending</xsl:when>
			<xsl:otherwise>Accept</xsl:otherwise>
		</xsl:choose>
		</dict:GlobalPurchaseOrderStatusCode>
		<dict:GlobalPurchaseOrderTypeCode>Packaged product</dict:GlobalPurchaseOrderTypeCode>
		<core:isDropShip>
			<core:AffirmationIndicator>Yes</core:AffirmationIndicator>
		</core:isDropShip>

		<xsl:apply-templates select="//rdf:Description/rdf:type[@rdf:resource='http://localhost:8080/ontologies/RosettaNetOntology.owl#ProductLineItem']">
		</xsl:apply-templates>
		
	</poc:PurchaseOrder>
	<core:requestingDocumentDateTime>
		<core:DateTimeStamp><xsl:value-of select="//rosetta:hasRequestingDocumentGenerationDateTime"/></core:DateTimeStamp>
	</core:requestingDocumentDateTime>
	<core:requestingDocumentIdentifier>
		<core:ProprietaryDocumentIdentifier><xsl:value-of select="//rosetta:hasRequestingDocumentIdentifier"/></core:ProprietaryDocumentIdentifier>
	</core:requestingDocumentIdentifier>
	<core:thisDocumentGenerationDateTime>
		<core:DateTimeStamp><xsl:value-of select="//rosetta:hasRequestingDocumentGenerationDateTime"/></core:DateTimeStamp>
	</core:thisDocumentGenerationDateTime>
	<core:thisDocumentIdentifier>
		<core:ProprietaryDocumentIdentifier><xsl:value-of select="//rosetta:hasResponseDocumentIdentifier"/></core:ProprietaryDocumentIdentifier>
	</core:thisDocumentIdentifier>
	<core:toRole>
		<core:PartnerRoleDescription>
			<dict:GlobalPartnerRoleClassificationCode>Buyer</dict:GlobalPartnerRoleClassificationCode>
		<core:PartnerDescription>
				<core:BusinessDescription>
					<core:businessName>
						<core:FreeFormText>Blue Company</core:FreeFormText>
					</core:businessName>
				</core:BusinessDescription>
				<core:ContactInformation>
					<core:contactName>
						<core:FreeFormText>Stefania Wolf</core:FreeFormText>
					</core:contactName>
					<core:EmailAddress>stefania.wolf@blue.com</core:EmailAddress>
					<core:telephoneNumber>
						<core:CommunicationsNumber>+43(650)89930023</core:CommunicationsNumber>
					</core:telephoneNumber>
				</core:ContactInformation>
				<core:PhysicalLocation>
						<core:PhysicalAddress>
								<core:addressLine1>
									<core:FreeFormText>North Business Center, Block 9</core:FreeFormText>
								</core:addressLine1>
								<core:cityName>
									<core:FreeFormText>Innsbruck</core:FreeFormText>
								</core:cityName>
								<dict:GlobalCountryCode>AT</dict:GlobalCountryCode>
								<core:NationalPostalCode>A-6020</core:NationalPostalCode>
						</core:PhysicalAddress>
				</core:PhysicalLocation>
			</core:PartnerDescription>
		</core:PartnerRoleDescription>
	</core:toRole>
</poc:Pip3A4PurchaseOrderConfirmation>
</xsl:template>

<xsl:template match="//rdf:Description/rdf:type[@rdf:resource='http://localhost:8080/ontologies/RosettaNetOntology.owl#ProductLineItem']">
		<poc:ProductLineItem>
			<core:buyerLineItem>
				<core:LineNumber><xsl:value-of select="../rosetta:hasLineNumber"/></core:LineNumber>
			</core:buyerLineItem>
			<dict:GlobalProductUnitOfMeasureCode><xsl:value-of select="../rosetta:hasGlobalProductUnitOfMeasureCode"/></dict:GlobalProductUnitOfMeasureCode>
			<dict:GlobalPurchaseOrderStatusCode><xsl:value-of select="../rosetta:hasGlobalPurchaseOrderStatusCode"/></dict:GlobalPurchaseOrderStatusCode>
			<core:isDropShip>
				<core:AffirmationIndicator><xsl:value-of select="../rosetta:hasDropShipAffirmationIndicator"/></core:AffirmationIndicator>
			</core:isDropShip>
			<core:LineNumber><xsl:value-of select="../rosetta:hasLineNumber"/></core:LineNumber>
			<poc:OrderQuantity>
				<core:requestedQuantity>
					<core:ProductQuantity><xsl:value-of select="../rosetta:hasRequestedQuantity"/></core:ProductQuantity>
				</core:requestedQuantity>
				<core:scheduledShipQuantity>
					<core:ProductQuantity><xsl:value-of select="../rosetta:hasRequestedQuantity"/></core:ProductQuantity>
				</core:scheduledShipQuantity>
			</poc:OrderQuantity>
			<core:ProductIdentification>
				<core:GlobalProductIdentifier><xsl:value-of select="../rosetta:hasGlobalProductIdentifier"/></core:GlobalProductIdentifier>
			</core:ProductIdentification>
			<core:requestedEvent>
				<core:TransportationEvent>
					<core:DateStamp><xsl:value-of select="../rosetta:hasRequestedDateStamp"/></core:DateStamp>
					<dict:GlobalTransportEventCode><xsl:value-of select="../rosetta:hasRequestedGlobalTransportEventCode"/></dict:GlobalTransportEventCode>
				</core:TransportationEvent>
			</core:requestedEvent>
			<core:requestedUnitPrice>
				<core:FinancialAmount>
					<xsl:variable name="requestedUnitPriceID" select="../rosetta:hasRequestedUnitPrice/@rdf:resource"/>
					<dict:GlobalCurrencyCode><xsl:value-of select="//rdf:Description[@rdf:about=$requestedUnitPriceID]/rosetta:hasGlobalCurrencyCode"/></dict:GlobalCurrencyCode>
					<core:MonetaryAmount><xsl:value-of select="//rdf:Description[@rdf:about=$requestedUnitPriceID]/rosetta:hasMonetaryAmount"/></core:MonetaryAmount>
				</core:FinancialAmount>
			</core:requestedUnitPrice>
			<core:ShippedQuantityInformation>
				<core:DateTimeStamp><xsl:value-of select="../rosetta:hasRequestedDateStamp"/></core:DateTimeStamp>
				<core:shippedQuantity>
					<core:ProductQuantity><xsl:value-of select="../rosetta:hasRequestedQuantity"/></core:ProductQuantity>
				</core:shippedQuantity>
			</core:ShippedQuantityInformation>
			<core:totalLineItemAmount>
				<core:FinancialAmount>
					<xsl:variable name="totalLineItemAmountID" select="../rosetta:hasTotalLineItemAmount/@rdf:resource"/>
					<dict:GlobalCurrencyCode><xsl:value-of select="//rdf:Description[@rdf:about=$totalLineItemAmountID]/rosetta:hasGlobalCurrencyCode"/></dict:GlobalCurrencyCode>
					<core:MonetaryAmount><xsl:value-of select="//rdf:Description[@rdf:about=$totalLineItemAmountID]/rosetta:hasMonetaryAmount"/></core:MonetaryAmount>
				</core:FinancialAmount>
			</core:totalLineItemAmount>
			<core:unitPrice>
				<core:FinancialAmount>
					<xsl:variable name="requestedUnitPriceID" select="../rosetta:hasRequestedUnitPrice/@rdf:resource"/>
					<dict:GlobalCurrencyCode><xsl:value-of select="//rdf:Description[@rdf:about=$requestedUnitPriceID]/rosetta:hasGlobalCurrencyCode"/></dict:GlobalCurrencyCode>
					<core:MonetaryAmount><xsl:value-of select="//rdf:Description[@rdf:about=$requestedUnitPriceID]/rosetta:hasMonetaryAmount"/></core:MonetaryAmount>
				</core:FinancialAmount>
			</core:unitPrice>
		</poc:ProductLineItem>
</xsl:template>

</xsl:stylesheet>

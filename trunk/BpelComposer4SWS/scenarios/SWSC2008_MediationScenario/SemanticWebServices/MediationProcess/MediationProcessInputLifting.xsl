<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:po="http://www.sws-challenge.org/schemas/rnet/POR" 
xmlns:dict="http://www.sws-challenge.org/schemas/rnet/dictionary" 
xmlns:core="http://www.sws-challenge.org/schemas/rnet/coreElements" 
xmlns:rosetta="http://localhost:8080/ontologies/RosettaNetOntology.owl#"
xmlns:anchor="http://localhost:8080/ontologies/ServiceAnchor.owl#"
xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
xmlns="http://localhost:8080/process/MediationProcessInput.owl#"
xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
xmlns:owl="http://www.w3.org/2002/07/owl#"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="//po:Pip3A4PurchaseOrderRequest">
		<in0>
		<rdf:RDF
			xmlns:rosetta="http://localhost:8080/ontologies/RosettaNetOntology.owl#"
			xmlns:anchor="http://localhost:8080/ontologies/ServiceAnchor.owl#"
			xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
			xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
			xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
			xmlns="http://localhost:8080/process/MediationProcessInput.owl#"
			xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
			xmlns:owl="http://www.w3.org/2002/07/owl#"
		  xml:base="http://localhost:8080/process/MediationProcessInput.owl">
		  <owl:Ontology rdf:about="http://localhost:8080/process/MediationProcessInput.owl">
			<owl:imports rdf:resource="http://localhost:8080/ontologies/ServiceAnchor.owl"/>
			<owl:imports rdf:resource="http://localhost:8080/ontologies/RosettaNetOntology.owl"/>
		  </owl:Ontology>
		  <rosetta:Pip3A4PurchaseOrderRequest rdf:ID="Pip3A4PurchaseOrderRequest_1">
			  <rosetta:hasPurchaseOrder>
				<rosetta:PurchaseOrder rdf:ID="PurchaseOrder_1">
				  <rosetta:hasRequestedDateStamp rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
				  ><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/po:PurchaseOrder/core:requestedEvent/core:TransportationEvent/core:DateStamp"/></rosetta:hasRequestedDateStamp>
				  <rosetta:hasDropShipAffirmationIndicator rdf:datatype=
				  "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/po:PurchaseOrder/core:isDropShip"/></rosetta:hasDropShipAffirmationIndicator>
				  <rosetta:hasRequestedGlobalTransportEventCode rdf:datatype=
				  "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/po:PurchaseOrder/core:requestedEvent/core:TransportationEvent/dict:GlobalTransportEventCode"/></rosetta:hasRequestedGlobalTransportEventCode>
				  <rosetta:hasGlobalPurchaseOrderTypeCode rdf:datatype=
				  "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/po:PurchaseOrder/dict:GlobalPurchaseOrderTypeCode"/></rosetta:hasGlobalPurchaseOrderTypeCode>
					<xsl:apply-templates select="/po:Pip3A4PurchaseOrderRequest/po:PurchaseOrder//po:ProductLineItem"/>
				</rosetta:PurchaseOrder>
			  </rosetta:hasPurchaseOrder>
			<rosetta:hasFromRole>
			  <rosetta:Buyer rdf:ID="Buyer_2">
				<rosetta:hasContactInformation>
				  <rosetta:ContactInformation rdf:ID="ContactInformation_3">
					<rosetta:hasTelephoneNumber rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
					><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:ContactInformation/core:telephoneNumber/core:CommunicationsNumber"/></rosetta:hasTelephoneNumber>
					<rosetta:hasContactName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
					><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:ContactInformation/core:contactName/core:FreeFormText"/></rosetta:hasContactName>
					<rosetta:hasEmailAddress rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
					><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:ContactInformation/core:EmailAddress"/></rosetta:hasEmailAddress>
				  </rosetta:ContactInformation>
				</rosetta:hasContactInformation>
				<rosetta:relatesToPartner>
				  <rosetta:Partner rdf:ID="Partner_4">
					<rosetta:hasPhysicalLocation>
					  <rosetta:PhysicalAddress rdf:ID="PhysicalAddress_7">
						<rosetta:hasCityName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/core:cityName/core:FreeFormText"/></rosetta:hasCityName>
						<rosetta:hasNationalPostalCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/core:NationalPostalCode"/></rosetta:hasNationalPostalCode>
						<rosetta:hasGlobalCountryCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/dict:GlobalCountryCode"/></rosetta:hasGlobalCountryCode>
						<rosetta:hasAddressLine1 rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/core:addressLine1/core:FreeFormText"/></rosetta:hasAddressLine1>
					  </rosetta:PhysicalAddress>
					</rosetta:hasPhysicalLocation>
					<rosetta:hasContactInformation>
					  <rosetta:ContactInformation rdf:ID="ContactInformation_6">
						<rosetta:hasTelephoneNumber rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:ContactInformation/core:telephoneNumber/core:CommunicationsNumber"/></rosetta:hasTelephoneNumber>
						<rosetta:hasEmailAddress rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:ContactInformation/core:EmailAddress"/></rosetta:hasEmailAddress>
						<rosetta:hasContactName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:ContactInformation/core:contactName/core:FreeFormText"/></rosetta:hasContactName>
					  </rosetta:ContactInformation>
					</rosetta:hasContactInformation>
					<rosetta:hasBusinessDescription>
					  <rosetta:BusinessDescription rdf:ID="BusinessDescription_5">
						<rosetta:hasBusinessName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:fromRole/core:PartnerRoleDescription/core:PartnerDescription/core:BusinessDescription/core:businessName/core:FreeFormText"/></rosetta:hasBusinessName>
					  </rosetta:BusinessDescription>
					</rosetta:hasBusinessDescription>
				  </rosetta:Partner>
				</rosetta:relatesToPartner>
			  </rosetta:Buyer>
			</rosetta:hasFromRole>
			<rosetta:hasToRole>
			  <rosetta:ProductProvider rdf:ID="ProductProvider_022">
				<rosetta:hasContactInformation>
				  <rosetta:ContactInformation rdf:ID="ContactInformation_033">
					<rosetta:hasTelephoneNumber rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
					><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:ContactInformation/core:telephoneNumber/core:CommunicationsNumber"/></rosetta:hasTelephoneNumber>
					<rosetta:hasContactName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
					><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:ContactInformation/core:contactName/core:FreeFormText"/></rosetta:hasContactName>
					<rosetta:hasEmailAddress rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
					><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:ContactInformation/core:EmailAddress"/></rosetta:hasEmailAddress>
				  </rosetta:ContactInformation>
				</rosetta:hasContactInformation>
				<rosetta:relatesToPartner>
				  <rosetta:Partner rdf:ID="Partner_044">
					<rosetta:hasPhysicalLocation>
					  <rosetta:PhysicalAddress rdf:ID="PhysicalAddress_077">
						<rosetta:hasCityName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/core:cityName/core:FreeFormText"/></rosetta:hasCityName>
						<rosetta:hasNationalPostalCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/core:NationalPostalCode"/></rosetta:hasNationalPostalCode>
						<rosetta:hasGlobalCountryCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/dict:GlobalCountryCode"/></rosetta:hasGlobalCountryCode>
						<rosetta:hasAddressLine1 rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:PhysicalLocation/core:PhysicalAddress/core:addressLine1/core:FreeFormText"/></rosetta:hasAddressLine1>
					  </rosetta:PhysicalAddress>
					</rosetta:hasPhysicalLocation>
					<rosetta:hasContactInformation>
					  <rosetta:ContactInformation rdf:ID="ContactInformation_066">
						<rosetta:hasTelephoneNumber rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:ContactInformation/core:telephoneNumber/core:CommunicationsNumber"/></rosetta:hasTelephoneNumber>
						<rosetta:hasEmailAddress rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:ContactInformation/core:EmailAddress"/></rosetta:hasEmailAddress>
						<rosetta:hasContactName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:ContactInformation/core:contactName/core:FreeFormText"/></rosetta:hasContactName>
					  </rosetta:ContactInformation>
					</rosetta:hasContactInformation>
					<rosetta:hasBusinessDescription>
					  <rosetta:BusinessDescription rdf:ID="BusinessDescription_055">
						<rosetta:hasBusinessName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
						><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:toRole/core:PartnerRoleDescription/core:PartnerDescription/core:BusinessDescription/core:businessName/core:FreeFormText"/></rosetta:hasBusinessName>
					  </rosetta:BusinessDescription>
					</rosetta:hasBusinessDescription>
				  </rosetta:Partner>
				</rosetta:relatesToPartner>
			  </rosetta:ProductProvider>
			</rosetta:hasToRole>
			<rosetta:hasRequestingDocumentGenerationDateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
			><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:thisDocumentGenerationDateTime/core:DateTimeStamp"/></rosetta:hasRequestingDocumentGenerationDateTime>
			<rosetta:hasRequestingDocumentIdentifier rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
			><xsl:value-of select="/po:Pip3A4PurchaseOrderRequest/core:thisDocumentIdentifier/core:ProprietaryDocumentIdentifier"/></rosetta:hasRequestingDocumentIdentifier>
		  </rosetta:Pip3A4PurchaseOrderRequest>
		  <anchor:Process rdf:ID="MediationProcess">
			<anchor:hasInput>
			  <anchor:Input rdf:ID="Input">
				<rdfs:label rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
				>Pip3A4PurchaseOrderRequest</rdfs:label>
				<anchor:parameterObject rdf:resource="#Pip3A4PurchaseOrderRequest_1"/>
			  </anchor:Input>
			</anchor:hasInput>
			<rdfs:label rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
			>MediationProcess</rdfs:label>
		  </anchor:Process>		
	  </rdf:RDF>
	  </in0>
	</xsl:template>
	
	<xsl:template match="po:ProductLineItem">
          <rosetta:hasProductLineItem>
            <xsl:text disable-output-escaping="yes">&lt;rosetta:ProductLineItem rdf:ID="ProductLineItem_</xsl:text><xsl:value-of select="core:LineNumber"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
               <rosetta:hasDropShipAffirmationIndicator rdf:datatype=
              "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="core:isDropShip/core:AffirmationIndicator"/></rosetta:hasDropShipAffirmationIndicator>
              <rosetta:hasRequestedQuantity rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
              ><xsl:value-of select="po:OrderQuantity/core:requestedQuantity/core:ProductQuantity"/></rosetta:hasRequestedQuantity>
              <rosetta:hasGlobalProductUnitOfMeasureCode rdf:datatype=
              "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="dict:GlobalProductUnitOfMeasureCode"/></rosetta:hasGlobalProductUnitOfMeasureCode>
              <rosetta:hasLineNumber rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
              ><xsl:value-of select="core:LineNumber"/></rosetta:hasLineNumber>
              <rosetta:hasRequestedUnitPrice>
                  <xsl:text disable-output-escaping="yes">&lt;rosetta:FinancialAmount rdf:ID="RequestedUnitPriceFinancialAmount_</xsl:text><xsl:value-of select="core:LineNumber"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
                  <rosetta:hasGlobalCurrencyCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
                  ><xsl:value-of select="core:requestedUnitPrice/core:FinancialAmount/dict:GlobalCurrencyCode"/></rosetta:hasGlobalCurrencyCode>
                  <rosetta:hasMonetaryAmount rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
                  ><xsl:value-of select="core:requestedUnitPrice/core:FinancialAmount/core:MonetaryAmount"/></rosetta:hasMonetaryAmount>
                  <xsl:text disable-output-escaping="yes">&lt;/rosetta:FinancialAmount&gt;</xsl:text>
              </rosetta:hasRequestedUnitPrice>
              <rosetta:hasGlobalProductIdentifier rdf:datatype=
              "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="core:ProductIdentification/core:GlobalProductIdentifier"/></rosetta:hasGlobalProductIdentifier>
              <rosetta:hasRequestedDateStamp rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
              ><xsl:value-of select="core:requestedEvent/core:TransportationEvent/core:DateStamp"/></rosetta:hasRequestedDateStamp>
              <rosetta:hasTotalLineItemAmount>
                 <xsl:text disable-output-escaping="yes">&lt;rosetta:FinancialAmount rdf:ID="TotalLineItemAmountFinancialAmount_</xsl:text><xsl:value-of select="core:LineNumber"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
                  <rosetta:hasMonetaryAmount rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
                  ><xsl:value-of select="core:totalLineItemAmount/core:FinancialAmount/core:MonetaryAmount"/></rosetta:hasMonetaryAmount>
                  <rosetta:hasGlobalCurrencyCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
                  ><xsl:value-of select="core:totalLineItemAmount/core:FinancialAmount/dict:GlobalCurrencyCode"/></rosetta:hasGlobalCurrencyCode>
                 <xsl:text disable-output-escaping="yes">&lt;/rosetta:FinancialAmount&gt;</xsl:text>
              </rosetta:hasTotalLineItemAmount>
              <rosetta:hasRequestedGlobalTransportEventCode rdf:datatype=
              "http://www.w3.org/2001/XMLSchema#string"><xsl:value-of select="core:requestedEvent/core:TransportationEvent/dict:GlobalTransportEventCode"/></rosetta:hasRequestedGlobalTransportEventCode>
                  <xsl:text disable-output-escaping="yes">&lt;/rosetta:ProductLineItem&gt;</xsl:text>
          </rosetta:hasProductLineItem>	
	</xsl:template>
</xsl:stylesheet>

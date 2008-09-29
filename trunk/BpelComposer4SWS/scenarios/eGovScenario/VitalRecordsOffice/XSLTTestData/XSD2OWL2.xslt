<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xt="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecordsOffice" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:template match="//orderBirthCertificateReturn">
		<rdf:RDF xmlns:j.0="http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsd="http://www.w3.org/2001/XMLSchema#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:sto="http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#" xmlns:psp="http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#" xmlns:daml="http://www.daml.org/2001/03/daml+oil#" xmlns:dc="http://purl.org/dc/elements/1.1/">
			<j.0:Acknowledgement>
				<j.0:hasDocumentType>
					<j.0:VitalRecordsDocumentType>
						<j.0:hasVitalRecordsDescription rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="document/description"/>
						</j.0:hasVitalRecordsDescription>
						<sto:hasIdentifier>
							<sto:Identifier>
								<sto:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="document/id "/>
								</sto:hasRef>
							</sto:Identifier>
						</sto:hasIdentifier>
					</j.0:VitalRecordsDocumentType>
				</j.0:hasDocumentType>
				<j.0:hasRecipientPostalAddress>
					<j.0:PostalAddress>
						<j.0:hasStreetAddress>
							<j.0:StreetAddress>
								<j.0:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="recipientPostalAddress/streetAddress"/>
								</j.0:hasRef>
							</j.0:StreetAddress>
						</j.0:hasStreetAddress>
						<j.0:hasZipcodeAndPostalDistrict>
							<j.0:ZipcodeAndPostalDistrict>
								<j.0:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="recipientPostalAddress/zipcodeAndPostalDistrict"/>
								</j.0:hasRef>
							</j.0:ZipcodeAndPostalDistrict>
						</j.0:hasZipcodeAndPostalDistrict>
						<j.0:hasRecipient>
							<j.0:Recipient>
								<j.0:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="recipientPostalAddress/recipient"/>
								</j.0:hasRef>
							</j.0:Recipient>
						</j.0:hasRecipient>
					</j.0:PostalAddress>
				</j.0:hasRecipientPostalAddress>
			</j.0:Acknowledgement>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

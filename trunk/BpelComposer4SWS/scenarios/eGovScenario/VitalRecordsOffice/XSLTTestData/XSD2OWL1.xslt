<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xt="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecordsOffice" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:template match="//getVitalRecordsDocumentInfoReturn">
		<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#" xmlns:sto="http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#" xmlns:psp="http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#">
			<j.0:DocumentInfo>
				<!-- DocumentType -->
				<j.0:hasDocumentType>
					<j.0:VitalRecordsDocumentType>
						<sto:hasIdentifier>
							<sto:Identifier>
								<sto:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="vitalRecordsDocumentType/id"/>
								</sto:hasRef>
							</sto:Identifier>
						</sto:hasIdentifier>
						<j.0:hasVitalRecordsDescription rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="vitalRecordsDocumentType/description"/>
						</j.0:hasVitalRecordsDescription>
					</j.0:VitalRecordsDocumentType>
				</j.0:hasDocumentType>
				<!-- Price -->
				<j.0:hasPrice>
					<j.0:Price>
						<psp:hasAmount>
							<psp:Amount>
								<psp:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="price"/>
								</psp:hasRef>
							</psp:Amount>
						</psp:hasAmount>
					</j.0:Price>
				</j.0:hasPrice>
				<!-- PaymentAccount -->
				<j.0:hasPaymentAccount>
					<psp:Account>
						<psp:hasAccountNumber>
							<psp:AccountNumber>
								<psp:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="paymentAccount/accountNumber"/>
								</psp:hasRef>
							</psp:AccountNumber>
						</psp:hasAccountNumber>
						<psp:hasBankID>
							<psp:BankID>
								<psp:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="paymentAccount/bankID"/>
								</psp:hasRef>
							</psp:BankID>
						</psp:hasBankID>
						<psp:hasAccountOwner>
							<psp:AccountOwner>
								<psp:hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="paymentAccount/accountOwner"/>
								</psp:hasRef>
							</psp:AccountOwner>
						</psp:hasAccountOwner>
					</psp:Account>
				</j.0:hasPaymentAccount>
			</j.0:DocumentInfo>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

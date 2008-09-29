<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xt="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecordsOffice" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:template match="//makePaymentReturn">
		<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsd="http://www.w3.org/2001/XMLSchema#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:daml="http://www.daml.org/2001/03/daml+oil#" xmlns="http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#" xmlns:dc="http://purl.org/dc/elements/1.1/">
			<Receipt>
				<!-- ConfirmedPayment -->
				<hasConfirmedPayment>
					<Payment>
					<!-- DestinationAccount -->
						<hasDestinationAccount>
							<Account>
								<hasAccountNumber>
									<AccountNumber>
										<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
											<xsl:value-of select="confirmedPayment/destAccount/accountNumber"/>
										</hasRef>
									</AccountNumber>
								</hasAccountNumber>
								<hasBankID>
									<BankID>
										<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
											<xsl:value-of select="confirmedPayment/destAccount/bankID"/>
										</hasRef>
									</BankID>
								</hasBankID>
								<hasAccountOwner>
									<AccountOwner>
										<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
											<xsl:value-of select="confirmedPayment/destAccount/accountOwner"/>
										</hasRef>
									</AccountOwner>
								</hasAccountOwner>
							</Account>
						</hasDestinationAccount>
						<!-- SourceAccount -->
						<hasSourceAccount>
							<Account>
								<hasAccountOwner>
									<AccountOwner>
										<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
											<xsl:value-of select="confirmedPayment/sourceAccount/accountOwner"/>
										</hasRef>
									</AccountOwner>
								</hasAccountOwner>
								<hasBankID>
									<BankID>
										<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
											<xsl:value-of select="confirmedPayment/sourceAccount/bankID"/>
										</hasRef>
									</BankID>
								</hasBankID>
								<hasAccountNumber>
									<AccountNumber>
										<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
											<xsl:value-of select="confirmedPayment/sourceAccount/accountNumber"/>
										</hasRef>
									</AccountNumber>
								</hasAccountNumber>
							</Account>
						</hasSourceAccount>
						<hasAmount>
							<Amount>
								<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="confirmedPayment/amount"/>
								</hasRef>
							</Amount>
						</hasAmount>
						<hasReasonForPayment>
							<ReasonForPayment>
								<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
									<xsl:value-of select="confirmedPayment/reasonForPayment"/>
								</hasRef>
							</ReasonForPayment>
						</hasReasonForPayment>
					</Payment>
				</hasConfirmedPayment>
				<!-- Confirmation-->
				<hasConfirmation>
					<Confirmation>
						<hasRef rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="confirmation"/>
						</hasRef>
					</Confirmation>
				</hasConfirmation>
			</Receipt>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

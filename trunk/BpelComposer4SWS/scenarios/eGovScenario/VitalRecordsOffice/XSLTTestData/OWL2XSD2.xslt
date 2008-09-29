<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#" xmlns:psp="http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#" xmlns="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords">
	<xsl:template match="/">
		<in0>
			<xsl:apply-templates select="//rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#BirthCertificateOrder']"/>
		</in0>
	</xsl:template>
	<!--BirthCerticateOrder-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#BirthCertificateOrder']">
		<person>
			<xsl:variable name="orderID" select="../j.0:hasPerson/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$orderID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#Person']"/>
		</person>
		<postalAddress>
			<xsl:variable name="addressID" select="../j.0:hasPostalAddress/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$addressID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#PostalAddress']"/>
		</postalAddress>
		<paymentReceipt>
			<xsl:variable name="orderID" select="../j.0:hasPaymentReceipt/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$orderID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Receipt']"/>
		</paymentReceipt>
	</xsl:template>
	<!--Person-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#Person']">
		<firstName>
			<xsl:variable name="firstNameID" select="../j.0:hasFirstName/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$firstNameID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#FirstName']"/>
		</firstName>
		<lastName>
			<xsl:variable name="lastNameID" select="../j.0:hasLastName/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$lastNameID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#LastName']"/>
		</lastName>
		<dateOfBirth>
			<xsl:variable name="birthID" select="../j.0:hasDateOfBirth/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$birthID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#DateOfBirth']"/>
		</dateOfBirth>
		<placeOfBirth>
			<xsl:variable name="placeID" select="../j.0:hasPlaceOfBirth/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$placeID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#PlaceOfBirth']"/>
		</placeOfBirth>
	</xsl:template>
	<!--PostalAddress-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#PostalAddress']">
		<recipient>
			<xsl:variable name="recipientID" select="../j.0:hasRecipient/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$recipientID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#Recipient']"/>
		</recipient>
		<streetAddress>
			<xsl:variable name="streetAddressID" select="../j.0:hasStreetAddress/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$streetAddressID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#StreetAddress']"/>
		</streetAddress>
		<zipcodeAndPostalDistrict>
			<xsl:variable name="zipcodeAndPostalDistrictID" select="../j.0:hasZipcodeAndPostalDistrict/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$zipcodeAndPostalDistrictID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#ZipcodeAndPostalDistrict']"/>
		</zipcodeAndPostalDistrict>
	</xsl:template>
	<!--FirstName,LastName,DateOfBirth,PlaceOfBirth,Recipient,StreetAddress,ZipCodeAndPostalDistrict,Confirmation-->
	<xsl:template match="rdf:type">
		<xsl:value-of select="../j.0:hasRef"/>
		<xsl:value-of select="../psp:hasRef"/>
	</xsl:template>
	<!--PaymentReceipt-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Receipt']">
		<confirm>
			<xsl:variable name="confirmID" select="../psp:hasConfirmation/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$confirmID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Confirmation']"/>
		</confirm>
	</xsl:template>
</xsl:stylesheet>
<!--Payment-->
<!--xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Payment']">
		<sourceAccount>
			<xsl:variable name="srcAccountID" select="../psp:hasSourceAccount/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$srcAccountID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Account']"/>
		</sourceAccount>
		<destAccount>
			<xsl:variable name="destAccountID" select="../psp:hasDestinationAccount/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$destAccountID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Account']"/>
		</destAccount>
		<amount>
			<xsl:variable name="AmountID" select="../psp:hasAmount/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$AmountID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Amount']"/>
		</amount>
		<reasonForPayment>
			<xsl:variable name="ReasonForPaymentID" select="../psp:hasReasonForPayment/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$ReasonForPaymentID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#ReasonForPayment']"/>
		</reasonForPayment>
	</xsl:template-->
<!--Account-->
<!--xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Account']">
		<accountOwner>
			<xsl:variable name="AccountOwnerID" select="../psp:hasAccountOwner/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$AccountOwnerID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#AccountOwner']"/>
		</accountOwner>
		<accountNumber>
			<xsl:variable name="AccountNumberID" select="../psp:hasAccountNumber/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$AccountNumberID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#AccountNumber']"/>
		</accountNumber>
		<bankID>
			<xsl:variable name="BankIDID" select="../psp:hasBankID/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$BankIDID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#BankID']"/>
		</bankID>
	</xsl:template-->
<!--Amount-->
<!--xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Amount']">
		<xsl:value-of select="../psp:hasRef"/>
	</xsl:template-->
<!--ReasonForPayment-->
<!--xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#ReasonForPayment']">
		<xsl:value-of select="../psp:hasRef"/>
	</xsl:template-->

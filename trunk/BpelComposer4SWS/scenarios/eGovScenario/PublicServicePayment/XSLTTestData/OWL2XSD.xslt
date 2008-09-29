<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#" xmlns="http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment">
	<xsl:template match="/">
		<in0>
			<xsl:apply-templates select="//rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Payment']"/>
		</in0>
	</xsl:template>
	<!--Payment-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Payment']">
		<sourceAccount>
			<xsl:variable name="srcAccountID" select="../j.0:hasSourceAccount/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$srcAccountID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Account']"/>
		</sourceAccount>
		<destAccount>
			<xsl:variable name="destAccountID" select="../j.0:hasDestinationAccount/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$destAccountID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Account']"/>
		</destAccount>
		<amount>
			<xsl:variable name="AmountID" select="../j.0:hasAmount/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$AmountID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Amount']"/>
		</amount>
		<reasonForPayment>
			<xsl:variable name="ReasonForPaymentID" select="../j.0:hasReasonForPayment/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$ReasonForPaymentID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#ReasonForPayment']"/>
		</reasonForPayment>
	</xsl:template>
	<!--Account-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Account']">
		<accountOwner>
			<xsl:variable name="AccountOwnerID" select="../j.0:hasAccountOwner/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$AccountOwnerID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#AccountOwner']"/>
		</accountOwner>
		<accountNumber>
			<xsl:variable name="AccountNumberID" select="../j.0:hasAccountNumber/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$AccountNumberID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#AccountNumber']"/>
		</accountNumber>
		<bankID>
			<xsl:variable name="BankIDID" select="../j.0:hasBankID/@rdf:nodeID"/>
			<xsl:apply-templates select="//rdf:Description[@rdf:nodeID=$BankIDID]/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#BankID']"/>
		</bankID>
	</xsl:template>
	<!--AccountOwner,AccountNumber,BankID-->
	<xsl:template match="rdf:type">
		<xsl:value-of select="../j.0:hasRef"/>
	</xsl:template>
	<!--Amount-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Amount']">
		<xsl:value-of select="../j.0:hasRef"/>
	</xsl:template>
	<!--ReasonForPayment-->
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#ReasonForPayment']">
		<xsl:value-of select="../j.0:hasRef"/>
	</xsl:template>
</xsl:stylesheet>
<!--


<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#Identifier']">
		<id>
			<xsl:value-of select="../sto:hasRef"/>
		</id>
	</xsl:template
[@rdf:resource='http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#AccountOwner']
<jo>
				<xsl:value-of select="$AmountID"/>
			</jo>

<xsl:apply-templates select="//rdf:description[@rdf:nodeID=$AmountID"/>

xs:import namespace="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords" schemaLocation="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords.xsd"/>
	<xs:import namespace="http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment" schemaLocation="http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment.xsd"/-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#" xmlns:sto="http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#" xmlns="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords">
	<xsl:template match="/">
		<in0>
			<xsl:apply-templates select="//rdf:type"/>
		</in0>
	</xsl:template>
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#VitalRecordsDocumentType']">
		<description>
			<xsl:value-of select="../j.0:hasVitalRecordsDescription"/>
		</description>
	</xsl:template>
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#Identifier']">
		<id>
			<xsl:value-of select="../sto:hasRef"/>
		</id>
	</xsl:template>
</xsl:stylesheet>





<!--xs:import namespace="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords" schemaLocation="http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords.xsd"/>
	<xs:import namespace="http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment" schemaLocation="http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment.xsd"/-->

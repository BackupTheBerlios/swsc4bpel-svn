<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#" xmlns="http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry">
	<xsl:template match="/">
		<in0>
			<xsl:apply-templates select="//rdf:type"/>
		</in0>
	</xsl:template>
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#Birth']">
		<dateOfBirth>
			<xsl:value-of select="../j.0:hasDateOfBirth"/>
		</dateOfBirth>
	</xsl:template>
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#Surname']">
		<surname>
			<xsl:value-of select="../j.0:hasRef"/>
		</surname>
	</xsl:template>
	<xsl:template match="rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#GivenName']">
		<givenName>
			<xsl:value-of select="../j.0:hasRef"/>
		</givenName>
	</xsl:template>
	<!-- disable default templates -->
	<xsl:template match="j.0:hasRef"/>
	<xsl:template match="j.0:hasDateOfBirth"/>
	<xsl:template match="j.0:hasGivenName"/>
	<xsl:template match="j.0:hasSurname"/>
	<xsl:template match="rdf:type"/>
</xsl:stylesheet>

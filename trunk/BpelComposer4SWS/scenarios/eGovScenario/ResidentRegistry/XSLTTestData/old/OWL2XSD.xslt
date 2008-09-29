<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#" xmlns="http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:template match="//rdf:Description">
	<xsl:variable name="X" select="rdf:type/@rdf:resource"/>
	<xsl:if test="$X='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile'">
		<in0>
			<givenName>
				<xsl:value-of select="j.0:hasGivenName"/>
			</givenName>
			<surname>
				<xsl:value-of select="j.0:hasSurname"/>
			</surname>
			<dateOfBirth>
				<xsl:value-of select="j.0:hasDateOfBirth"/>
			</dateOfBirth>
		</in0>
		</xsl:if>
		</xsl:template>
</xsl:stylesheet>

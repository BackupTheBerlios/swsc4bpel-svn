<?xml version="1.0" encoding="UTF-8"?><xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/Test/TestOntology.owl#" xmlns="http://localhost:8080/PublicServices/Test"><xsl:template match="//rdf:Description">	<xsl:variable name="X" select="rdf:type/@rdf:resource"/>	<xsl:if test="$X='http://localhost:8080/PublicServices/Test/TestOntology.owl#Address'">		<in0>			<recipient>				<xsl:value-of select="j.0:hasRecipient"/>			</recipient>			<streetAddress>				<xsl:value-of select="j.0:hasStreetAddress"/>			</streetAddress>			<zipcodeAndPostalDistrict>				<xsl:value-of select="j.0:hasZipCodeAndLocality"/>			</zipcodeAndPostalDistrict>		</in0>		</xsl:if>		</xsl:template>	</xsl:stylesheet>
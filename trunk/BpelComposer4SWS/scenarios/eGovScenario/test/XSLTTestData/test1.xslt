<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/Test/TestOntology.owl#" xmlns="http://localhost:8080/PublicServices/Test">
	<xsl:template match="//j.0:Address">
		<in0>
			<recipient>
				<xsl:value-of select="j.0:hasRecipient"/>
			</recipient>
			<streetAddress>
				<xsl:value-of select="j.0:hasStreetAddress"/>
			</streetAddress>
			<zipcodeAndPostalDistrict>
				<xsl:value-of select="j.0:hasZipCodeAndLocality"/>
			</zipcodeAndPostalDistrict>
		</in0>
	</xsl:template>
</xsl:stylesheet>
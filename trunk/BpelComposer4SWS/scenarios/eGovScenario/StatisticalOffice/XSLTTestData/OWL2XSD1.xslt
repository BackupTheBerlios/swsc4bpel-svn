<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#">
	<xsl:template match="/">
		<in0>
			<identifier>
				<xsl:value-of select=" //j.0:hasRef"/>
			</identifier>
		</in0>
	</xsl:template>
</xsl:stylesheet>

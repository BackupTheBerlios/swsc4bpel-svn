<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns="http://localhost:8080/PublicServices/services/CompositionTest">
	<xsl:output method="xml" version="1.0" encoding="ISO-8859-1"  indent="yes" cdata-section-elements="ResidentSearchProfile"/>
	<xsl:template match="/">
		<ResidentSearchProfile xsi:type="xsd:string">
			<xsl:copy-of select="/"/>
		</ResidentSearchProfile>
	</xsl:template>
</xsl:stylesheet>

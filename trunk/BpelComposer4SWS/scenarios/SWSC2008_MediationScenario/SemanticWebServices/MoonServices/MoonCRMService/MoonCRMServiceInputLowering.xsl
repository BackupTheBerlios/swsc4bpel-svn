<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="/">
				<searchString>
					<xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasBusinessName"/>
				</searchString>
	</xsl:template>
</xsl:stylesheet>

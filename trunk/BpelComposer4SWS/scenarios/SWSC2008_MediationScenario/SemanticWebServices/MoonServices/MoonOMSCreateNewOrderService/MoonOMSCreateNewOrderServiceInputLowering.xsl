<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="/">
		<Order xmlns="mooncompany">
			<authToken>RalfWeinand</authToken>
			<contact>
			  <name><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasContactName"/></name>
			  <telephone><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasTelephone"/></telephone>
			  <email><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasEmail"/></email>
			</contact>
			<shipTo>
			  <name><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasBusinessName"/></name>
			  <street><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasStreet"/></street>
			  <city><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasCity"/></city>
			  <postalCode><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasPostalCode"/></postalCode>
			  <country><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasCountryCode"/></country>
			</shipTo>
			<!-- optional -->
			<billTo>
			  <name></name>
			  <street></street>
			  <city></city>
			  <postalCode></postalCode>
			  <country></country>
			</billTo>
		</Order>
	</xsl:template>
</xsl:stylesheet>

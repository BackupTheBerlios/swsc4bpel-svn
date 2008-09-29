<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="/">
  		<LineItem xmlns="mooncompany">
  			<authToken>RalfWeinand</authToken>
  			<orderId><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasOrderID"/></orderId>
  			<item>
  			  <articleId><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasArticleID"/></articleId>
  			  <quantity><xsl:value-of select="/rdf:RDF/rdf:Description/moon:hasQuantity"/></quantity>
  			</item>
  		</LineItem>
	</xsl:template>
</xsl:stylesheet>

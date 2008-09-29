<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xmoon="mooncompany" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<rdf:RDF
			xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#"
			xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
			xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
			xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
			xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
			xmlns:owl="http://www.w3.org/2002/07/owl#"
			xmlns="http://localhost:8080/process/MediationProcess/MoonOMSAddLineItemServiceOutput.owl#"
		  xml:base="http://localhost:8080/process/MediationProcess/MoonOMSAddLineItemServiceOutput.owl">			
		  <xsl:text disable-output-escaping="yes">&lt;moon:AddLineItemResponse rdf:ID="AddLineItemResponse_4Item</xsl:text><xsl:value-of select="//xmoon:lineItemId"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
				<moon:hasItem>
					<moon:ProcessedItem rdf:ID="ProcessedItem_X">
						<moon:hasItemID rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="//xmoon:lineItemId"/>
						</moon:hasItemID>
					</moon:ProcessedItem>
				</moon:hasItem>
				<moon:hasOrder>
					<xsl:text disable-output-escaping="yes">&lt;moon:Order rdf:ID="Order_4Item</xsl:text><xsl:value-of select="//xmoon:lineItemId"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
						<moon:hasOrderID rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="//xmoon:orderId"/>
						</moon:hasOrderID>
					<xsl:text disable-output-escaping="yes">&lt;/moon:Order&gt;</xsl:text>
				</moon:hasOrder>
			<xsl:text disable-output-escaping="yes">&lt;/moon:AddLineItemResponse&gt;</xsl:text>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

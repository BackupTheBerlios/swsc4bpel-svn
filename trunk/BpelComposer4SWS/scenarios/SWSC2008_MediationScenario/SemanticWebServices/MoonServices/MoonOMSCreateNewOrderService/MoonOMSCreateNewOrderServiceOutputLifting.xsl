<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xmoon="mooncompany" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="/">
		<rdf:RDF
			xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#"
			xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
			xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
			xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
			xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
			xmlns:owl="http://www.w3.org/2002/07/owl#"
			xmlns="http://localhost:8080/process/MediationProcess/MoonOMSCreateNewOrderServiceOutput.owl#"
		  xml:base="http://localhost:8080/process/MediationProcess/MoonOMSCreateNewOrderServiceOutput.owl">
		  <moon:CreateNewOrderResponse rdf:ID="CreateNewOrderResponse_X">
			<moon:hasOrder>
			  <moon:Order rdf:ID="Order_X">
				<moon:hasOrderID rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
				><xsl:value-of select="/xmoon:NewOrderResponse/xmoon:orderId"/></moon:hasOrderID>
			  </moon:Order>
			</moon:hasOrder>
		  </moon:CreateNewOrderResponse>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

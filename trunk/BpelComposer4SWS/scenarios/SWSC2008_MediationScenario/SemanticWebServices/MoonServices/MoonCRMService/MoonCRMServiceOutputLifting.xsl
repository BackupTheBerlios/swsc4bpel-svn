<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xmoon="mooncompany">
	<xsl:template match="/">
		<rdf:RDF
			xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#"
			xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
			xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
			xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
			xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
			xmlns:owl="http://www.w3.org/2002/07/owl#"
			xmlns="http://localhost:8080/process/MediationProcess/MoonCRMServiceOutput.owl#"
		  xml:base="http://localhost:8080/process/MediationProcess/MoonCRMServiceOutput.owl">
		  <moon:CustomerLookupResponse rdf:ID="CustomerLookupResponse_X">
  			<moon:hasCustomer>
  			  <moon:IdentifiedCustomer rdf:ID="Customer_X">
  				<moon:hasCustomerID rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
  				><xsl:value-of select="/xmoon:SearchCustomerResponse/xmoon:customerId"/></moon:hasCustomerID>
  				<moon:hasBusinessName rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
  				><xsl:value-of select="/xmoon:SearchCustomerResponse/xmoon:businessName"/></moon:hasBusinessName>
  			  </moon:IdentifiedCustomer>
  			</moon:hasCustomer>
		  </moon:CustomerLookupResponse>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

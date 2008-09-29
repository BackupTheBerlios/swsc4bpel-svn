<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xmoon="mooncompany" xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:j.0="http://localhost:8080/ontologies/ServiceAnchor.owl#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/">
<in0>
<xsl:text disable-output-escaping="yes">&lt;rdf:RDF
    xmlns="http://localhost:8080/process/MediationProcess/MediationProcessConfirmLineItemServiceInput_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">.owl#"
    xmlns:moon="http://localhost:8080/ontologies/MoonOntology.owl#"
    xmlns:j.0="http://localhost:8080/ontologies/ServiceAnchor.owl#"
    xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
    xmlns:owl="http://www.w3.org/2002/07/owl#"
  xml:base="http://localhost:8080/process/MediationProcess/MediationProcessConfirmLineItemServiceInput_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">.owl"&gt;</xsl:text>
  <xsl:text disable-output-escaping="yes">&lt;owl:Ontology rdf:about="http://localhost:8080/process/MediationProcess/MediationProcessConfirmLineItemServiceInput_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">.owl"&gt;</xsl:text>  
    <owl:imports rdf:resource="http://localhost:8080/ontologies/MoonOntology.owl"/>
    <owl:imports rdf:resource="http://localhost:8080/ontologies/ServiceAnchor.owl"/>
  <xsl:text disable-output-escaping="yes">&lt;/owl:Ontology&gt;</xsl:text>
  <xsl:text disable-output-escaping="yes">&lt;j.0:Process rdf:ID="Process_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
    <j.0:hasInput>
	  <xsl:text disable-output-escaping="yes">&lt;j.0:Input rdf:ID="Input_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
        <rdfs:label rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
        >ConfirmLineItemRequest</rdfs:label>
        <j.0:parameterObject>
          <xsl:text disable-output-escaping="yes">&lt;j.0:ConfirmLineItemRequest rdf:ID="ConfirmLineItemRequest_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
            <moon:hasItem>
              <xsl:text disable-output-escaping="yes">&lt;j.0:ProcessedItem rdf:ID="ProcessedItem_4Item</xsl:text><xsl:value-of select="//xmoon:itemId"/><xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
                <moon:hasItemID rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
                ><xsl:value-of select="//xmoon:itemId"/></moon:hasItemID>
                <moon:hasStatus rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
                ><xsl:value-of select="//xmoon:status"/></moon:hasStatus>
              <xsl:text disable-output-escaping="yes">&lt;/j.0:ProcessedItem&gt;</xsl:text>
            </moon:hasItem>
          <xsl:text disable-output-escaping="yes">&lt;/j.0:ConfirmLineItemRequest&gt;</xsl:text>
        </j.0:parameterObject>
      <xsl:text disable-output-escaping="yes">&lt;/j.0:Input&gt;</xsl:text>
    </j.0:hasInput>
    <rdfs:label rdf:datatype="http://www.w3.org/2001/XMLSchema#string"
    >ConfirmLineItemProcess</rdfs:label>
  <xsl:text disable-output-escaping="yes">&lt;/j.0:Process&gt;</xsl:text>
<xsl:text disable-output-escaping="yes">&lt;/rdf:RDF&gt;</xsl:text>
</in0>
</xsl:template>
</xsl:stylesheet>

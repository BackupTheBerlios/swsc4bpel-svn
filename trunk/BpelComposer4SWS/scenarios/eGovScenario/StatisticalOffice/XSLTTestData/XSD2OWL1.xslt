<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:template match="//addToOfficialCitizenDocumentsStatisticReturn">
		<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsd="http://www.w3.org/2001/XMLSchema#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:daml="http://www.daml.org/2001/03/daml+oil#" xmlns="http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#" xmlns:dc="http://purl.org/dc/elements/1.1/">
			<Confirm>
				<hasMessage rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
					<xsl:value-of select="message"/>
				</hasMessage>
			</Confirm>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

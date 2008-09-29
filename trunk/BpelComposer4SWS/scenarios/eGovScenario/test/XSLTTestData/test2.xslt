<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xt="http://localhost:8080/PublicServices/Test/XTest">
	<xsl:template match="/performTestReturn">
		<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:to="http://localhost:8080/PublicServices/Test/TestOntology.owl#" >
			<to:Letter>
				<to:hasTitle>
					<xsl:value-of select="xt:title"/>
				</to:hasTitle>
				<to:hasContent>
					<xsl:value-of select="xt:content"/>
				</to:hasContent>
			</to:Letter>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>
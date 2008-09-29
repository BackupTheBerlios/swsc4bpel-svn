




<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#" xmlns="http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry">
<xsl:template match="/">
		<in0>
			<dateOfBirth>
					ddddddd
			</dateOfBirth>
			<surname>
					sssssss
			</surname>
			<givenName>
				    ggggggg
			</givenName>
	</in0>
</xsl:template>
</xsl:stylesheet>



<xsl:template match="//rdf:Description/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile']">
		<dateOfBirth>
			<xsl:value-of select="../j.0:hasDateOfBirth"/>
		</dateOfBirth>
	</xsl:template>
	<xsl:template match="//rdf:Description/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#Surname']">
		<surname>
			<xsl:value-of select="../j.0:hasRef"/>
		</surname>
	</xsl:template>
	<xsl:template match="//rdf:Description/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#GivenName']">
		<givenName>
			<xsl:value-of select="../j.0:hasRef"/>
		</givenName>
	</xsl:template>




<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#" xmlns="http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<!--xsl:template match="//rdf:Description">
	<xsl:variable name="X" select="rdf:type/@rdf:resource"/>
	<xsl:if test="$X='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile'">
		<in0>
			<givenName>
				<xsl:value-of select="j.0:hasGivenName"/>
			</givenName>
			<surname>
				<xsl:value-of select="j.0:hasSurname"/>
			</surname>
			<dateOfBirth>
				<xsl:value-of select="j.0:hasDateOfBirth"/>
			</dateOfBirth>
		</in0>
		</xsl:if>
		</xsl:template>
</xsl:stylesheet-->
	<xsl:template match="//rdf:Description">
		<xsl:variable name="X" select="rdf:type/@rdf:resource"/>
		<xsl:if test="$X='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile'">
			<in0>
				<givenName>
					<xsl:call-template name="GivenName"/>
				</givenName>
				<surname>
					<!--xsl:call-template name="Surname"/-->
				</surname>
				<dateOfBirth>
					<xsl:value-of select="j.0:hasDateOfBirth"/>
				</dateOfBirth>
			</in0>
		</xsl:if>
	</xsl:template>
	<xsl:template name="GivenName" match="//rdf:Description">
		<xsl:variable name="X" select="rdf:type/@rdf:resource"/>
		<xsl:if test="$X='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#GivenName'">
			<jo>$X</jo>
		</xsl:if>
	</xsl:template>
	<xsl:template name="Surname" match="//rdf:Description/rdf:type[@rdf:resource='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#Surame']">
		<xsl:value-of select="parent::node()//j.0:hasRef"/>
	</xsl:template>
	<!--xsl:template name="Surame" match="//rdf:Description">
		<xsl:variable name="X" select="rdf:type/@rdf:resource"/>
		<xsl:if test="$X='http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#Surame'">
			<xsl:value-of select="j.0:hasRef"/>
		</xsl:if>
	</xsl:template-->
</xsl:stylesheet>

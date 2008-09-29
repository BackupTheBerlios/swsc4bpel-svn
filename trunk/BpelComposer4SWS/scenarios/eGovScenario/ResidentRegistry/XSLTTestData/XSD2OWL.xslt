<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xt="http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:template match="//getResidentInfoReturn">
		<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:j.0="http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#">
			<j.0:ResidentInfo>
			<!-- Name -->
				<j.0:hasName>
					<j.0:Name>
						<xsl:if test="not(name/titles[@xsi:nil])">
							<j.0:hasTitles rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
								<xsl:value-of select="name/titles"/>
							</j.0:hasTitles>
						</xsl:if>
						<j.0:hasGivenName rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="name/givenName"/>
						</j.0:hasGivenName>
						<xsl:if test="not(name/middleNames[@xsi:nil])">
							<j.0:hasMiddleNames rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
								<xsl:value-of select="name/middleNames"/>
							</j.0:hasMiddleNames>
						</xsl:if>
						<j.0:hasSurname rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="name/surname"/>
						</j.0:hasSurname>
					</j.0:Name>
				</j.0:hasName>
				<!-- Address -->
				<j.0:hasAddress>
					<j.0:Address>
						<j.0:hasStreet rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="address/street"/>
						</j.0:hasStreet>
						<j.0:hasLocality rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="address/locality"/>
						</j.0:hasLocality>
						<j.0:hasStreetNumber rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="address/streetNumber"/>
						</j.0:hasStreetNumber>
						<j.0:hasZipCode rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="address/zipCode"/>
						</j.0:hasZipCode>
					</j.0:Address>
				</j.0:hasAddress>
				<!-- Birth -->
				<j.0:hasBirth>
					<j.0:Birth>
						<j.0:hasDateOfBirth rdf:datatype="http://www.w3.org/2001/XMLSchema#date">
							<xsl:value-of select="birth/dateOfBirth"/>
						</j.0:hasDateOfBirth>
						<j.0:hasPlaceOfBirth rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="birth/placeOfBirth"/>
						</j.0:hasPlaceOfBirth>
					</j.0:Birth>
				</j.0:hasBirth>
				<!-- Death -->
				<xsl:if test="not(death[@xsi:nil])">
				<j.0:hasDeath>
					<j.0:Death>
						<j.0:hasDateOfDeath rdf:datatype="http://www.w3.org/2001/XMLSchema#date">
							<xsl:value-of select="death/dateOfDeath"/>
						</j.0:hasDateOfDeath>
						<j.0:hasPlaceOfDeath rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="death/placeOfDeath"/>
						</j.0:hasPlaceOfDeath>
					</j.0:Death>
				</j.0:hasDeath>
				</xsl:if>
				<!-- Nationality -->
				<j.0:hasNationality>
					<j.0:Nationality>
						<j.0:hasNationalityKey rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="nationality/key"/>
						</j.0:hasNationalityKey>
					</j.0:Nationality>
				</j.0:hasNationality>
				<!-- Religion -->
				<xsl:if test="not(religion[@xsi:nil])">
				<j.0:hasReligion>
					<j.0:Religion>
						<j.0:hasReligionKey rdf:datatype="http://www.w3.org/2001/XMLSchema#string">
							<xsl:value-of select="religion/key"/>
						</j.0:hasReligionKey>
					</j.0:Religion>
				</j.0:hasReligion>
				</xsl:if>
			</j.0:ResidentInfo>
		</rdf:RDF>
	</xsl:template>
</xsl:stylesheet>

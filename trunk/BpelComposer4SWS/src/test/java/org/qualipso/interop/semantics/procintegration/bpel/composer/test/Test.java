package org.qualipso.interop.semantics.procintegration.bpel.composer.test;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Parameter;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.Constants;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;


/**
 * Testing class for Composition Tool
 */
public class Test {

	public static void main(String argv[]) throws Exception {
		
		System.out.println(Constants.DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR());
		removeTBox();
		
	}
	

	// org.qualipso.interop.semantics.procintegration.bpel.composer.test ResidentRegistry
	public static void generateResidentInfo() throws Exception {
		Logger.info("Generate ResidentInfo");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentInfo"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/ResidentInfo.owl"), "RDF/XML-ABBREV", null);

	}

	public static void generateResidentSearchProfile() throws Exception {
		Logger.info("Generate ResidentSearchProfile");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/ResidentSearchProfile.owl"), "RDF/XML", null);
	}

	// org.qualipso.interop.semantics.procintegration.bpel.composer.test VitalRecordsOffice
	public static void generateVitalRecordsDocumentType() throws Exception {
		Logger.info("Generate VitalRecordsDocumentType");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#VitalRecordsDocumentType"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/VitalRecordsDocumentType.owl"), "RDF/XML", null);
	}
	
	public static void generateVitalRecordsDocumentInfo() throws Exception {
		Logger.info("Generate DocumentInfo");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#DocumentInfo"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/VitalRecordsDocumentInfo.owl"), "RDF/XML-ABBREV", null);
	}
	
	public static void generateBirthCertificateOrder() throws Exception {
		Logger.info("Generate BirthCertificateOrder");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#BirthCertificateOrder"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/BirthCertificateOrder.owl"), "RDF/XML", null);
	}
	
	public static void generateVitalRecordsOfficeAcknowledgement() throws Exception {
		Logger.info("Generate Acknowledgement");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#Acknowledgement"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/VitalRecordsofficeAcknowledgement.owl"), "RDF/XML-ABBREV", null);
	}
	
	// org.qualipso.interop.semantics.procintegration.bpel.composer.test PublicServicePayment
	public static void generatePublicServicePayment() throws Exception {
		Logger.info("Generate PublicServicePayment");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Payment"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/Payment.owl"), "RDF/XML", null);
	}
	
	public static void generateReceipt() throws Exception {
		Logger.info("Generate Receipt");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#Receipt"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/Receipt.owl"), "RDF/XML-ABBREV", null);
	}

	// org.qualipso.interop.semantics.procintegration.bpel.composer.test StasticalOffice
	public static void generateOfficialCitizenDocumentType() throws Exception {
		Logger.info("Generate OfficialCitizenDocumentType");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#OfficialCitizenDocumentType"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/OfficialCitizenDocumentType.owl"), "RDF/XML", null);
	}
	
	public static void generateStatisticalOfficeConfirm() throws Exception {
		Logger.info("Generate StatisticalOfficeConfirm");
		Parameter parameter = new Parameter();
		OWLKnowledgeBase kb = OWLFactory.createKB();
		kb
				.read("http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl");
		parameter
				.createProxyParameterValue(
						null,
						null,
						new URI(
								"http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#Confirm"),
						kb);
		((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
				"org.qualipso.interop.semantics.procintegration.bpel.composer.test/StatisticalOfficeConfirm.owl"), "RDF/XML-ABBREV", null);
	}
	

	public void test() throws Exception {
		List rules = Rule.rulesFromURL("file:org.qualipso.interop.semantics.procintegration.bpel.composer.test.rule");

		// Jena rule model
		OntModelSpec customInfSpec = new OntModelSpec(OntModelSpec.OWL_MEM);
		GenericRuleReasoner reasoner = new GenericRuleReasoner(rules,
				customInfSpec.getReasonerFactory());
		customInfSpec.setReasoner(reasoner);
		OntModel ruleModel = ModelFactory.createOntologyModel(customInfSpec,
				null);

		ruleModel.read("file:org.qualipso.interop.semantics.procintegration.bpel.composer.test.owl");

		// Pellet OWL model
		OntModel pelletModel = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		// copy knowledge base to Pellet OWL model
		pelletModel.add(ruleModel);

		// write model including all inferred statements
		pelletModel.writeAll(new FileOutputStream("testOutput.owl"), "RDF/XML",
				null);
	}

	private static void removeTBox() throws Exception{
		 OWLKnowledgeBase kb = OWLFactory.createKB();
		 kb.read("file:ClassificationTest.owl");
		 ((OntModel) kb.getImplementation()).remove((OntModel) kb.getImplementation());
		
			((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
			"PostClassificationTest.owl"), "RDF/XML", null);
	}

	static String[] extractOntologies(String semanticBridge) {
		FileReader in = null;
		String source = null;
		String target = null;
		try {
			in = new FileReader(semanticBridge);
			StringBuffer sb = new StringBuffer();
			int c = in.read();
			while ('@' != c) {
				sb.append((char) c);
				c = in.read();
			}
			String intro = sb.toString();
			source = intro.subSequence(intro.indexOf("<") + 1,
					intro.indexOf(">")).toString();
			target = intro.subSequence(intro.lastIndexOf("<") + 1,
					intro.lastIndexOf(">")).toString();

		}

		catch (IOException e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
		}
		String[] result = { source, target };
		return result;
	}

}

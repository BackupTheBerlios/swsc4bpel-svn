package org.qualipso.interop.semantics.procintegration.bpel.composer.deployment;


import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLOntology;
import org.mindswap.owls.grounding.Grounding;
import org.mindswap.owls.grounding.MessageMap;
import org.mindswap.owls.grounding.WSDLAtomicGrounding;
import org.mindswap.owls.grounding.WSDLOperationRef;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.profile.Profile;
import org.mindswap.owls.service.Service;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessInput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessOutput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;


/**
 * Generates an OWL-S description for the designed process.
 * 
 */
public class OWLSWriter {

	/**
	 * Generate OWL-S description for designed process.
	 * 
	 * @param wsdlGroundingURL the URL for the grounding
	 * 
	 * @return OWL-S file
	 */
	public File generateOWLS(String wsdlGroundingURL) {
		ProcessModel process = Controller.getInstance().getProcess();

		MessagePanel.appendOut("    Generating OWL-S ");
		MessagePanel.appendOutBlue(process.getProcessName() + ".owl");
		MessagePanel.appendOut(" ... ");

		// write OWL-S service describtion to file
		File owls = new File("temp/" + process.getProcessName() + "/" 
		        + process.getProcessName() + ".owl");

		try {
			// create Mindswap OWL-S API OWL-S writer
			// OWLWriter owlsWriter = OWLFactory.createWriter();
			OWLOntology serviceOntology = OWLFactory.createOntology();
			// OWLWriter owlsWriter = serviceOntology.getWriter();

			// profile
			Profile profile = serviceOntology.createProfile();
			profile.setLabel(process.getProcessName() + "Profile");
			profile.setServiceName(process.getProcessName());
			profile.setTextDescription(process.getProcessName());

			// process
			AtomicProcess atomicProcess = serviceOntology.createAtomicProcess();
			atomicProcess.setLabel(process.getProcessName() + "Process");

			// add inputs
			Iterator inputIter = process.getProcessInputs().iterator();
			Vector owlsInputParameters = new Vector();
			while (inputIter.hasNext()) {
				ProcessInput processInput = (ProcessInput) inputIter.next();
				Input input = serviceOntology.createInput();
				input.setLabel(processInput.getName());
				//TODO check if createClass or getClass therefore import
				input.setParamType(serviceOntology.createClass(processInput
						.getParameterTypeURI()));
				profile.addInput(input);
				atomicProcess.addInput(input);
				owlsInputParameters.add(input);
			}

			// add outputs
			Iterator outputIter = process.getProcessOutputs().iterator();
			Vector owlsOutputParameters = new Vector();
			while (outputIter.hasNext()) {
				ProcessOutput processOutput = (ProcessOutput) outputIter.next();
				Output output = serviceOntology.createOutput();
				output.setLabel(processOutput.getName());
				output.setParamType(serviceOntology.createClass(processOutput
						.getParameterTypeURI()));
				atomicProcess.addOutput(output);
				profile.addOutput(output);
				owlsOutputParameters.add(output);
			}

			// grounding
			// WSDLAtomicGrounding
			WSDLAtomicGrounding wsdlAtomicGrounding = serviceOntology
					.createWSDLAtomicGrounding();
			wsdlAtomicGrounding.setProcess(atomicProcess);
			wsdlAtomicGrounding.setWSDL(new URI(wsdlGroundingURL));

			// WSDLOperation
			WSDLOperationRef wsdlOperationRef = serviceOntology
					.createWSDLOperationRef();
			wsdlOperationRef.setPortType(new URI(wsdlGroundingURL + "#"
					+ process.getProcessName()));
			wsdlOperationRef.setOperation(new URI(wsdlGroundingURL + "#"
					+ first2LowerCase(process.getProcessName())));
			wsdlAtomicGrounding.setOperationRef(wsdlOperationRef);

			// WSDLInputMessage
			wsdlAtomicGrounding.setInputMessage(new URI(wsdlGroundingURL + "#"
					+ first2LowerCase(process.getProcessName()) + "Request"));
			
			// add mapping for each input
			String inputTransformation = //"<![CDATA[\n"
					 "<xsl:stylesheet version=\"1.0\"\n"
					+ " xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"\n"
					+ " xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
					+ " xmlns=\"http://localhost:8080/PublicServices/services/"
					+ process.getProcessName() + "\">\n"
					+ "<xsl:template match=\"/\">\n"
					+ "<xsl:copy-of select=\"/\"/>\n" + "</xsl:template>\n"
					+ "</xsl:stylesheet>\n"; //+ "]]>";
			Iterator owlsInputIter = owlsInputParameters.iterator();
			while (owlsInputIter.hasNext()) {
				Input owlsInput = (Input) owlsInputIter.next();
				MessageMap wsdlInputMessageMap = serviceOntology
				.createWSDLInputMessageMap();
				wsdlInputMessageMap.setOWLSParameter(owlsInput);
				wsdlInputMessageMap.setGroundingParameter(wsdlGroundingURL
						+ "#" + first2LowerCase(owlsInput.getLabel()));
				wsdlInputMessageMap.setTransformation(inputTransformation);
				wsdlAtomicGrounding.addInputMap(wsdlInputMessageMap);
			}

			// WSDLOutputMessage
			wsdlAtomicGrounding.setOutputMessage(new URI(wsdlGroundingURL + "#"
					+ first2LowerCase(process.getProcessName()) + "Response"));
			
			// add mapping for each output
			String outputTransformation = //"<![CDATA[\n"
					"<xsl:stylesheet version=\"1.0\"\n"
					+ "xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"\n"
					+ "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
					+ " xmlns=\"http://localhost:8080/PublicServices/services/"
					+ process.getProcessName()
					+ "\">\n"
					+ "<xsl:template match=\"/\">\n"
					+ "<xsl:value-of select=\"/\" disable-output-escaping=\"yes\"/>\n"
					+ "</xsl:template>\n" + "</xsl:stylesheet>\n"; //+ "]]>";
			Iterator owlsOutputIter = owlsOutputParameters.iterator();
			while (owlsOutputIter.hasNext()) {
				Output owlsOutput = (Output) owlsOutputIter.next();
				MessageMap wsdlOutputMessageMap = serviceOntology
				.createWSDLOutputMessageMap();
				wsdlOutputMessageMap.setOWLSParameter(owlsOutput);
				wsdlOutputMessageMap.setGroundingParameter(wsdlGroundingURL
						+ "#" + first2LowerCase(owlsOutput.getLabel()));
				wsdlOutputMessageMap.setTransformation(outputTransformation);
				wsdlAtomicGrounding.addOutputMap(wsdlOutputMessageMap);
			}
			
			// finalize grounding
			Grounding grounding = serviceOntology.createGrounding();
			grounding.setLabel(process.getProcessName() + "Grounding");
			grounding.addGrounding(wsdlAtomicGrounding);

			// service
			Service service = serviceOntology.createService();
			service.setName(process.getProcessName() + "Service");
			service.setProfile(profile);
			service.setProcess(atomicProcess);
			service.setGrounding(grounding);
			
			// add ontology imports from services
			//TODO add ontology imports from services
			Iterator serviceIter = process.getServices().iterator();
			//Set allImports = new HashSet();
			while (serviceIter.hasNext()) {
				ServiceModel serviceModel = (ServiceModel) serviceIter.next();
				Set imports = serviceModel.getOwlsService().getOntology()
						.getImports(true);
				Iterator importsIter = imports.iterator();
				while (importsIter.hasNext()) {
					OWLOntology serviceImport = (OWLOntology) importsIter
							.next();
					//allImports.add(serviceImport);
					serviceOntology.addImport(serviceImport); // no effect on output file!
				}
			}
			//serviceOntology.setImports(allImports);
			//MessagePanel.appendErrorln("Size: " + allImports.size());
			
			// write file
			FileWriter fileWriter = new FileWriter(owls);
			serviceOntology.write(fileWriter);
			fileWriter.flush();
			fileWriter.close();

		} catch (Exception e) {
			MessagePanel
					.appendErrorln("Error writing OWL-S service description!");
			Logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}

		MessagePanel.appendSuccessln("done.");

		return owls;
	}

	/**
	 * Some WSDL identifiers are generated with lower case start character.
	 * 
	 * @param string the string
	 * 
	 * @return  string with first character changed to lower case
	 */
	private String first2LowerCase(String string) {
		return string.replaceFirst(string.substring(0, 1), string.substring(0, 1)
				.toLowerCase());
	}
}

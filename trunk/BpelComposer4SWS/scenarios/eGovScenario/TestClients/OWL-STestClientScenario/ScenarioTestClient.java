import impl.owls.grounding.WSDLAtomicGroundingImpl;

import java.net.URI;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.InputList;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.process.OutputList;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;
import org.mindswap.utils.Utils;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;



public class ScenarioTestClient {

	public static void main(String[] args) throws Exception {
		
		long t1 = System.currentTimeMillis();
		
		System.out.println("Start ScenarioTestClient");
		
		// init
		ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
		OWLKnowledgeBase kb = OWLFactory.createKB();

		// read the service description
		Service service = kb.readService("http://localhost:8080/PublicServices/FinalScenario/FinalScenario3.owl");
		
		Process process = service.getProcess();

		// get inputs
		kb.read("file:test/ScenarioTestClientInput.owl");
	
		// ResidentSearchProfile
		OWLClass ResidentSearchProfile = kb
				.getClass(new URI(
						"http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile"));
		OWLIndividual aResidentSearchProfile = kb.getInstances(
				ResidentSearchProfile).individualAt(0);
		
		// VitalRecordsDocumentType
		OWLClass VitalRecordsDocumentType = kb
				.getClass(new URI(
						"http://localhost:8080/PublicServices/VitalRecordsOffice/VitalRecordsOfficeOntology.owl#VitalRecordsDocumentType"));
		OWLIndividual aVitalRecordsDocumentType = kb.getInstances(
				VitalRecordsDocumentType).individualAt(0);
		
		// AccountNumber
		OWLClass AccountNumber = kb
				.getClass(new URI(
						"http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#AccountNumber"));
		OWLIndividual aAccountNumber = kb.getInstances(
				AccountNumber).individualAt(0);
		
		// BankID
		OWLClass BankID = kb
				.getClass(new URI(
						"http://localhost:8080/PublicServices/PublicServicePayment/PublicServicePaymentOntology.owl#BankID"));
		OWLIndividual aBankID = kb.getInstances(
				BankID).individualAt(0);

		// set input
		ValueMap values = new ValueMap();
		InputList inputs = process.getInputs();
		for(int i= 0;i<inputs.size();i++){
			Input input = inputs.inputAt(i);
			if(input.getLabel().equals("ResidentSearchProfile")){
				values.setValue(input,
						aResidentSearchProfile);
			}else if(input.getLabel().equals("VitalRecordsDocumentType")){
				values.setValue(input,
						aVitalRecordsDocumentType);
			}else if(input.getLabel().equals("AccountNumber")){
				values.setValue(input,
						aAccountNumber);
			}else if(input.getLabel().equals("BankID")){
				values.setValue(input,
						aBankID);
			}
		}
		

		// makes OWL-S grounding XSL transformations visible
		WSDLAtomicGroundingImpl.DEBUG = true;
		

		// invoke CompositionTest
		values = exec.execute(process, values);
		// get output param
		OWLIndividual out1 = null;
		OWLIndividual out2 = null;
		OutputList outputs = process.getOutputs();
		for(int i= 0;i<outputs.size();i++){
			Output output = outputs.outputAt(i);
			if(output.getLabel().equals("Acknowledgement")){
				out1 = values.getIndividualValue(output);
			}else if(output.getLabel().equals("Receipt")){
				out2 = values.getIndividualValue(output);
			}
		}
		
//	    OWLIndividual out1 = values.getIndividualValue(process.getOutput());
//		OWLIndividual out2 = values.getIndividualValue(process.getOutput("ResidentInfo"));

		// display the results
		Logger.info("Executed service " + service.getName());
		Logger.info("Grounding WSDL: "
				+ ((AtomicProcess) process).getGrounding().getDescriptionURL());
		Logger.info("Inputs:");
		Logger.info(Utils.formatRDF(aResidentSearchProfile.toRDF()));
		Logger.info(Utils.formatRDF(aVitalRecordsDocumentType.toRDF()));
		Logger.info(Utils.formatRDF(aAccountNumber.toRDF()));
		Logger.info(Utils.formatRDF(aBankID.toRDF()));
		Logger.info("Outputs:");
		Logger.info(Utils.formatRDF(out1.toRDF()));
		Logger.info("Output2 = ");
		Logger.info(Utils.formatRDF(out2.toRDF()));
		
		long t2 = System.currentTimeMillis();
		Logger.info("OverallTimeConsumption: " + (t2 - t1));

	}
}

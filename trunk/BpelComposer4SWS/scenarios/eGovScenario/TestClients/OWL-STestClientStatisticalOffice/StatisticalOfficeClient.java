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

public class StatisticalOfficeClient {

	public static void main(String[] args) throws Exception {
		
		long t1 = System.currentTimeMillis();
		
		System.out.println("Start StatisticalOfficeClient");
		
		// init
		ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
		OWLKnowledgeBase kb = OWLFactory.createKB();

		// read the service description
		Service service = kb.readService("http://localhost:8080/PublicServices/StatisticalOffice/AddToCitizenDocumentsStatistics.owl");
	
		Process process = service.getProcess();

		// get input
		kb.read("file:test/StatisticalOfficeClientInput.owl");
		OWLClass OfficialCitizenDocumentType = kb
				.createClass(new URI(
						"http://localhost:8080/PublicServices/StatisticalOffice/StatisticalOfficeOntology.owl#OfficialCitizenDocumentType"));
		OWLIndividual aOfficialCitizenDocumentType = kb.getInstances(
				OfficialCitizenDocumentType).individualAt(0);

		// set input
		ValueMap values = new ValueMap();
		InputList inputs = process.getInputs();
		for(int i= 0;i<inputs.size();i++){
			Input input = inputs.inputAt(i);
			if(input.getLabel().equals("OfficialCitizenDocumentType")){
				values.setValue(input,
						aOfficialCitizenDocumentType);
			}
		}
		

		// makes OWL-S grounding XSL transformations visible
		WSDLAtomicGroundingImpl.DEBUG = true;
		//WSDLOperation.DEBUG = true;

		// invoke CompositionTest
		values = exec.execute(process, values);
		
		// get output param
		OWLIndividual out1 = null;
		OutputList outputs = process.getOutputs();
		for(int i= 0;i<outputs.size();i++){
			Output output = outputs.outputAt(i);
			if(output.getLabel().equals("Confirm")){
				out1 = values.getIndividualValue(output);
			}
		}
		
		// display the results
		System.out.println("Executed service " + service);
		System.out.println("Grounding WSDL: "
				+ ((AtomicProcess) process).getGrounding().getDescriptionURL());
		System.out.println("Input  = ");
		System.out.println(Utils.formatRDF(aOfficialCitizenDocumentType.toRDF()));
		System.out.println("Output1 = ");
		System.out.println(Utils.formatRDF(out1.toRDF()));

		
		long t2 = System.currentTimeMillis();
		System.out.println("OverallTimeConsumption: " + (t2 - t1));

	}
}

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
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;
import org.mindswap.utils.Utils;

public class CompositionTestClient {

	public static void main(String[] args) throws Exception {
		
		long t1 = System.currentTimeMillis();
		
		System.out.println("Start CompositionTestClient");
		
		// init
		ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
		OWLKnowledgeBase kb = OWLFactory.createKB();

		// read the service description
		Service service = kb.readService("http://localhost:8080/PublicServices/Composition1/Composition1.owl");
		
		Process process = service.getProcess();

		// get input
		kb.read("file:test/CompositionTestClientInput.owl");
		OWLClass ResidentSearchProfile = kb
				.createClass(new URI(
						"http://localhost:8080/PublicServices/ResidentRegistry/ResidentRegistryOntology.owl#ResidentSearchProfile"));
		OWLIndividual aResidentSearchProfile = kb.getInstances(
				ResidentSearchProfile).individualAt(0);

		// set input
		ValueMap values = new ValueMap();
		InputList inputs = process.getInputs();
		for(int i= 0;i<inputs.size();i++){
			Input input = inputs.inputAt(i);
			if(input.getLabel().equals("ResidentSearchProfile")){
				values.setValue(input,
						aResidentSearchProfile);
			}
		}
		

		// makes OWL-S grounding XSL transformations visible
		WSDLAtomicGroundingImpl.DEBUG = true;
		

		// invoke CompositionTest
		values = exec.execute(process, values);
		
		// get output param
		OWLIndividual out1 = null;
//		OutputList outputs = process.getOutputs();
//		for(int i= 0;i<outputs.size();i++){
//			Output output = outputs.outputAt(i);
//			if(output.getLabel().equals("Receipt")){
//				out1 = values.getIndividualValue(output);
//			}else if(output.getLabel().equals("ResidentInfo")){
//				out2 = values.getIndividualValue(output);
//			}
//		}
		
	out1 = values.getIndividualValue(process.getOutput());

		// display the results
		System.out.println("Executed service " + service.getName());
		System.out.println("Grounding WSDL: "
				+ ((AtomicProcess) process).getGrounding().getDescriptionURL());
		System.out.println("Input  = ");
		System.out.println(Utils.formatRDF(aResidentSearchProfile.toRDF()));
		System.out.println("Output1 = ");
		System.out.println(Utils.formatRDF(out1.toRDF()));
//		System.out.println("Output2 = ");
//		System.out.println(Utils.formatRDF(out2.toRDF()));
		
		long t2 = System.currentTimeMillis();
		System.out.println("OverallTimeConsumption: " + (t2 - t1));

	}
}

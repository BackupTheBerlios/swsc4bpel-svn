import impl.owls.grounding.WSDLAtomicGroundingImpl;

import java.net.URI;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLValue;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.InputList;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.service.Service;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.query.ValueMap;
import org.mindswap.utils.Utils;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class German2EnglishDictTestClient {

	public static void main(String[] args) throws Exception {

		long t1 = System.currentTimeMillis();

		System.out.println("Start German2EnglishDictClient");
		
		// init
		ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
		OWLKnowledgeBase kb = OWLFactory.createKB();

		// read the service description
		Service service = kb
		.readService("http://localhost:8080/PublicServices/German2EnglishDict/German2EnglishDict.owl");
		kb.read("http://www.daml.org/2003/09/factbook/languages.owl");
		kb.read("http://www.mindswap.org/2004/owl-s/1.1/BabelFishTranslator.owl");
		OWLClass supportedLang = kb.getClass(new URI("http://www.mindswap.org/2004/owl-s/1.1/BabelFishTranslator.owl#SupportedLanguage"));
		

		
		// inputs
		OWLDataValue inputString = kb.createDataValue("Zeit");
		OWLIndividual german = kb.getIndividual(new URI("http://www.daml.org/2003/09/factbook/languages#German"));
		OWLIndividual english = kb.getIndividual(new URI("http://www.daml.org/2003/09/factbook/languages#English"));
		
		
		// classify
		OntModel owlDLModel = ModelFactory
		.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		owlDLModel.add((OntModel)kb.getImplementation());
		((OntModel)kb.getImplementation()).add(owlDLModel);
		english.addType(supportedLang);
		german.addType(supportedLang);
		
		
		Process process = service.getProcess();


		// set input
		ValueMap values = new ValueMap();
		InputList inputs = process.getInputs();
		for(int i= 0;i<inputs.size();i++){
			Input input = inputs.inputAt(i);
			if(input.getLabel().equals("InputLanguage")){
				values.setValue(input,german);
			}else if(input.getLabel().equals("OutputLanguage")){
				values.setValue(input,english);
			}else if(input.getLabel().equals("InputString")){
				values.setValue(input,
						inputString);
			}
		}

		// makes OWL-S grounding XSL transformations visible
		WSDLAtomicGroundingImpl.DEBUG = true;

		// invoke CompositionTest
		values = exec.execute(process, values);
		// get output param
		OWLValue out1 = null;

		Output output = process.getOutput();

		//out1 = values.getIndividualValue(output);
		out1 = values.getValue(output);

		// display the results
		Logger.info("Executed service " + service.getName());
		Logger.info("Grounding WSDL: "
				+ ((AtomicProcess) process).getGrounding().getDescriptionURL());
		Logger.info("Inputs:");
		Logger.info(inputString.getValue().toString());
		Logger.info(Utils.formatRDF(german.toRDF()));
		Logger.info(Utils.formatRDF(english.toRDF()));
		Logger.info("Output:");
		Logger.info(((OWLDataValue)out1).getValue().toString());

		long t2 = System.currentTimeMillis();
		Logger.info("OverallTimeConsumption: " + (t2 - t1));

	}
}

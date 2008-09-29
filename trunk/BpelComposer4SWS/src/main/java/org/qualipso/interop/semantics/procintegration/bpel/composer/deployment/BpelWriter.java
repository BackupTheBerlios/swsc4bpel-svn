package org.qualipso.interop.semantics.procintegration.bpel.composer.deployment;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.XMLSerializer;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Dataflow;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Parameter;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessInput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessOutput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.SemanticBridge;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.SemanticUtils;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.hp.hpl.jena.util.FileUtils;

import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.model.RDFIndividual;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;

/**
 * Used by specific deployer to generate the BPEL execution plan for the
 * designed process based on the service sequence and process expert specified
 * assignments.
 */
public class BpelWriter {

    private static final String BPEL_PARTNER_LINK = "bpel:partnerLink";
    private static final String BPEL_PARTNER_LINKS = "bpel:partnerLinks";
    private static final String BPEL_IMPORT = "bpel:import";
    private static final String NAMESPACE_PREFIX_BPEL = "bpel";
    private static final String NAMESPACE_PREFIX_XSD = "xsd";
    private static final String NAMESPACE_PREFIX_EXTENSION_WSDL = "extWsdl";
    private static final String NAMESPACE_PREFIX_SEMANTIC_BPEL = "semBpel";

    private static final String ATTR_NAME = "name";
    private static final String ATTR_TYPE = "type";
    private static final String ATTR_MESSAGE_TYPE = "messageType";
    private static final String ATTR_VARIABLE = "variable";
    private static final String ATTR_PORT_TYPE = "portType";
    private static final String ATTR_PARTNER_LINK = "partnerLink";
    private static final String ATTR_OPERATION = "operation";
    private static final String ATTR_CREATE_INSTANCE = "createInstance";
    private static final String ATTR_PART = "part";
    private static final String ATTR_PARTNER_LINK_TYPE = "partnerLinkType";
    private static final String ATTR_MY_ROLE = "myRole";
    private static final String ATTR_MUST_UNDERSTAND = "mustUnderstand";
    private static final String ATTR_NAMESPACE = "namespace";
    private static final String ATTR_IMPORT_TYPE = "importType";
    private static final String ATTR_LOCATION = "location";

    private static final String BPEL_PROCESS = NAMESPACE_PREFIX_BPEL + ":process";
    private static final String BPEL_EXTENSION = NAMESPACE_PREFIX_BPEL + ":extension";
    private static final String BPEL_EXTENSIONS = NAMESPACE_PREFIX_BPEL + ":extensions";
    private static final String BPEL_ASSIGN = NAMESPACE_PREFIX_BPEL + ":assign";
    private static final String BPEL_VARIABLE = NAMESPACE_PREFIX_BPEL + ":variable";
    private static final String BPEL_VARIABLES = NAMESPACE_PREFIX_BPEL + ":variables";
    private static final String BPEL_REPLY = NAMESPACE_PREFIX_BPEL + ":reply";
    private static final String BPEL_RECEIVE = NAMESPACE_PREFIX_BPEL + ":receive";
    private static final String BPEL_SEQUENCE = NAMESPACE_PREFIX_BPEL + ":sequence";
    private static final String BPEL_LITERAL = "bpel:literal";
    private static final String BPEL_TO = "bpel:to";
    private static final String BPEL_FROM = "bpel:from";
    private static final String BPEL_COPY = "bpel:copy";

    private static final String LABEL_PREFIX_4_SWS_CALL = "ExecuteSemanticWebService_";
    private static final String LABEL_PREFIX_4_SDF_CALL = "ExecuteSemanticDataflow_4_";
    private static final String LABEL_PREFIX_4_SB_CALL = "ExecuteSemanticBridges_4_";
    private static final String LABEL_RECEIVE_PROCESS_INPUTS = "ReceiveProcessInputs";
    private static final String LABEL_REPLY_WITH_PROCESS_OUTPUTS = "ReplyWithProcessOutputs";
    private static final String LABEL_HANDLE_PROCESS_OUTPUTS =
            "ExtractProcessOutputsFromContainerVariable";
    private static final String LABEL_HANDLE_PROCESS_INPUTS =
            "HookProcessInputsIntoContainerVariable";
    private static final String LABEL_HANDLE_SWS = "HandleSWS_";

    private static final String PREFIX_4_DATAFLOW_VARIABLE = "dataflow4";

    private static final String POSTFIX_4_OUTPUT_CONTAINER = "Output";
    private static final String POSTFIX_4_INPUT_CONTAINER = "Input";
    private static final String POSTFIX_4_PROCESS_OUTPUT_VARIABLE = "ReplyOutput";
    private static final String POSTFIX_4_PROCESS_INPUT_VARIABLE = "ReceiveInput";
    private static final String POSTFIX_4_PORT_TYPE_NAMES = "_PortType";
    private static final String POSTFIX_4_PARTNER_LINK_NAMES = "_PartnerLink";
    private static final String POSTFIX_4_PROCESS_OPERATION_NAMES = "_processOperation";

    private static final String REGISTERED_NAME_4_HANDLE_SEMANTIC_PROCESS_INPUTS_FUNCTION =
            NAMESPACE_PREFIX_SEMANTIC_BPEL + ":handleSemanticProcessInputs";
    private static final String REGISTERED_NAME_4_HANDLE_SEMANTIC_PROCESS_OUTPUTS_FUNCTION =
            NAMESPACE_PREFIX_SEMANTIC_BPEL + ":handleSemanticProcessOutputs";
    private static final String REGISTERED_NAME_4_SEMANTIC_DATAFLOW_FUNCTION =
            NAMESPACE_PREFIX_SEMANTIC_BPEL + ":executeSemanticDataflow";
    private static final String REGISTERED_NAME_4_SEMANTIC_WEB_SERVICE_FUNCTION =
            NAMESPACE_PREFIX_SEMANTIC_BPEL + ":executeSemanticWebService";
    private static final String REGISTERED_NAME_4_SEMANTIC_BRIDGE_FUNCTION =
            NAMESPACE_PREFIX_SEMANTIC_BPEL + ":executeSemanticBridge";

    private static final String VALUE_XSD_ANY_TYPE = NAMESPACE_PREFIX_XSD + ":anyType";
    private static final String VALUE_MY_ROLE = "serviceProvider";

    private static final String ATTR_QUERYLANGUAGE = "expressionLanguage";
    private static final String XQUERY_VERSION_1_0 =
            "urn:active-endpoints:expression-language:xquery1.0";

    /** Counter to ensure unique prefixes used in container variables. */
    private static int containerCount = 0;

    /**
     * Generates and writes BPEL code according to the defined process into a
     * file. Before calling generateExecutionPlan generateDataflows needs to be
     * called.
     * 
     * @return File defining the complete BPEL process
     * 
     * @throws Exception the exception that occurred
     */
    public static File generateBpelProcess() throws Exception {
        ProcessModel process = Controller.getInstance().getProcess();

        MessagePanel.appendOut("    Generating execution plan ");
        MessagePanel.appendOutBlue(process.getProcessName() + ".bpel");
        MessagePanel.appendOut("... ");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // generate the constant BPEL header
        Element bpelProcess = generateBpelHeader(process, document);

        // generate BPEL code to declare all necessary variables
        generateBpelVariableDeclarations(process, bpelProcess, document);

        // declare bpel:sequence element
        Element sequence = document.createElement(BPEL_SEQUENCE);
        bpelProcess.appendChild(sequence);

        // define receive element for the process
        Element receive = document.createElement(BPEL_RECEIVE);
        receive.setAttribute(ATTR_CREATE_INSTANCE, "yes");
        receive.setAttribute(ATTR_NAME, LABEL_RECEIVE_PROCESS_INPUTS);
        receive.setAttribute(ATTR_OPERATION, process.getProcessName()
                + POSTFIX_4_PROCESS_OPERATION_NAMES);
        receive.setAttribute(ATTR_PARTNER_LINK, process.getProcessName()
                + POSTFIX_4_PARTNER_LINK_NAMES);
        receive.setAttribute(ATTR_PORT_TYPE, NAMESPACE_PREFIX_EXTENSION_WSDL + ":"
                + process.getProcessName() + POSTFIX_4_PORT_TYPE_NAMES);
        receive.setAttribute(ATTR_VARIABLE, process.getProcessName()
                + POSTFIX_4_PROCESS_INPUT_VARIABLE);
        sequence.appendChild(receive);

        // handle process inputs
        generateAssign2HandleProcessInputs(sequence, document);

        // iterate services and
        // 1. init the input and output containers,
        // 2. define dataflow,
        // 3. invoke the service,
        // (4.) if necessary, execute semantic bridges
        Iterator servicesIter = process.getServices().iterator();
        while (servicesIter.hasNext()) {
            ServiceModel serviceModel = (ServiceModel) servicesIter.next();

            generateBpelSequence2HandleSWSCall(serviceModel, sequence, document);
        }

        // only if there are process outputs assigned, generate a reply
        if (process.getProcessOutputs().size() > 0) {
            // generate dataflow for process outputs
            generateAssign2CallSemanticDataflow(process, sequence, document);

            // prepare process outputs
            generateAssign2HandleProcessOutputs(sequence, document);

            // define reply element for the process
            Element reply = document.createElement(BPEL_REPLY);
            reply.setAttribute(ATTR_NAME, LABEL_REPLY_WITH_PROCESS_OUTPUTS);
            reply.setAttribute(ATTR_OPERATION, process.getProcessName()
                    + POSTFIX_4_PROCESS_OPERATION_NAMES);
            reply.setAttribute(ATTR_PARTNER_LINK, process.getProcessName()
                    + POSTFIX_4_PARTNER_LINK_NAMES);
            reply.setAttribute(ATTR_PORT_TYPE, NAMESPACE_PREFIX_EXTENSION_WSDL + ":"
                    + process.getProcessName() + POSTFIX_4_PORT_TYPE_NAMES);
            reply.setAttribute(ATTR_VARIABLE, process.getProcessName()
                    + POSTFIX_4_PROCESS_OUTPUT_VARIABLE);
            sequence.appendChild(reply);
        }

        // write file
        File executionPlan =
                new File("temp/" + process.getProcessName() + "/" + process.getProcessName()
                        + ".bpel");
        FileOutputStream out = new FileOutputStream(executionPlan);
        XMLSerializer serializer = new XMLSerializer(out, null);
        document.normalize();
        serializer.serialize(document);
        out.flush();
        out.close();

        MessagePanel.appendSuccessln("done.");

        return executionPlan;
    }

    /**
     * Generates BPEL code to declare all necessary variables.
     * 
     * There are two variables of type <code>messageType</code> for the
     * messages sent to the process and for the reply (if applicable).
     * 
     * The next two messages are container variables for these messages, which
     * are necessary because messages sent to the process do not container
     * anchor information which is essential for performing semantic data flows.
     * 
     * Afterwards, three variables for each service are generated. One input and
     * one output container variable, as well as one variable that stores the
     * rule-based data flow definition to initialise the input container
     * variable prior to the service execution.
     * 
     * @param process
     *            the composed process to be deployed
     * @param bpelProcess
     *            the parent BPEL element for variables
     * @param document
     *            the DOM document which stores all created elements
     * 
     */
    private static void generateBpelVariableDeclarations(ProcessModel process, Element bpelProcess,
            Document document) {
        // declare bpel:variables element
        Element variables = document.createElement(BPEL_VARIABLES);
        bpelProcess.appendChild(variables);

        Element processInput;
        Element processOutput;
        Element containerInput;
        Element containerOutput;
        Element containerInputDataflow;
        Node literalValue;

        // define process input variable
        processInput = document.createElement(BPEL_VARIABLE);
        processInput.setAttribute(ATTR_NAME, process.getProcessName()
                + POSTFIX_4_PROCESS_INPUT_VARIABLE);
        processInput.setAttribute(ATTR_MESSAGE_TYPE, NAMESPACE_PREFIX_EXTENSION_WSDL + ":"
                + "processInputMessage");
        variables.appendChild(processInput);

        // define typing container variable for process input
        containerInput = document.createElement(BPEL_VARIABLE);
        containerInput
                .setAttribute(ATTR_NAME, process.getProcessName() + POSTFIX_4_INPUT_CONTAINER);
        containerInput.setAttribute(ATTR_TYPE, VALUE_XSD_ANY_TYPE);
        variables.appendChild(containerInput);

        // only if there are process outputs assigned, generate a process output
        // variable
        if (process.getProcessOutputs().size() > 0) {
            // define process output variable
            processOutput = document.createElement(BPEL_VARIABLE);
            processOutput.setAttribute(ATTR_NAME, process.getProcessName()
                    + POSTFIX_4_PROCESS_OUTPUT_VARIABLE);
            processOutput.setAttribute(ATTR_MESSAGE_TYPE, NAMESPACE_PREFIX_EXTENSION_WSDL + ":"
                    + "processOutputMessage");
            variables.appendChild(processOutput);

            // define typing container variable for process output
            containerOutput = document.createElement(BPEL_VARIABLE);
            containerOutput.setAttribute(ATTR_NAME, process.getProcessName()
                    + POSTFIX_4_OUTPUT_CONTAINER);
            containerOutput.setAttribute(ATTR_TYPE, VALUE_XSD_ANY_TYPE);
            literalValue = generateContainer(process, document);
            initialiseVariableFromLiteral(literalValue, containerOutput, document);
            variables.appendChild(containerOutput);

            // define variable for the data flow definition for the process
            // output
            Element containerOutputDataflow = document.createElement(BPEL_VARIABLE);
            containerOutputDataflow.setAttribute(ATTR_NAME, PREFIX_4_DATAFLOW_VARIABLE
                    + process.getProcessName() + POSTFIX_4_OUTPUT_CONTAINER);
            containerOutputDataflow.setAttribute(ATTR_TYPE, VALUE_XSD_ANY_TYPE);
            literalValue = generateLiteralValue4Dataflow(process.getOutputDataflow(), document);
            initialiseVariableFromLiteral(literalValue, containerOutputDataflow, document);
            variables.appendChild(containerOutputDataflow);
        }

        // define input and output container elements for all services
        Iterator servicesIter = process.getServices().iterator();
        while (servicesIter.hasNext()) {
            ServiceModel serviceModel = (ServiceModel) servicesIter.next();
            String serviceName = serviceModel.getName();

            // define input container variable for current service
            containerInput = document.createElement(BPEL_VARIABLE);
            containerInput.setAttribute(ATTR_NAME, serviceName + POSTFIX_4_INPUT_CONTAINER);
            containerInput.setAttribute(ATTR_TYPE, VALUE_XSD_ANY_TYPE);
            literalValue = generateContainer(serviceModel, POSTFIX_4_INPUT_CONTAINER, document);
            initialiseVariableFromLiteral(literalValue, containerInput, document);
            variables.appendChild(containerInput);

            // define output container variable for current service
            containerOutput = document.createElement(BPEL_VARIABLE);
            containerOutput.setAttribute(ATTR_NAME, serviceName + POSTFIX_4_OUTPUT_CONTAINER);
            containerOutput.setAttribute(ATTR_TYPE, VALUE_XSD_ANY_TYPE);
            literalValue = generateContainer(serviceModel, POSTFIX_4_OUTPUT_CONTAINER, document);
            initialiseVariableFromLiteral(literalValue, containerOutput, document);
            variables.appendChild(containerOutput);

            // define variable for the data flow definition for the input of
            // current service
            containerInputDataflow = document.createElement(BPEL_VARIABLE);
            containerInputDataflow.setAttribute(ATTR_NAME, PREFIX_4_DATAFLOW_VARIABLE + serviceName
                    + POSTFIX_4_INPUT_CONTAINER);
            containerInputDataflow.setAttribute(ATTR_TYPE, VALUE_XSD_ANY_TYPE);
            literalValue = generateLiteralValue4Dataflow(serviceModel.getDataflow(), document);
            initialiseVariableFromLiteral(literalValue, containerInputDataflow, document);
            variables.appendChild(containerInputDataflow);
        }
    }

    /**
     * Generates the BPEL header. All BPEL process need to declare namespaces,
     * use the query-extension and define one partnerlink to the extension WSDL
     * file, which declares the operation. Inputs and outputs of this process
     * are defined according to the composition.
     * 
     * @param process
     *            the composed process to be deployed
     * @param document
     *            the DOM document which stores all created elements
     * 
     * @return the root-element which is a <code><bpel:process/></code> element
     * 
     */
    private static Element generateBpelHeader(ProcessModel process, Document document) {
        // define root element bpel:process
        Element bpelProcess =
                document.createElementNS(
                        "http://docs.oasis-open.org/wsbpel/2.0/process/executable", BPEL_PROCESS);
        bpelProcess.setAttribute("xmlns:" + NAMESPACE_PREFIX_BPEL,
                "http://docs.oasis-open.org/wsbpel/2.0/process/executable");
        bpelProcess.setAttribute("xmlns:ext",
                "http://www.activebpel.org/2006/09/bpel/extension/query_handling");
        bpelProcess.setAttribute("xmlns:" + NAMESPACE_PREFIX_SEMANTIC_BPEL,
                "http://org.qualipso.interop.semantic.bpel.extension");
        bpelProcess.setAttribute("xmlns:" + NAMESPACE_PREFIX_XSD,
                "http://www.w3.org/2001/XMLSchema");
        bpelProcess.setAttribute("ext:createTargetXPath", "yes");
        bpelProcess.setAttribute("ext:disableSelectionFailure", "yes");
        bpelProcess.setAttribute("suppressJoinFailure", "yes");
        bpelProcess.setAttribute("targetNamespace", "urn:" + process.getProcessName()
                + "_targetNamespaceURN");
        bpelProcess.setAttribute("xmlns:impl", "urn:" + process.getProcessName() + "_URN");
        bpelProcess.setAttribute("xmlns:" + NAMESPACE_PREFIX_EXTENSION_WSDL, "urn:"
                + process.getProcessName() + "_extWsdlURN");
        bpelProcess.setAttribute(ATTR_NAME, process.getProcessName());
        document.appendChild(bpelProcess);

        // define bpel:extensions element
        Element extensions = document.createElement(BPEL_EXTENSIONS);
        bpelProcess.appendChild(extensions);

        // define bpel:extension element for advance query handling
        Element extension = document.createElement(BPEL_EXTENSION);
        extension.setAttribute(ATTR_MUST_UNDERSTAND, "yes");
        extension.setAttribute(ATTR_NAMESPACE,
                "http://www.activebpel.org/2006/09/bpel/extension/query_handling");
        extensions.appendChild(extension);

        // define bpel:import element for WSDL
        Element bpelImport = document.createElement(BPEL_IMPORT);
        bpelImport.setAttribute(ATTR_IMPORT_TYPE, "http://schemas.xmlsoap.org/wsdl/");
        bpelImport.setAttribute(ATTR_LOCATION, process.getProcessName() + ".wsdl");
        bpelImport.setAttribute(ATTR_NAMESPACE, "urn:" + process.getProcessName() + "_extWsdlURN");
        bpelProcess.appendChild(bpelImport);

        // declare bpel:partnerLinks element
        Element partnerLinks = document.createElement(BPEL_PARTNER_LINKS);
        bpelProcess.appendChild(partnerLinks);

        // declare bpel:partnerLink defined in the WSDL file
        Element partnerLink = document.createElement(BPEL_PARTNER_LINK);
        partnerLink.setAttribute(ATTR_MY_ROLE, VALUE_MY_ROLE);
        partnerLink
                .setAttribute(ATTR_NAME, process.getProcessName() + POSTFIX_4_PARTNER_LINK_NAMES);
        partnerLink.setAttribute(ATTR_PARTNER_LINK_TYPE, NAMESPACE_PREFIX_EXTENSION_WSDL + ":"
                + process.getProcessName() + "_PartnerLinkType");
        partnerLinks.appendChild(partnerLink);
        return bpelProcess;
    }

    /**
     * Since process inputs are sent without a typing container (necessary for
     * the dataflow definitions) the inputs have to be hooked into an empty
     * container; this is performed by calling the custom function
     * <code>semBpel:handleSemanticProcessInputs</code> via a BPEL-Assign-Copy
     * statement which is generated here.
     * 
     * @param sequence
     *            the parent element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateAssign2HandleProcessInputs(Element sequence, Document document) {
        ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();

        // define bpel:assign element
        Element assign = document.createElement(BPEL_ASSIGN);
        assign.setAttribute(ATTR_NAME, LABEL_HANDLE_PROCESS_INPUTS);
        sequence.appendChild(assign);

        // generate the expression used in the
        // semBpel:executeSemanticDataflow-function
        // example:
        // semBpel:handleSemanticProcessInputs("project:/Composition1/Composition1.owls",
        // "GetResidentInfo" $input.GetResidentInfo, "Identifier",
        // $input.Identifier)
        // 1. argument: URL to the semantic description of the process
        // (2+3)* argument: arbitrary number of "name/value" pairs for process
        // inputs

        StringBuffer expression = new StringBuffer();
        expression.append(REGISTERED_NAME_4_HANDLE_SEMANTIC_PROCESS_INPUTS_FUNCTION);
        expression.append("(\"");
        expression.append(processName);
        expression.append("\"");

        // for each process input generate a name/value pair consisting of
        // 1. the name of the input parameter
        // 2. the name of the process variable used in the BPEL-Receive + 1.
        Iterator inputIter = process.getProcessInputs().iterator();
        while (inputIter.hasNext()) {
            ProcessInput processInput = (ProcessInput) inputIter.next();
            String processInputName = processInput.getName();
            expression.append(", \"");
            expression.append(processInputName);
            expression.append("\", \"");
            expression.append(processInput.getParameterTypeURI());
            expression.append("\", $");

            String processInputDot =
                    process.getProcessName() + POSTFIX_4_PROCESS_INPUT_VARIABLE + ".";
            expression.append(processInputDot + processInputName);
        }

        expression.append(")");

        // based on the assembled expression, generate a BPEL Copy statement
        generateCopyFromExpression(expression,
                process.getProcessName() + POSTFIX_4_INPUT_CONTAINER, assign, document);
    }

    /**
     * Since process outputs have to be sent without a typing container (which
     * was necessary for the dataflow) the inputs have to be extracted from the
     * type container; this is performed by calling the custom function
     * <code>semBpel:handleSemanticProcessOutputs</code> via a
     * BPEL-Assign-Copy statement.
     * 
     * @param sequence
     *            the parent element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateAssign2HandleProcessOutputs(Element sequence, Document document) {
        ProcessModel process = Controller.getInstance().getProcess();

        // define bpel:assign element
        Element assign = document.createElement(BPEL_ASSIGN);
        assign.setAttribute(ATTR_NAME, LABEL_HANDLE_PROCESS_OUTPUTS);
        sequence.appendChild(assign);

        // generate the expression used in the
        // semBpel:handleSemanticProcessOutputs-function
        // example: semBpel:handleSemanticProcessOutputs(Composition1Output,
        // "Composition1Process", "ResidentInfo", "Receipt")
        // 1. argument: process output container variable
        // 2. argument: process name
        // x. argument: name of part of process output message variable
        StringBuffer expression = new StringBuffer();
        expression.append(REGISTERED_NAME_4_HANDLE_SEMANTIC_PROCESS_OUTPUTS_FUNCTION);
        expression.append("($");
        expression.append(process.getProcessName() + POSTFIX_4_OUTPUT_CONTAINER);
        expression.append(", \"");
        expression.append(process.getProcessName());
        expression.append("\"");

        // for each part of process output message variable use the part-name as
        // parameter
        Iterator outputIter = process.getProcessOutputs().iterator();
        while (outputIter.hasNext()) {
            ProcessOutput processOutput = (ProcessOutput) outputIter.next();
            expression.append(", \"");
            expression.append(processOutput.getName());
            expression.append("\"");
        }

        expression.append(")");

        // based on the assembled expression, generate a BPEL Copy statement
        // to call the function that handles the process outputs
        generateCopyFromExpression(expression, process.getProcessName()
                + POSTFIX_4_OUTPUT_CONTAINER, assign, document);

        // afterwards, the parts of the reply message variable have to be set
        // one by one
        // this is done via consequent XQuery calls for each part
        outputIter = process.getProcessOutputs().iterator();
        while (outputIter.hasNext()) {
            ProcessOutput processOutput = (ProcessOutput) outputIter.next();

            Element copyAssign = document.createElement(BPEL_COPY);
            assign.appendChild(copyAssign);

            // define bpel:from element to declare the XQuery statement in
            Element fromCopy = document.createElement(BPEL_FROM);
            fromCopy.setAttribute(ATTR_QUERYLANGUAGE, XQUERY_VERSION_1_0);
            copyAssign.appendChild(fromCopy);

            // define the XQuery and add to bpel:from element
            Text xquery =
                    document.createTextNode("$" + process.getProcessName()
                            + POSTFIX_4_OUTPUT_CONTAINER + "//" + processOutput.getName());
            fromCopy.appendChild(xquery);

            // define bpel:to with process output message variable and current
            // part
            Element toCopy = document.createElement(BPEL_TO);
            toCopy.setAttribute(ATTR_VARIABLE, process.getProcessName()
                    + POSTFIX_4_PROCESS_OUTPUT_VARIABLE);
            toCopy.setAttribute(ATTR_PART, processOutput.getName());
            copyAssign.appendChild(toCopy);
        }

    }

    /**
     * Creates a BPEL assign activity that performs all necessary steps to
     * execute a Semantic Web Service.
     * 
     * 1. define dataflow, 2. invoke the service, (3.) if necessary, execute
     * semantic bridges
     * 
     * @param serviceModel
     *            the service that is handled here
     * @param sequence
     *            the parent BPEL element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateBpelSequence2HandleSWSCall(ServiceModel serviceModel,
            Element sequence, Document document) {
        // define bpel:assign element that specifies all actions
        // to be taken in order to execute a Semantic Web Service (SWS)
        Element swsSequence = document.createElement(BPEL_SEQUENCE);
        swsSequence.setAttribute(ATTR_NAME, LABEL_HANDLE_SWS + serviceModel.getName());
        sequence.appendChild(swsSequence);

        // initialise input and output containers and perform dataflow
        generateAssign2CallSemanticDataflow(serviceModel, swsSequence, document);

        // call the Semantic Web Service
        generateAssign2CallSemanticWebService(serviceModel, swsSequence, document);

        // if necessary, execute Semantic Bridges
        Vector semanticBridges = serviceModel.getSemanticBridges();
        if (!semanticBridges.isEmpty()) {
            generateAssign2CallSemanticBridges(serviceModel, swsSequence, document);
        }
    }

    /**
     * Creates an BPEL assign activity that performs a Semantic Data Flow call
     * for a given service. (necessary prior to execution of the given Semantic
     * Web Service).
     * 
     * @param serviceModel
     *            the service that is handled here
     * @param parent
     *            the parent BPEL element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateAssign2CallSemanticDataflow(ServiceModel serviceModel,
            Element parent, Document document) {
        String serviceName = serviceModel.getName();

        // define bpel:assign element that specifies a call
        // to a Semantic Data Flow (SDF)
        Element dataflowAssign = document.createElement(BPEL_ASSIGN);
        dataflowAssign.setAttribute(ATTR_NAME, LABEL_PREFIX_4_SDF_CALL + serviceName);
        parent.appendChild(dataflowAssign);

        // generate the expression used in the
        // semBpel:executeSemanticDataflow-function
        // example: semBpel:executeSemanticDataflow(
        // $dataflow4mediationProcessOutputContainer, $processOutput, "",
        // $LineItemConfirmationLifted)
        // 1. argument: variable containing dataflow definition
        // 2. argument: variable containing target container
        // 3. argument: NOT USED (can be used to replace a placeholder variable
        // in rules)
        // 4+x. argum.: source containers for the dataflow
        String dataflowVariable =
                PREFIX_4_DATAFLOW_VARIABLE + serviceName + POSTFIX_4_INPUT_CONTAINER;
        String targetContainer = serviceName + POSTFIX_4_INPUT_CONTAINER;
        Vector sources = serviceModel.getDataflow().getSources();
        Iterator sourceIt = sources.iterator();
        StringBuffer expression = new StringBuffer();
        expression.append(REGISTERED_NAME_4_SEMANTIC_DATAFLOW_FUNCTION);
        expression.append("($");
        expression.append(dataflowVariable);
        expression.append(", $");
        expression.append(targetContainer);
        expression.append(", \"\""); // empty string means, no placeholders
                                        // should be replaced in rules

        while (sourceIt.hasNext()) {
            expression.append(", $");
            expression.append((String) sourceIt.next());
        }

        expression.append(")");

        // based on the assembled expression, generate a BPEL Copy statement
        generateCopyFromExpression(expression, targetContainer, dataflowAssign, document);
    }

    /**
     * Creates an BPEL assign activity that calls a Semantic Data Flow for the
     * process outputs.
     * 
     * @param processModel
     *            the process that is handled here
     * @param parent
     *            the parent BPEL element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateAssign2CallSemanticDataflow(ProcessModel processModel,
            Element parent, Document document) {
        String processName = processModel.getProcessName();

        // define bpel:assign element that specifies a call
        // to a Semantic Data Flow (SDF)
        Element dataflowAssign = document.createElement(BPEL_ASSIGN);
        dataflowAssign.setAttribute(ATTR_NAME, LABEL_PREFIX_4_SDF_CALL + "ProcessOutputs");
        parent.appendChild(dataflowAssign);

        // generate the expression used in the
        // semBpel:executeSemanticDataflow-function
        // example: semBpel:executeSemanticDataflow(
        // $dataflow4mediationProcessOutputContainer, $processOutput, "",
        // $LineItemConfirmationLifted)
        // 1. argument: variable containing dataflow definition
        // 2. argument: variable containing target container
        // 3. argument: NOT USED (can be used to replace a placeholder variable
        // in rules)
        // 4+x. argum.: source containers for the dataflow
        String dataflowVariable =
                PREFIX_4_DATAFLOW_VARIABLE + processName + POSTFIX_4_OUTPUT_CONTAINER;
        String targetContainer = processName + POSTFIX_4_OUTPUT_CONTAINER;
        Vector sources = processModel.getOutputDataflow().getSources();
        Iterator sourceIt = sources.iterator();
        StringBuffer expression = new StringBuffer();
        expression.append(REGISTERED_NAME_4_SEMANTIC_DATAFLOW_FUNCTION);
        expression.append("($");
        expression.append(dataflowVariable);
        expression.append(", $");
        expression.append(targetContainer);
        expression.append(", \"\""); // empty string means, no placeholders
                                        // should be replaced in rules

        while (sourceIt.hasNext()) {
            expression.append(", $");
            expression.append((String) sourceIt.next());
        }

        expression.append(")");

        // based on the assembled expression, generate a BPEL Copy statement
        generateCopyFromExpression(expression, targetContainer, dataflowAssign, document);
    }

    /**
     * Generates a literal value from the generated data flow file content,
     * which can then be assigned to a data flow variable.
     * 
     * @param dataflow
     *            the dataflow definition to use
     * @param document
     *            the document where all elements are created in
     * 
     * @return the Literal-value containing the serialised data flow
     * 
     */
    private static Node generateLiteralValue4Dataflow(Dataflow dataflow, Document document) {
        Node literalValue = document.createElement("WrappingElement4SWRLIndividual");

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(dataflow.getDataflowFile());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // normalize text representation
        doc.getDocumentElement().normalize();
        literalValue.appendChild(document.importNode(doc.getDocumentElement(), true));
        return literalValue;
    }

    /**
     * Creates an BPEL assign activity that executes a Semantic Web Service).
     * 
     * @param serviceModel
     *            the service that should be executed
     * @param parent
     *            the parent BPEL element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateAssign2CallSemanticWebService(ServiceModel serviceModel,
            Element parent, Document document) {
        String serviceName = serviceModel.getName();

        // define bpel:assign element that specifies all actions
        // to be taken in order to execute a Semantic Web Service (SWS)
        Element assign = document.createElement(BPEL_ASSIGN);
        assign.setAttribute(ATTR_NAME, LABEL_PREFIX_4_SWS_CALL + serviceName);
        parent.appendChild(assign);

        // prepare inputs to construct the expression for the
        // semBpel:executeSemanticWebService-function
        String serviceURI = serviceModel.getOwlsService().getURI().toString();
        String sourceContainer = serviceName + POSTFIX_4_INPUT_CONTAINER;
        String targetContainer = serviceName + POSTFIX_4_OUTPUT_CONTAINER;

        // generate the expression used in the
        // semBpel:executeSemanticWebService-function
        // example:
        // semBpel:executeSemanticWebService("http://localhost:8080/SemanticWebServices
        //                                       /MoonServices/MoonCRMService.owl",
        // $moonCRMServiceInputContainer, $moonCRMServiceOutputContainer )
        // 1. argument: URL of the semantic service description
        // 2. argument: input container containing the service input
        // 3. argument: output container containing the service output
        StringBuffer expression = new StringBuffer();
        expression.append(REGISTERED_NAME_4_SEMANTIC_WEB_SERVICE_FUNCTION);
        expression.append("(\"");
        expression.append(serviceURI);
        expression.append("\", $");
        expression.append(sourceContainer);
        expression.append(", $");
        expression.append(targetContainer);
        expression.append(")");

        // based on the assembled expression, generate a BPEL Copy statement
        generateCopyFromExpression(expression, targetContainer, assign, document);
    }

    /**
     * Creates an BPEL assign activity that performs executes a Semantic
     * Bridge).
     * 
     * @param serviceModel
     *            the service where the outputs should be bridged
     * @param parent
     *            the parent BPEL element
     * @param document
     *            the DOM document where all elements are created in
     * 
     */
    private static void generateAssign2CallSemanticBridges(ServiceModel serviceModel,
            Element parent, Document document) {
        String serviceName = serviceModel.getName();

        // define bpel:assign element that specifies all actions
        // to be taken in order to execute a Semantic Web Service (SWS)
        Element dataflowAssign = document.createElement(BPEL_ASSIGN);
        dataflowAssign.setAttribute(ATTR_NAME, LABEL_PREFIX_4_SB_CALL + serviceName);
        parent.appendChild(dataflowAssign);

        // perform dataflow
        String sourceContainer = serviceName + POSTFIX_4_OUTPUT_CONTAINER;

        Iterator registeredSemanticBridgesIt = serviceModel.getSemanticBridges().iterator();
        while (registeredSemanticBridgesIt.hasNext()) {
            SemanticBridge semanticBridge = (SemanticBridge) registeredSemanticBridgesIt.next();

            // generate the expression used in the
            // semBpel:executeSemanticBridge-function
            // example:
            // semBpel:executeSemanticBridge("http://localhost:8080/ontologies/semantic-bridges/
            //                                SemanticBridge_RosettaNetOntology2MoonOntology.owl",
            // $processInputLifted )
            // 1. argument: URL of the semantic bridge definition
            // 2. argument: input container containing to be bridged
            StringBuffer expression = new StringBuffer();
            expression.append(REGISTERED_NAME_4_SEMANTIC_BRIDGE_FUNCTION);
            expression.append("(\"");
            expression.append(semanticBridge.getSemanticBridgeURL());
            expression.append("\", $");
            expression.append(sourceContainer);
            expression.append(")");

            // based on the assembled expression, generate a BPEL Copy statement
            generateCopyFromExpression(expression, sourceContainer, dataflowAssign, document);
        }
    }

    /**
     * Generates a expression-based BPEL Copy statement for an Assign activity.
     * 
     * @param expression
     *            the expression to use
     * @param targetVariable
     *            the target of the copy
     * @param parent
     *            the parent element, which should be an Assign
     * @param document
     *            the original document where all elements belong to
     * 
     */
    private static void generateCopyFromExpression(StringBuffer expression, String targetVariable,
            Element parent, Document document) {
        // generate the complete BPEL copy statement
        // define bpel:copy element to initiate the input container of SWS
        Element copyAssign = document.createElement(BPEL_COPY);
        parent.appendChild(copyAssign);

        // define bpel:from element to declare the literal in
        Element fromCopy = document.createElement(BPEL_FROM);
        copyAssign.appendChild(fromCopy);

        // define bpel:literal with initialised type container
        Text expressionNode = document.createTextNode(expression.toString());
        fromCopy.appendChild(expressionNode);

        // define bpel:to element for the variable to store the literal in
        Element toCopy = document.createElement(BPEL_TO);
        toCopy.setAttribute(ATTR_VARIABLE, targetVariable);
        copyAssign.appendChild(toCopy);
    }

    /**
     * Generate a BPEL Copy statement with a literal from value.
     * 
     * @param literalValue the literal value to assign
     * @param assign the parent of the copy element
     * @param document the base document to create elements in 
     * 
     */
    private static void initialiseVariableFromLiteral(Node literalValue, Element assign,
            Document document) {
        // define bpel:from element to declare the literal in
        Element fromCopy = document.createElement(BPEL_FROM);
        assign.appendChild(fromCopy);

        // define bpel:literal with initialised type container
        Element initialTypeContainer = document.createElement(BPEL_LITERAL);
        initialTypeContainer.appendChild(literalValue);
        fromCopy.appendChild(initialTypeContainer);
    }

    /**
     * Creates an ontology model with an initialised type container.
     * 
     * @param serviceModel
     *            the service for which the type container should be created
     * @param type
     *            Type of the variable (can be either "input" or "output")
     * @param document
     *            the base document to create elements in
     * 
     * @return a node containing an RDF/XML serialisation of the ontology
     */
    private static Node generateContainer(ServiceModel serviceModel, String type, 
                Document document) {
        containerCount += 1;

        // returned node
        Element wrappedRdfElement = null;

        try {
            // create container
            JenaOWLModel jenaOWLModel = ProtegeOWL.createJenaOWLModel();
            String ontologyName = "http://service." + serviceModel.getName() + "." + type;
            NamespaceManager nsm = jenaOWLModel.getNamespaceManager();
            nsm.setPrefix(ontologyName + "#", "container" + containerCount);
            nsm.setDefaultNamespace(ontologyName + "#");
            nsm.setPrefix("http://localhost:8080/ontologies/ServiceAnchor.owl#", "anchor");
            // jenaOWLModel.getDefaultOWLOntology().setName(ontologyName);

            // import all necessary ontologies
            ImportHelper importHelper = new ImportHelper(jenaOWLModel);
            for (Iterator iterator =
                    serviceModel.getDataflow().getRegisteredPrefixes().keySet().iterator(); iterator
                    .hasNext();) {
                String importUri = (String) iterator.next();
                // remove last character '#'
                if (importUri.endsWith("#")) {
                    importUri = importUri.substring(0, importUri.length() - 1);
                }
                importHelper.addImport(new URI(importUri));
            }
            importHelper.addImport(new URI("http://localhost:8080/ontologies/ServiceAnchor.owl"));
            importHelper.importOntologies(false);

            // add ProcessRoot with name of the service to container model
            RDFIndividual compositeProcess =
                    SemanticUtils.addProcessRoot(jenaOWLModel, serviceModel.getName() + "Process");

            Iterator aArgs;
            if (type.equals(POSTFIX_4_INPUT_CONTAINER)) {
                // operate on inputs --> construct input container
                aArgs = serviceModel.getInputs().iterator();
            } else {
                // operate on outputs --> construct output container
                aArgs = serviceModel.getOutputs().iterator();
            }

            // for each input(/output) parameter of the service
            // construct a input(/output) parameter in the conainer model
            while (aArgs.hasNext()) {
                Parameter parameter = (Parameter) aArgs.next();

                // compositeProcess hasInput Input
                String parameterName = parameter.getName();

                SemanticUtils.addProcessParameter(jenaOWLModel, compositeProcess, parameterName,
                        type);
            }

            // serialise the model to a DOM-Element
            wrappedRdfElement = getSerialisation4Container(document, jenaOWLModel, ontologyName);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.error("Could not generate " + type + " for service " + serviceModel.getName());
        }

        return wrappedRdfElement;
    }

    /**
     * Write the model to a file, read it into a string, clean the model-string
     * and construct a DOM-document which is then returned.
     * 
     * @param document
     *            the document to create the cleaned model in
     * @param jenaOWLModel
     *            the model to clean
     * @param ontologyName
     *            the base URI to use in the serialisation
     * 
     * @return the cleaned up model as a DOM-Element
     * 
     * @throws Exception the exception that occured
     */
    private static Element getSerialisation4Container(Document document, JenaOWLModel jenaOWLModel,
            String ontologyName) throws Exception {
        // write the model into a file
        Vector errors = new Vector();
        String fileName = ontologyName.substring(ontologyName.lastIndexOf("/") + 1);
        File tempFile = File.createTempFile(fileName, ".owl");
        JenaOWLModel.save(tempFile, jenaOWLModel.getOntModel(), FileUtils.langXMLAbbrev, jenaOWLModel
                .getDefaultOWLOntology().getNamespace());
        if (!errors.isEmpty()) {
            for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
                Object error = iterator.next();
                Logger.debug("Error: " + error);
            }
        }

        // read the model from file into a string
        FileInputStream fis = new FileInputStream(tempFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        String line;
        StringBuffer fileContent = new StringBuffer();
        while ((line = dis.readLine()) != null) {
            fileContent.append(line);
        }

        dis.close();
        bis.close();
        fis.close();

        String output = fileContent.toString();

        // clean model: remove xml-version info, since it causes problems later
        output = output.substring("<?xml version=\"1.0\"?>".length());

        Document rdfDocument = SemanticUtils.xmlStringToElement(output, null);

        // write the resulting clean up document
        Element wrappedRdfElement = document.createElement("WrappingElement4OwlIndividual");
        wrappedRdfElement.appendChild(document.importNode(rdfDocument.getDocumentElement(), true));

        return wrappedRdfElement;
    }

    /**
     * Creates an ontology model with an initialised type container.
     * 
     * @param processModel
     *            the service for which the type container should be created
     * @param document
     *            the base document to create elements in
     * 
     * @return a node containing an RDF/XML serialisation of the ontology
     */
    private static Node generateContainer(ProcessModel processModel, Document document) {
        containerCount += 1;

        // returned node
        Node wrappedRdfElement = null;

        try {
            // create container
            JenaOWLModel jenaOWLModel = ProtegeOWL.createJenaOWLModel();
            String ontologyName = "http://process." + processModel.getProcessName() + ".output";
            NamespaceManager nsm = jenaOWLModel.getNamespaceManager();
            nsm.setPrefix(ontologyName + "#", "container" + containerCount);
            nsm.setDefaultNamespace(ontologyName + "#");
            nsm.setPrefix("http://localhost:8080/ontologies/ServiceAnchor.owl#", "anchor");
            // jenaOWLModel.getDefaultOWLOntology().setName(ontologyName);

            // import all necessary ontologies
            ImportHelper importHelper = new ImportHelper(jenaOWLModel);
            for (Iterator iterator =
                    processModel.getOutputDataflow().getRegisteredPrefixes().keySet().iterator(); 
                        iterator.hasNext();) {
                String importUri = (String) iterator.next();
                // remove last character '#'
                if (importUri.endsWith("#")) {
                    importUri = importUri.substring(0, importUri.length() - 1);
                }
                importHelper.addImport(new URI(importUri));
            }
            importHelper.addImport(new URI("http://localhost:8080/ontologies/ServiceAnchor.owl"));
            importHelper.importOntologies(false);

            // add ProcessRoot with name of the service to container model
            RDFIndividual compositeProcess =
                    SemanticUtils.addProcessRoot(jenaOWLModel, processModel.getProcessName()
                            + "Process");

            // get outputs
            Iterator aArgs = processModel.getProcessOutputs().iterator();

            // for each output parameter of the process
            // construct a output parameter in the conainer model
            while (aArgs.hasNext()) {
                Parameter parameter = (Parameter) aArgs.next();

                // compositeProcess hasOutput Output
                String parameterName = parameter.getName();

                SemanticUtils.addProcessParameter(jenaOWLModel, compositeProcess, parameterName,
                        POSTFIX_4_OUTPUT_CONTAINER);
            }

            // write the resulting container
            wrappedRdfElement = getSerialisation4Container(document, jenaOWLModel, ontologyName);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.error("Could not generate Output for process " + processModel.getProcessName());
        }

        return wrappedRdfElement;
    }

}

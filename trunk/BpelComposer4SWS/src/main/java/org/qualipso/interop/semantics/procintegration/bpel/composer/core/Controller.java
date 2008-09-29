package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import java.net.URI;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.qualipso.interop.semantics.procintegration.bpel.composer.deployment.ActiveBPELDeployer;
import org.qualipso.interop.semantics.procintegration.bpel.composer.deployment.FileDeployer;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.Constants;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.HelpFrame;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.Menu;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.ParameterPanel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.ParameterSplitPane;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.ProcessPanel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.matching.MatchingEngine;

import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;


/**
 * Central singleton controller for delegation of actions and GUI interaction.
 */
public class Controller {

	// singleton object
	private static Controller controller;

	// composite process
	private ProcessModel process;

	// matching engine
	private MatchingEngine matchingEngine;

	// deployer
	private FileDeployer deployer;

	// references to GUI components
	private JFrame frame;

	private Menu menu;

	private ProcessPanel processPanel;

	private ParameterSplitPane parameterPane;

	/**
	 * singleton creator
	 * 
	 * @return Controller
	 */
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private Controller() {

		this.matchingEngine = new MatchingEngine();

		// use a tomcat specific deployer
		this.deployer = new ActiveBPELDeployer();
		    
        new Thread() {
            public void run() {
                // pre-load all Protege-Classes to reduce time for the first call of a Semantic Bridge
                JenaOWLModel model = ProtegeOWL.createJenaOWLModel();
                
                // free memory
                model.dispose();           
            }
        }.run();
	}

	/**
	 * Set process attribute.
	 * 
	 * @param process
	 */
	private void setProcess(ProcessModel process) {
		this.process = process;
	}

	/**
	 * Create new process and updates GUI elements asynchronly.
	 * 
	 */
	public void createNewProcess() {

		// get name
		final String name = (String) JOptionPane.showInputDialog(this.frame,
				"Set process name:", "Process name", JOptionPane.PLAIN_MESSAGE,
				null, null, "Composition1");

		if (name == null)
			return;

		new Thread() {
			public void run() {

				MessagePanel.appendOut("Creating new process ");
				MessagePanel.appendOutBlue(name);
				MessagePanel.appendOut(" ... ");

				// reset menu
				controller.getMenu().removeSemanticBridges();

				// set new process
				ProcessModel newProcess = new ProcessModel(name);
				controller.setProcess(newProcess);
				controller.getProcessPanel().setProcess(newProcess);
				controller.getParameterSplitPane().clear();

				MessagePanel.appendSuccessln("done.");
			}
		}.start();

	}

	/**
	 * Add new semantic web service by URL and update GUI elements asynchronly.
	 * 
	 */
	public void addSemanticWebService() {
		if (this.process == null) {
			MessagePanel
					.appendErrorln("No existing process. Create new process first.");
		} else {

			// get one of default owl-s descriptions
			String owlstemp = (String) JOptionPane
					.showInputDialog(
							this.frame,
							"Set URL of OWL-S service description:",
							"Service description",
							JOptionPane.PLAIN_MESSAGE,
							null,
							new String[] {
									"http://localhost:8080/PublicServices/ResidentRegistry/GetResidentInfo.owl",
									"http://localhost:8080/PublicServices/VitalRecordsOffice/GetVitalRecordsDocumentInfo.owl",
									"http://localhost:8080/PublicServices/PublicServicePayment/MakePayment.owl",
									"http://localhost:8080/PublicServices/VitalRecordsOffice/OrderBirthCertificate.owl",
									"http://localhost:8080/PublicServices/StatisticalOffice/AddToOfficialCitizenDocumentsStatistic.owl",
									"http://www.mindswap.org/2004/owl-s/1.1/BabelFishTranslator.owl",
									"http://www.mindswap.org/2004/owl-s/1.1/Dictionary.owl",
                                    "http://localhost:8080/PublicServices/Test/performTest.owl",
//                                    "http://localhost:8080/SemanticWebServices/MoonServices/MoonCRMService.owl",
//                                    "http://localhost:8080/SemanticWebServices/MoonServices/MoonOMSCreateNewOrderService.owl",
//                                    "http://localhost:8080/SemanticWebServices/MoonServices/MoonOMSAddLineItemService.owl",
//                                    "http://localhost:8080/SemanticWebServices/MoonServices/MoonOMSCloseOrderService.owl",
									"New URL" },
							"http://localhost:8080/PublicServices/ResidentRegistry/GetResidentInfo.owl");

			// get owl-s description from new URL
			if ("New URL".equals(owlstemp)) {
				owlstemp = (String) JOptionPane
						.showInputDialog(this.frame,
								"Set URL of OWL-S service description:",
								"Service description",
								JOptionPane.PLAIN_MESSAGE, null, null,
								"http://localhost:8080/PublicServices/ResidentRegistry/GetResidentInfo.owl");
			}

			String nameTemp;
			try {

				// get name, default name from service name
				String owlsName = owlstemp
						.substring(owlstemp.lastIndexOf("/") + 1);
				nameTemp = (String) JOptionPane.showInputDialog(this.frame,
						"Set service name:", "Service name",
						JOptionPane.PLAIN_MESSAGE, null, null, owlsName
								.substring(0, owlsName.lastIndexOf(".")));

			} catch (Exception e) {
				nameTemp = (String) JOptionPane.showInputDialog(this.frame,
						"Set service name:", "Service name",
						JOptionPane.PLAIN_MESSAGE, null, null, "SWS"
								+ this.getProcess().getServiceCount());
			}
			final String name = nameTemp;

			// abort if information is missing
			final String owls = owlstemp;
			if (name == null || owls == null) {
				MessagePanel.appendErrorln("No Semantic Web Service added.");
				return;
			}

			new Thread() {
				public void run() {
					try {

						MessagePanel
								.appendOut("Adding new Semantic Web Service ");
						MessagePanel.appendOutBlue(name);
						MessagePanel.appendOut(" described by ");
						MessagePanel.appendOutBlue(owls);
						MessagePanel.appendOutln(" ... ");

						// add new service
						ServiceModel newService = new ServiceModel(name, owls);
						controller.getProcess().addService(newService);

						// visualize
						controller.getProcessPanel().addService(newService);
						controller.displayServiceParameters(newService
								.getName());

						MessagePanel.appendSuccessln("done.");

					} catch (Exception e) {
						MessagePanel
								.appendErrorln("\nCould not load service description. Make sure service description is available.");
						Logger.error(e.getMessage());
						e.printStackTrace();
					}
				}

			}.start();

		}
	}

	/**
	 * Load semantic bridge and apply to service outputs which type is defined
	 * in semantic bridge source ontology.
	 * 
	 */
	public void loadSemanticBridge() {

		// get semanticBridgeFilename
		if (this.process == null) {
			MessagePanel
					.appendErrorln("No existing process. Create new process first.");
		} else {

			// get one of default semantic bridges
			String semanticBridgeFilenameTemp = (String) JOptionPane
					.showInputDialog(
							this.frame,
							"Load Semantic Bridge:",
							"Semantic Bridge",
							JOptionPane.PLAIN_MESSAGE,
							null,
							new String[] {
                                    "http://localhost:8080/PublicServices/ResidentRegistry/SemanticBridgeResidentRegistry2PublicServicePayment.owl",
                                    "http://localhost:8080/PublicServices/ResidentRegistry/SemanticBridgeResidentRegistry2VitalRecords.owl",
//									"http://localhost:8080/PublicServices/ResidentRegistry/SemanticBridgeResidentRegistry2PublicServicePayment.rules",
//                                  "http://localhost:8080/PublicServices/ResidentRegistry/SemanticBridgeResidentRegistry2VitalRecords.rules",
									"New URL" },
							"http://localhost:8080/PublicServices/ResidentRegistry/SemanticBridgeResidentRegistry2PublicServicePayment.owl");

			// get owl-s description from new URL
			if (semanticBridgeFilenameTemp.equals("New URL")) {
				semanticBridgeFilenameTemp = (String) JOptionPane
						.showInputDialog(
								this.frame,
								"Load Semantic Bridge:",
								"Semantic Bridge",
								JOptionPane.PLAIN_MESSAGE,
								null,
								null,
								"http://localhost:8080/PublicServices/ResidentRegistry/SemanticBridgeResidentRegistry2Test.rules");
			}

			// abort if information is missing
			final String semanticBridgeFilename = semanticBridgeFilenameTemp;
			if (semanticBridgeFilename == null) {
				MessagePanel.appendErrorln("No Semantic Bridge loaded.");
				return;
			}

			// check if semantic bridge already loaded
			Iterator semanticBridgesIter = this.process.getSemanticBridges()
					.iterator();
			while (semanticBridgesIter.hasNext()) {
				SemanticBridge semanticBridge = (SemanticBridge) semanticBridgesIter
						.next();
				if (semanticBridge.getSemanticBridgeURL().equals(
						semanticBridgeFilename)) {
					MessagePanel
							.appendErrorln("Semantic Bridge already loaded.");
					return;
				}
			}

			new Thread() {

				public void run() {

					MessagePanel.appendOut("Loading Semantic Bridge ");
					MessagePanel.appendOutBlue(semanticBridgeFilename);
					MessagePanel.appendOutln(" ... ");

					// load Semantic Bridge
					SemanticBridge semanticBridge = new SemanticBridge(
							semanticBridgeFilename);

					// add to menu list
					controller.getMenu().addSemanticBridge(semanticBridge);

					// apply semantic bridge to matching services
					ServiceModel service = controller.getProcess()
							.addSemanticBridge(semanticBridge);

					// display a service to which semantic bridge was applied
					if (service != null) {
						displayServiceParameters(service.getName());

					} else {
						MessagePanel
								.appendOutln("    No service output avaiable matching semantic bridge source ontology.");
					}

					MessagePanel.appendSuccessln("done.");

				}

			}.start();
		}

	}

	/**
	 * Initiate deployment of designed process to a web container through
	 * deployer.
	 * 
	 */
	public void deployProcess(String deployer) {

		if (this.process == null) {
			MessagePanel
					.appendErrorln("No existing process. Create new process first.");
		} else {

			MessagePanel.appendOut("Deploying ");
			MessagePanel.appendOutBlue(process.getProcessName() + " ");

			if (deployer.equals(Constants.TOMCAT_AXIS_DEPLOYER)) {
				this.deployer = new ActiveBPELDeployer();
				MessagePanel.appendOutln("to " + Constants.TOMCAT_AXIS_DEPLOYER
						+ " ... ");

			} else if (deployer.equals(Constants.File_DEPLOYER)) {
				this.deployer = new FileDeployer();
				MessagePanel.appendOutln("descriptions to file system ... ");
			}

			new Thread() {
				public void run() {

					controller.deployer.deployProcessInit();
					MessagePanel.appendSuccessln("done.");

				}

			}.start();
		}
	}

	/**
	 * Display input parameters of service in left panel and output parameters
	 * in right panel.
	 * 
	 * @param serviceName
	 */
	public void displayServiceParameters(final String serviceName) {

		new Thread() {
			public void run() {
				// MessagePanel.appendOut("Dislpay service ");
				// MessagePanel.appendOutBlue(serviceName);
				// MessagePanel.appendOut(" ... ");

				// get service
				ServiceModel currentService = controller.getProcess()
						.getServiceByName(serviceName);

				// left panel
				controller.getParameterSplitPane().setLeftPanel(
						new ParameterPanel(currentService.getInputs(), false));
				controller.getParameterSplitPane().setLeftTitle(
						currentService.getName() + " input:");

				// right panel
				controller.getParameterSplitPane().setRightPanel(
						new ParameterPanel(currentService.getOutputs(), false));
				controller.getParameterSplitPane().setRightTitle(
						currentService.getName() + " output:");

				// MessagePanel.appendSuccessln("done.");
			}

		}.start();
	}

	/**
	 * Updates GUI and displays process input and output parameters.
	 * 
	 */
	public void displayProcessParameters() {

		new Thread() {
			public void run() {

				// left panel
				controller.getParameterSplitPane().setLeftPanel(
						new ParameterPanel(getProcess().getProcessInputs(),
								false));
				controller.getParameterSplitPane().setLeftTitle(
						getProcess().getProcessName() + " input:");

				// right panel
				controller.getParameterSplitPane().setRightPanel(
						new ParameterPanel(getProcess().getProcessOutputs(),
								false));
				controller.getParameterSplitPane().setRightTitle(
						getProcess().getProcessName() + " output:");

			}

		}.start();
	}

	/**
	 * Create new process input, add to process and update GUI.
	 * 
	 * @param node
	 */
	public void addProcessInputParameter(final ParameterNode node) {

		// get name for new process input parameter
		final String name = (String) JOptionPane.showInputDialog(this.frame,
				"Set process input name:", "Process Input Name",
				JOptionPane.PLAIN_MESSAGE, null, null, node.getDirectType()
						.getFragment());

		// test
		if (name == null)
			return;

		new Thread() {
			public void run() {

				// add process input
				ProcessInput newProcessInput = new ProcessInput(name, node);
				controller.getProcess().addProcessInput(newProcessInput);

				// show new assignment status
				controller.displayServiceParameters(((ServiceInput) node
						.getParameter()).getService().getName());
			}

		}.start();
	}

	/**
	 * Create new process output, add to process and update GUI.
	 * 
	 * @param parameterNode
	 */
	public void addProcessOutputParameter(final ParameterNode parameterNode) {

		// get name for new process input parameter
		final String name = (String) JOptionPane.showInputDialog(this.frame,
				"Set process output name:", "Process Output Name",
				JOptionPane.PLAIN_MESSAGE, null, null, parameterNode
						.getDirectType().getFragment());
		if (name == null)
			return;

		// if parameterNode has more than one type ask user to select one
		// specific, process outputs need to have one specific type for OWL-S
		// generation
		URI directType = null;
		if (parameterNode.getTypes().size() == 1) {
			directType = (URI) parameterNode.getTypes().firstElement();

		} else {
			// hide trivial type owl:Thing
			URI preselectType = (((URI) parameterNode.getTypes().firstElement())
					.getFragment().equals("Thing")) ? ((URI) parameterNode
					.getTypes().elementAt(1)) : ((URI) parameterNode.getTypes()
					.firstElement());

			directType = (URI) JOptionPane.showInputDialog(this.frame,
					"Type for process output parameter:", "Select Type",
					JOptionPane.PLAIN_MESSAGE, null, parameterNode.getTypes()
							.toArray(), preselectType);

			// check user input
			if (directType == null) {
				MessagePanel
						.appendErrorln("No process output parameter added because no direct type was set.");
				return;
			}

		}

		final URI directTypeFinal = directType;

		new Thread() {
			public void run() {

				// add process inputs
				ProcessOutput newProcessOutput = new ProcessOutput(name,
						parameterNode, directTypeFinal);
				controller.getProcess().addProcessOutput(newProcessOutput);

				// // set dataflow describtion
				// controller.getProcess().getOutputDataflow().addProcessOutputDataflow(parameterNode,newProcessOutput);

				// show new assignment status
				controller
						.displayServiceParameters(((ServiceOutput) parameterNode
								.getParameter()).getService().getName());
			}

		}.start();
	}

	/**
	 * Remove process input or process output.
	 * 
	 * @param parameter
	 */
	public void clearProcessParameter(final Parameter parameter) {

		new Thread() {
			public void run() {

				if (parameter instanceof ProcessInput) {
					controller.getProcess().removeProcessInput(
							parameter.getName());
				} else if (parameter instanceof ProcessOutput) {
					controller.getProcess().removeProcessOutput(
							parameter.getName());
				}

			}

		}.start();
	}

	/**
	 * Delete assignment information, both on source side and on target side.
	 * 
	 * @param node
	 */
	public void clearAssignment(final ParameterNode node) {

		new Thread() {
			public void run() {

				// if clearAssignment initiated from source =
				// hasAssignmentTarget
				if (node.getAssignmentTargetParameterNodesInfo().length() > 0) {

					// delete assignment on target side
					Vector targets = (Vector) node
							.getAssignmentTargetParameterNodes().clone();
					Iterator iter = targets.iterator();
					while (iter.hasNext()) {
						ParameterNode target = (ParameterNode) iter.next();
						if (target.getParameter() instanceof ProcessOutput) {
							controller.getProcess().removeProcessOutput(
									target.getParameter().getName());
						}
						target.setAssignmentSourceParameterNode(null);

						// delete assignment on source side
						node.removeAssignmentTargetParameterNode(target);
					}

					// show new assignemnt status
					if (node.getParameter() instanceof ProcessInput) {
						controller.displayProcessParameters();

					} else if (node.getParameter() instanceof ServiceOutput) {
						controller
								.displayServiceParameters(((ServiceOutput) node
										.getParameter()).getService().getName());
					}

					// clear initiated from target
				} else {

					// clear assignment on source side
					ParameterNode source = node
							.getAssignmentSourceParameterNode();
					source.removeAssignmentTargetParameterNode(node);

					// clear assignment on target side
					node.setAssignmentSourceParameterNode(null);

					// source is a process input and node was the only forward
					// target then delete this process input because this
					// process input is then not needed anymore.
					// if(source.getParameter() instanceof ProcessInput &&
					// !source.hasAssignmentTargetParameterNodes()){
					// // if sub part is still used in another forward root can
					// not be deleted
					// }

					// show new assignemnt status
					if (node.getParameter() instanceof ProcessOutput) {
						controller.displayProcessParameters();

					} else if (node.getParameter() instanceof ServiceInput) {
						controller
								.displayServiceParameters(((ServiceInput) node
										.getParameter()).getService().getName());
					}
				}

			}

		}.start();
	}

	/**
	 * Initiate matching search for current node and mark all matching nodes.
	 * Display matching results and update GUI.
	 * 
	 * @param node
	 */
	public void callMatchingEngine(final ParameterNode node) {

		new Thread() {
			public void run() {

				MessagePanel.appendOut("Search matchings for ");
				MessagePanel.appendOutBlue(node.getDirectType() + " ... ");

				// match
				Vector matchingParameters = controller.getMatchingEngine()
						.match(node);

				// display results
				controller.getParameterSplitPane().setRightTitle(
						Constants.MATCHING_RESULT);
				controller.getParameterSplitPane().setRightPanel(
						new ParameterPanel(matchingParameters, true));

				// show left panel in matching mode (matching target highlighted
				// green)
				Vector serviceInputs = ((ServiceInput) node.getParameter())
						.getService().getInputs();
				controller.getParameterSplitPane().setLeftPanel(
						new ParameterPanel(serviceInputs, false));

				MessagePanel.appendOut("found "
						+ controller.getMatchingEngine().getCurrentMatchCount()
						+ " matching(s) ... ");
				MessagePanel.appendSuccessln("done.");
			}
		}.start();

	}

	/**
	 * Set new assignment information in source node and in target node of
	 * assignment. Delete former assignment source. Update GUI.
	 * 
	 * @param sourceParameterNode
	 */
	public void setAssignment(final ParameterNode sourceParameterNode) {
		final ParameterNode targetParameterNode = controller
				.getMatchingEngine().getCurrentMatchingTarget();
		new Thread() {
			public void run() {

				// delete former assignment if existent
				ParameterNode source = targetParameterNode
						.getAssignmentSourceParameterNode();

				if (source != null) {
					// clear assignment on source side
					source
							.removeAssignmentTargetParameterNode(targetParameterNode);

					// clear assignment on target side
					targetParameterNode.setAssignmentSourceParameterNode(null);
				}

				// set new assignment
				targetParameterNode
						.setAssignmentSourceParameterNode(sourceParameterNode);
				sourceParameterNode
						.addAssignmentTargetParameterNode(targetParameterNode);

				// display new assignment status:

				// right panel
				controller.getParameterSplitPane().setRightPanel(
						new ParameterPanel(controller.getMatchingEngine()
								.getCurrentMatchingResult(), true));

				// left panel
				Vector serviceInputs = ((ServiceInput) targetParameterNode
						.getParameter()).getService().getInputs();
				controller.getParameterSplitPane().setLeftPanel(
						new ParameterPanel(serviceInputs, false));
			}
		}.start();

	}

	/**
	 * Display about dialog.
	 */
	public void displayAbout() {
		JOptionPane.showMessageDialog(controller.getFrame(), Constants.ABOUT);
	}

	/**
	 * Display about help dialog.
	 * 
	 */
	public void displayHelp() {

		
		HelpFrame.displayHelpFrame();

	}


	public MatchingEngine getMatchingEngine() {
		return matchingEngine;
	}

	public ProcessModel getProcess() {
		return process;
	}

	public ProcessPanel getProcessPanel() {
		return processPanel;
	}

	public void setProcessPanel(ProcessPanel processPanel) {
		this.processPanel = processPanel;
	}

	public void setParameterPane(ParameterSplitPane parameterPane) {
		this.parameterPane = parameterPane;
	}

	public ParameterSplitPane getParameterSplitPane() {
		return parameterPane;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenu() {
		return menu;
	}

	public JFrame getFrame() {
		return frame;
	}

}

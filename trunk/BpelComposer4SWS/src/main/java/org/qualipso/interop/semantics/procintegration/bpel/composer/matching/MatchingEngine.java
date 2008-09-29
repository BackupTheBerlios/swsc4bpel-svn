package org.qualipso.interop.semantics.procintegration.bpel.composer.matching;

import java.net.URI;
import java.util.Iterator;
import java.util.Vector;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Parameter;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ParameterNode;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceInput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceOutput;


/**
 * The MatchingEngine performs the matching by traversing all parameter nodes and marks them if
 * their types correspond with the current matching target.
 * 
 */
public class MatchingEngine {

	// matching listener
	private Vector listeners = new Vector();

	private int currentMatchCount;

	private ParameterNode currentMatchingTarget;

	private Vector currentMatchingResult;

	public ParameterNode getCurrentMatchingTarget() {
		return currentMatchingTarget;
	}

	/**
	 * Collects potential matching parameters (prior service outputs and process
	 * inputs) into matching result collection. Then traverses each parameter
	 * node of them and checks if all types of target node match types in that
	 * current parameter node. Matchings parameter nodes are marked if their
	 * type set is a subset of the macthing target types set.
	 * 
	 * @param node
	 * @return Vector of parameters
	 */
	public Vector match(ParameterNode node) {

		long t1 = System.currentTimeMillis();

		// get target information
		ServiceModel targetService = ((ServiceInput) node.getParameter())
				.getService();

		// get process inputs and outputs of prior services in process sequence
		// as potential matching partners
		Vector potentialSourceParameters = new Vector();

		// add process inputs
		potentialSourceParameters.addAll(Controller.getInstance().getProcess()
				.getProcessInputs());

		// get potential sources for parameter matchings
		Vector potentialSourceServices = targetService.getPriorServices();

		// add outputs of prior services
		Iterator serviceIter = potentialSourceServices.iterator();
		while (serviceIter.hasNext()) {
			Vector serviceOutputs = ((ServiceModel) serviceIter.next())
					.getOutputs();
			Iterator outputIter = serviceOutputs.iterator();
			while (outputIter.hasNext()) {
				potentialSourceParameters
						.add((ServiceOutput) outputIter.next());
			}

		}

		// reset match counter
		this.currentMatchCount = 0;

		// set matching target
		this.currentMatchingTarget = node;

		// start matching
		Iterator parameterIter = potentialSourceParameters.iterator();
		while (parameterIter.hasNext()) {
			Parameter next = (Parameter) parameterIter.next();
			ParameterNode root = (ParameterNode) next.getRoot();
			// Logger.info("check parameter " + next.getName());
			// parameter pre-order tree walk for checking
			preorderMatchingTreeWalk(root, node.getTypes());
		}
		this.fireMatchingFinished();

		// set matching result
		this.currentMatchingResult = potentialSourceParameters;

		// time logging
		long t2 = System.currentTimeMillis();
		Logger.info("MatchingTimeConsumption for "
				+ node.getValueAllTypesLabel() + ": " + (t2 - t1));

		return potentialSourceParameters;
	}

	/**
	 * Traverses a parameter tree recursively and
	 * 
	 * @param root
	 * @param targetTypes
	 */
	private void preorderMatchingTreeWalk(ParameterNode root, Vector targetTypes) {

		// reset former matches
		root.setCurrentmatchingPartner(false);

		// test type compatibility
		if (root.getTypes().containsAll(targetTypes)) {
			// Logger.info("MATCHING");
			this.currentMatchCount++;
			root.setCurrentmatchingPartner(true);
		}

		// Logger.info("Current node "+root.getPropertyLabel()+" types: ");
		// showTypes(root.getTypes(), root);

		Iterator children = root.getChildren().iterator();
		while (children.hasNext()) {
			preorderMatchingTreeWalk((ParameterNode) children.next(),
					targetTypes);
		}
	}

	/**
	 * Test method showing all inferred types.
	 * 
	 * @param types
	 * @param node
	 */
	private void showTypes(Vector types, ParameterNode node) {
		Iterator iter = types.iterator();
		while (iter.hasNext()) {
			Logger.info(((URI) iter.next()).toString());
		}

		Logger.info("types for label: " + node.getValueAllTypesLabel());
	}

	public void addListener(MatchingEngineListener listener) {
		this.listeners.add(listener);
	}

	public void fireMatchingFinished() {
		Iterator iter = listeners.iterator();
		while (iter.hasNext()) {
			((MatchingEngineListener) iter.next()).matchingFinished();
		}
	}

	public int getCurrentMatchCount() {
		return currentMatchCount;
	}

	public Vector getCurrentMatchingResult() {
		return currentMatchingResult;
	}
}

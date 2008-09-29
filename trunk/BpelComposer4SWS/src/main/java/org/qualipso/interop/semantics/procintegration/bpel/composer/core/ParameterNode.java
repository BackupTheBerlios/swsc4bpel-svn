package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLProperty;
import org.mindswap.owl.OWLValue;

/**
 * Triples get stored in a tree structure, where each node is a ParameterNode.
 * 
 * e.g. Address<br>
 * _____|_hasName Name<br>
 * _______________|_hasGivenName 'Heinz'<br>
 * _______________|_hasSurname 'Mayer'<br>
 * 
 * A ParameterNode represents one line in the above tree structure.
 */
public class ParameterNode {

	// parameter to which parameter node belongs
	private Parameter parameter;

	// subject = parent
	private ParameterNode parent;

	// direct type of parameter as defined in direct class definition
	private URI directType;

	// predicate = property to which value belongs
	private OWLProperty property;

	// object = (individual|literal) proxy representing the value of this
	// parameter part
	private OWLValue value;

	private boolean leaf = false;

	// direect plus inferred types
	private Vector types = new Vector();;

	// children
	private Vector children = new Vector();

	// reference to assignment source
	// in a finished composition design all service input parameter parts need
	// to be assigned
	// only used in ParameterNodes of ServiceInputs TODO: subclass!
	private ParameterNode assignmentSourceParameterNode;

	// reciprocal reference 1:N relation
	// only used in ParameterNodes of ProcessInputs and ServiceOutputs
	private Vector assignmentTargetParameterNodes = new Vector();

	// true if this holds an direct source assignment
	private boolean directAssignment = false;

	// true if parent has an assignment or all children have an assignment
	private boolean derivedAssignment = false;

	private boolean currentMatchingPartner = false;

	/**
	 * Construct empty parameter node.
	 */
	public ParameterNode() {
	}

	/**
	 * Construct full parameter node.
	 * 
	 * @param parameter
	 * @param parent
	 * @param directType
	 * @param property
	 * @param value
	 */
	public ParameterNode(Parameter parameter, ParameterNode parent,
			URI directType, OWLProperty property, OWLValue value) {

		// set attributes
		this.parameter = parameter;
		this.parent = parent;
		this.property = property;
		this.value = value;
		this.directType = directType;

		// set type(s)
		if (value.isDataValue()) {
			this.types.add(((OWLDataValue) value).getDatatypeURI());
			this.leaf = true;

		} else if (value.isIndividual()) {
			OWLIndividual individual = (OWLIndividual) value;

			// get types
			Set owlClasses = individual.getTypes();
			Iterator iter = owlClasses.iterator();
			while (iter.hasNext()) {

				OWLClass owlClass = (OWLClass) iter.next();
				this.types.add(owlClass.getURI());
			}
		}
	}

	public boolean isRoot() {
		return property == null;
	}

	public void addChild(ParameterNode child) {
		this.children.add(child);
	}

	public ParameterNode getChildAt(int index) {
		return (ParameterNode) children.get(index);

	}

	public int getChildCount() {
		return children.size();
	}

	public int getIndexOfChild(ParameterNode child) {
		if (children == null || children.isEmpty()) {
			return -1;
		}
		return children.indexOf(child);
	}

	/**
	 * Returns the fragment of the property's URI.
	 * 
	 * @return PropertyLabel
	 */
	public String getPropertyLabel() {
		String propertyName = (this.property != null) ? (this.property.getURI()
				.getFragment()) : ("");

		return propertyName;
	}

	/**
	 * Returns the fragment of datatype URI.
	 * 
	 * @return ValueLabel
	 */
	public String getValueLabel() {

		String valueName = null;
		if (this.value.isDataValue()) {
			valueName = ((OWLDataValue) this.value).getDatatypeURI()
					.getFragment();
		} else {
			valueName = ((OWLIndividual) this.value).getType().getURI()
					.getFragment();
		}
		return valueName;
	}

	/**
	 * Returns label string consisting of fragments of all inferred type names.
	 * 
	 * @return ValueAllTypesLabel
	 */
	public String getValueAllTypesLabel() {
		String result = "type: ";

		if (this.value.isDataValue()) {
			result += ((OWLDataValue) this.value).getDatatypeURI()
					.getFragment();
		} else {
			Set types = ((OWLIndividual) this.value).getTypes();
			Iterator iter = types.iterator();

			// append additional types
			while (iter.hasNext()) {
				String nextType = ((OWLClass) iter.next()).getURI()
						.getFragment();
				// skip useless type information
				if (nextType.equals("Thing"))
					continue;
				result += nextType + ", ";
			}
			result = result.substring(0, result.length() - 2);

		}

		return result;
	}

	/**
	 * Returns all complete type names.
	 * 
	 * @return FullTypeInfo
	 */
	public String getFullTypeInfo() {
		String result = "";

		if (this.value.isDataValue()) {
			result += ((OWLDataValue) this.value).getDatatypeURI().toString();
		} else {
			Set types = ((OWLIndividual) this.value).getTypes();
			Iterator iter = types.iterator();

			// append additional types
			while (iter.hasNext()) {
				String nextType = ((OWLClass) iter.next()).getURI().toString();
				// skip useless type information
				if (nextType.equals("http://www.w3.org/2002/07/owl#Thing"))
					continue;
				result += nextType + ", ";
			}
			result = result.substring(0, result.length() - 2);

		}

		return result;
	}

	/**
	 * Returns complete direct type name.
	 * 
	 * @return ValueDirectTypeLabel
	 */
	public String getValueDirectTypeLabel() {

		if (this.directType == null) {
			// only process outputs affected:
			// no direct type was set during semantic bridging
			// set one of inferred types as direct type
			Iterator iter = this.types.iterator();
			URI thing = null;
			while (iter.hasNext()) {
				URI type = (URI) iter.next();

				// ignore Thing
				if (type.toString().equals(
						"http://www.w3.org/2002/07/owl#Thing")) {
					thing = type;

				} else {
					this.directType = type;
					break;
				}
			}
			// if no other type found is thing
			if (this.directType == null) {
				this.directType = thing;
			}
		}

		String result = "type: " + this.getDirectType().getFragment();

		return result;
	}

	/**
	 * Update inferred types (java class attribute) recursively after
	 * classification.
	 * 
	 */
	public void updateInferredTypes() {
		// individual
		if (this.getValue().isIndividual()) {
			Set types = ((OWLIndividual) this.value).getTypes();
			Iterator iter = types.iterator();
			// append new inferred types
			while (iter.hasNext()) {
				URI type = ((OWLClass) iter.next()).getURI();
				if (!this.types.contains(type)) {
					this.types.add(type);
				}
			}
			Iterator children = this.getChildren().iterator();
			while (children.hasNext()) {
				ParameterNode child = (ParameterNode) children.next();
				child.updateInferredTypes();
			}
			// datavalue
		} else {
			if (!this.types.contains(((OWLDataValue) this.value)
					.getDatatypeURI())) {
				this.types.add(((OWLDataValue) this.value).getDatatypeURI());
			}
		}
	}

	public Vector getTypes() {
		return types;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public ParameterNode getParent() {
		return parent;
	}

	public Vector getChildren() {
		return children;
	}

	public boolean isCurrentmatchingPartner() {
		return currentMatchingPartner;
	}

	public void setCurrentmatchingPartner(boolean currentmatchingPartner) {
		this.currentMatchingPartner = currentmatchingPartner;
	}

	public OWLValue getValue() {
		return value;
	}

	public void setValue(OWLValue value) {
		this.value = value;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public OWLProperty getProperty() {
		return property;
	}

	/**
	 * Set assignment source information. Recursively set dervided assignemnt
	 * information for parent if all children have assignments and set derived
	 * assignments for all children.
	 * 
	 * @param assignmentSourceParameterNode
	 */
	public void setAssignmentSourceParameterNode(
			ParameterNode assignmentSourceParameterNode) {

		this.assignmentSourceParameterNode = assignmentSourceParameterNode;
		this.setDirectAssignment(assignmentSourceParameterNode != null);

		// set parent derivedAssignment if all children have assignments
		// no data forward for parent needed, parameter construction will be
		// done during dataflow processing through sub parts
		if (this.parent != null) {

			boolean parenthasDerivedAssignment = assignmentSourceParameterNode != null;
			Iterator siblingsIter = this.getParent().getChildren().iterator();
			while (siblingsIter.hasNext()) {
				ParameterNode sibling = (ParameterNode) siblingsIter.next();
				parenthasDerivedAssignment &= sibling
						.getAssignmentSourceParameterNode() != null;
			}
			this.getParent().setDerivedAssignment(parenthasDerivedAssignment);
		}

		// set predecessors derivred assignment if all children have assignment
		this.setPredecessorsDerivedAssignmentRecursively();

		// set children having derived assignment
		this
				.setAssignmentSourceParameterNodeRecursivly(assignmentSourceParameterNode);

	}
	/**
	 * Helper method for setAssignmentSourceParameterNode.
	 *
	 */
	private void setPredecessorsDerivedAssignmentRecursively() {
		if (this.parent != null) {

			boolean parenthasDerivedAssignment = true;
			Iterator siblingsIter = this.getParent().getChildren().iterator();
			while (siblingsIter.hasNext()) {
				ParameterNode sibling = (ParameterNode) siblingsIter.next();
				parenthasDerivedAssignment &= sibling.hasDerivedAssignment()
						|| sibling.hasDirectAssignment();
			}
			this.parent.setDerivedAssignment(parenthasDerivedAssignment);
			this.parent.setPredecessorsDerivedAssignmentRecursively();
		}
	}
	
	/**
	 * Helper method for setAssignmentSourceParameterNode.
	 * @param assignmentSourceParameterNode
	 */
	private void setAssignmentSourceParameterNodeRecursivly(
			ParameterNode assignmentSourceParameterNode) {

		this.assignmentSourceParameterNode = assignmentSourceParameterNode;

		// set children having derived assignment
		// not really consistent but usefull for assignment label
		Iterator childIter = this.getChildren().iterator();
		while (childIter.hasNext()) {
			ParameterNode next = (ParameterNode) childIter.next();
			next.setDerivedAssignment(assignmentSourceParameterNode != null);
			next
					.setAssignmentSourceParameterNodeRecursivly(assignmentSourceParameterNode);
		}
	}

	public URI getDirectType() {
		return directType;
	}

	public ParameterNode getAssignmentSourceParameterNode() {
		return assignmentSourceParameterNode;
	}

	
	/**
	 * Remove assignmentTargetNode from assignment target list.
	 * @param assignmentTargetParameterNode
	 */
	public void removeAssignmentTargetParameterNode(
			ParameterNode assignmentTargetParameterNode) {
		Iterator iter = this.assignmentTargetParameterNodes.iterator();
		while (iter.hasNext()) {
			ParameterNode next = (ParameterNode) iter.next();
			if (next.equals(assignmentTargetParameterNode)) {
				assignmentTargetParameterNodes
						.remove(assignmentTargetParameterNode);
				break;
			}
		}

		// if no more targets setDirectAssignment(false) and recursivly
		// derivedAssignement(false)
		if (this.assignmentTargetParameterNodes.size() < 1) {
			this.setDirectAssignment(false);
			this.setDerivedAssignment(false);

			// recursivly for children too
			Iterator children = this.getChildren().iterator();
			while (children.hasNext()) {
				ParameterNode child = (ParameterNode) children.next();
				child
						.removeAssignmentTargetParameterNode(assignmentTargetParameterNode);
			}
		}
	}
	
	/**
	 * Store new assignment target in target list.
	 * @param assignmentTargetParameterNode
	 */
	public void addAssignmentTargetParameterNode(
			ParameterNode assignmentTargetParameterNode) {
		this.setDirectAssignment(assignmentTargetParameterNode != null);
		this
				.addAssignmentTargetParameterNodeRecursivly(assignmentTargetParameterNode);

	}
	
	/**
	 * Helper method for addAssignmentTargetParameterNode.
	 * @param assignmentTargetParameterNode
	 */
	private void addAssignmentTargetParameterNodeRecursivly(
			ParameterNode assignmentTargetParameterNode) {
		this.assignmentTargetParameterNodes.add(assignmentTargetParameterNode);

		// not really consistent but usefull for assignment label
		Iterator childIter = this.getChildren().iterator();
		while (childIter.hasNext()) {
			ParameterNode next = (ParameterNode) childIter.next();
			next.setDerivedAssignment(true);
			next
					.addAssignmentTargetParameterNodeRecursivly(assignmentTargetParameterNode);
		}
	}
	
	
	public boolean hasAssignmentTargetParameterNodes() {
		return this.assignmentTargetParameterNodes != null
				&& this.assignmentTargetParameterNodes.size() > 0;
	}

	/**
	 * Returns concatenated String of service names to where ParameterNode is
	 * forwarded.
	 * 
	 * @return AssignmentTargetParameterNodesInfo
	 */
	public String getAssignmentTargetParameterNodesInfo() {
		String info = "";
		Iterator iter = this.assignmentTargetParameterNodes.iterator();
		while (iter.hasNext()) {
			if (info.length() > 1)
				info += ", ";
			ParameterNode next = (ParameterNode) iter.next();
			Parameter parameter = next.getParameter();
			if (parameter instanceof ServiceInput) {
				info += ((ServiceInput) parameter).getService().getName();
			} else if (parameter instanceof ServiceOutput) {
				info += ((ServiceOutput) parameter).getService().getName();
			} else if (parameter instanceof ProcessInput) {
				info += Controller.getInstance().getProcess().getProcessName()
						+ " Input";
			} else {
				info += Controller.getInstance().getProcess().getProcessName()
						+ " Output";
			}

		}
		return info;
	}

	public boolean hasDerivedAssignment() {
		return derivedAssignment;
	}

	public void setDerivedAssignment(boolean derivedAssignment) {
		this.derivedAssignment = derivedAssignment;
	}

	public boolean hasDirectAssignment() {
		return directAssignment;
	}

	public void setDirectAssignment(boolean directAssignment) {
		this.directAssignment = directAssignment;
	}

	public Vector getAssignmentTargetParameterNodes() {
		return assignmentTargetParameterNodes;
	}

	public void setDirectType(URI directType) {
		this.directType = directType;
	}

}

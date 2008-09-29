package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLDataProperty;
import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLObjectProperty;
import org.mindswap.owl.OWLProperty;
import org.mindswap.owl.OWLValue;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.vocabulary.XSD;

/**
 * Superclass for ServiceInput/ServiceOutput and ProcessInput/Processoutput.
 * This parameter class is a wrapper around the tree structure the parameter parts are presented.
 * 
 */
public class Parameter implements TreeModel {

	// parameter name
	protected String name;

	// parameterType
	protected URI parameterTypeURI;

	// root of parameter tree
	protected ParameterNode root;

	// KB (OWL-S API wrapped model) for parameter handling
	protected OWLKnowledgeBase kb;

	/**
	 * Simple constructor to be used for process parameters without builidng a
	 * parameter tree.
	 * 
	 */
	public Parameter() {
	}

	/**
	 * Generates a service parameter. For the given parameterType a parameter
	 * tree of ParameterNodes is constructed. Dummy individual with filler
	 * objects representing the parameter is stored in a seperate A-Box model to
	 * keep KB size handy for data handling .
	 * 
	 * @param name
	 * @param parameterTypeURI
	 */
	public Parameter(String name, URI parameterTypeURI) {

		this.name = name;
		this.parameterTypeURI = parameterTypeURI;

		long t1 = System.currentTimeMillis();

		try {
			// create KB for parameters
			this.kb = OWLFactory.createKB();

			if (!isPrimitiveType(this.parameterTypeURI)) {
				// extracting ontology URL from parameterTypeURL
				String ontologyURL = parameterTypeURI.toString().substring(
						0,
						parameterTypeURI.toString().lastIndexOf(
								parameterTypeURI.getFragment()) - 1);

				this.kb.read(ontologyURL);
			}

			// generate proxy parameter
			this.root = createProxyParameterValue(null, null, parameterTypeURI,
					kb);

			// remove T-Box overhead
			// removeTBox();

		} catch (Exception e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
		}

		// time logging
		long t2 = System.currentTimeMillis();
		Logger.info("GenerateParameterTimeConsumption for " + name + ": "
				+ (t2 - t1));
	}

	/**
	 * Rebuild parameter tree after semantic bridge was applied.
	 * 
	 */
	public void rebuildParameterTreeStart() {
		OWLIndividual rootIndividual = (OWLIndividual) this.root.getValue();
		// SemanticBridge.writeModel((OntModel)rootIndividual.getKB().getImplementation(),"CheckDat2.owl");
		// Logger.info(rootIndividual.toRDF());
		this.setRoot(this.rebuildParameterTreeRecursively(null, null,
				rootIndividual, ((ParameterNode) this.root).getDirectType()));

	}

	/**
	 * Help method for rebuildParameterTreeStart().
	 * 
	 * @param parent
	 * @param property
	 * @param value
	 * @param directType
	 * @return new root ParameterNode
	 */
	private ParameterNode rebuildParameterTreeRecursively(ParameterNode parent,
			OWLProperty property, OWLValue value, URI directType) {

		ParameterNode newNode = new ParameterNode(this, parent, directType,
				property, value);

		// if value is an individual, go on and append children
		if (value.isIndividual()) {
			OWLIndividual individual = (OWLIndividual) value;
			// get children
			Map childProperties = individual.getProperties();
			for (Iterator i = childProperties.keySet().iterator(); i.hasNext();) {
				OWLProperty childProperty = (OWLProperty) i.next();

                // get values for each property
                List childValues = (List) childProperties.get(childProperty);
                for (Iterator j = childValues.iterator(); j.hasNext();) {
                    OWLValue childValue = (OWLValue) j.next();
                    
                    // get direct type of child
                    URI directTypeChild = null;
                    if(childValue.isDataValue()){
                        directTypeChild = ((OWLDataValue)childValue).getDatatypeURI();
                    }else{
                        directTypeChild = ((OWLIndividual)childValue).getType().getURI();
                    }

                    // add child
                    if (individual.equals(childValue))
                    {
                        // Logger.debug("IGNORING same child: " + childProperty.getURI());
                    }
                    else
                    {
                        // Logger.debug("Recursivly adding child: " + childProperty.getURI());
                        ParameterNode child = rebuildParameterTreeRecursively(
                                newNode, childProperty, childValue, directTypeChild);
                        newNode.getChildren().add(child);
                    }
                }				
			}
		}

		return newNode;

	}

	/**
	 * Generates proxy parameter value in KB according to definedClass
	 * description and constructs java object parameter tree of ParameterNodes.
	 * Together in one method because range of properties in defined class is
	 * needed for ParameterNode to set directType an essential attribute in
	 * ParameterNode.
	 * 
	 * @param parent
	 * @param property
	 * @param directType
	 * @param kb
	 * @return new generated ParameterNode
	 */
	
	public ParameterNode createProxyParameterValue(ParameterNode parent,
			OWLProperty property, URI directType, OWLKnowledgeBase kb) {

		// primitive datatype
		if (isPrimitiveType(directType)) {
			OWLDataValue datavalue = kb.createDataValue("", directType);
			return new ParameterNode(this, parent, directType, property,
					datavalue);
		}

		// create proxy individual
		OWLClass definedClassOWLS = kb.getClass(directType);
		OWLIndividual proxy = kb.createInstance(definedClassOWLS);

		ParameterNode parameterNode = new ParameterNode(this, parent,
				directType, property, proxy);

		try {

			// use Jena API to get property range
			OntClass definedClassJena = ((OntModel) kb.getImplementation())
					.getOntClass(directType.toString());
			List propertyURIs = listConceptProperties((OntModel) kb
					.getImplementation(), definedClassJena);

			for (int i = 0; i < propertyURIs.size(); i++) {
				OWLProperty owlsProperty = kb.getProperty(new URI(
						(String) propertyURIs.get(i)));
				URI propertyURI = owlsProperty.getURI();
				OntProperty jenaProperty = ((OntModel) kb.getImplementation())
						.getOntProperty(propertyURI.toString());
				String type = jenaProperty.getRange().getURI();

				if (jenaProperty.isDatatypeProperty()) {
					// TODO: set node value according to primitive data type
					OWLDataValue dataValue = kb.createDataValue("7",
							new URI(type));

					// create new ParameterNode child
					ParameterNode child = new ParameterNode(this,
							parameterNode, new URI(type),
							(OWLDataProperty) owlsProperty, dataValue);

					// add to parameter tree
					parameterNode.addChild(child);

					// add in KB
					proxy
							.addProperty((OWLDataProperty) owlsProperty,
									dataValue);

				} else {
					kb.createClass(new URI(type));

					// create new ParameterNode child
					ParameterNode child = createProxyParameterValue(
							parameterNode, (OWLObjectProperty) owlsProperty,
							new URI(type), kb);

					// add to parameter tree
					parameterNode.addChild(child);

					// add in KB
					proxy.addProperty((OWLObjectProperty) owlsProperty,
							(OWLIndividual) child.getValue());

				}
			}

		} catch (Exception e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
		}

		return parameterNode;
	}

	/**
	 * Checks if parameter is a primitive data type.
	 * 
	 * @param directType
	 * @return  isPrimitiveType
	 */
	public boolean isPrimitiveType(URI directType) {
		// http://www.w3.org/2001/XMLSchema#string
		boolean primitive = directType.toString().equals(
				XSD.xstring.getURI().toString())
				|| directType.toString().equals(XSD.xint.getURI().toString())
				|| directType.toString().equals(XSD.xshort.getURI().toString())
				|| directType.toString().equals(XSD.xlong.getURI().toString())
				|| directType.toString().equals(XSD.xfloat.getURI().toString())
				|| directType.toString()
						.equals(XSD.xdouble.getURI().toString())
				|| directType.toString().equals(XSD.xbyte.getURI().toString())
				|| directType.toString().equals(
						XSD.xboolean.getURI().toString())
				|| directType.toString().equals(
						XSD.nonNegativeInteger.getURI().toString())
				|| directType.toString().equals(
						XSD.nonPositiveInteger.getURI().toString());
		return primitive;
	}

	/**
	 * Creates a proxy instance filled recursivly with dummies according to
	 * defined class definition. Model needs to contain all ontology information
	 * about definedClass.
	 * 
	 * @param definedClass
	 * @param model
	 * @return Individual
	 */
	// public static Individual createProxyInstance(OntClass definedClass,
	// OntModel model) {
	//
	// // time logging
	// long t1 = System.currentTimeMillis();
	//
	// Individual pseudo = model.createIndividual(definedClass);
	// List properties = listConceptProperties(definedClass);
	// for (int i = 0; i < properties.size(); i++) {
	// OntProperty property = (OntProperty) properties.get(i);
	// if (property.isDatatypeProperty()) {
	// String type = property.getRange().toString();
	// Literal literal = model.createTypedLiteral("");
	// if (type.equals("http://www.w3.org/2001/XMLSchema#string")) {
	// literal = model.createTypedLiteral("Horst", type);
	// } else if (type.equals("http://www.w3.org/2001/XMLSchema#int")) {
	// literal = model.createTypedLiteral("7", type);
	// } else if (type.equals("http://www.w3.org/2001/XMLSchema#date")) {
	// literal = model.createTypedLiteral("1111-11-11", type);
	// }
	// pseudo.addProperty(property, literal);
	// } else if (property.isObjectProperty()) {
	// OntClass objectClass = model.createClass(property.getRange()
	// .getURI());
	// pseudo.addProperty(property, createProxyInstance(objectClass,
	// model));
	// }
	// }
	// // time logging
	// long t2 = System.currentTimeMillis();
	// // Logger.info("CreateDummyInstanceTimeConsumption for "
	// // + definedClass.getURI().substring(
	// // definedClass.getURI().indexOf("#")) + ": " + (t2 - t1));
	// return pseudo;
	// }
	
	/**
	 * Lists all corresponding property URIs of a defined class given by its
	 * URI.
	 * 
	 * @param definedClass
	 * @return List of properties
	 */
	public static List listConceptProperties(OntModel model,
			OntClass definedClass) {
		// return list
		List propertyURIs = new ArrayList();

		// get all properties of equivalent classes
		Iterator iter1 = definedClass.listEquivalentClasses();
		while (iter1.hasNext()) {
			OntClass take = (OntClass) iter1.next();
			if (!take.equals(definedClass)) {
				propertyURIs.addAll(listConceptProperties(model, take));
			}
		}

		// get all properties of super classes
		Iterator iterSuper = definedClass.listSuperClasses();
		while (iterSuper.hasNext()) {
			OntClass take = (OntClass) iterSuper.next();

			if (!take.equals(definedClass)) {

				propertyURIs.addAll(listConceptProperties(model, take));
			}
		}

		// get intersection classes
		if (definedClass.isIntersectionClass()) {
			Iterator iter2 = definedClass.asIntersectionClass().listOperands();
			while (iter2.hasNext()) {
				propertyURIs.addAll(listConceptProperties(model,
						(OntClass) iter2.next()));
			}
			// get property from restriction
		} else if (definedClass.isRestriction()) {
			Restriction restriction = definedClass.asRestriction();
			propertyURIs.add(restriction.getOnProperty().getURI());
			if (restriction.isAllValuesFromRestriction()) {
				restriction.getOnProperty().setRange(
						restriction.asAllValuesFromRestriction()
								.getAllValuesFrom());
			} else if (restriction.isSomeValuesFromRestriction()) {
				restriction.getOnProperty().setRange(
						restriction.asSomeValuesFromRestriction()
								.getSomeValuesFrom());
			}
			// TODO: restriction casses are not complete however not all class definitions over
			// restrictions are usefull in context of service parameter data
			// flow design
		}
		return propertyURIs;
	}
	
	public void addTreeModelListener(TreeModelListener arg0) {
	}

	// TODO: removeTBox
	// public void removeTBox(){
	// // extract A-Box, parameter root individual
	// try {
	// ((OntModel) kb.getImplementation()).writeAll(new FileOutputStream(
	// i+++"ClassificationTest.owl"), "RDF/XML", null);

	// OWLIndividual rootIndividual = (OWLIndividual) this.kb
	// .getInstances(
	// this.kb.getClass(new URI(parameterTypeURI
	// .toString()))).iterator().next();
	//		
	// // and store without T-Box in model for data handling
	// OWLKnowledgeBase kbTemp = OWLFactory.createKB();
	// ((OntModel) kbTemp.getImplementation()).read(new StringReader(
	// rootIndividual.toRDF()), null);
	//		
	// // weird but necessary for individual typing
	// OntModel model = ModelFactory
	// .createOntologyModel(PelletReasonerFactory.THE_SPEC);
	// model.add((OntModel) kbTemp.getImplementation());
	// ((OntModel) kbTemp.getImplementation()).add(model);
	//		
	// ((OntModel) this.kb.getImplementation()).removeAll();
	// ((OntModel)
	// this.kb.getImplementation()).add((OntModel)kbTemp.getImplementation());
	//		
	// } catch (Exception e) {
	// Logger.error(e.getMessage());
	// e.printStackTrace();
	// }
	// }
	
//	/**
//	 * Clean up model and remove T-Box when not needed anymore. T-Box should be
//	 * removed to keep models small for efficient data handling. Removes T-Box
//	 * to downsize model for inference engine.
//	 */
//	public void removeTBoxbackup() {
//
//		// alternative: listStatements of individual and recursivly for
//		// objects, then add statements to new model
//		if (true)
//			return;
//
//		// does not work
//		try {
//
//			OWLIndividual rootIndividual = (OWLIndividual) this.kb
//					.getInstances(
//							this.kb.getClass(new URI(parameterTypeURI
//									.toString()))).iterator().next();
//
//			// and store without T-Box in model for data handling
//			OWLKnowledgeBase kbTemp = OWLFactory.createKB();
//			((OntModel) kbTemp.getImplementation()).read(new StringReader(
//					rootIndividual.toRDF()), null);
//
//			// TODO: rootIndividual cannot be changed
//			// set extracted individual as root
//			// OWLIndividual newRootIndividual = (OWLIndividual) this.kb
//			// .getInstances(
//			// this.kb.getClass(new URI(parameterTypeURI
//			// .toString()))).iterator().next();
//			// this.root = ... newRootIndividual;
//
//			// weird necessary for individual typing
//			OntModel model = ModelFactory
//					.createOntologyModel(PelletReasonerFactory.THE_SPEC);
//			model.add((OntModel) kbTemp.getImplementation());
//			((OntModel) kbTemp.getImplementation()).add(model);
//
//			// get handle for rootIndividual of new KB
//			// this.root.setValue((OWLIndividual) kbTemp.getInstances(
//			// kbTemp.getClass(new URI(parameterTypeURL.toString()))).iterator()
//			// .next());
//			// restore in orginial KB
//			this.kb = kbTemp;
//
//		} catch (Exception e) {
//			Logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//
//		// SemanticBridge.writeModel((OntModel) kb.getImplementation(),
//		// this.name
//		// + "_nach.owl");
//	}

//	/**
//	 * Exctract A-Box from model.
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public OntModel extractABoxFromModel(OntModel model) {
//
//		OntModel returnModel = ModelFactory
//				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
//
//		try {
//
//			// get root
//			ExtendedIterator iter = model.listIndividuals();
//			Individual rootIndividual = null;
//			while (iter.hasNext()) {
//				Individual i = (Individual) iter.next();
//				if (i.hasRDFType(parameterTypeURI.toString())) {
//					rootIndividual = i;
//					break;
//				}
//			}
//
//			// copy recursivly ABox statements to returnModel
//			this.copyABoxStatements(rootIndividual, model, returnModel);
//
//		} catch (Exception e) {
//			Logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//
//		return returnModel;
//
//	}


//	/**
//	 * Help method for extractABoxFromModel 
//	 * (too slow, not used anyway)
//	 * @param root
//	 * @param source
//	 * @param target
//	 */
//	private void copyABoxStatements(Resource root, OntModel source,
//			OntModel target) {
//		StmtIterator rootStmts1 = source.listStatements(root, (Property) null,
//				(RDFNode) null);
//		target.add(rootStmts1);
//		StmtIterator rootStmts2 = source.listStatements(root, (Property) null,
//				(RDFNode) null);
//		while (rootStmts2.hasNext()) {
//			Statement statement = rootStmts2.nextStatement();
//			RDFNode object = statement.getObject();
//			if (object.equals(root))
//				continue;
//			if (object instanceof Resource) {
//				Resource new_root = (Resource) object;
//				copyABoxStatements(new_root, source, target);
//			}
//		}
//	}
	
//	/**
//	 * Help method for extractABoxFromModel 
//	 * (too slow, not used anyway)
//	 * @param root
//	 * @param source
//	 * @param target
//	 */
//	private void addStatements(Resource root, OWLKnowledgeBase source,
//			OWLKnowledgeBase target) {
//		StmtIterator iter1 = ((OntModel) source.getImplementation())
//				.listStatements(root, (Property) null, (RDFNode) null);
//		((OntModel) target.getImplementation()).add(iter1);
//		StmtIterator iter2 = ((OntModel) source.getImplementation())
//				.listStatements(root, (Property) null, (RDFNode) null);
//		while (iter2.hasNext()) {
//			Statement statement = iter2.nextStatement();
//			RDFNode object = statement.getObject();
//			if (object instanceof Resource) {
//				Resource newRoot = (Resource) object;
//				addStatements(newRoot, source, target);
//			}
//		}
//	}

	/**
	 * Copy ParameterNode root from service input parameter to set process input
	 * visualization independent from service input.
	 * 
	 * @param oldRoot
	 * @return copy of old root parameter node
	 */
	protected ParameterNode copyRootParameterNode(ParameterNode oldRoot) {
		// set new parameter wrapper and delete parent and property information
		ParameterNode newRoot = new ParameterNode(this, null, oldRoot
				.getDirectType(), null, oldRoot.getValue());
		Iterator childIter = oldRoot.getChildren().iterator();
		while (childIter.hasNext()) {
			newRoot.addChild(copyParameterNode(newRoot,
					(ParameterNode) childIter.next()));
		}
		return newRoot;
	}

	/**
	 * Recursive helper method for copyRootParameterNode.
	 * 
	 * @param parent
	 * @param node
	 * @return copy of parameter node
	 */
	private ParameterNode copyParameterNode(ParameterNode parent,
			ParameterNode node) {
		ParameterNode newNode = new ParameterNode(this, parent, node
				.getDirectType(), node.getProperty(), node.getValue());
		Iterator childIter = node.getChildren().iterator();
		while (childIter.hasNext()) {
			newNode.addChild(copyParameterNode(newNode,
					(ParameterNode) childIter.next()));
		}
		return newNode;
	}

	public Object getChild(Object parent, int index) {
		return ((ParameterNode) parent).getChildAt(index);
	}

	public int getChildCount(Object parent) {
		return ((ParameterNode) parent).getChildren().size();
	}

	public int getIndexOfChild(Object parent, Object child) {
		return ((ParameterNode) parent).getIndexOfChild((ParameterNode) child);
	}

	public Object getRoot() {
		return root;
	}

	public boolean isLeaf(Object parent) {
		return ((ParameterNode) parent).getChildCount() == 0;
	}

	public void removeTreeModelListener(TreeModelListener arg0) {

	}

	public void valueForPathChanged(TreePath arg0, Object arg1) {

	}

	public void setRoot(ParameterNode root) {
		this.root = root;
	}

	public OWLKnowledgeBase getKb() {
		return kb;
	}

	public String getName() {
		return name;
	}

	public URI getParameterTypeURI() {
		return parameterTypeURI;
	}
}

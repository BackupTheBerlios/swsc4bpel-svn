package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Parameter;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ParameterNode;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessInput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessOutput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceInput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceOutput;


/**
 * Panel that represents the parameter node tree of a parameter.
 * Can be used for process parameters, service parameters, and matching results.
 *
 */
public class ParameterPanel extends JPanel {

	// parameter to visualize
	private Vector parameters;

	// tree representing parameter
	private JTree tree;
	
	// displayed if no parameters available
	private JLabel noParameterLabel;

	// popup menu to control findMatching-, setAsProcessInput-, etc. activities
	// depending on subclass
	private JPopupMenu popupMenu;

	// display parameters as matchings results
	private boolean matchingResult;

	/**
	 * Create ParameterPanel for givven parameters if matchingResult, present
	 * them as matching results.
	 * 
	 * @param parameters
	 * @param matchingResult
	 */
	public ParameterPanel(Vector parameters, boolean matchingResult) {
		
		this.parameters = parameters;
		this.matchingResult = matchingResult;
		this.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);

		// no parameters
		if (parameters == null || parameters.size() == 0) {
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.noParameterLabel = new JLabel("No parameters available", Constants.ROOT_ICON,
					JLabel.LEFT);
				this.add(this.noParameterLabel);
			return;
		}

		// parameters > 0 -> display parameter tree
		this.setLayout(new BorderLayout());
		// parameter tree with invisible root holding all parameters
		ParameterNode invisibleRoot = new ParameterNode();
		Parameter treeModel = new Parameter();
		treeModel.setRoot(invisibleRoot);

		// append parameter children
		Iterator iter = this.parameters.iterator();
		while (iter.hasNext()) {
			invisibleRoot.addChild((ParameterNode) ((Parameter) iter.next())
					.getRoot());
		}
		
		// create JTree
		this.tree = new JTree(treeModel);
		this.tree.setRowHeight(Constants.PARAMETER_TREE_ROW_HEIGHT);
		this.tree.setRootVisible(false);
		this.tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setCellRenderer(new ParameterTreeRenderer());
		this.tree.addMouseListener(new ParameterTreeMouseAdapter());
		ToolTipManager.sharedInstance().registerComponent(tree);
		this.add(this.tree);
	

		if (matchingResult) {
			// expand matching nodes in matching result
			this.expandMatchingResults(invisibleRoot);
		
		}else {
			
			// expand root node of each parameter
			this.expandParameterRoots();
			
			// expand service parameters with assignment
			this.expandParametersWithAssignment(invisibleRoot);
			
			// expand service parameters with assignment
			this.expandMatchingTargets(invisibleRoot);
		}

	}

	/**
	 * Create specific popup menu for service input parameters with no
	 * assignment.
	 * 
	 * @return FindMatchingAndSetAsProcessInputMenu
	 */
	private JPopupMenu createFindMatchingAndSetAsProcessInputMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem matchItem = new JMenuItem(Constants.FIND_MATCHING);
		matchItem.addActionListener(new PopupMenuListener());

		JMenuItem asProcessInputItem = new JMenuItem(
				Constants.SET_AS_PROCESS_INPUT);
		asProcessInputItem.addActionListener(new PopupMenuListener());

		popupMenu.add(matchItem);
		popupMenu.add(asProcessInputItem);

		return popupMenu;
	}

	/**
	 * Create specific popup menu for parameters with assignment.
	 * 
	 * @return ClearAssignmentMenu
	 */
	private JPopupMenu createClearAssignmentMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem clearItem = new JMenuItem(Constants.CLEAR_ASSIGNMENT);
		clearItem.addActionListener(new PopupMenuListener());

		JMenuItem showAssignmentItem = new JMenuItem(Constants.SHOW_ASSIGNMENT);
		showAssignmentItem.addActionListener(new PopupMenuListener());

		popupMenu.add(clearItem);

		return popupMenu;
	}

	/**
	 * Create specific popup menu for service output parameters with no
	 * assignment.
	 * 
	 * @return SetAsProcessOutputMenu
	 */
	private JPopupMenu createSetAsProcessOutputMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem asProcessInputItem = new JMenuItem(
				Constants.SET_AS_PROCESS_OUTPUT);
		asProcessInputItem.addActionListener(new PopupMenuListener());

		popupMenu.add(asProcessInputItem);

		return popupMenu;
	}
	
	/**
	 * Create specific popup menu for service output parameters having an incoming assignment but still can be set as process output.
	 * assignment.
	 * @return ClearAssignmentSetAsProcessOutputMenu
	 */
	private JPopupMenu createClearAssignmentSetAsProcessOutputMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem asProcessInputItem = new JMenuItem(
				Constants.SET_AS_PROCESS_OUTPUT);
		asProcessInputItem.addActionListener(new PopupMenuListener());
		
		JMenuItem clearItem = new JMenuItem(Constants.CLEAR_ASSIGNMENT);
		clearItem.addActionListener(new PopupMenuListener());

		popupMenu.add(asProcessInputItem);
		popupMenu.add(clearItem);
		
		return popupMenu;
	}
	
	

	/**
	 * Create specific popup menu for matching result parameters not yet
	 * assigned.
	 * 
	 * @return SetAssignmentMenu
	 */
	private JPopupMenu createSetAssignmentMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem setAssignmentItem = new JMenuItem(Constants.SET_ASSIGNMENT);
		setAssignmentItem.addActionListener(new PopupMenuListener());

		popupMenu.add(setAssignmentItem);

		return popupMenu;
	}

	/**
	 * Create specific popup menu for process input and output parameters.
	 * 
	 * @return ClearParameterMenu
	 */
	private JPopupMenu createClearParameterMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem clearParameterItem = new JMenuItem(Constants.CLEAR_PARAMETER);
		clearParameterItem.addActionListener(new PopupMenuListener());

		popupMenu.add(clearParameterItem);

		return popupMenu;
	}

	private void treePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			
			// also select node when right mouse button clickeed
			this.tree.setSelectionPath(this.tree.getPathForLocation(e.getX(), e
					.getY()));
			
			// get node if selected and print type info
		} else if (this.tree.getSelectionPath() != null) {
			ParameterNode parameterNode = (ParameterNode) this.tree
					.getSelectionPath().getLastPathComponent();

			// show full type info
			MessagePanel.appendOut("Type info: ");
			MessagePanel.appendOutBlueln(parameterNode.getFullTypeInfo());
		}
	}

	/**
	 * Show popup menu according to available actions for parameter type and
	 * according to assigment status.
	 * 
	 * @param e
	 */
	private void treeReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {

			// get selected node
			ParameterNode parameterNode = (ParameterNode) this.tree
					.getSelectionPath().getLastPathComponent();

			// process input node or process output node
			if (isProcessParameter(parameterNode) && !matchingResult) {
				this.popupMenu = createClearParameterMenu();
				this.popupMenu.show(this.tree, e.getX(), e.getY());

				// if node has already assignment to target node in matching
				// mode show clear assignment menu
			} else if (matchingResult && hasAssignmentToTarget(parameterNode)) {

				this.popupMenu = createClearAssignmentMenu();
				this.popupMenu.show(this.tree, e.getX(), e.getY());

				// matching result node
			} else if (matchingResult
					&& parameterNode.isCurrentmatchingPartner()) {

				// popup assign menu if matching partner
				this.popupMenu = createSetAssignmentMenu();
				this.popupMenu.show(this.tree, e.getX(), e.getY());

				// has direect assignemnt in non-matching mode ->
				// ClearAssignmentMenu
			} else if (!matchingResult && parameterNode.hasDirectAssignment()) {
				
				this.popupMenu = createClearAssignmentMenu();
				if (parameterNode.getParameter() instanceof ServiceOutput) {
					this.popupMenu = createClearAssignmentSetAsProcessOutputMenu();
				}
				this.popupMenu.show(this.tree, e.getX(), e.getY());

				// service input node with no assignment
			} else if (parameterNode.getParameter() instanceof ServiceInput) {
				this.popupMenu = createFindMatchingAndSetAsProcessInputMenu();
				this.popupMenu.show(this.tree, e.getX(), e.getY());

				// service output with no assignment
			} else if (parameterNode.getParameter() instanceof ServiceOutput) {
				this.popupMenu = createSetAsProcessOutputMenu();
				this.popupMenu.show(this.tree, e.getX(), e.getY());

			}

		}
	}

	private boolean hasAssignmentToTarget(ParameterNode source) {

		// get target
		ParameterNode target = Controller.getInstance().getMatchingEngine()
				.getCurrentMatchingTarget();

		// check conditions
		return source.hasDirectAssignment() && target != null
				&& target.getAssignmentSourceParameterNode() != null
				&& target.getAssignmentSourceParameterNode().equals(source);
	}

	private boolean isProcessParameter(ParameterNode parameterNode) {
		return (parameterNode.getParameter() instanceof ProcessInput || parameterNode
				.getParameter() instanceof ProcessOutput)
				&& parameterNode.isRoot();
	}

	private JPanel getMatchingResultTreeCellPanel(ParameterNode node) {
		JPanel panel = new JPanel();
		JLabel label = createLabel(node, true);
		panel.add(label);

		// show matching
		if (node.isCurrentmatchingPartner()) {
			label.setOpaque(true);
			label.setBackground(Constants.LIGHT_GREEN);
		}

		// show assignment info icon if matching partner
		if (isAssignedMatchingPartner(node)) {
			JLabel iconLabel = new JLabel(Constants.ARROW_RIGHT);

			// get assignment targets info for tooltip
			panel.add(iconLabel);
			panel.setToolTipText("assignment to "
					+ node.getAssignmentTargetParameterNodesInfo());

		} else {

			panel
					.setToolTipText("select highlighted parameter and set assignment");
		}
		return panel;

	}

	private boolean isAssignedMatchingPartner(ParameterNode parameterNode) {
		ParameterNode target = Controller.getInstance().getMatchingEngine()
				.getCurrentMatchingTarget();
		return parameterNode.hasDirectAssignment()
				&& (target.getAssignmentSourceParameterNode() != null && target
						.getAssignmentSourceParameterNode().equals(
								parameterNode));
	}

	private JPanel getProcessInputTreeCellPanel(ParameterNode node) {
		return getServiceOutputTreeCellPanel(node);
	}

	private JPanel getProcessOutputTreeCellPanel(ParameterNode node) {
		return getServiceInputTreeCellPanel(node);
	}

	private JPanel getServiceInputTreeCellPanel(ParameterNode parameterNode) {
		JPanel panel = new JPanel();
		// set text
		JLabel label = createLabel(parameterNode, false);
		label.setToolTipText("[" + parameterNode.getDirectType().toString()
				+ "]");
		panel.add(label);

		// if matching target in matching mode -> highlight
		if (parameterNode.equals(Controller.getInstance().getMatchingEngine()
				.getCurrentMatchingTarget())
				&& Controller.getInstance().getParameterSplitPane()
						.getRightTitle().equals(Constants.MATCHING_RESULT)) {
			label.setOpaque(true);
			label.setBackground(Constants.LIGHT_GREEN);
		}

		// set assignment icon if existing assignment from/to any ParameterNode
		if (parameterNode.getAssignmentSourceParameterNode() != null) {
			String assignmentType = "";
			if (parameterNode.hasDerivedAssignment()) {
				JLabel iconLabel = new JLabel(Constants.ARROW_LEFT_LIGHT);
				panel.add(iconLabel);
				assignmentType = "derived ";
			} else {
				JLabel iconLabel = new JLabel(Constants.ARROW_LEFT);
				panel.add(iconLabel);
			}

			// get assignment source info for tooltip
			String assignmentSource;

			// assignment source is process input
			if (parameterNode.getAssignmentSourceParameterNode().getParameter() instanceof ProcessInput) {
				assignmentSource = Controller.getInstance().getProcess()
						.getProcessName()
						+ " Input";

				// assignemnt source is prior service
			} else {
				assignmentSource = ((ServiceOutput) parameterNode
						.getAssignmentSourceParameterNode().getParameter())
						.getService().getName();
			}

			panel.setToolTipText(assignmentType + "assignment from "
					+ assignmentSource);

		} else if (parameterNode.hasDerivedAssignment()) {
			JLabel iconLabel = new JLabel(Constants.ARROW_LEFT_LIGHT);
			panel.add(iconLabel);
			panel.setToolTipText("derived from sub component's assignments");

			// no assignment is set
		} else {
			// JLabel label = new JLabel(Constants.QUESTIONMARK);
			// panel.add(label);
			((JLabel) panel.getComponent(0)).setText(((JLabel) panel
					.getComponent(0)).getText()
					+ "   ?");

			panel
					.setToolTipText("find matching and set assignment or set as process input");
		}

		return panel;
	}

	private JPanel getServiceOutputTreeCellPanel(ParameterNode node) {
		JPanel panel = new JPanel();

		// set text
		JLabel label = createLabel(node, true);
		panel.add(label);

		// set assignment icon if assignment from/to any ParameterNode
		if (node.hasAssignmentTargetParameterNodes()) {

			// gray arrow for derived assignments
			if (node.hasDerivedAssignment()) {
				JLabel iconLabel = new JLabel(Constants.ARROW_RIGHT_LIGHT);
				panel.add(iconLabel);
				// get assignment targets info for tooltip
				panel.setToolTipText(" derived assignment to "
						+ node.getAssignmentTargetParameterNodesInfo());

				// black arrow for direct assignments
			} else if (node.hasDirectAssignment()) {
				JLabel iconLabel = new JLabel(Constants.ARROW_RIGHT);
				panel.add(iconLabel);
				// get assignment targets info for tooltip
				panel.setToolTipText("assignment to "
						+ node.getAssignmentTargetParameterNodesInfo());

			}

		} else {
			panel
					.setToolTipText("service outputs can be set as process outputs");
		}

		return panel;
	}
	
	/**
	 * Draws label for ParameterNode with following text:
	 * (rootInfo), parameterName, parameterTypes;
	 * @param node
	 * @param inferredTypes
	 * @return JLabel
	 */
	private JLabel createLabel(ParameterNode node, boolean inferredTypes) {

		String parameterTypes = "";
		String rootInfo = "";

		// for matching results show inferred types too
		// and start root label with process input or service name
		if (inferredTypes && !node.getParameter().isPrimitiveType(node.getDirectType())) {
			parameterTypes = node.getValueAllTypesLabel();
			if (node.getParameter() instanceof ServiceOutput) {
				rootInfo = ((ServiceOutput) node.getParameter()).getService()
						.getName()
						+ " output: ";
			} else {
				rootInfo = Controller.getInstance().getProcess()
						.getProcessName()
						+ " input: ";
			}

			// otherwise show only direct type
		} else {
			parameterTypes = node.getValueDirectTypeLabel();
		}

		// root label
		if (node.isRoot()) {

			String parameterName = node.getParameter().getName();

			String text = rootInfo + parameterName + " " + parameterTypes;

			return new JLabel(text, Constants.ROOT_ICON, JLabel.LEFT);

			// inner node label
		} else {
			String text = node.getPropertyLabel() + " " + parameterTypes;

			return new JLabel(text);

		}
	}

	/**
	 * Expand matching results by traversing parameter tree.
	 * currentMatchingPartner -> expand
	 * 
	 * @param root
	 */
	private void expandMatchingResults(ParameterNode root) {
		Vector treePath = new Vector();
		treePath.add(root);
		traverseAndExpand(root, treePath);
	}

	/**
	 * Recurciv helper method for expandMatchingResults(...)
	 * 
	 * @param root
	 * @param treePath
	 */
	private void traverseAndExpand(ParameterNode root, Vector treePath) {
		if (root.isCurrentmatchingPartner()) {
			this.tree.expandPath(new TreePath(treePath.toArray())
					.getParentPath());
		}
		Iterator children = root.getChildren().iterator();
		while (children.hasNext()) {
			ParameterNode child = (ParameterNode) children.next();
			Vector childTreePath = (Vector) treePath.clone();
			childTreePath.add(child);
			traverseAndExpand(child, childTreePath);
		}

	}
	
	/**
	 * Expand parameter tree that matching target nodes are visible.
	 * @param root
	 */
	private void expandMatchingTargets(ParameterNode root) {
		Vector treePath = new Vector();
		treePath.add(root);
		expandMatchingTragetsRecursively(root, treePath);
	}
	
	/**
	 * Help method for expandMatchingTargets.
	 * @param root
	 * @param treePath
	 */
	private void expandMatchingTragetsRecursively(ParameterNode root, Vector treePath) {
		if (root.equals(Controller.getInstance().getMatchingEngine()
				.getCurrentMatchingTarget())
				&& Controller.getInstance().getParameterSplitPane()
						.getRightTitle().equals(Constants.MATCHING_RESULT)) {
			this.tree.expandPath(new TreePath(treePath.toArray())
					.getParentPath());
		}
		Iterator children = root.getChildren().iterator();
		while (children.hasNext()) {
			ParameterNode child = (ParameterNode) children.next();
			Vector childTreePath = (Vector) treePath.clone();
			childTreePath.add(child);
			expandMatchingTragetsRecursively(child, childTreePath);
		}
	}
	
	/**
	 * Expand parametrs with assignment by traversing parameter tree.
	 * hasDirectAssignment -> expand
	 * 
	 * @param root
	 */
	private void expandParametersWithAssignment(ParameterNode root) {
		Vector treePath = new Vector();
		treePath.add(root);
		expandParametersWithAssignmentsRecursivly(root, treePath);
	}

	/**
	 * Recurciv helper method for expandParametersWithAssignment(...)
	 * 
	 * @param root
	 * @param treePath
	 */
	private void expandParametersWithAssignmentsRecursivly(
			ParameterNode root, Vector treePath) {
		if (root.hasDirectAssignment()) {
			this.tree.expandPath(new TreePath(treePath.toArray())
					.getParentPath());
		}
		Iterator children = root.getChildren().iterator();
		while (children.hasNext()) {
			ParameterNode child = (ParameterNode) children.next();
			Vector childTreePath = (Vector) treePath.clone();
			childTreePath.add(child);
			expandParametersWithAssignmentsRecursivly(child, childTreePath);
		}

	}
	
	/**
	 * Expands  root node of each parameter.
	 *
	 */
	private void expandParameterRoots(){
		ParameterNode invisibleRoot = (ParameterNode)this.tree.getModel().getRoot();
		Iterator parameterRootIter = invisibleRoot.getChildren().iterator();
		while(parameterRootIter.hasNext()){
			TreePath parameterRootPath = new TreePath(new Object[]{invisibleRoot,parameterRootIter.next()});
			this.tree.expandPath(parameterRootPath);
		}
	}

	/**
	 * Define visualization for a parameter node.
	 *
	 */
	class ParameterTreeRenderer implements TreeCellRenderer {

		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			ParameterNode parameterNode = (ParameterNode) value;
			
			
			JPanel panel = null;

			// visualize matching results
			if (matchingResult) {
				panel = getMatchingResultTreeCellPanel(parameterNode);

				// visualize process input parameters
			} else if (parameterNode.getParameter() instanceof ProcessInput) {
				panel = getProcessInputTreeCellPanel(parameterNode);

				// visualize process output parameters
			} else if (parameterNode.getParameter() instanceof ProcessOutput) {
				panel = getProcessOutputTreeCellPanel(parameterNode);

				// visualize service input parameters
			} else if (parameterNode.getParameter() instanceof ServiceInput) {
				panel = getServiceInputTreeCellPanel(parameterNode);

				// visualize service output parameters
			} else if (parameterNode.getParameter() instanceof ServiceOutput) {
				panel = getServiceOutputTreeCellPanel(parameterNode);
			}

			// set background
			panel.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);

			// highlight selected node
			if (sel) {

				// if ParametrNode is currentMatchingPartner and therefore
				// has already green background -> dark green
				if (((JLabel) panel.getComponent(0)).getBackground().equals(
						Constants.LIGHT_GREEN)) {
					((JLabel) panel.getComponent(0))
							.setBackground(Constants.LIGHT_GREEN.darker());
				} else {
					((JLabel) panel.getComponent(0)).setOpaque(true);
					((JLabel) panel.getComponent(0))
							.setBackground(Color.LIGHT_GRAY);
				}
			}

			return panel;
		}

	}

	class ParameterTreeMouseAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			treePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			treeReleased(e);
		}
	}

	// delegate menu commands to controller
	class PopupMenuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// commands for service input menu
			if (e.getActionCommand().equals(Constants.SET_AS_PROCESS_INPUT)) {
				TreePath path = tree.getSelectionPath();
				Controller.getInstance().addProcessInputParameter(
						((ParameterNode) path.getLastPathComponent()));

			} else if (e.getActionCommand().equals(Constants.CLEAR_ASSIGNMENT)) {
				TreePath path = tree.getSelectionPath();
				Controller.getInstance().clearAssignment(
						((ParameterNode) path.getLastPathComponent()));

			} else if (e.getActionCommand().equals(Constants.FIND_MATCHING)) {
				TreePath path = tree.getSelectionPath();
				Controller.getInstance().callMatchingEngine(
						((ParameterNode) path.getLastPathComponent()));

				// commands for service output menu
			} else if (e.getActionCommand().equals(
					Constants.SET_AS_PROCESS_OUTPUT)) {
				TreePath path = tree.getSelectionPath();
				Controller.getInstance().addProcessOutputParameter(
						((ParameterNode) path.getLastPathComponent()));

				// commands for process input and output menu
			} else if (e.getActionCommand().equals(Constants.CLEAR_PARAMETER)) {
				TreePath path = tree.getSelectionPath();

				// clear assignment on source and target side
				Controller.getInstance().clearAssignment(
						((ParameterNode) path.getLastPathComponent()));

				// clear parameter
				Controller.getInstance().clearProcessParameter(
						((ParameterNode) path.getLastPathComponent())
								.getParameter());

				// set assignment
			} else if (e.getActionCommand().equals(Constants.SET_ASSIGNMENT)) {
				TreePath path = tree.getSelectionPath();
				Controller.getInstance().setAssignment(
						((ParameterNode) path.getLastPathComponent()));

			}
		}
	}

	public boolean isMatchingResult() {
		return matchingResult;
	}

	public JTree getTree() {
		return tree;
	}

	public JLabel getNoParameterLabel() {
		return noParameterLabel;
	}
}

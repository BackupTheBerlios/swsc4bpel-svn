package org.qualipso.interop.semantics.procintegration.bpel.composer.matching;

/**
 * Defines the interface listeners of the matching engine need to implement.
 * Matching listeners are notified when the matching process is finished.
 *
 */
public interface MatchingEngineListener{
	public void matchingFinished();
}
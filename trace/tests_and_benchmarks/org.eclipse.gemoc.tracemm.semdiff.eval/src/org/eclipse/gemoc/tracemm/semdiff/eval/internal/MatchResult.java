/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.tracemm.semdiff.eval.internal;

public class MatchResult {

	private boolean matches = false;
	private boolean matchedWithoutErrors = true;
	private boolean inconclusive = false;

	public MatchResult() {
	}

	public MatchResult(boolean matches, boolean matchedWithoutErrors) {
		this.matches = matches;
		this.matchedWithoutErrors = matchedWithoutErrors;
	}

	public MatchResult(boolean matches, boolean matchedWithoutErrors,
			boolean inconclusive) {
		this.matches = matches;
		this.matchedWithoutErrors = matchedWithoutErrors;
		this.inconclusive = inconclusive;
	}

	public boolean matches() {
		return matches;
	}

	public boolean matchedWithoutErrors() {
		return matchedWithoutErrors;
	}

	public boolean matchingInconclusive() {
		return inconclusive;
	}

	public void setMatchingInconclusive() {
		this.inconclusive = true;
	}

	public void setMatches(boolean matches) {
		this.matches = matches;
	}

	public void setMatchedWithoutErrors(boolean matchedWithoutErrors) {
		this.matchedWithoutErrors = matchedWithoutErrors;
	}
}

package org.eclipse.gemoc.trace.commons.model.generictrace.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gemoc.trace.commons.model.trace.Step;

public class TraceHelper {

		public static String stepComputedId(Step step) {
			String id = "";
			if(step.eContainer() != null && step.eContainer() instanceof Step) {
				id = stepComputedId((Step) step.eContainer());
				Object list = step.eContainer().eGet(step.eContainingFeature());
				if(list instanceof EList) {
					id += "/"+((EList)list).indexOf(step);
				}
			} else {
				id = "#";
			}
			return id;
		}
}

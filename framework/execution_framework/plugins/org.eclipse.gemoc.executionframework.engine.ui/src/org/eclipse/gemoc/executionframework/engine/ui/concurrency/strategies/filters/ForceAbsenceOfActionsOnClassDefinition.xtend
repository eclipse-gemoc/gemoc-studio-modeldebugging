package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters

import java.lang.reflect.Method
import java.util.Arrays
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters.ForceAbsenceOfActionsOnClassStrategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters.NonIdentityElementsStrategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyControlUpdateListener
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.List

class ForceAbsenceOfActionsOnClassDefinition extends FilteringStrategyDefinition {
	
	List control = null
	
	MSEModel mseModel = null
	
	new() {
		super("org.eclipse.gemoc.xdsml.strategies.remove_allactionsfromtype", "Force Absence Any Rules on",
			ForceAbsenceOfActionsOnClassStrategy)
	}

	override getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) {
		control = new List(parent, SWT.MULTI.bitwiseOr(SWT.V_SCROLL).bitwiseOr(SWT.BORDER))
		control.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)
		
		
		if (scul !== null) {
			control.addSelectionListener(new SelectionListener() {
				
				override widgetDefaultSelected(SelectionEvent e) { }
				
				override widgetSelected(SelectionEvent e) {
					scul.controlUpdated(ForceAbsenceOfActionsOnClassDefinition.this)
				}				
			})
		}

		control
	}

	override initaliseControl(Control uiElement, String configData) {
		val list = uiElement as List
		if (list.items.size > 0) {
			val namesToSelect = configData.split("@@")

			list.select(#[0..list.itemCount-1].flatten.filter[namesToSelect.contains(list.items.get(it))])
		}
	}

	override void initaliseControl(Control uiElement, Strategy strategy) {
		val list = uiElement as List
		list.setSelection(#[] as int[])
		
		if (strategy instanceof NonIdentityElementsStrategy) {
			list.selection = strategy.nonIdentityTypes.map[name]
		}
	}

	override encodeConfigInformation(Control uiElement) {
		val list = uiElement as List

		list.selectionIndices.map[i | list.items.get(i)].join("@@")
	}

	override initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {
		if (strategy instanceof ForceAbsenceOfActionsOnClassStrategy) {
			
//			lcc.addMetamodelChangeListener([ evt |
//				strategy.updateObjectsWithoutRules(evt.newValue as MSEModel, configData)
//			])
			if(mseModel === null){
				mseModel = lcc.engine.executionContext.MSEModel
				updateMSEModel()
			}
			strategy.updateObjectsWithoutRules(mseModel, configData)			
			
		}
	}

	def updateMSEModel() {
		control.items = emptyList
		if (mseModel !== null) {
			for (EObject c : mseModel.ownedMSEs.map[mse| mse.caller]){
				println("dealing with "+c.name)
				if(c.name !== null){
					control.add(c.name)
				}
			}
		}
	}
	
	def String getName(EObject object){
		var Method getName = null;
    	try {
      		getName = getAllMethodsInHierarchy(object.class).findFirst[m|m.name=="getName"];
      		var res = getName.invoke(object) as String
      		return res
   		} catch (Exception e) {
      		return null;
    	}
	}

	def updateObjectsWithoutRules(ForceAbsenceOfActionsOnClassStrategy nieh, MSEModel mseModel, String configData) {
		nieh.toBeAbsentObject.clear
		
		if (mseModel !== null) {
			val objNames = configData.split("@@").toList
			nieh.toBeAbsentObject = mseModel.ownedMSEs.filter[mse|objNames.exists[on | on==mse.caller.name]].map[mse| mse.caller].toSet
		}
	}
	
	/**
     * Gets an array of all methods in a class hierarchy walking up to parent classes
     * @param objectClass the class
     * @return the methods array
     */
    def static Set<Method> getAllMethodsInHierarchy(Class<?> objectClass) {
        var Set<Method> allMethods = new HashSet<Method>();
        var Method[] declaredMethods = objectClass.getDeclaredMethods();
        var Method[] methods = objectClass.getMethods();
        if (objectClass.getSuperclass() != null) {
            var Class<?> superClass = objectClass.getSuperclass();
            var Method[] superClassMethods = getAllMethodsInHierarchy(superClass);
            allMethods.addAll(Arrays.asList(superClassMethods));
        }
        allMethods.addAll(Arrays.asList(declaredMethods));
        allMethods.addAll(Arrays.asList(methods));
        return allMethods;
    }
	
}

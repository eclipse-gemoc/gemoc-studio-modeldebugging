package org.eclipse.gemoc.executionframework.debugger

import org.eclipse.gemoc.executionframework.debugger.IntrospectiveMutableFieldExtractor
import org.eclipse.gemoc.executionframework.debugger.IDynamicPartAccessor
import org.eclipse.emf.ecore.EObject
import org.eclipse.gemoc.xdsmlframework.commons.DynamicAnnotationHelper
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine
import java.util.List
import java.util.ArrayList
import org.eclipse.emf.ecore.EStructuralFeature
import java.lang.reflect.Field
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.transaction.util.TransactionUtil
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.gemoc.executionframework.engine.core.CommandExecution

class K3AspectDynamicPartAccessor extends IntrospectiveMutableFieldExtractor implements IDynamicPartAccessor {
	IExecutionEngine engine;
	
	Resource aspectEMFResource = null;
	
	new(String languageName, IExecutionEngine engine) {
		super(languageName)
		this.engine = engine
	}
	
	override isDynamic(EObject obj) {
		if(obj === null) {
			return false;
		}
		
		return DynamicAnnotationHelper.isDynamic(obj.eClass());
	}
	
	protected override List<MutableField> getMutableFieldsFromAspect(EObject eObject, Class<?> properties, Class<?> aspect) {

		val result = new ArrayList

		val fields = properties.fields

		if (!fields.empty) {
			fields.forEach [ f |
				val methods = aspect.methods.filter[m|m.name.equals(f.name)]
				val getter = methods.findFirst[m|m.parameterCount == 1]
				val setter = methods.findFirst[m|m.parameterCount == 2]
				if (getter !== null && setter !== null) {
					val data = new MutableField(
						f.name+" ("+findDataName(eObject)+ " :"+eObject.eClass().getName() +")",
						eObject,
						getFeatureForField(aspect, f),
						[getter.invoke(null, eObject)],
						[t|setter.invoke(null, eObject, t)]
					)
					result.add(data)
				}
			]
		}

		return result
	}
	
	/*
	 * 
	 */
	protected def EStructuralFeature getFeatureForField(Class<?> aspect, Field field){
		val c = getAspectEClass(aspect)
		 var res = getAspectEClass(aspect).EStructuralFeatures.filter(EStructuralFeature).findFirst[f | f.name == field.name]
		 if( res === null) {
		 	val t = field.type
		 	// if ref
		 	val eRef = EcoreFactory.eINSTANCE.createEReference
			eRef.name = aspect.name
			val ed = TransactionUtil.getEditingDomain(getAspectResource());
			var RecordingCommand command = new RecordingCommand(ed,
				"Creating EReference for aspect class coming from K3") {
				protected override void doExecute() {
					c.getEStructuralFeatures.add(eRef)
				}
			};
			CommandExecution.execute(ed, command);
			
			// else if attribute 
			// TODO
			res = eRef
		 }
		 return res
	}

	protected def EClass getAspectEClass(Class<?> aspect){
		val p = getAspectEPackage(aspect)
		var res = p.EClassifiers.filter(EClass).findFirst[c | c.name == aspect.simpleName]
		if(res === null) {
			val c = EcoreFactory.eINSTANCE.createEClass
			c.name = aspect.simpleName
			val ed = TransactionUtil.getEditingDomain(getAspectResource());
			var RecordingCommand command = new RecordingCommand(ed,
				"Creating EClass for aspect class coming from K3") {
				protected override void doExecute() {
					p.EClassifiers.add(c)
				}
			};
			CommandExecution.execute(ed, command);
			
			res = c
		}
		return res
	}
	protected def EPackage getAspectEPackage(Class<?> aspect){
		var res = getAspectResource().contents.filter(EPackage).findFirst[p | p.name == aspect.package.name]
		if(res === null) {
			val p = EcoreFactory.eINSTANCE.createEPackage
			p.name = aspect.package.name
			val ed = TransactionUtil.getEditingDomain(getAspectResource());
			var RecordingCommand command = new RecordingCommand(ed,
				"Creating EPackage for aspect class coming from K3") {
				protected override void doExecute() {
					getAspectResource().contents.add(p)
				}
			};
			CommandExecution.execute(ed, command);
			res = p
		}
		return res
	}
	protected def Resource getAspectResource() {
		if(aspectEMFResource === null) {
			aspectEMFResource = engine.executionContext.resourceModel.resourceSet.createResource(URI.createURI("dummyResourceForK3Aspects"));
		}
		return aspectEMFResource
	}	
}
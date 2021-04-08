package org.eclipse.gemoc.executionframework.event.manager;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.Value;
import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

public class EventManagerUtils {

	public static Object convertValueToObject(Value value) {
		Object object = null;
		switch (value.eClass().getClassifierID()) {
		case ValuePackage.SINGLE_REFERENCE_VALUE:
			object = ((SingleReferenceValue) value).getReferenceValue();
			break;
		case ValuePackage.SINGLE_OBJECT_VALUE:
			object = ((SingleObjectValue) value).getObjectValue();
			break;
		case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
			object = ((BooleanAttributeValue) value).isAttributeValue();
			break;
		case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
			object = ((BooleanObjectAttributeValue) value).getAttributeValue();
			break;
		case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
			object = ((IntegerAttributeValue) value).getAttributeValue();
			break;
		case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
			object = ((IntegerObjectAttributeValue) value).getAttributeValue();
			break;
		case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
			object = ((FloatAttributeValue) value).getAttributeValue();
			break;
		case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
			object = ((FloatObjectAttributeValue) value).getAttributeValue();
			break;
		case ValuePackage.STRING_ATTRIBUTE_VALUE:
			object = ((StringAttributeValue) value).getAttributeValue();
			break;
		}
		return object;
	}

	public static Value convertObjectToValue(Object object) {
		Value value = null;
		switch (object.getClass().getName()) {
		case "java.lang.Boolean":
			value = ValueFactory.eINSTANCE.createBooleanObjectAttributeValue();
			((BooleanObjectAttributeValue) value).setAttributeValue((Boolean) object);
			break;
		case "java.lang.Float":
			value = ValueFactory.eINSTANCE.createFloatObjectAttributeValue();
			((FloatObjectAttributeValue) value).setAttributeValue((Float) object);
			break;
		case "java.lang.Integer":
			value = ValueFactory.eINSTANCE.createIntegerObjectAttributeValue();
			((IntegerObjectAttributeValue) value).setAttributeValue((Integer) object);
			break;
		case "java.lang.String":
			value = ValueFactory.eINSTANCE.createStringAttributeValue();
			((StringAttributeValue) value).setAttributeValue((String) object);
			break;
		case "bool":
			value = ValueFactory.eINSTANCE.createBooleanAttributeValue();
			((BooleanAttributeValue) value).setAttributeValue((Boolean) object);
			break;
		case "float":
			value = ValueFactory.eINSTANCE.createFloatAttributeValue();
			((FloatAttributeValue) value).setAttributeValue((Float) object);
			break;
		case "int":
			value = ValueFactory.eINSTANCE.createIntegerAttributeValue();
			((IntegerAttributeValue) value).setAttributeValue((Integer) object);
			break;
		default:
			value = ValueFactory.eINSTANCE.createSingleReferenceValue();
			((SingleReferenceValue) value).setReferenceValue((EObject) object);
			break;
		}
		return value;
	}
}

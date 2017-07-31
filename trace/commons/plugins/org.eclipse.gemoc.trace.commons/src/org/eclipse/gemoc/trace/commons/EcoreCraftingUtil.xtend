/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.commons

import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import java.util.Set
import org.eclipse.emf.ecore.EOperation

class EcoreCraftingUtil {
	public static def EReference addReferenceToClass(EClass clazz, String refName, EClassifier refType) {
		val res = EcoreFactory.eINSTANCE.createEReference
		res.name = refName
		res.EType = refType
		clazz.EStructuralFeatures.add(res)
		return res
	}

	public static def EReference addReferenceToClass(EClass clazz, String refName, EClass refType) {
		if (clazz != null && refName != null && refName != "" && refType != null) {
			val res = EcoreFactory.eINSTANCE.createEReference
			res.name = refName
			res.EType = refType
			clazz.EStructuralFeatures.add(res)
			return res
		} else {
			return null
		}

	}

	public static def String getBaseFQN(EOperation o) {
		val EClass c = o.EContainingClass
		return EcoreCraftingUtil.getBaseFQN(c) + "." + o.name
	}

	public static def EAttribute addAttributeToClass(EClass clazz, String attName, EDataType attType) {
		val res = EcoreFactory.eINSTANCE.createEAttribute
		res.name = attName
		res.EType = attType
		clazz.EStructuralFeatures.add(res)
		return res
	}

	public static def EStructuralFeature addFeatureToClass(EClass clazz, String name, EClassifier type) {
		var EStructuralFeature res = null
		if (type instanceof EDataType)
			res = EcoreFactory.eINSTANCE.createEAttribute
		else if (type instanceof EClass)
			res = EcoreFactory.eINSTANCE.createEReference
		res.name = name
		res.EType = type
		clazz.EStructuralFeatures.add(res)
		return res
	}

	public static def String getFQN(EClassifier c, String separator) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.name
		} else {
			return c.name
		}
	}

	public static def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name
		}
	}

	public static def String getBaseFQN(EClassifier c) {
		if (c != null) {
			val EPackage p = c.getEPackage
			if (p != null) {
				return getBaseFQN(p) + "." + c.name
			} else {
				return c.name
			}
		} else {
			return ""
		}
	}

	public static def String getJavaFQN(EClassifier c, Set<GenPackage> refGenPackages) {
		getJavaFQN(c, refGenPackages, false)
	}

	public static def String getJavaFQN(EClassifier c, Set<GenPackage> refGenPackages,
		boolean enforcePrimitiveJavaClasses) {

		if (c.instanceClass != null) {
			if (enforcePrimitiveJavaClasses) {
				switch (c.instanceClass.canonicalName) {
					case "int": return "java.lang.Integer"
					case "boolean": return "java.lang.Boolean"
				// TODO
				}
			}
			return c.instanceClass.canonicalName

		}

		if (c.instanceClassName != null && c.instanceClassName != "")
			return c.instanceClassName

		var String base = ""
		val gc = getGenClassifier(c, refGenPackages)

		if (gc != null && gc.genPackage != null) {
			base = getBase(gc.genPackage)
		}
		return base + getBaseFQN(c);
	}

	private static def String getBase(GenPackage gp) {
		var String base = ""
		if (gp.basePackage != null && gp.superGenPackage == null) {
			base = gp.basePackage + "."
		}
		return base
	}

	public static def String getBaseFQN(EPackage p) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getBaseFQN(superP) + "." + p.name
		} else {
			return p.name
		}
	}

	public static def String getJavaFQN(EPackage p, Set<GenPackage> refGenPackages) {

		var String base = ""
		val gp = getGenPackage(p, refGenPackages)
		if (gp != null) {
			base = getBase(gp)
		}
		return base + getBaseFQN(p);
	}

	public static def GenClassifier getGenClassifier(EClassifier c, Set<GenPackage> refGenPackages) {
		if (c != null) {
			for (gp : refGenPackages) {
				for (gc : gp.eAllContents.filter(GenClassifier).toSet) {
					val ecoreClass = gc.ecoreClassifier
					if (ecoreClass != null) {
						val s1 = getBaseFQN(ecoreClass)
						val s2 = getBaseFQN(c)
						if (s1 != null && s2 != null && s1.equalsIgnoreCase(s2)) {
							return gc
						}
					}
				}
			}

		}
		return null
	}

	public static def GenPackage getGenPackage(EPackage p, Set<GenPackage> refGenPackages) {
		if (p != null) {
			for (gp : refGenPackages) {
				val packageInGenpackage = gp.getEcorePackage
				if (packageInGenpackage != null) {
					val s1 = getBaseFQN(p)
					val s2 = getBaseFQN(packageInGenpackage)
					if (s1 != null && s2 != null && s1.equalsIgnoreCase(s2)) {
						return gp
					}
				}
			}
		}
		return null
	}

	public static def String stringCreate(EClass c) {
		val EPackage p = c.EPackage
		return EcoreCraftingUtil.getBaseFQN(p) + "." + p.name.toFirstUpper + "Factory.eINSTANCE.create" + c.name + "()"
	}

	public static def String stringCreateImplicitStep(EClass c) {
		val EPackage p = c.EPackage
		return EcoreCraftingUtil.getBaseFQN(p) + "." + p.name.toFirstUpper + "Factory.eINSTANCE.create" + c.name +
			"_ImplicitStep()"
	}

	public static def String stringGetter(EStructuralFeature f) {
		if (f instanceof EAttribute) {
			if (f.EAttributeType.name.equals("EBoolean")) {
				return "is" + f.name.toFirstUpper + "()"
			}
		}
		return "get" + f.name.toFirstUpper + "()"
	}

	public static def String stringFeatureID(EStructuralFeature feature, EClassifier containingClass,
		Set<GenPackage> refGenPackages) {
		val EPackage p = containingClass.EPackage
		val GenPackage gp = getGenPackage(p, refGenPackages)
		return getJavaFQN(p, refGenPackages) + "." + gp.prefix + "Package.eINSTANCE.get" +
			containingClass.name.toFirstUpper + "_" + feature.name.toFirstUpper + "().getFeatureID()";
	}

	public static def String stringClassifierID(EClassifier c, Set<GenPackage> refGenPackages) {
		val EPackage p = c.EPackage
		val GenPackage gp = getGenPackage(p, refGenPackages)
		return getJavaFQN(p, refGenPackages) + "." + gp.prefix + "Package.eINSTANCE.get" + c.name.toFirstUpper +
			"().getClassifierID()";
	}

	public static def String stringGetter(String s) {
		return "get" + s.toFirstUpper + "()"
	}

	public static def String stringSetter(EStructuralFeature f, String value, Set<GenPackage> genPackages) {
		// TODO find way to remove systematic cast
		return "set" + f.name.toFirstUpper + "( ( " + getJavaFQN(f.EType, genPackages) + " )" + value + ")"
	}

	public static def String stringSetter(EStructuralFeature f, String value) {
		return "set" + f.name.toFirstUpper + "(" + value + ")"
	}

	public static def String stringSetter(String f, String value) {
		return "set" + f.toFirstUpper + "(" + value + ")"
	}

}

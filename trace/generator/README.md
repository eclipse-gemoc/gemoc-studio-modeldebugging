# Multidimensional Domain-Specific Execution Trace Management Facilities Generation

## Description

This set of plugins handles the generation of multidimensional domain-specific trace management facilities for a given xDSML. 
Among others, we consider an xDSML to be composed of:

- An *abstract syntax*, defined using the Ecore language
- *Operational semantics*, defined using any language(s) and itself composed of:
    - the definition of an *execution extension* to the abstract syntax in order to define the execution state of conforming models
    - the definition of a *model transformation* that changes such state to perform an execution

The generation process is divided in three steps:

1. Because operation semantics can be defined in using any language(s), we first extract the execution extension into an intermediate representation in order to then generically process it with the generator. The intermediate representation is defined using the Ecore metamodel that can be found in the plugin `fr.inria.diverse.opsemanticsview.model`. An conforming model is composed of new `EStructuralFeature` added to classes of the abstract classes, of new `EClass` objects, and of transformation rules specified as `EOperation` objects. Two extractors are provided for now:

    - One for Kermeta in the plugin `fr.inria.diverse.trace.plaink3.tracematerialextractor`.
    - One for xMOF in the plugin `fr.inria.diverse.trace.xmof.tracematerialextractor`.

2. A generic generator is called to process the intermediate representation and to produce an Ecore multidimensional domain-specific trace metamodel. The generator can be found in the plugin `fr.inria.diverse.trace.metamodel.generator`.

3. A GEMOC engine addon generator is called, to process the intermediate representation, the generated trace metamodel and some traceability links in order to produce a trace management plugin that contains the trace metamodel and a trace manager (in Java). The *state manager* provides services to construct a trace, and to restore the executed model into a former state in order to create a Java class to integrate the trace plugin into a GEMOC engine addon. Contrary to the previous steps, this last step is specific and dependent to the GEMOC Studio plugins. The generator can be found in `fr.inria.diverse.trace.gemoc.generator`.

## Publication

This part of the GEMOC Studio is based on the paper "A Generative Approach to Define Rich Domain-Specific Trace Metamodels" presented at ECMFA'15, written by Erwan Bousse, Tanja Mayerhofer, Benoit Combemale and Benoit Baudry.

**Abstract:**

> Executable Domain-Specific Modeling Languages (xDSMLs) open many possibilities for performing early verification and validation (V&V) of systems. Dynamic V&V approaches rely on execution traces, which represent the evolution of models during their execution. In order to construct traces, generic trace metamodels can be used. Yet, regarding trace manipulations, they lack both efficiency because of their sequential structure, and usability because of their gap to the xDSML. Our contribution is a generative approach that defines a rich and domain-specific trace metamodel enabling the construction of execution traces for models conforming to a given xDSML. Efficiency is increased by providing a variety of navigation paths within traces, while usability is improved by narrowing the concepts of the trace metamodel to fit the considered xDSML. We evaluated our approach by generating a trace metamodel for fUML and using it for semantic differencing, which is an important V&V activity in the realm of model evolution. Results show a significant performance improvement and simplification of the semantic differencing rules as compared to the usage of a generic trace metamodel.

The paper can be downloaded here https://hal.inria.fr/hal-01154225

# ModelDebugging
Runtime and tools to bring execution, debug and simulation in your domain specific tooling built with Sirius and/or Eclipse Modeling Framework.

To have a better understanding of the features and services offered by this framework, you can read the following [post (Breathe life into your designer)](http://gemoc.org/breathe-life-into-your-designer/).

The repository includes several components:
- ___simulationmodelanimation___ The plugins required to animate models with Sirius
- ___framework___ contains the components used to implement execution engines with model debugging support. The framework is two folds: 
 - *xdsml_framework* The plugins for the language workbench (ie. plugins that provides support for designing xdsml languages using a given engine). 
 - *execution_framework* The plugins for the modeling workbench (ie. the engine runtime for executing models).
- ___trace___ The plugins dedicated to execution traces. It offers two traces implementations that cans be used by the framework:
 - a simple generic trace metamodel 
 - an efficient trace metamodel powered by a generative approach. This trace enables all omniscient debugging capabilities of the framework and offers a multidimentional timeline.
- ___java_execution___ contains an implementation of the framework using a sequential java engine (based on xtend+k3). 

This project is included within the [GEMOC](http://gemoc.org/ "GEMOC Homepage"). This project offers an Eclipse package ([GEMOC Studio](http://gemoc.org/studio-download/)) with all these components preinstalled. It also provides additionnal engines (concurrent engine, coordination of concurrent engines), documentation and tutorials.



This project is still in incubation and need more work to reach industrial grade. If you need some help to get started with this project or have any questions about it feel free to contact [Cédric Brun](mailto:cedric.brun@obeo.fr).

# Development
- Continuous integration on Travis [![Build Status](https://travis-ci.org/SiriusLab/ModelDebugging.svg?branch=master)](https://travis-ci.org/SiriusLab/ModelDebugging)
- Javadoc will come soon

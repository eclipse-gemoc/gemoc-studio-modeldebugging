

## Model Execution Trace Protocol (METP)

This folder contains the definition of the Model Execution Trace Protocol (METP) expressed using a json schema.

A generator is provided. It generates a node module (in typescript) corresponding to the   



## Useful commands


* `npm run compile` compiles the generator (in out folder)

* `npm run  watch` continuously compiles the generator (useful in dev)

* `npm run  generate_ts` calls the generator to generate the typescript code from the `ModelExecutionTraceProtocol.json` into `../metp_protocol/src/ModelExecutionTraceProtocol.ts`
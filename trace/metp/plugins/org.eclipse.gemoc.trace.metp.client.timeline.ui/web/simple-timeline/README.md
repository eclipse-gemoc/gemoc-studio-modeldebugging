

## Web app that offers a simple time line in the browser supporting the METP protocol


## Useful commands




* `npm run  webpack` creates the web bundle.js and copies the static resources (in dist folder)


* `npm run  watch` continuously update the bundle.js (useful in dev)


Tip: as it uses modelexecutiontraceprotocol module that is defined in `../../../../webapps/metp_protocol`, the `npm link` command might be useful too when developping:
```
cd ../../../../webapps/metp_protocol
npm link
cd ../../plugins/org.eclipse.gemoc.trace.metp.client.timeline.ui/web/simple-timeline
npm link modelexecutiontraceprotocol
``` 
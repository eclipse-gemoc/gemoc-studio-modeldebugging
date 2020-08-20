// simplified way to resolve internal references in the json

/**
 * Single resource resolver 
 * allows to complete the built objects with "ref" pointing to internal cross references
 */
export class EMFJSONsingleResourceResolver {
    
	// the json representation of the considered resource
	private  jsonResourceString : string;
	
	private index : Map<string, any>;
	
	constructor(jsonResourceString : string) {
		this.jsonResourceString = jsonResourceString;
		this.index = new Map<string, any>();
	}

	/**
	 * parse and resolve internal cross references
	 */
	load() : any {
		const rawData = JSON.parse(this.jsonResourceString);
		this.buildIndex(rawData);
		console.table(this.index);
		this.resolveInternalRef(rawData);
		return rawData;
	}
	
	/**
	 * traverse the data to retreive all '_id' used by '$ref'
	 */
	buildIndex(rawData : any ) {
		if(rawData._id) {
			this.index.set(rawData._id, rawData);
		}
		// traverse the data
		Object.keys(rawData).forEach(key=> {
			if(typeof rawData[key] === 'object') {
				this.buildIndex(rawData[key]);
			}
		
        });
	}
	
	
	resolveInternalRef(rawData : any ) {
		if(rawData.$ref) {
			const resolvedRef = this.index.get(rawData.$ref);
			if(resolvedRef) {
				rawData.ref = resolvedRef;	
			}
		}
		// traverse the data
		Object.keys(rawData).forEach(key=> {
			// takes care to not traverse the newly added ref
			if(typeof rawData[key] === 'object' && !(rawData.$ref && rawData.ref )) {
				this.resolveInternalRef(rawData[key]);
			}
		
        });
	}
}
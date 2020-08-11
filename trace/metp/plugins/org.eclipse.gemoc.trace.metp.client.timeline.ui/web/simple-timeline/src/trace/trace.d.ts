export interface gemocTraceObject {
	kind: string;
	data: gemocTraceData;
}

export interface gemocTraceData {
	kind: string;
	children: Array<gemocTraceStep>;
}

export interface gemocTraceStep {
	kind: string;
	name: string;
}


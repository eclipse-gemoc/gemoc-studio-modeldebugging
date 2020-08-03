/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.commons.utils;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class ModelAwarePrintStream extends PrintStream {
	
	private PrintStream baseStream;
	private Thread modelExecutionThread;
	
	public ModelAwarePrintStream(OutputStream out, PrintStream baseStream) {
		super(out);
		this.baseStream = baseStream;
	}
	
	public void registerModelExecutionThread(Thread modelExecutionThread) {
		this.modelExecutionThread = modelExecutionThread;
	}

	@Override
	public void print(boolean b) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(b);
		} else {
			this.baseStream.print(b);
		}
	}

	@Override
	public void print(char c) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(c);
		} else {
			this.baseStream.print(c);
		}
	}

	@Override
	public void print(int i) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(i);
		} else {
			this.baseStream.print(i);
		}
	}

	@Override
	public void print(long l) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(l);
		} else {
			this.baseStream.print(l);
		}
	}

	@Override
	public void print(float f) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(f);
		} else {
			this.baseStream.print(f);
		}
	}

	@Override
	public void print(double d) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(d);
		} else {
			this.baseStream.print(d);
		}
	}

	@Override
	public void print(char[] s) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(s);
		} else {
			this.baseStream.print(s);
		}
	}

	@Override
	public void print(String s) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(s);
		} else {
			this.baseStream.print(s);
		}
	}

	@Override
	public void print(Object obj) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.print(obj);
		} else {
			this.baseStream.print(obj);
		}
	}

	@Override
	public void println() {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println();
		} else {
			this.baseStream.println();
		}
	}

	@Override
	public void println(boolean x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(char x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}
	
	@Override
	public void println(int x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(long x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(float x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(double x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(char[] x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(String x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public void println(Object x) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			super.println(x);
		} else {
			this.baseStream.println(x);
		}
	}

	@Override
	public PrintStream printf(String format, Object... args) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			return super.printf(format, args);
		} else {
			return this.baseStream.printf(format, args);
		}
	}

	@Override
	public PrintStream printf(Locale l, String format, Object... args) {
		if (Thread.currentThread() == this.modelExecutionThread) {
			return super.printf(l, format, args);
		} else {
			return this.baseStream.printf(l, format, args);
		}
	}
	
	

}

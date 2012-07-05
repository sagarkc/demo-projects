package com.gmail.sabuj.career.common.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class ReverseOutputStreamWriter extends OutputStreamWriter{

	
	public ReverseOutputStreamWriter(OutputStream out, Charset cs) {
		super(out, cs);
	}

	public ReverseOutputStreamWriter(OutputStream out, CharsetEncoder enc) {
		super(out, enc);
	}

	public ReverseOutputStreamWriter(OutputStream out, String charsetName)
			throws UnsupportedEncodingException {
		super(out, charsetName);
	}

	public ReverseOutputStreamWriter(OutputStream out) {
		super(out);
	}

	@Override
	public void write(String str) throws IOException {
		append(str, 0, str.length());
	}
	
}

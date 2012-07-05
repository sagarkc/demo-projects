package com.gmail.sabuj.career.batch.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.Resource;

public class LocalResource implements Resource {

	private String location;
	private File file;
	
	public LocalResource(String location) throws FileNotFoundException {
		this.location = location;
		File file = new File(location);
		if(!file.exists()){
			throw new FileNotFoundException(location);
		}
		this.file = file;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

	@Override
	public Resource createRelative(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		return file.exists();
	}

	@Override
	public String getDescription() {
		return file.getAbsolutePath();
	}

	@Override
	public File getFile() throws IOException {
		return file;
	}

	@Override
	public String getFilename() {
		return file.getName();
	}

	@Override
	public URI getURI() throws IOException {
		return file.toURI();
	}

	@Override
	public URL getURL() throws IOException {
		return file.toURL();
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public boolean isReadable() {
		return file.canRead();
	}

	@Override
	public long lastModified() throws IOException {
		return file.lastModified();
	}

}

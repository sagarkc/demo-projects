/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.paradox.core.parser;

import com.gs.paradox.core.model.DataBlock;
import com.gs.paradox.core.model.Header;
import java.io.File;
import java.util.List;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ParadoxFileReader implements ParadoxReader{

	private final File dbFile;
	

	public ParadoxFileReader(File dbFile) {
		this.dbFile = dbFile;
	}

	public Header readHeader() {
		
	}

	public DataBlock readDataBlock(int blockNumber) {
		
	}

	public List<DataBlock> readDataBlocks() {
		
	}
	
	
}

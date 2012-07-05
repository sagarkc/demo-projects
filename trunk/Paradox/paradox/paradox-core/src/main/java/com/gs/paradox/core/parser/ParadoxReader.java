/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.paradox.core.parser;

import com.gs.paradox.core.model.DataBlock;
import com.gs.paradox.core.model.Header;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public interface ParadoxReader {

	Header readHeader();
	
	DataBlock readDataBlock(int blockNumber);
	
	List<DataBlock> readDataBlocks();
	
}

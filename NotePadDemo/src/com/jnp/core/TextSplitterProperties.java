package com.jnp.core;

/**
 *
 * @author sabuj.das
 */
public class TextSplitterProperties extends SplitterProperties{

    private int totalNumberOfParts = -1;
    private int maximumLinesPerPart = -1;
    

    public int getMaximumLinesPerPart() {
        return maximumLinesPerPart;
    }

    public void setMaximumLinesPerPart(int maximumLinesPerPart) {
        this.maximumLinesPerPart = maximumLinesPerPart;
    }


    public int getTotalNumberOfParts() {
        return totalNumberOfParts;
    }

    public void setTotalNumberOfParts(int totalNumberOfParts) {
        this.totalNumberOfParts = totalNumberOfParts;
    }

    @Override
    public boolean isValid() {
        boolean valid = super.isValid();
        
        return valid;
    }

    
}

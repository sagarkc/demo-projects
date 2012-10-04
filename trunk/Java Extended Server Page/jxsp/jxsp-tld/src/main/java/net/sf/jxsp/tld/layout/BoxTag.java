package net.sf.jxsp.tld.layout;

import net.sf.jxsp.tld.AbstractViewTag;

public class BoxTag extends AbstractViewTag {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 198648654876087L;
	private static final String VALUE_SEPERATOR = ",";
	private static final String PERCENT = "%";
	
	private int width;
	private int maxWidth;
	private int minWidth;
	private String percentWidth;
	
	private int height;
	private int maxHeight;
	private int minHeight;
	private String percentHeight;
	
	private String padding;
	private int paddingTop;
	private int paddingLeft;
	private int paddingBottom;
	private int paddingRight;
	
	private String margin;
	private int marginTop;
	private int marginLeft;
	private int marginBottom;
	private int marginRight;
	
	private String anchor;
	
	public BoxTag() {
		// TODO Auto-generated constructor stub
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public String getPercentWidth() {
		return percentWidth;
	}

	public void setPercentWidth(String percentWidth) {
		this.percentWidth = percentWidth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public String getPercentHeight() {
		return percentHeight;
	}

	public void setPercentHeight(String percentHeight) {
		this.percentHeight = percentHeight;
	}

	public String getPadding() {
		return padding;
	}

	public void setPadding(String padding) {
		this.padding = padding;
	}

	public int getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public int getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public int getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public int getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(int marginTop) {
		this.marginTop = marginTop;
	}

	public int getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	public int getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(int marginBottom) {
		this.marginBottom = marginBottom;
	}

	public int getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}
	
	
}

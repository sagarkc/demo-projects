/**
 * 
 */
package net.sf.jquery.tags.ui.flexigrid.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import net.sf.jquery.tags.common.AbstractViewTag;
import net.sf.jquery.tags.ui.flexigrid.model.Flexigrid;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class FlexigridViewTag extends AbstractViewTag implements
		DynamicAttributes {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8202656326565685659L;

	private Map<String, String> dynamicAttributes = new HashMap<String, String>();

	private String id;
	private String url = "#";
	private String dataType = "json";
	private String sortname = "";
	private String sortorder = "asc";
	private boolean usepager = false;
	private boolean singleSelect = false;
	private String title = "";
	private boolean useRowPerPage = false;
	private int rowPerPage = 10;
	private boolean showTableToggleBtn = false;
	private int width = 10;
	private int height = 10;
	
	private transient StringBuffer tagContent;
	private transient StringBuffer columnModelContent;
	private transient StringBuffer sortItemsContent;
	private transient Flexigrid flexigrid;
	
	/**
	 * 
	 */
	public FlexigridViewTag() {
		flexigrid = new Flexigrid();
		tagContent = new StringBuffer();
		columnModelContent = new StringBuffer();
		sortItemsContent = new StringBuffer();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public boolean isUsepager() {
		return usepager;
	}

	public void setUsepager(boolean usepager) {
		this.usepager = usepager;
	}

	public boolean isSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isUseRowPerPage() {
		return useRowPerPage;
	}

	public void setUseRowPerPage(boolean useRowPerPage) {
		this.useRowPerPage = useRowPerPage;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public boolean isShowTableToggleBtn() {
		return showTableToggleBtn;
	}

	public void setShowTableToggleBtn(boolean showTableToggleBtn) {
		this.showTableToggleBtn = showTableToggleBtn;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public StringBuffer getTagContent() {
		return tagContent;
	}

	public void setTagContent(StringBuffer tagContent) {
		this.tagContent = tagContent;
	}

	public Flexigrid getFlexigrid() {
		return flexigrid;
	}


	public StringBuffer getColumnModelContent() {
		return columnModelContent;
	}


	public StringBuffer getSortItemsContent() {
		return sortItemsContent;
	}


	public void setDynamicAttribute(String arg0, String key, Object value)
			throws JspException {
		dynamicAttributes.put(key.toLowerCase(), String.valueOf(value));
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(
					new StringBuffer("<table id=\"")
						.append(id)
						.append("\" style=\"display: none\"></table>\n")
						.append("<script type=\"text/javascript\">")
						.toString());
			
			tagContent.append("$('#"+id+"').flexigrid(\n")
				.append("{\n\t")
				.append("url: '").append(url).append("' ,\n\t")
				.append("dataType: '").append(dataType).append("' ,\n\t")
				.append("sortname: '").append(sortname).append("' ,\n\t")
				.append("sortorder: '").append(sortorder).append("' ,\n\t")
				.append("usepager: ").append(usepager).append(" ,\n\t")
				.append("singleSelect: ").append(singleSelect).append(" ,\n\t")
				.append("title: '").append(title).append("' ,\n\t")
				.append("useRp: ").append(useRowPerPage).append(" ,\n\t")
				.append("rp: ").append(rowPerPage).append(" ,\n\t")
				.append("showTableToggleBtn: ").append(showTableToggleBtn).append(" ,\n\t")
				.append("width: ").append(width).append(" ,\n\t")
				.append("height: ").append(height);
			
		} catch (IOException e) {
			pageContext.getServletContext().log(e.getLocalizedMessage(), e);
		}

		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			tagContent.append(" ,\n\t")
				.append("colModel : [\n\t\t")
				.append(columnModelContent.substring(0, columnModelContent.length()-1))
				.append("\n\t],");
			tagContent.append("\n\t")
				.append("searchitems : [\n\t\t")
				.append(sortItemsContent.substring(0, sortItemsContent.length()-1))
				.append("\n\t]");
			tagContent.append("});");
			pageContext.getOut().write(tagContent.toString());
			pageContext.getOut().write("</script>");
		} catch (IOException e) {
			pageContext.getServletContext().log(e.getLocalizedMessage(), e);
		}
		return EVAL_PAGE;
	}
}

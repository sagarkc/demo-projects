package com.mercuria.etl.mgr.web.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.mercuria.etl.mgr.web.client.i18n.ApplicationMessages;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EtlManager implements EntryPoint {

	private static Logger logger = Logger.getLogger(EtlManager.class.getName());
	
	public static final ApplicationMessages MESSAGES = GWT
			.create(ApplicationMessages.class);

	
	private TabSet mainTabSet;
    private SideNavTree sideNav;
    
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		logger.info("In EtlManager.onModuleLoad()");
		// get rid of scroll bars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area
	    Window.enableScrolling(false);
	    Window.setMargin("0px");
	    Cookies.setCookie("skin_name_2_4", "EnterpriseBlue");
	    
		AppController appViewer = new AppController();
	    appViewer.go(RootLayoutPanel.get());
	    
	    /*final String initToken = History.getToken();

        //setup overall layout / viewport
        VLayout main = new VLayout() {
            {
                setID("isc_Showcase_1_0");
            }

            
        };

        ToolStrip topBar = new ToolStrip();
        topBar.setHeight(33);
        topBar.setWidth100();
        topBar.addMember(new Label());
        
        main.addMember(topBar);
        
        
        HLayout hLayout = new HLayout();
        hLayout.setLayoutMargin(5);
        hLayout.setWidth100();
        hLayout.setHeight100();
        
        VLayout sideNavLayout = new VLayout();
        sideNavLayout.setHeight100();
        sideNavLayout.setWidth(185);
        sideNavLayout.setShowResizeBar(true);
        sideNav = new SideNavTree();
        sideNav.setID("isc_SideNavTree_0");
        
        sideNavLayout.addMember(sideNav);
        
        ToolStrip toolStripVersion = new ToolStrip();
        toolStripVersion.setWidth100();
        Label version = new Label("Version: ");
        version.setWidth100();
        version.setPadding(5);
        toolStripVersion.addMember(version);
        sideNavLayout.addMember(toolStripVersion);
        
        hLayout.addMember(sideNavLayout);
        
        
        Canvas canvas = new Canvas();
        canvas.setBackgroundColor("blue");
        canvas.setWidth100();
        canvas.setHeight100();
        canvas.addChild(mainTabSet);

        hLayout.addMember(canvas);
        main.addMember(hLayout);
        
        RootPanel.get().add(main);*/
        
	}
}

class SideNavTree extends TreeGrid {

    private String idSuffix = "";


    public SideNavTree() {
        setWidth100();
        setHeight100();
        setSelectionType(SelectionStyle.SINGLE);
        setCustomIconProperty("icon");
        setAnimateFolderTime(100);
        setAnimateFolders(true);
        setAnimateFolderSpeed(1000);
        setNodeIcon("silk/application_view_list.png");
        setShowSortArrow(SortArrow.CORNER);
        setShowAllRecords(true);
        setLoadDataOnDemand(false);
        setCanSort(false);
        
        TreeGridField field = new TreeGridField();
        field.setCanFilter(true);
        field.setName("nodeTitle");
        field.setTitle("<b>Samples</b>");
        setFields(field);

        Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setNameProperty("nodeTitle");
        tree.setOpenProperty("isOpen");
        tree.setIdField("nodeID");
        tree.setParentIdField("parentNodeID");
        tree.setRootValue("root" + idSuffix);


        setData(tree);
    }

}

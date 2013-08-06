/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.view.HistoryJobMonitorView
 * Date:	Aug 5, 2013  8:12:46 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.view;

import com.mercuria.etl.mgr.web.client.EtlManager;
import com.mercuria.etl.mgr.web.client.core.UIEventManager;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class HistoryJobMonitorView extends VLayout {

	private static UIEventManager uiEventManager = UIEventManager.getInstance();
	
	private final ToolStripButton refreshButton = new ToolStripButton("Refresh");
	private final JobExecutionHistoryGrid jobMonitorHistoryGrid 
		= new JobExecutionHistoryGrid();
	private final JobHistoryFilterView jobHistoryFilterView = new JobHistoryFilterView(); 
	private final CheckboxItem showFilterCheckboxItem = new CheckboxItem();
	private final ToolStrip jobHistoryToolStrip = new ToolStrip();
	
	public HistoryJobMonitorView() {
		setStyleName("job-monitor-realTime");
		setWidth100();
		setHeight100();
		setLayoutMargin(2);
		initComponents();
	}

	private void initComponents() {
		HLayout header = new HLayout();
		header.setWidth100();
		header.setHeight(25);
		  
        jobHistoryToolStrip.setWidth100();
        jobHistoryToolStrip.setHeight(25);
        
        Img image = new Img();
        image.setSrc("monitor/job/history-24x24.png");
        image.setWidth(16);
        image.setHeight(16);
        image.setShowRollOver(false);
        image.setShowDownIcon(false);
        image.setShowDown(false);
        jobHistoryToolStrip.addMember(image);
        
        Label titleLabel = new Label(EtlManager.MESSAGES.getTitleMonitorJobHistoricalData());
        titleLabel.setStyleName("toolbar-title");
        titleLabel.setWidth(250);
        jobHistoryToolStrip.addMember(titleLabel);
        jobHistoryToolStrip.addSeparator();
        jobHistoryToolStrip.addFill();
        Menu historyMenu = new Menu();  
        
        historyMenu.setShowShadow(true);  
        historyMenu.setShadowDepth(10);  
  
        MenuItem newFilterItem = new MenuItem("New Filter", "icons/16/document_plain_new.png", "Ctrl+N");  
  
        MenuItem openFilterItem = new MenuItem("Open Filter", "icons/16/folder_out.png", "Ctrl+O");  
  
        MenuItem saveFilterItem = new MenuItem("Save Filter", "icons/16/disk_blue.png", "Ctrl+S");  
        MenuItem saveFilterAsItem = new MenuItem("Save Filter As", "icons/16/save_as.png");  
  
        MenuItem recentFiltersItem = new MenuItem("Saved Filters", "icons/16/folder_document.png");  
  
        Menu recentDocSubMenu = new Menu();  
        MenuItem dataSM = new MenuItem("data.xml");  
        dataSM.setChecked(true);  
        MenuItem componentSM = new MenuItem("Component Guide.doc");  
        MenuItem ajaxSM = new MenuItem("AJAX.doc");  
        recentDocSubMenu.setItems(dataSM, componentSM, ajaxSM);  
  
        recentFiltersItem.setSubmenu(recentDocSubMenu);  
  
        MenuItem exportItem = new MenuItem("Export Dara as...", "icons/16/export1.png");  
        Menu exportSM = new Menu();  
        exportSM.setItems(  
                new MenuItem("XML"),  
                new MenuItem("CSV"),  
                new MenuItem("Plain text"));  
        exportItem.setSubmenu(exportSM);  
  
          
        MenuItemSeparator separator = new MenuItemSeparator();  
        
        historyMenu.setItems(newFilterItem, openFilterItem, separator, saveFilterItem, saveFilterAsItem,  
                separator, recentFiltersItem, separator, exportItem);  
  
        
        MenuButton menuButton = new MenuButton("Actions", historyMenu);
        menuButton.setWidth(120);
        
        jobHistoryToolStrip.addMember(menuButton);
        
        jobHistoryToolStrip.addSeparator();
       
        
        jobHistoryToolStrip.addButton(refreshButton);
        
		showFilterCheckboxItem.setTitle("Show Filter Section");
		showFilterCheckboxItem.setValue(false);
		showFilterCheckboxItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				if((Boolean)event.getValue() ){
					jobHistoryFilterView.animateShow(AnimationEffect.SLIDE);
				} else {
					jobHistoryFilterView.animateHide(AnimationEffect.SLIDE);
				}
			}
		});
		
		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setItems(showFilterCheckboxItem);
		jobHistoryToolStrip.addMember(dynamicForm);
		
		
		header.addMember(jobHistoryToolStrip);
		
		addMember(header);
		jobHistoryFilterView.hide();
		addMember(jobHistoryFilterView);
		
		jobMonitorHistoryGrid.setWidth100();  
		jobMonitorHistoryGrid.setHeight100();  
		
		
		addMember(jobMonitorHistoryGrid);  
  
        
	}

	public JobExecutionHistoryGrid getJobMonitorHistoryGrid() {
		return jobMonitorHistoryGrid;
	}

	
	public JobHistoryFilterView getJobHistoryFilterView() {
		return jobHistoryFilterView;
	}
	
	
}
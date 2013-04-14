
/* Home page Tabs */
$(function(){

	$('#home-tabs').tabs('option', 'selected', 1);
	
	$('#home-tabs').tabs();
	
	$('#login-button').button();
	
	$('#register-button').button();
	
	// Login Dialog
	$('#login-dialog').dialog({
		autoOpen: false,
		width: 400,
		buttons: {
			"Login": function() {
				$(this).dialog("close");
			},
			"Cancel": function() {
				$(this).dialog("close");
			}
		}
	});
	
	$('#left-nav-menu').menu();
	
	
	
	
	
});
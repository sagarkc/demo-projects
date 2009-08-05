package com.gs.fx.control
{
	import mx.controls.TextInput;
	import flash.events.Event;
	import mx.controls.Alert;

	[Event(name="change", type="flash.events.Event")]
	[Event(name="keyUp", type="flash.events.KeyboardEvent")]
	[Event(name="keyDown", type="flash.events.KeyboardEvent")]
	
	public class NumberInput extends TextInput
	{
		
		public var afterDecimal:Number;
		public var beforeDecimal:Number;
		public var acceptNegetive:Boolean = false;
		
		public function NumberInput(){
			super();
		    this.addEventListener("change", change);
			this.addEventListener("keyUp", keyUpHandler1);
			this.addEventListener("keyDown", keyDownHandler1);
			this.addEventListener("focusOut", focusOut);
			this.restrict = "[\\- 0-9.0-9]";
		}
		
		public var oldVal = "";
		
		public function restrictionPress(bd:Number,ad:Number):void{
			var strPass = this.text;
 			if ( validateNumberField( strPass, bd, ad ) == 'true' ){
 	    		oldVal = strPass;
 			}
 		}
	
		public function restrictionUp(bd:Number,ad:Number):void{
			var strPass = this.text;
			var retVal =  validateNumberField( strPass, bd, ad);
			if (retVal == false){
	        	this.text = oldVal;
	   		}else {
	       		oldVal = strPass;
	    	}
		}
	
		public function restrictionfocusOut(bd:Number,ad:Number):void {
			var strPass:String = this.text;
			var retVal =  validateNumberField( strPass, bd, ad)
			if (retVal == false) {
				this.text = oldVal;
			} else {
				oldVal = strPass;
			}
			if(oldVal == "."){
				oldVal = "";
				this.text = oldVal;
			}
		}
	
		public function restrictionDown(bd:Number,ad:Number):void{
			var strPass:String = this.text;
	        var retVal =  validateNumberField( strPass, bd, ad)
	      	if (retVal == true)   {
	          oldVal = strPass;
	      	}
		}

		function  validateNumberField( strValue, integerPart, decimalPart) {
			var REMoneyControlsString:RegExp;
		     if(acceptNegetive){
		     	if(decimalPart == 0){
		        	REMoneyControlsString  =  
		        	new RegExp("(^\\-\\d{0," + integerPart + "}$)|(^\\d{0,"+ integerPart + "}$)");     	
		     	}else{
		        	REMoneyControlsString  =  
		        	new RegExp("((^\\-\\d{0," + integerPart + "}\\.\\d{0," + decimalPart +"}$)|(^\\-\\d{0,"+ integerPart + "}$)|(^\\-\\.\\d{1,"+ decimalPart + "}$))"+
 						"|((^\\d{0," + integerPart + "}\\.\\d{0," + decimalPart +"}$)|(^\\d{0,"+ integerPart + "}$)|(^\\.\\d{1,"+ decimalPart + "}$))");     	
		     	}
		     }else{
		     	if(decimalPart == 0){
		        	REMoneyControlsString  =  new RegExp("(^\\d{0," + integerPart + "}$)|(^\\d{0,"+ integerPart + "}$)");     	
		     	}else{
		        	REMoneyControlsString  =  new RegExp("(^\\d{0," + integerPart + "}\\.\\d{0," + decimalPart +"}$)|(^\\d{0,"+ integerPart + "}$)|(^\\.\\d{1,"+ decimalPart + "}$)");     	
		     	}
		     }
     		var retVal =  REMoneyControlsString.test(strValue) || (strValue == '');
     		return retVal;
  		}
	
		
		public function change(event:Event):void {
			restrictionPress(beforeDecimal,afterDecimal);
		}
		
		public function focusOut(event:Event):void {
			restrictionfocusOut(beforeDecimal,afterDecimal);
		}
		
		public function keyUpHandler1(event:Event):void {
			restrictionUp(beforeDecimal,afterDecimal);
		}
		
		public function keyDownHandler1(event:Event):void {
			restrictionDown(beforeDecimal,afterDecimal);
		}
	
	}
}
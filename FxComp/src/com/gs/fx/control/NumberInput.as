package com.gs.fx.control
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	
	import mx.controls.TextInput;
	import mx.formatters.NumberFormatter;

	[Event(name="change", type="flash.events.Event")]
	[Event(name="keyUp", type="flash.events.KeyboardEvent")]
	[Event(name="keyDown", type="flash.events.KeyboardEvent")]
	[Event(name="valueChanged", type="flash.events.Event")]
	
	public class NumberInput extends TextInput
	{
		
		public const MONEY_FORMAT:NumberFormatter = new NumberFormatter();
		
		public var afterDecimal:Number = 2;
		public var beforeDecimal:Number = 9;
		public var acceptNegetive:Boolean = false;
		
		
		private var _numberValue:Number;
		private var oldVal = "";
		
		public function NumberInput(){
			super();
			this.addEventListener("change", change);
			this.addEventListener("keyUp", keyUpHandler1);
			this.addEventListener("keyDown", keyDownHandler1);
			this.addEventListener("focusOut", focusOut);
			this.addEventListener("valueChanged", valueChanged);
			this.restrict = "[\\- \\, 0-9.0-9]";
		}
		
		override public function set text(value:String):void
    	{
    		super.text = value;
    		numberValue = new Number(value);
    	}
		
		public function set numberValue(number:Number):void{
			if(!isNaN(number)){
				_numberValue = number;
			}else{
				_numberValue = 0;
			}
			//this.text = _numberValue.toString();
		}
		
		public function get numberValue():Number{
			return _numberValue;
		}
		
		public function restrictionPress(bd:Number,ad:Number):void{
			var strPass = this.text;
 			if ( validateNumberField( strPass, bd, ad ) == 'true' ){
 	    		oldVal = strPass;
 			}
 			numberValue = new Number(oldVal);
     		dispatchEvent(new flash.events.Event("valueChanged"));
 		}
	
		public function restrictionUp(bd:Number,ad:Number):void{
			var strPass = this.text;
			var retVal =  validateNumberField( strPass, bd, ad);
			if (retVal == false){
	        	this.text = oldVal;
	   		}else {
	       		oldVal = strPass;
	    	}
	    	numberValue = new Number(oldVal);
     		dispatchEvent(new flash.events.Event("valueChanged"));
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
			numberValue = new Number(oldVal);
     		dispatchEvent(new flash.events.Event("valueChanged"));
		}
	
		public function restrictionDown(bd:Number,ad:Number):void{
			var strPass:String = this.text;
	        var retVal =  validateNumberField( strPass, bd, ad)
	      	if (retVal == true)   {
	          oldVal = strPass;
	      	}
	      	numberValue = new Number(oldVal);
     		dispatchEvent(new flash.events.Event("valueChanged"));
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
	
		public function valueChanged(event:Event):void {
			
		}
	}
}
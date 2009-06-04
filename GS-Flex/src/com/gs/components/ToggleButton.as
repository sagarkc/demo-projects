package com.gs.components
{
	  import flash.events.Event; 
  import flash.events.MouseEvent; 
   
  import mx.controls.Button; 
  [Style(name="toggledColor", type="uint", format="Color", inherit="no")] 
  [Style(name="toggledTextRollOverColor", type="uint", format="Color", inherit="no")] 
  [Style(name="toggledTextSelectedColor", type="uint", format="Color", inherit="no")] 
   
  public class ToggleButton extends Button 
  { 
  	private var defaultColor:uint; 
  	private var defaultRoll:uint; 
  	private var defaultSelected:uint; 
  	private var toggledColor:uint; 
  	private var toggledRoll:uint; 
  	private var toggledSelected:uint; 
  
  	public function ToggleButton() 
  	{ 
    	super(); 
    	addEventListener('creationComplete',init); 
  	} 
    
  	private function init(e:Event):void{ 
	    defaultColor=getStyle('color'); 
	    defaultRoll=getStyle('textRollOverColor'); 
	    defaultSelected=getStyle('textSelectedColor'); 
	    toggledColor=getStyle('toggledColor'); 
	    toggledRoll=getStyle('toggledTextRollOverColor'); 
	    toggledSelected=getStyle('toggledTextSelectedColor'); 
  	} 
    
      override protected function clickHandler(e:MouseEvent):void 
      { 
      if(!selected){ 
      	setStyle('color',toggledColor); 
      	setStyle('textRollOverColor',toggledRoll); 
      	setStyle('textSelectedColor',toggledSelected); 
      }  
      else{  
      	setStyle('color',defaultColor); 
      	setStyle('textRollOverColor',defaultRoll); 
      	setStyle('textSelectedColor',defaultSelected); 
      } 
         super.clickHandler(e);         
      } 
      
  } 
}
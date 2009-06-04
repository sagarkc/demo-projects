package com.gs.components
{
	import com.adobe.cairngorm.business.Responder;
	
	import flash.net.URLLoader;
	
	import mx.controls.Button;
	import mx.rpc.http.HTTPService;

	public class ButtonWithIcon extends Button implements Responder
	{
		private var urlLoader:URLLoader;
		
		public var imageUrl:String;
		
		public function ButtonWithIcon()
		{
			super();
			/* var req:URLRequest = new URLRequest("http://www.destinygrp.com/destiny/ldgimages/top-login.gif"); 
			req.contentType = "image";
			req.method = "GET";
			urlLoader = new URLLoader();
			urlLoader.load(req);
			urlLoader. */
			//var obj:Object = urlLoader.valueOf();
			var service:HTTPService = new HTTPService("http://www.destinygrp.com/destiny/ldgimages/top-login.gif",
				"http://www.destinygrp.com/destiny/ldgimages/");
			service.addEventListener("TYPE", onResult);
			service.send();
		}
		
		
		public function onResult(event:*=null):void
		{
			var obj:Object = event.data;
		}
		
		public function onFault(event:*=null):void
		{
			
		}
		
	}
}
package com.gs.delegate
{
	import com.adobe.cairngorm.business.Responder;
	
	import mx.rpc.http.HTTPService;

	public class GSMainDelegate implements Responder
	{
		protected var responder:Responder;
		protected var service:HTTPService;
		
		public function GSMainDelegate()
		{
		}

		public function onResult(event:Object=null):void
		{
		}
		
		public function onFault(event:Object=null):void
		{
		}
		
	}
}
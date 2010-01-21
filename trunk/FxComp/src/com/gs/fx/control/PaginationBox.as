package com.gs.fx.control
{
	import com.gs.fx.vo.PaginationVO;
	
	import mx.containers.HBox;
	import mx.containers.VBox;
	import mx.controls.Label;
	import mx.controls.LinkButton;

	public class PaginationBox extends VBox
	{
		
		private var _prevLink:LinkButton;
		private var _nextLink:LinkButton;
		private var _recordCountLabel:Label;
		private var _paginationVO:PaginationVO;
		
		public function PaginationBox()
		{
			super();
			initComponents();
		}
		
		public override function set data(value:Object):void
		{
			if(value == null){
				return;
			}
			super.data = value;
			if(value is PaginationVO){
				_paginationVO = value as PaginationVO;
				_prevLink.enabled = _paginationVO.hasPreviousPage;
				_nextLink.enabled = _paginationVO.hasNextPage;
			}
		}
		
		public function set paginationVO(page:PaginationVO):void{
			if(page == null){
				return;
			}
			_paginationVO = page;
			_prevLink.enabled = page.hasPreviousPage;
			_nextLink.enabled = page.hasNextPage;
		}
		
		private function initComponents():void{
			var linkBox:HBox = new HBox();
			linkBox.setStyle("horizontalAlign", "middle");
			linkBox.setStyle("verticalAlign", "right");
			linkBox.setStyle("horizontalGap", 5);
			
			_prevLink = new LinkButton();
			_prevLink.label = "<< Prev";
			// TODO: add the event
			
			linkBox.addChild(_prevLink);
			
			_recordCountLabel = new Label();
			_recordCountLabel.text = "Records";
			linkBox.addChild(_recordCountLabel);
			
			_nextLink = new LinkButton();
			_nextLink.label = "Next >>";
			// TODO: add the event
			
			linkBox.addChild(_nextLink);
			
			
			
			
			addChild(linkBox);
		}
	}
}
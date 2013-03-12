package com.duckduckgo.mobile.android.util.menuItems;

import com.duckduckgo.mobile.android.DDGApplication;
import com.duckduckgo.mobile.android.R;
import com.duckduckgo.mobile.android.activity.DuckDuckGo;
import com.duckduckgo.mobile.android.util.Action;
import com.duckduckgo.mobile.android.util.Item;
import com.duckduckgo.mobile.android.util.Item.ItemType;

public class DeleteFeedInHistoryMenuItem extends Item {
	private DuckDuckGo context;
	private String feedObjectId;

	public DeleteFeedInHistoryMenuItem(DuckDuckGo context, String feedObjectId){
		super(context.getResources().getString(R.string.Delete), android.R.drawable.ic_menu_close_clear_cancel, ItemType.DELETE);
		this.context = context;
		this.feedObjectId = feedObjectId;
		
		ActionToExecute = getActionToExecute();
	}
	
	public Action getActionToExecute() {
		return new Action() {
			@Override
			public void Execute() {
				final long delResult = DDGApplication.getDB().deleteHistoryByFeedId(feedObjectId);
				if(delResult != 0) {							
					context.syncAdapters();
				}
			};
		};
	}
}

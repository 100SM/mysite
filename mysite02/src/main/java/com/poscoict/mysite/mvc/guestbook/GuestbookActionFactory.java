package com.poscoict.mysite.mvc.guestbook;

import com.poscoict.mysite.mvc.user.UpdateAction;
import com.poscoict.mysite.mvc.user.UpdateFormAction;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else if ("update".equals(actionName)) {
			action = new UpdateAction();
		} else if ("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		} else {
			action = new IndexAction();
		}

		return action;
	}
}
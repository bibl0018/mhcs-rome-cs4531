package mhcs.client;

import com.google.gwt.event.shared.GwtEvent;

public class AddEvent extends GwtEvent<AddEventHandler> {

	public static Type<AddEventHandler> TYPE = new Type<AddEventHandler>();

	public AddEvent() {
		
	}
	
	@Override
	public Type<AddEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final AddEventHandler handler) {
		handler.onEvent(this);
	}
	
}

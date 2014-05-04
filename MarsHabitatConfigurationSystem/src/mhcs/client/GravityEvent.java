package mhcs.client;

import com.google.gwt.event.shared.GwtEvent;

public class GravityEvent extends GwtEvent<GravityEventHandler> {

	public static Type<GravityEventHandler> TYPE = new Type<GravityEventHandler>();
	public int xcoord;
	public int ycoord;
	
	public GravityEvent(final int xCoord, final int yCoord) {
		this.xcoord = xCoord;
		this.ycoord = yCoord;
	}
	
	@Override
	public Type<GravityEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final GravityEventHandler handler) {
		handler.onEvent(this);
	}
	
}

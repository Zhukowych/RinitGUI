package com.rinit.gui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class EventHandler implements IEventHandler {

	private Map<Event, List<IListener>> listeners = new HashMap<Event, List<IListener>>();
	
	private Mode keyMode = Mode.DEFAULT;
	private Map<Mode, Map<KeyStroke, List<IListener>>> listenersByModes = new HashMap<Mode, Map<KeyStroke, List<IListener>>>();
	
	public EventHandler() {
		for (Event event : Event.values()) {
			this.listeners.put(event, new ArrayList<IListener>());
		}
	}
	
	public void subscribe(IListener listener, Event event) {
		this.listeners.get(event).add(listener);
	}

	public void subscribeForKeyEvent(IListener listener, KeyStroke stroke) {
		for (Mode mode : Mode.values()) {
			this.subscribeForKeyEvent(listener, stroke, mode);
		}
	}
	
	public void subscribeForKeyEvent(IListener listener, KeyStroke stroke, Mode mode) {
		Map<KeyStroke, List<IListener>> listenersByKeys = this.listenersByModes.get(mode);
		if (listenersByKeys == null) {
			listenersByKeys = new HashMap<KeyStroke, List<IListener>>();
		}
		List<IListener> listeners = listenersByKeys.get(stroke);
		if (listeners != null) {
			listeners.add(listener);
		}else {
			listeners = new ArrayList<IListener>();
			listeners.add(listener);
			listenersByKeys.put(stroke, listeners);
			this.listenersByModes.put(mode, listenersByKeys);
		}
	}
	
	public void setKeyMode(Mode mode) {
		this.keyMode = mode;
	}

	public void performEvent(Event event, Object parent, IEventContext context) {
		for(IListener listener : this.listeners.get(event)) {
			listener.eventPerformed(context);
		}
	}

	public void bindKeyEvents(JRootPane jRootPane) { 
		Set<KeyStroke> strokes = this.getKeyStrokes();
		for(final KeyStroke stroke : strokes) {
			jRootPane.registerKeyboardAction(new ActionListener ()
			{
			    public void actionPerformed ( final ActionEvent e )
			    {
					for (Mode mode : listenersByModes.keySet()) {
						Map<KeyStroke, List<IListener>> listenersByStrokes = listenersByModes.get(mode); 
						List<IListener> listeners = listenersByStrokes.get(stroke);
						if(listeners == null) {
							continue;
						}
						for (IListener listener : listeners) {
							if (keyMode == mode) {
								listener.eventPerformed(null);
							}
						}
					}
			    }
			}, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW );
		}
	}
	
	public Set<KeyStroke> getKeyStrokes() {
		HashSet<KeyStroke> strokes = new HashSet<KeyStroke>();
		for (Mode mode : this.listenersByModes.keySet()) {
			Map<KeyStroke, List<IListener>> listeners = this.listenersByModes.get(mode); 
			for (KeyStroke stroke : listeners.keySet()) {
				strokes.add(stroke);
			}
		}
		return strokes;
	}
	
}

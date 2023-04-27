package com.mysite.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.replication.ReplicationAction;

@Component(service = EventHandler.class, property  = {EventConstants.EVENT_TOPIC+"=" + ReplicationAction.EVENT_TOPIC},
 immediate = true)
public class ArticleActivationEventHandler implements EventHandler {
	
public static final Logger LOG=LoggerFactory.getLogger(ArticleActivationEventHandler.class);
	
	public void handleEvent(Event event){
		LOG.info("Event handler method");
		for(String name : event.getPropertyNames()) {
			LOG.info("Property Name-{}, Property Value - {}", name,event.getProperty(name));
		}

}
}

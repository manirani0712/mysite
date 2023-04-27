package com.mysite.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.SlingConstants;

@Component(immediate = true, service = EventHandler.class, property = {
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
		EventConstants.EVENT_FILTER + "(&" + "(path=/content/myproject/us/en/*/jcr:content) (|("
				+ SlingConstants.PROPERTY_CHANGED_ATTRIBUTES + "=*jcr:title) " + "(" + ResourceChangeListener.CHANGES
				+ "=*jcr:title)))" })
public class RohitActivationEventHandler implements EventHandler {

	public static final Logger LOG = LoggerFactory.getLogger(RohitActivationEventHandler.class);

	@Override
	public void handleEvent(Event event) {
		LOG.info("Event handler method");
		for (String name : event.getPropertyNames()) {
			LOG.info("Property Name-{}, Property Value - {}", name, event.getProperty(name));
		}

	}
}

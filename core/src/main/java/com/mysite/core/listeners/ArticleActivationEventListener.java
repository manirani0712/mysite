package com.mysite.core.listeners;

import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
//import org.osgi.service.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=EventListener.class, immediate=true)
public class ArticleActivationEventListener implements EventListener {

	//@Reference
	//ResourceResolverFactory factory;
     //@Reference
	private Session session;
     
     @Reference
     SlingRepository slingrepo;
	public static final Logger LOG = LoggerFactory.getLogger(ArticleActivationEventListener.class);

	@Override
	public void onEvent(EventIterator events) {
		LOG.info("event inside onevent method");
	}

	@Activate
	public void activate() {
		LOG.info("event inside onevent method");
		//ResourceResolver resolver = null;

		//Map<String, Object> data = new HashMap<>();
		//data.put(ResourceResolverFactory.SUBSERVICE, "c");

		try {
			String[] nodeTypes = { "cq:PageContent" };
			//resolver = factory.getServiceResourceResolver(data);
			session=slingrepo.loginService("eventingService", null);
			ObservationManager ovsmgr = session.getWorkspace().getObservationManager();
			ovsmgr.addEventListener(this, Event.PROPERTY_ADDED | Event.NODE_ADDED, "/content/mysite",
					true, null, nodeTypes, true);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}

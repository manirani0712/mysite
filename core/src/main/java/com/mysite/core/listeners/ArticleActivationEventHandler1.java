package com.mysite.core.listeners;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;
import com.mysite.core.services.MyProjectUtilServices;

@Component(service = EventHandler.class,
 property  = {
	EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC
  // EventConstants.EVENT_FILTER+"= (& (type=ACTIVATE) (paths=/content/myheadache/us/en/*))"
},
immediate = true)
public class ArticleActivationEventHandler1 implements EventHandler {
	
public static final Logger LOG=LoggerFactory.getLogger(ArticleActivationEventHandler1.class);
	
@Reference
	MyProjectUtilServices mput;
	@Override
	public void handleEvent(Event event){
		LOG.info("Event handler method");
		
          String[] paths= (String[]) event.getProperty("paths"); 
		  ResourceResolver resolver=mput.getResourceResolver();
		  for(String path: paths){
			Resource res=resolver.getResource(path+"/jcr:content");
			ModifiableValueMap mpop=res.adaptTo(ModifiableValueMap.class);
			mpop.put("pagePublished", true);
			try{
				resolver.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		  }
		
		}

}


package com.mysite.core.services;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = MyProjectUtilServices.class, immediate = true)
public class MyProjectUtilServices {
	@Reference
	ResourceResolverFactory factory;

	public ResourceResolver getResourceResolver() {
		ResourceResolver resolver = null;
		try {
			Map<String, Object> data = new HashMap<>();
			data.put(ResourceResolverFactory.SUBSERVICE, "eventingService");
			resolver = factory.getServiceResourceResolver(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resolver;
	}
}

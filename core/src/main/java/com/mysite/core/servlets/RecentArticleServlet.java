package com.mysite.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, immediate = true)
@SlingServletResourceTypes(resourceTypes = { "mysite/components/page" }, methods = { "GET" }, extensions = { "json",
		"txt", "html" }, selectors = "recent")
// @SlingServletPaths("/bin/myproject/services/recent-article")
public class RecentArticleServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException, IOException {

		try {
			ResourceResolver res = request.getResourceResolver();
			Resource resource = res.getResource("/content/mysite/jcr:content");

			ModifiableValueMap mprop = resource.adaptTo(ModifiableValueMap.class);
			mprop.put("jcr:title", "myproject my headache");
			mprop.put("category", "sports");
			res.commit();

			response.setContentType("application/json");
			response.getWriter().write("Response from do post");

		} catch (Exception e) {

		}

	}

}

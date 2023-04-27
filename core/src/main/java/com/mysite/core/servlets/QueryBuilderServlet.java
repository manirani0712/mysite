package com.mysite.core.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class,
        property = {
        Constants.SERVICE_DESCRIPTION + "=Query Builder servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/aemtask/querybuilder"
})
public class QueryBuilderServlet extends SlingSafeMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(QueryBuilderServlet.class);
    
    @Reference
    private QueryBuilder builder;

    public Session session;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            log.info("----------< Executing Query Builder Servlet >----------");
            String param = request.getParameter("param");
            log.info("Search term is: {}", param);
            ResourceResolver resourceResolver = request.getResourceResolver();
            session = resourceResolver.adaptTo(Session.class);
            Map<String, String> predicate = new HashMap<>();

            predicate.put("path", "/content/dam");
            predicate.put("type", "dam:Asset");
            predicate.put("group.p.or", "true");
            predicate.put("group.1_fulltext", param);
            predicate.put("group.1_fulltext.relPath", "jcr:content");

            Query query = builder.createQuery(PredicateGroup.create(predicate), session);

            query.setStart(0);
            query.setHitsPerPage(20);

            SearchResult searchResult = query.getResult();
            for(Hit hit : searchResult.getHits()) {
                String path = hit.getPath();
                response.getWriter().println(path);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if(session != null) {
                session.logout();
            }
        }
    }
}

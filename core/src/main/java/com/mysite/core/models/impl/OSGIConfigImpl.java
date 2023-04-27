package com.mysite.core.models.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

import com.mysite.core.models.OSGIconfig;

@Component(service=OSGIconfig.class, immediate = true)
@Designate(ocd=OSGIConfigImpl.ServiceConfig.class)
public class OSGIConfigImpl implements OSGIconfig {

	@ObjectClassDefinition(name="SurgeSoftware OSGI Configuration")
	public @interface ServiceConfig{
		 
		@AttributeDefinition(
				name="Service Name",
				description="Enter the Service Name",
				type=AttributeType.STRING)
		public String getServiceName() default "Surge Service";
		
		@AttributeDefinition(
				name="Service Count",
				description="Count the service",
				type=AttributeType.INTEGER)
		public int getServiceCount() default 5;
		

		@AttributeDefinition(
				name="Live Data",
				description="Check this to get live data",
				type=AttributeType.BOOLEAN)
		public  boolean isLiveData() default true;
	
		
		@AttributeDefinition(
				name="Run Modes",
				description="Select the Run Modes",
				type=AttributeType.STRING,
				options= {
			     @Option(label="Author",value="author"),
			     @Option(label="Publish",value="publish"),	
		         @Option(label="Both",value="both")	
				})
		public String getRunModes() default "both" ;
		}
	
	private String serviceName;
	private int serviceCount;
	private boolean isLive;
	private String runModes;
	 
	@Activate
	protected void activate(ServiceConfig config)
	{
	serviceName=config.getServiceName();
	serviceCount=config.getServiceCount();
	isLive=config.isLiveData();
	runModes=config.getRunModes();
	}
	
	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public int getServiceCount() {
		return serviceCount;
	}

	@Override
	public boolean isLiveData() {
		return isLive;
	}

	@Override
	public String getRunModes() {
		return runModes;
	}
	

}

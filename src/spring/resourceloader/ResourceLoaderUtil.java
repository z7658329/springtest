package spring.resourceloader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderUtil implements ResourceLoaderAware{

	ResourceLoader resource;
	@Override
	public void setResourceLoader(
			ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		resource = resourceLoader;
		System.out.println("resourceLoader is init!!!!!!!!!!!!!!!!!!!!!");
	}
	public ResourceLoader getResource() {
		return resource;
	}
	public void setResource(ResourceLoader resource) {
		this.resource = resource;
	}
	
}

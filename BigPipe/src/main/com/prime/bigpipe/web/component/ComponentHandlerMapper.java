package com.prime.bigpipe.web.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

@Service
public class ComponentHandlerMapper implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	//also includes logic for A/B testing 
	public ComponentHandler findHandlerFor(String componentName) {
		return (ComponentHandler) applicationContext.getBean(componentName + "ComponentHandler");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}

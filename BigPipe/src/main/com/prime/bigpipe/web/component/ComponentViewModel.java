package com.prime.bigpipe.web.component;

import java.util.HashMap;
import java.util.Map;

public class ComponentViewModel {
	private String view;
	private Map<String,Object> model = new HashMap<String,Object>();

	public ComponentViewModel(String view) {
		validateView(view);
		
		this.view = view;
	}
	public ComponentViewModel() {
	
	}
	
	public String getView() {
		return view;
	}
	
	public void setView(String view) {
		validateView(view);
		this.view = view;
	}
	private void validateView(String view) {
		if (view.startsWith("components/")) {
			throw new IllegalArgumentException("Remove components directory from view. That is the default location");
		}
	}
	
	Map<String, Object> getModel() {
		return model;
	}
	
	public void put(String key,Object val) {
		model.put(key, val);
	}
	
	public void putAll(Map<String,Object> map) {
		model.putAll(map);
	}
}

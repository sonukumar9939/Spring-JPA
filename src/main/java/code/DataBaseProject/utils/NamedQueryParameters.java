package code.DataBaseProject.utils;

import java.util.Map;

public class NamedQueryParameters {
	
	private String queryName;
	
	private Map<String, Object> parameters;

	public NamedQueryParameters(String queryName) {
		super();
		this.queryName = queryName;
	}
	
	public void addParameter(String paramName, Object paramValue) {
		this.parameters.put(paramName, paramValue);
	}
	
	public void addAllParameters(Map<String,Object> params) {
		this.parameters.putAll(params);
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}

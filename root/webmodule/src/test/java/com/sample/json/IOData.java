package com.sample.json;

import java.util.Map;

public class IOData<I, O> {
	private I inputObject;
	private O outputObject;
	private String inputAsString;
	private String outputAsString;
	private Map inputAsMap;
	private Map outputAsMap;
	public IOData(I inputObject, O outputObject, String inputAsString,
			String outputAsString, Map inputAsMap, Map outputAsMap) {
		super();
		this.inputObject = inputObject;
		this.outputObject = outputObject;
		this.inputAsString = inputAsString;
		this.outputAsString = outputAsString;
		this.inputAsMap = inputAsMap;
		this.outputAsMap = outputAsMap;
	}
	public IOData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public I getInputObject() {
		return inputObject;
	}
	public void setInputObject(I inputObject) {
		this.inputObject = inputObject;
	}
	public O getOutputObject() {
		return outputObject;
	}
	public void setOutputObject(O outputObject) {
		this.outputObject = outputObject;
	}
	public String getInputAsString() {
		return inputAsString;
	}
	public void setInputAsString(String inputAsString) {
		this.inputAsString = inputAsString;
	}
	public String getOutputAsString() {
		return outputAsString;
	}
	public void setOutputAsString(String outputAsString) {
		this.outputAsString = outputAsString;
	}
	public Map getInputAsMap() {
		return inputAsMap;
	}
	public void setInputAsMap(Map inputAsMap) {
		this.inputAsMap = inputAsMap;
	}
	public Map getOutputAsMap() {
		return outputAsMap;
	}
	public void setOutputAsMap(Map outputAsMap) {
		this.outputAsMap = outputAsMap;
	}

}

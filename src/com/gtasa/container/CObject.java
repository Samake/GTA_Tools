package com.gtasa.container;

import com.gtasa.math.Vector3;

public class CObject {
	private String modelID;
	private String name;
	private String interior;
	private Vector3 position;
	private Vector3 rotation;
	private String lod;
	
	public CObject() {
		this.modelID = null;
		this.name = null;
		this.position = new Vector3();
		this.rotation = new Vector3();
	}
	
	public CObject(String modelID, String name, String interior, Vector3 position, Vector3 rotation, String lod) {
		this.modelID = modelID;
		this.name = name;
		this.interior = interior;
		this.position = position;
		this.rotation = rotation;
		this.lod = lod;
	}

	public String getModelID() {
		return this.modelID;
	}

	public void setModelID(String modelID) {
		this.modelID = modelID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterior() {
		return this.interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public Vector3 getPosition() {
		return this.position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getRotation() {
		return this.rotation;
	}

	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}

	public String getLOD() {
		return this.lod;
	}

	public void setLOD(String lod) {
		this.lod = lod;
	}
}

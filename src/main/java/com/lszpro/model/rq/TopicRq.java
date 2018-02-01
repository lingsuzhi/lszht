package com.lszpro.model.rq;


import com.lszpro.soa.common.BaseRq;

public class TopicRq extends BaseRq {

private static final long serialVersionUID = 1L;
private String name;
private String diseasescode;

public String getDiseasescode() {
	return diseasescode;
}
public void setDiseasescode(String diseasescode) {
	this.diseasescode = diseasescode;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
}

package com.lszpro.model.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class TopicBO{
	
	private String diseasesName;
 
 

    public String getDiseasesName() {
		return diseasesName;
	}
	public void setDiseasesName(String diseasesName) {
		this.diseasesName = diseasesName;
	}

	/**  */
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    /** 题目 */
    private String title;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    /** 问题描述 */
    private String description;
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    /** 客户id */
    private String userid;
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUserid() {
        return userid;
    }

    /** 性别-(0:女 1:男) */
    private Boolean sex;
    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    public Boolean getSex() {
        return sex;
    }

    /** 年龄 */
    private Integer age;
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }

    /** 病种值-(数据字典code:o2o_diseases) */
    private String diseasescode;
    public void setDiseasescode(String diseasescode) {
        this.diseasescode = diseasescode;
    }
    public String getDiseasescode() {
        return diseasescode;
    }

    /** 药师 */
    private String docname;
    public void setDocname(String docname) {
        this.docname = docname;
    }
    public String getDocname() {
        return docname;
    }

    /** 回答 */
    private String answer;
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    /**  */
    private Date createTime;
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }

}

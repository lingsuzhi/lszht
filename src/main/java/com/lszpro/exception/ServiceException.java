package com.lszpro.exception;


import com.lszpro.common.BodyStatus;
import com.lszpro.common.Utils;

/**
 * Service层公用的Exception.
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 */
public class ServiceException extends RuntimeException {
 
    private static final long serialVersionUID = 1401593546385403720L;
    private BodyStatus bodyStatus;

    public ServiceException() {
        super();
    }

    public ServiceException(int code, String message){
        this.bodyStatus = Utils.bodyStatus(code,message);
    }

    public ServiceException(String code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public BodyStatus getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(BodyStatus bodyStatus) {
        this.bodyStatus = bodyStatus;
    }
}
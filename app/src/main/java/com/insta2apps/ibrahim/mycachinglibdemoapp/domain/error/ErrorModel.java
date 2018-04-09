package com.insta2apps.ibrahim.mycachinglibdemoapp.domain.error;

import android.support.annotation.IntDef;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class ErrorModel {
    @ErrorType
    private int errorType;
    private String errorCode;
    private String errorDes;

    public ErrorModel(int errorType) {
        this.errorType = errorType;
    }

    public ErrorModel(int errorType, String errorCode, String errorDes) {
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.errorDes = errorDes;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDes() {
        return errorDes;
    }

    public void setErrorDes(String errorDes) {
        this.errorDes = errorDes;
    }

    @IntDef(value = {ErrorType.NETWORK_ERROR, ErrorType.BUSSINESS_ERROR, ErrorType.GENERAL_ERROR})
    public @interface ErrorType {
        int NETWORK_ERROR = 0;
        int BUSSINESS_ERROR = 1;
        int GENERAL_ERROR = 2;
    }
}

package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source;

import java.io.Serializable;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class BaseModel implements Serializable {
    private String errorCode;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}

package com.nhn.nhnsoft.jobray.userservice.global.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CareResponse<T> {
    private int code = 0;
    private String message = "SUCCESS";
    private T result;

    public static <E> CareResponse<E> of(E result) {
        CareResponse<E> ret = new CareResponse<>();
        ret.setResult(result);
        return ret;
    }

    public static CareResponse empty() {
        return new CareResponse<>();
    }
}

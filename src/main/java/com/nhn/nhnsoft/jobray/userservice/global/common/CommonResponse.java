package com.nhn.nhnsoft.jobray.userservice.global.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonResponse<T> {
    private int code = 0;
    private String message = "SUCCESS";
    private T result;

    public static <E> CommonResponse<E> of(E result) {
        CommonResponse<E> ret = new CommonResponse<>();
        ret.setResult(result);
        return ret;
    }

    public static CommonResponse empty() {
        return new CommonResponse<>();
    }
}

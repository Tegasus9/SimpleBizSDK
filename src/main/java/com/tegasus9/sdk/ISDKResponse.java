package com.tegasus9.sdk;

/**
 * @author XiongYiGe
 * @date 2022/6/29
 * @description
 */
public interface ISDKResponse<T> {
    Class<T> getResponseClass();
}

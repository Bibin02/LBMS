package com.project.lbms.util;

import java.util.List;

import com.project.lbms.exception.LbmsException;

@FunctionalInterface
public interface ResponseBuilder {
    public List<? extends Object> build(List<? extends Object> rawData) throws LbmsException;
}

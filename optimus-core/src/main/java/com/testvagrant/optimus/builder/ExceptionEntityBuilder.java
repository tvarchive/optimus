package com.testvagrant.optimus.builder;


import com.testvagrant.optimus.entity.ExceptionEntity;

public class ExceptionEntityBuilder {

    private ExceptionEntity exceptionEntity;
    public ExceptionEntityBuilder() {
        exceptionEntity = new ExceptionEntity();
    }

    public ExceptionEntityBuilder withException(String exception) {
        String s = exception.replaceAll(":", "");
        String substring = s.substring(exception.lastIndexOf(".")+1, exception.length()-1);
        exceptionEntity.setName(substring);
        return this;
    }

    public ExceptionEntityBuilder withWaitFor(String waitFor) {
        exceptionEntity.setWaitFor(waitFor.replaceAll(":",""));
        return this;
    }

    public ExceptionEntityBuilder withLocator(String locator) {
        exceptionEntity.setLocator(locator.replaceAll(":",""));
        return this;
    }

    public ExceptionEntityBuilder withValue(String value) {
        exceptionEntity.setValue(value.replaceAll(":",""));
        return this;
    }

    public ExceptionEntity build() {
        return exceptionEntity;
    }
}

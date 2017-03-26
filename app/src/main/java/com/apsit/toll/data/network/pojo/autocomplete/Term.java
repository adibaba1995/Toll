
package com.apsit.toll.data.network.pojo.autocomplete;

import java.util.HashMap;
import java.util.Map;

public class Term {

    private long offset;
    private String value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

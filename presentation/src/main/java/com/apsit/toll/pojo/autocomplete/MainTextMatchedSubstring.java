
package com.apsit.toll.pojo.autocomplete;

import java.util.HashMap;
import java.util.Map;

public class MainTextMatchedSubstring {

    private long length;
    private long offset;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

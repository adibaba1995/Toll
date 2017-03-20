
package com.apsit.toll.pojo.autocomplete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructuredFormatting {

    private String mainText;
    private List<MainTextMatchedSubstring> mainTextMatchedSubstrings = null;
    private String secondaryText;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public List<MainTextMatchedSubstring> getMainTextMatchedSubstrings() {
        return mainTextMatchedSubstrings;
    }

    public void setMainTextMatchedSubstrings(List<MainTextMatchedSubstring> mainTextMatchedSubstrings) {
        this.mainTextMatchedSubstrings = mainTextMatchedSubstrings;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

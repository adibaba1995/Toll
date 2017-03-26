
package com.apsit.toll.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Addresses {

    private List<Prediction> predictions = null;
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

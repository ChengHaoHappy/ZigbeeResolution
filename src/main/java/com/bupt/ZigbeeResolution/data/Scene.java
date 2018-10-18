package com.bupt.ZigbeeResolution.data;

import lombok.Data;

@Data
public class Scene {
    Integer scene_id;
    String sceneId;
    String sceneName;
    int sceneNumber;
    Integer customerId;
    String sceneSelectorId;

    public Scene(){}

    public Scene(String sceneName, Integer customerId){
        this.sceneName = sceneName;
        this.customerId =customerId;
    }

    public Scene(Integer scene_id, String sceneId, String sceneName, Integer sceneNumber, Integer customerId, String sceneSelectorId){
        this.scene_id = scene_id;
        this.sceneId = sceneId;
        this.sceneName = sceneName;
        this.sceneNumber =sceneNumber;
        this.customerId =customerId;
        this.sceneSelectorId = sceneSelectorId;
    }



    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"sceneId\":")
                .append("\"" + sceneId + "\"").append(",");
        sb.append("\"sceneName\":")
                .append("\"" + sceneName + "\"").append(",");
        sb.append("\"sceneNumber\":")
                .append("\"" + sceneNumber + "\"");
        sb.append('}');
        return sb.toString();
    }
}

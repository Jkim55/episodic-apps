package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class ProgressEvent extends Event {
    private HashMap<String, Object> data;

//    public HashMap<String, Object> getData() {
//        HashMap<String, Object> progressData = new HashMap();
//        progressData.put("offset", offset);
//        return progressData;
//    }

    @JsonProperty("type")
    public String getEventType(){
        return "progress";
    }

}

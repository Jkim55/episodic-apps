package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class PauseEvent extends Event {
    private int offset;

    public HashMap<String, Object> getData() {
        HashMap<String, Object> pauseData = new HashMap();
        pauseData.put("offset", offset);
        return pauseData;
    }

    @JsonProperty("type")
    public String getEventType(){
        return "pause";
    }

}

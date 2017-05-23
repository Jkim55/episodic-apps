package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class FastForwardEvent extends Event {
    private int startOffset;
    private int endOffset;
    private int speed;

    public HashMap<String, Object> getData() {
        HashMap<String, Object> fastForwardData = new HashMap();
        fastForwardData.put("startOffset", startOffset);
        fastForwardData.put("endOffset", endOffset);
        fastForwardData.put("speed", speed);
        return fastForwardData;
    }

    @JsonProperty("type")
    public String getEventType(){
        return "fastForward";
    }

}

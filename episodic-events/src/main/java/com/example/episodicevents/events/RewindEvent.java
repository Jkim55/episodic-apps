package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class RewindEvent extends Event {
    private int startOffset;
    private int endOffset;
    private int speed;

    private HashMap<String, Object> getData() {
        HashMap<String, Object> rewindData = new HashMap();
        rewindData.put("startOffset", startOffset);
        rewindData.put("endOffset", endOffset);
        rewindData.put("speed", speed);
        return rewindData;
    }

    @JsonProperty("type")
    public String getEventType(){
        return "rewind";
    }

}

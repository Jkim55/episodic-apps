package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class ScrubEvent extends Event {
    private HashMap<String, Object> data;

//    public HashMap<String, Object> getData() {
//        HashMap<String, Object> scrubData = new HashMap();
//        scrubData.put("startOffset", startOffset);
//        scrubData.put("endOffset", endOffset);
//        return scrubData;
//    }

    @JsonProperty("type")
    public String getEventType(){
        return "scrub";
    }

}

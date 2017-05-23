package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class PlayEvent extends Event {
    private HashMap<String, Object> data;

//    @JsonProperty("data")
//    public HashMap<String, Object> getData() {
//        HashMap<String, Object> playData = new HashMap();
//        playData.put("offset", offset);
//        return playData;
//    }

    @JsonProperty("type")
    public String getEventType(){
        return "play";
    }

}

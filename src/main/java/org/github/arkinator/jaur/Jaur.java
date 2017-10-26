package org.github.arkinator.jaur;

import com.jsoniter.JsonIterator;
import lombok.Builder;
import org.github.arkinator.jaur.data.JaurDataJsonObject;

@Builder
public class Jaur {
    public JaurDataJsonObject parseJson(String jsonString) {
        return JaurDataJsonObject.builder()
                .value(JsonIterator.deserialize(jsonString))
                .build();
    }
}

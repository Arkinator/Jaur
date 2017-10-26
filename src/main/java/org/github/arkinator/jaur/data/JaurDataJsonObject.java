package org.github.arkinator.jaur.data;

import com.jsoniter.any.Any;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class JaurDataJsonObject implements JaurDataObject<JaurDataJsonObject> {
    private Any value;

    @Override
    public Optional<JaurDataJsonObject> get(String... query) {
        return Optional.of(JaurDataJsonObject.builder()
                .value(value.get(query))
                .build());
    }
}

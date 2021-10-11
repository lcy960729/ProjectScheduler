package com.lcy.ps.contents.api.v1.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcy.ps.contents.api.v1.dto.WriteContentsEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class WriteContentsEventDeserializer implements Deserializer<WriteContentsEvent> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public WriteContentsEvent deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, WriteContentsEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException();
        }
    }
}

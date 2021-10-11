package com.lcy.ps.contents.api.v1.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcy.ps.contents.api.v1.dto.WriteContentsEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class WriteContentsEventSerializer implements Serializer<WriteContentsEvent> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, WriteContentsEvent writeContentsEvent) {
        try {
            return mapper.writeValueAsBytes(writeContentsEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new SerializationException();
        }
    }
}

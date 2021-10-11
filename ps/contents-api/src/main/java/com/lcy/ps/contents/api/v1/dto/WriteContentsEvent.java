package com.lcy.ps.contents.api.v1.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

@Data
@NoArgsConstructor
public class WriteContentsEvent {
    private Long contentsId;
    private String data;

    @Builder
    public WriteContentsEvent(Long contentsId, String data) {
        this.contentsId = contentsId;
        this.data = data;
    }
}

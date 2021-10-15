package com.lcy.ps.contents.domain;

import com.lcy.ps.contents.dto.WriteContentsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WriteContentsEventPublisher {

    @Value("${writeContentsEvent.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, WriteContentsEvent> kafkaTemplate;

    public void sendWriteContentsEvent(long contentsId, String data) {
        WriteContentsEvent writeContentsEvent = WriteContentsEvent.builder()
                .contentsId(contentsId)
                .data(data)
                .build();

        kafkaTemplate.send(topic, writeContentsEvent);
    }
}

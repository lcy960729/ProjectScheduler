package com.lcy.ps.contents.api.v1.domain;

import com.lcy.ps.contents.api.v1.dto.WriteContentsEvent;
import com.lcy.ps.core.domain.contents.Contents;
import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.core.repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
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

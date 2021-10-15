package com.lcy.ps.contents.domain;

import com.lcy.ps.contents.dto.WriteContentsEvent;
import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.contents.repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WriteContentsEventHandler {

    @Autowired
    private ContentsRepository contentsRepository;

    @KafkaListener(topics = "${writeContentsEvent.topic}")
    public void write(WriteContentsEvent writeContentsEvent) {
        Contents contents = contentsRepository.findById(writeContentsEvent.getContentsId())
                .orElseThrow(NotFoundEntityException::new);

        contents.setData(writeContentsEvent.getData());

        contents = contentsRepository.save(contents);
    }
}

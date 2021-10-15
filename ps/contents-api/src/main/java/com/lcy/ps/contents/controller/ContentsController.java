package com.lcy.ps.contents.controller;

import com.lcy.ps.contents.domain.WriteContentsEventPublisher;
import com.lcy.ps.contents.dto.WriteContentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contents")
public class ContentsController {

    @Autowired
    private WriteContentsEventPublisher publisher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> write(Long userId,
                                        @PathVariable("id") Long contentsId,
                                        @RequestBody WriteContentsDTO writeContentsDTO) {

        publisher.sendWriteContentsEvent(contentsId, writeContentsDTO.getData());

        return ResponseEntity.ok("success");
    }
}

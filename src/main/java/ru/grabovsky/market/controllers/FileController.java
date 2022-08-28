package ru.grabovsky.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.market.services.FileService;

import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping(value = "/{filename}", produces = "application/octet-stream")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@PathVariable String filename){
        byte[] result;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(filename).build().toString());
        try {
            return ResponseEntity.ok().headers(httpHeaders).body(fileService.getFileData(filename));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

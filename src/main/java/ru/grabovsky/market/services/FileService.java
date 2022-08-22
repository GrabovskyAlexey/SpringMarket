package ru.grabovsky.market.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    private final String baseDir;

    public FileService(@Value("${market.storage.path}") String baseDir) throws IOException {
        this.baseDir = baseDir;
        Path dir = Path.of(baseDir);
        if(Files.notExists(dir)){
            Files.createDirectory(dir);
        }
    }

    public Path getFilePath(String fileName) {
        return Path.of(baseDir).resolve(fileName);
    }

    public byte[] getFileData(String fileName) throws IOException {
        return Files.readAllBytes(getFilePath(fileName));
    }
}

package com.digitaul.lagunachicken.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/resources")
public class FileController {

    @Value("${serlo.app.fileDirectory}")
    private String fileDirectory;

    @GetMapping("file/{fileName}")
    //@PreAuthorize("permitAll()")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) throws IOException {
        String externalFilePath = fileDirectory + fileName;

        // Carga el archivo desde la ubicación externa
        Resource resource = new FileSystemResource(externalFilePath);

        if (!resource.exists()) {
            // Maneja el caso en que el archivo no existe
            // Por ejemplo, lanzando una excepción o devolviendo un ResponseEntity con un código de estado 404
            return ResponseEntity.notFound().build();
        }

        // Lee el contenido del archivo en un array de bytes
        byte[] fileContent = Files.readAllBytes(resource.getFile().toPath());

        // Configura los encabezados de respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", fileName);

        // Retorna el contenido del archivo como un ResponseEntity
        return ResponseEntity.ok().headers(headers).body(fileContent);
    }

}

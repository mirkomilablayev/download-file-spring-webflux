package com.example.springwebfluxfile;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @GetMapping("/download")
    public Mono<Void> downloadFile(ServerHttpResponse response) throws IOException {
        ZeroCopyHttpOutputMessage zeroCopyHttpOutputMessage =
                (ZeroCopyHttpOutputMessage) response;
        response.getHeaders()
                .set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=district_types.xlsx");
        response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ClassPathResource resource = new ClassPathResource("district_types.xlsx");
        File file = resource.getFile();
        return zeroCopyHttpOutputMessage.writeWith(file, 0, file.length());


    }

}
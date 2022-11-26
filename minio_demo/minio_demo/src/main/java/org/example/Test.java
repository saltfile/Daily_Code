package org.example;

import com.jlefebure.spring.boot.minio.MinioConfigurationProperties;
import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import io.minio.messages.Item;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class Test {
    @Autowired

    private MinioService minioService;

    @Autowired

    private MinioConfigurationProperties configurationProperties;

    @GetMapping("/files")

    public List<Item> testMinio() throws MinioException {

        return minioService.list();

    }

    @GetMapping("files/{object}")

    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws com.jlefebure.spring.boot.minio.MinioException, IOException {

        InputStream inputStream = minioService.get(Paths.get(object));

        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Set the content type and attachment header.

        response.addHeader("Content-disposition", "attachment;filename=" + object);

        response.setContentType(URLConnection.guessContentTypeFromName(object));

        // Copy the stream to the response's output stream.

        IOUtils.copy(inputStream, response.getOutputStream());

        response.flushBuffer();

    }

    @PostMapping
    public void addAttachement(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        String filename = file.getOriginalFilename();


        Path path = Paths.get("/png/"+filename);
        String url = configurationProperties.getUrl() + "/" + configurationProperties.getBucket() + path.toAbsolutePath();

        System.out.println(url);

        try {

            minioService.upload(path, file.getInputStream(), file.getContentType());

        } catch (MinioException e) {

            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);

        } catch (IOException e) {

            throw new IllegalStateException("The file cannot be read", e);

        }

    }

}
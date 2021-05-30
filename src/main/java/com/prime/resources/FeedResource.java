package com.prime.resources;

import com.prime.domain.Post;
import com.prime.dto.PostDTO;
import com.prime.service.FeedService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/feed")
public class FeedResource {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public ResponseEntity<Page<PostDTO>> getFeed(@RequestParam(value="page", defaultValue="0") Integer page,
                                                 @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
                                                 @RequestParam(value="orderBy", defaultValue="data") String orderBy,
                                                 @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<PostDTO> listPosts = feedService.listPosts(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(listPosts);
    }

    @PostMapping
    public ResponseEntity<Void> setPost(@Valid @RequestBody Post post) {
        feedService.setPost(post);
        return ResponseEntity.created(null).build();
    }

    @PostMapping(value = "/imagem")
    public ResponseEntity<String> setPostImage(@Valid @RequestBody MultipartFile file) throws NoSuchAlgorithmException, IOException {
        String imageName = feedService.setPostImage(file);
        JSONObject obj = new JSONObject();
        obj.put("ImageName", imageName);
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(obj.toString());
    }

    @GetMapping(value = "/imagem/{imageName}")
    public ResponseEntity<ByteArrayResource> getPostImage(@PathVariable String imageName) throws NoSuchAlgorithmException, IOException {
        File image = feedService.getPostImage(imageName);
        Path path = Paths.get(image.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .contentLength(image.length())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}

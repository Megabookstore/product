package com.bookstore.Image.ui;

import com.bookstore.Image.application.ImageService;
import com.bookstore.Image.dto.ImageRequest;
import com.bookstore.Image.dto.ImageResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> register(@RequestBody final ImageRequest imageRequest) {
        ImageResponse imageResponse = imageService.register(imageRequest);
        URI uri = URI.create("/image/" + imageResponse.getId());
        return ResponseEntity.created(uri).body(imageResponse);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<ImageResponse> update(
        @RequestBody final ImageRequest imageRequest,
        @PathVariable("imageId") Long imageId) {
        ImageResponse imageResponse = imageService.update(imageId, imageRequest);
        return ResponseEntity.ok().body(imageResponse);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<ImageResponse> delete(@PathVariable("imageId") Long imageId) {
        ImageResponse imageResponse = imageService.delete(imageId);
        return ResponseEntity.ok().body(imageResponse);
    }
}

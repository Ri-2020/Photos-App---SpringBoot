package com.example.demo.web;


import com.example.demo.model.Photo;
import com.example.demo.service.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RestController
public class PhotozController {



    private final PhotosService photosService;

    public PhotozController(PhotosService photosService){
        this.photosService = photosService;
    }


    @GetMapping("/")
    public String hell(){
        return "Hello World";
    }


    @GetMapping("/photos")
    public Iterable<Photo> getPhotos(){
        return  photosService.get();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhoto(@PathVariable Integer id){
        Photo photo =  photosService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }
    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable Integer id){
        photosService.remove(id);
    }

    @PostMapping("/photo")
    public Photo createPhoto( @RequestPart("data") MultipartFile file ) throws IOException {

        return photosService.save(file.getOriginalFilename() , file.getContentType() , file.getBytes());
    }


}


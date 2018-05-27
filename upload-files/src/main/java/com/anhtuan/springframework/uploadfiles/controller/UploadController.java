package com.anhtuan.springframework.uploadfiles.controller;

import com.anhtuan.springframework.uploadfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }


}

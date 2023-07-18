package com.example.home_decoration.service;

import com.example.home_decoration.model.Result;
import org.springframework.stereotype.Service;

@Service
public interface PictureService {


    Result<String> projectUpload(String url, Integer o_id);

    Result<String> getProjectUrl(Integer o_id);
}

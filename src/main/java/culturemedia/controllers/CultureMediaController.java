package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.impl.CultureMediaServiceImpl;

public class CultureMediaController {

    private final CultureMediaServiceImpl cultureMediaService;


    public CultureMediaController(CultureMediaServiceImpl cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }


    public List<Video> findAllVideos() throws VideoNotFoundException {
        return cultureMediaService.findAll();
    }


}
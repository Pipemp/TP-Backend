package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.CultureMediaService;

public class CultureMediaController {

    private final CultureMediaService cultureMediaService;


    public CultureMediaController(CultureMediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }


    public List<Video> find_allVideos() throws VideoNotFoundException {
        return cultureMediaService.findAll();
    }


}
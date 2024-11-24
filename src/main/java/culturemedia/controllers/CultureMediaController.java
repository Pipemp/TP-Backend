
package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.impl.CultureMediaService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")

@RestController
public class CultureMediaController {

    private final CultureMediaService cultureMediaService;

    public CultureMediaController(CultureMediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    @GetMapping("/video")
    public List<Video> findAll() throws VideoNotFoundException {
        return cultureMediaService.findAll();
    }

    @PostMapping("/video")
    public Video save(@RequestBody Video video) throws VideoNotFoundException {
        return this.cultureMediaService.save(video);
    }
}
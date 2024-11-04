package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CultureMediaServiceImplTest {

    private CultureMediaService cultureMediaService;

    @BeforeEach
    void init(){

        cultureMediaService = new CultureMediaServiceImpl(new VideoRepositoryImpl(),new ViewsRepositoryImpl());

        List<Video> videos = List.of(
                new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1)
        );


        for ( Video video : videos ) {
            cultureMediaService.save( video );
        }

    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
            List<Video> videos = cultureMediaService.findAll();
            assertEquals(6, videos.size());
    }


    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
            cultureMediaService.findAll();
            Assertions.assertTrue(false, "Expected VideoNotFoundException to be thrown, but it was not.");
    }


    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
            String titleToSearch = "Título Inexistente";
            List<Video> videos = cultureMediaService.find(titleToSearch);
            assertEquals(0, videos.size());
    }

    @Test
    void when_FindByTitle_finds_videos_successfully() throws VideoNotFoundException {
        String titleToSearch = "Clic";
            List<Video> videos = cultureMediaService.find(titleToSearch);
            assertFalse(videos.isEmpty(), "Expected videos to be found but none were returned.");
            assertEquals(2, videos.size());
            for (Video video : videos) {
                assertTrue(video.title().contains(titleToSearch), "Expected video title to contain: " + titleToSearch);
            }
    }

    @Test
    void when_FindByDuration_finds_videos_successfully() throws VideoNotFoundException {
        double fromDuration = 4.0;
        double toDuration = 5.5;
            List<Video> videos = cultureMediaService.find(fromDuration, toDuration);
            assertFalse(videos.isEmpty(), "Expected videos to be found but none were returned.");
            assertEquals(4, videos.size());
            for (Video video : videos) {
                assertTrue(video.duration() >= fromDuration && video.duration() <= toDuration,
                        "Expected video duration to be within range: " + video.duration());
            }
    }

    @Test
    void when_FindByDuration_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        double fromDuration = 10.0;
        double toDuration = 15.0;
            List<Video> videos = cultureMediaService.find(fromDuration, toDuration);
            assertTrue(videos.isEmpty(), "Expected no videos to be found, but some were returned.");
    }
}


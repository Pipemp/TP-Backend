package culturemedia.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;


public class CultureMediaServiceImplTest {

    @Mock
    private VideoRepository videoRepository;


    @InjectMocks
    private CultureMediaServiceImpl cultureMediaService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        List<Video> videos = List.of(
                new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1)
        );

        when(videoRepository.findAll()).thenReturn(videos);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = cultureMediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll(), "Expected VideoNotFoundException");
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        String titleToSearch = "Título Inexistente";
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find(titleToSearch);
        }, "Expected VideoNotFoundException to be thrown, but it was not.");
    }

    @Test
    void when_FindByTitle_finds_videos_successfully() throws VideoNotFoundException {
        String titleToSearch = "Clic";
        List<Video> videosWithTitle = List.of(
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1)
        );

        when(videoRepository.find(titleToSearch)).thenReturn(videosWithTitle);

        List<Video> videos = cultureMediaService.find(titleToSearch);
        assertEquals(2, videos.size());
        for (Video video : videos) {
            assertTrue(video.title().contains(titleToSearch), "Expected video title to contain: " + titleToSearch);
        }
    }

    @Test
    void when_FindByDuration_finds_videos_successfully() throws VideoNotFoundException {
        double fromDuration = 4.0;
        double toDuration = 5.5;
        List<Video> videosInDurationRange = List.of(
                new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4)
        );

        when(videoRepository.find(fromDuration, toDuration)).thenReturn(videosInDurationRange);

        List<Video> videos = cultureMediaService.find(fromDuration, toDuration);
        assertEquals(3, videos.size());
        for (Video video : videos) {
            assertTrue(video.duration() >= fromDuration && video.duration() <= toDuration,
                    "Expected video duration to be within range: " + video.duration());
        }
    }

    @Test
    void when_FindByDuration_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        double fromDuration = 10.0;
        double toDuration = 15.0;
        when(videoRepository.find(fromDuration, toDuration)).thenReturn(Collections.emptyList());

        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find(fromDuration, toDuration),
                "Expected VideoNotFoundException");
    }
}



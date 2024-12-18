package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureMediaServiceImpl implements CultureMediaService {
    private final VideoRepository videoRepository;
    private final ViewsRepository viewsRepository;


    public CultureMediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();

        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No videos found");
        }

        return videos;
    }

    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public View save(View view) {
        return viewsRepository.save(view);
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> filteredVideos = videoRepository.find(title);
        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException("No video found with title: " + title);
        }
        return filteredVideos;
    }

    @Override
    public List<Video> find(double fromDuration, double toDuration) throws VideoNotFoundException {
        List<Video> filteredVideos = videoRepository.find(fromDuration, toDuration);
        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException("No videos found in the specified duration range.");
        }
        return filteredVideos;
    }
}


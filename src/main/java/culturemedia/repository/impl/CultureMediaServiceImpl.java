package culturemedia.repository.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.CultureMediaService;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.List;

public class CultureMediaServiceImpl implements CultureMediaService {
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    @Override
    public List<Video> findAll() {
        return null;
    }

    @Override
    public Video save(Video save) {
        return save;
    }

    @Override
    public View save(View save) {
        return save;
    }
}


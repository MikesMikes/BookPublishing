package mikesmikes.github.bookpublishing.services.serviceSDJpaImpls;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.repositories.PublisherRepository;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Set<Publisher> findAll() {
        Set<Publisher> publishers = new HashSet<>();
        publisherRepository.findAll().iterator().forEachRemaining(publishers::add);
        return publishers;
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public Publisher save(Publisher object) {
        log.info("save - ");
        System.out.println(object.getId());
        if (object.getId() != null){
            Publisher publisher = publisherRepository.findById(object.getId()).get();
            publisher.setName(object.getName());
            publisher.setAddress(object.getAddress());

            return publisherRepository.save(publisher);
        }
        return publisherRepository.save(object);
    }

    @Override
    public void delete(Publisher object) {
        publisherRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}

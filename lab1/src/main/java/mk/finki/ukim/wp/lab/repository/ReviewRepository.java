package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.data.ReviewDataHolder;
import mk.finki.ukim.wp.lab.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReviewRepository {
    public void save(Review review) {
        ReviewDataHolder.insert(review);
    }

    public List<Review> findAll() {
        return ReviewDataHolder.reviews;
    }

    public List<Review> findAllByEvtId(Long evtId) {
        return ReviewDataHolder.reviews.stream().filter(review -> review.getEventId().equals(evtId)).collect(Collectors.toList());
    }
}

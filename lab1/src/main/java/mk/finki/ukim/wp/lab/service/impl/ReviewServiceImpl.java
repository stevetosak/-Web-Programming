package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.entity.Review;
import mk.finki.ukim.wp.lab.repository.ReviewRepository;
import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final EventService eventService;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(EventService eventService, ReviewRepository reviewRepository) {
        this.eventService = eventService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(Long evtId,String comment,int rating) {
        Event event = eventService.getEventById(evtId);

        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setEventId(evtId);

        reviewRepository.save(review);
    }
}

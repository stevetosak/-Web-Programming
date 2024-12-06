package mk.finki.ukim.wp.lab.data;

import mk.finki.ukim.wp.lab.entity.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewDataHolder {

    public static final List<Review> reviews = new ArrayList<>();
    private static long id_count = 0;

    public static void insert(Review review) {
        review.setId(id_count ++);
        reviews.add(review);

        System.out.println("Added review: " + review);
    }

}

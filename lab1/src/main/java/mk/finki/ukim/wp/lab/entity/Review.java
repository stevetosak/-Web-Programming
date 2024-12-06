package mk.finki.ukim.wp.lab.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {
    Long id;
    int rating;
    String comment;
    Long eventId;
}

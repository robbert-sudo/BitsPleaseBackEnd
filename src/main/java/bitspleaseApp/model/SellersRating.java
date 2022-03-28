package bitspleaseApp.model;

import javax.persistence.*;

@Entity
@Table
public class SellersRating {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rating_id;

    @Column
    private long ratedUserId;

    @Column
    private long rating;

    public long getRatingId() {return rating_id;}
    public void setRatingId(long rating_id) {this.rating_id = rating_id;}
    public long getRatedUserId() {return ratedUserId;}
    public void setRatedUserId(long ratedUserId) {this.ratedUserId = ratedUserId;}
    public long getRating() {return rating;}
    public void setRating(long rating) {this.rating = rating;}
}

package Innovation.Academy.Innovation_academy_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Feedbacks")
public class FeedbackEntity {

    @Id
    @Column (name = "user_id")
    private Integer userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "feedback_stars",nullable = false)
    private Integer feedbackStars;

    @Column(name = "feedback_description")
    private String feedbackDescription;
}
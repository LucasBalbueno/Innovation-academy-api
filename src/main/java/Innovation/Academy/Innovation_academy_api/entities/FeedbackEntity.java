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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "feedback_id",nullable = false)
    private Integer feedback_id;

    @Column(name = "feedback_username")
    private String feedbackUsername;

    @Column(name = "feedback_name")
    private String feedbackName;

    @Column(name = "feedback_stars",nullable = false)
    private Integer feedbackStars;

    @Column(name = "feedback_description")
    private String feedbackDescription;
}
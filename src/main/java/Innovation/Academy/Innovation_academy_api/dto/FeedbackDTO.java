package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class FeedbackDTO {

    @Schema(description = "codigo indentificador do feedback", example = "1")
    private Integer feedbackId;

    @Schema(description = "numero de estrelas a serem atribuidas no feedback", example = "5")
    private Integer feedbackStars;

    @Schema(description = "descrição do feedback", example = "Plataforma muito boa, la tem ótimos cursos")
    private String feedbackDescription;

    private UserEntity userId;
}

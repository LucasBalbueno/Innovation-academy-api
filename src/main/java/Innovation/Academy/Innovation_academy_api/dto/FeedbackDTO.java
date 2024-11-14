package Innovation.Academy.Innovation_academy_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FeedbackDTO {

    @Schema(description = "codigo indentificador do feedback", example = "1")
    private Integer userId;

    @Schema(description = "numero de estrelas a serem atribuidas no feedback", example = "5")
    private Integer feedbackStars;

    @Schema(description = "descrição do feedback", example = "Plataforma muito boa, la tem ótimos cursos")
    private String feedbackDescription;
}

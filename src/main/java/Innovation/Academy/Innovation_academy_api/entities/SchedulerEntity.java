package Innovation.Academy.Innovation_academy_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Schedulers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedulerEntity {

    @Id
    @Column(name = "scheduler_id")
    private Integer schedulerId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "Schedulers_Events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<EventEntity> events;



}

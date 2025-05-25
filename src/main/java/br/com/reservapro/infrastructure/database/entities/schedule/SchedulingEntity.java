package br.com.reservapro.infrastructure.database.entities.schedule;

import br.com.reservapro.infrastructure.database.entities.costumer.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_agendamento")
public class SchedulingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private Date schedullingDay;

    @ManyToOne
    @JoinColumn(name = "costumerId", referencedColumnName = "id")
    private CustomerEntity customer;
}

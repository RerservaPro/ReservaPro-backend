package br.com.reservapro.infrastructure.database.entities.costumer;

import br.com.reservapro.infrastructure.database.entities.schedule.SchedulingEntity;
import br.com.reservapro.infrastructure.database.entities.servico.ServicoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SchedulingEntity> schedulingEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ServicoEntity> servicoList = new ArrayList<>();
}

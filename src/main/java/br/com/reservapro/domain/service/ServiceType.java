package br.com.reservapro.domain.service;

import br.com.reservapro.domain.ActivateStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceType {
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private ActivateStatus activateStatus;
}

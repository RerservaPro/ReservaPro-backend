package br.com.reservapro.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateStatus {
    private Instant creationDate;
    private Instant deactivationDate;
    private Boolean isActive;
}

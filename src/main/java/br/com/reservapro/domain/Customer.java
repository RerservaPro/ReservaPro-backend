package br.com.reservapro.domain;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Costumer {
    @EqualsAndHashCode.Include
    private String id;
    private String email;
    private String phoneNumber;
}

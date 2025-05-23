package br.com.reservapro.domain;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedulling {
    @EqualsAndHashCode.Include
    private String id;
    private Date schedullingDate;
}

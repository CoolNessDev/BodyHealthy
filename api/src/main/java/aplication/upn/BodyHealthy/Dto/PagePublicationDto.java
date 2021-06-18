package aplication.upn.BodyHealthy.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
public class PagePublicationDto {
    @Getter@Setter
    Page<PublicacionDto> publicacionDtos;
    @Getter@Setter
    long TotalElements;
}

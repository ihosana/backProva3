package edu.ufrn.tads.apirestpharmakon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco extends AbstractEntity {
    String cidade;
    String rua;
    @JsonIgnoreProperties({"pessoa" })
    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;

    @Data
    public static class DtoRequestEndereco {
        String cidade;
        String rua;

        public static Endereco convertToEndereco(DtoRequestEndereco dto, ModelMapper mapper) {
            return mapper.map(dto, Endereco.class);
        }

        @Data
        public static class DtoResponseEndereco extends RepresentationModel<DtoResponseEndereco> {
            String cidade;
            String rua;

            public static DtoResponseEndereco convertToDtoEndereco(Endereco e, ModelMapper mapper) {
                return mapper.map(e, DtoResponseEndereco.class);
            }
        }
    }


}

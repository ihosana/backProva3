package edu.ufrn.tads.apirestpharmakon.domain;
import com.fasterxml.jackson.annotation.*;
import edu.ufrn.tads.apirestpharmakon.controller.PessoaController;
import jakarta.persistence.*;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE pessoas SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
@Table(name = "pessoas")

@JsonIgnoreProperties("endereco")
public class Pessoa extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    Long id;
    String nome;
    Integer idade;
    String password;
    Boolean admin = false;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "endereco_id")

    Endereco endereco;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "pessoa")
    private List<Pedido> pedidos = new ArrayList<>();





    @Data
    public static class DtoRequest{
        @NotBlank(message = "Usuário com nome em branco")
        String nome;
        @Min(value = 18, message = "Usuário com idade insuficiente")
        Integer idade;
        Endereco endereco;
        String password;
        List<Pedido> pedidos;
        public static Pessoa convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Pessoa.class);
        }
    }

    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        String nome;
        Integer idade;
        Boolean admin;
        String password;

        Endereco endereco;

        public static DtoResponse convertToDto(Pessoa p, ModelMapper mapper){

            return mapper.map(p, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(PessoaController.class).slash(id).withSelfRel());
            add(linkTo(PessoaController.class).withRel("pessoas"));
            add(linkTo(PessoaController.class).slash(id).withRel("delete"));
        }


    }

}

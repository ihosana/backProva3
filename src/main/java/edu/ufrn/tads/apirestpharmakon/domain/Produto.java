package edu.ufrn.tads.apirestpharmakon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ufrn.tads.apirestpharmakon.controller.PessoaController;
import edu.ufrn.tads.apirestpharmakon.controller.ProdutoController;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE produtos SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
@Table(name = "produtos")
public class Produto extends AbstractEntity {
    String nome;
    String dataValidade;
    String formaApresentacao;
    String lote;
    Double preco;
    @JsonIgnore
    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    @Data
    public static class DtoRequest {
        String nome;
        String dataValidade;
        String formaApresentacao;
        String lote;
        Double preco;

        public static Produto convertToEntity(DtoRequest dto, ModelMapper mapper) {
            return mapper.map(dto, Produto.class);
        }
    }

    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        String nome;
        String dataValidade;
        String formaApresentacao;
        String lote;
        Double preco;

        public static DtoResponse ConvertToDto(Produto p, ModelMapper mapper) {
            return mapper.map(p, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(ProdutoController.class).slash(id).withSelfRel());
            add(linkTo(ProdutoController.class).withRel("produtos"));
            add(linkTo(ProdutoController.class).slash(id).withRel("delete"));
        }
    }
}


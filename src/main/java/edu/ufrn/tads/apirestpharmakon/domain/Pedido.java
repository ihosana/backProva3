package edu.ufrn.tads.apirestpharmakon.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ufrn.tads.apirestpharmakon.controller.PedidoController;
import edu.ufrn.tads.apirestpharmakon.controller.PessoaController;
import jakarta.persistence.*;
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
@SQLDelete(sql = "UPDATE pedidos SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
@Table(name = "pedidos")
public class Pedido extends AbstractEntity {

    Long numPedido;
    String descricao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    Pessoa pessoa;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pedido_produto", joinColumns = { @JoinColumn(name = "pedido_id",
            referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "produto_id") })
    private List<Produto> produtos = new ArrayList<Produto>();

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Data
    public static class DtoRequest{
        Long numPedido;
        String descricao;

        public static Pedido convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Pedido.class);
        }
    }
    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse>{
        Long numPedido;
        String descricao;

        public static DtoResponse convertToDto(Pedido p, ModelMapper mapper){
            return mapper.map(p, DtoResponse.class);
        }
        public void generateLinks(Long id){
            add(linkTo(PedidoController.class).slash(id).withSelfRel());
            add(linkTo(PedidoController.class).withRel("pedidos"));
            add(linkTo(PedidoController.class).slash(id).withRel("delete"));
        }

    }
}

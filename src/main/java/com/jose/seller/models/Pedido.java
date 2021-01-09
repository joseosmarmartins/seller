package com.jose.seller.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends Entidade {

    @Column(insertable = false, updatable = false, columnDefinition="serial")
    private Long numero;
    private Integer valorTotal;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(
                    name = "pedido_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "produto_id", referencedColumnName = "id"))
    private List<Produto> produtos = new ArrayList<>();
}

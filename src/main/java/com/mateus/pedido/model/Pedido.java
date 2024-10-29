package com.mateus.pedido.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao;
    private Double valorTotal;
    private String status;
    private String descricao;
    private String tipoCliente;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
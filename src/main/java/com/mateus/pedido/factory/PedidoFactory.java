package com.mateus.pedido.factory;

import com.mateus.pedido.model.Pedido;

public class PedidoFactory {

    public static Pedido criarPedido(String status) {
        Pedido pedido = new Pedido();
        pedido.setStatus(status);
        // Configurações específicas para cada tipo de pedido
        if ("PENDENTE".equalsIgnoreCase(status)) {
            pedido.setValorTotal(0.0); // Exemplo
        } else if ("APROVADO".equalsIgnoreCase(status)) {
            pedido.setValorTotal(100.0); // Exemplo
        } else if ("CANCELADO".equalsIgnoreCase(status)) {
            pedido.setValorTotal(0.0); // Exemplo
        }
        return pedido;
    }
}

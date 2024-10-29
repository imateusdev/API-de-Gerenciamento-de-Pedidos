package com.mateus.pedido.service;

import com.mateus.pedido.model.Pedido;

public class ProcessamentoPedidoVip extends ProcessamentoPedidoTemplate {

    @Override
    protected void validarPedido(Pedido pedido) {
        System.out.println("Validação específica para pedidos VIP.");
    }

    @Override
    protected void calcularValorTotal(Pedido pedido) {
        System.out.println("Calculando valor total com desconto VIP.");
        pedido.setValorTotal(pedido.getValorTotal() * 0.9); // Exemplo de desconto
    }
}

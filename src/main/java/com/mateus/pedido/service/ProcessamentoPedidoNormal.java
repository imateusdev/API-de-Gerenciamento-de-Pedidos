package com.mateus.pedido.service;

import com.mateus.pedido.model.Pedido;

public class ProcessamentoPedidoNormal extends ProcessamentoPedidoTemplate {

    @Override
    protected void validarPedido(Pedido pedido) {
        System.out.println("Validação para pedido normal.");
    }

    @Override
    protected void calcularValorTotal(Pedido pedido) {
        System.out.println("Calculando valor total sem desconto.");
    }
}

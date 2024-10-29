package com.mateus.pedido.service;

import com.mateus.pedido.model.Pedido;

public abstract class ProcessamentoPedidoTemplate {

    public final void processarPedido(Pedido pedido) {
        validarPedido(pedido);
        calcularValorTotal(pedido);
        salvarPedido(pedido);
    }

    protected abstract void validarPedido(Pedido pedido);

    protected abstract void calcularValorTotal(Pedido pedido);

    protected void salvarPedido(Pedido pedido) {
        // Implementação padrão de salvar pedido no repositório
        System.out.println("Salvando pedido no banco de dados...");
    }
}

package com.mateus.pedido.strategy;

import com.mateus.pedido.model.Pedido;

// Estratégia com desconto para clientes VIP
public class CalculoValorDescontoVip implements CalculoValorTotalStrategy {
    @Override
    public double calcular(Pedido pedido) {
        return pedido.getValorTotal() * 0.9; // 10% de desconto
    }
}

package com.mateus.pedido.strategy;

import com.mateus.pedido.model.Pedido;

// Estratégia padrão, sem desconto
public class CalculoValorSemDesconto implements CalculoValorTotalStrategy {
    @Override
    public double calcular(Pedido pedido) {
        return pedido.getValorTotal(); // Sem desconto
    }
}

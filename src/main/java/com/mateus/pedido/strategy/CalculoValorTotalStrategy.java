package com.mateus.pedido.strategy;

import com.mateus.pedido.model.Pedido;

public interface CalculoValorTotalStrategy {
    double calcular(Pedido pedido);
}

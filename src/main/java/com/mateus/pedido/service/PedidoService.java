package com.mateus.pedido.service;

import com.mateus.pedido.model.Pedido;
import com.mateus.pedido.repository.PedidoRepository;
import com.mateus.pedido.strategy.CalculoValorTotalStrategy;
import com.mateus.pedido.strategy.CalculoValorSemDesconto;
import com.mateus.pedido.strategy.CalculoValorDescontoVip;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mateus.pedido.factory.PedidoFactory;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private CalculoValorTotalStrategy calculoStrategy;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.calculoStrategy = new CalculoValorSemDesconto(); // estratégia padrão
    }

    public void definirCalculoStrategy(String tipoCliente) {
        if ("VIP".equalsIgnoreCase(tipoCliente)) {
            calculoStrategy = new CalculoValorDescontoVip();
        } else {
            calculoStrategy = new CalculoValorSemDesconto();
        }
    }

    public void processarPedido(Pedido pedido, String tipoPedido) {
        ProcessamentoPedidoTemplate processamento;
        if ("VIP".equalsIgnoreCase(tipoPedido)) {
            processamento = new ProcessamentoPedidoVip();
        } else {
            processamento = new ProcessamentoPedidoNormal();
        }
        processamento.processarPedido(pedido);
        pedidoRepository.save(pedido);
    }

    public double calcularValorPedido(Pedido pedido) {
        return calculoStrategy.calcular(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido salvarPedido(Pedido pedido) {
        if (pedido.getDataCriacao() == null) {
            pedido.setDataCriacao(LocalDateTime.now());
        }
        if (pedido.getStatus() == null) {
            pedido.setStatus("PENDENTE");
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido criarPedidoComStatus(String status) {
        Pedido novoPedido = PedidoFactory.criarPedido(status);
        return pedidoRepository.save(novoPedido);
    }

    public Optional<Pedido> buscarPedido(Long id) {
        return pedidoRepository.findById(id);
    }

    public boolean existePedido(Long id) {
        return pedidoRepository.existsById(id);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}

package com.mateus.pedido.controller;

import com.mateus.pedido.model.Pedido;
import com.mateus.pedido.service.PedidoService;
import com.mateus.pedido.repository.ClienteRepository;
import com.mateus.pedido.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
    private final PedidoService pedidoService;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PedidoController(PedidoService pedidoService, ClienteRepository clienteRepository) {
        this.pedidoService = pedidoService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {

        Cliente cliente = clienteRepository.findById(1L).orElseGet(() -> {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome("Cliente Padrão");
            novoCliente.setEmail("padrao@email.com");
            novoCliente.setTelefone("1234567890");
            return clienteRepository.save(novoCliente);
        });

        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus("PENDENTE");

        if ("VIP".equalsIgnoreCase(pedido.getTipoCliente())) {
            pedidoService.definirCalculoStrategy("VIP");
            double valorComDesconto = pedidoService.calcularValorPedido(pedido);
            pedido.setValorTotal(valorComDesconto);
        }

        Pedido novoPedido = pedidoService.salvarPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPedido(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (!pedidoService.existePedido(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Pedido> existingPedido = pedidoService.buscarPedido(id);
        if (existingPedido.isPresent()) {
            Cliente existingCliente = existingPedido.get().getCliente();
            if (existingCliente != null) {
                pedido.setCliente(existingCliente);
            }
        }

        if (pedido.getCliente() == null) {
            Cliente cliente = clienteRepository.findById(1L).orElseGet(() -> {
                Cliente novoCliente = new Cliente();
                novoCliente.setNome("Cliente Padrão");
                novoCliente.setEmail("padrao@email.com");
                novoCliente.setTelefone("1234567890");
                return clienteRepository.save(novoCliente);
            });
            pedido.setCliente(cliente);
        }

        pedido.setId(id);
        if ("VIP".equalsIgnoreCase(pedido.getTipoCliente())) {
            pedidoService.definirCalculoStrategy("VIP");
            double valorComDesconto = pedidoService.calcularValorPedido(pedido);
            pedido.setValorTotal(valorComDesconto);
        }

        Pedido pedidoAtualizado = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarPedido(@PathVariable Long id) {
        if (!pedidoService.existePedido(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.deletarPedido(id);
        logger.info("Pedido {} deletado com sucesso", id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pedido " + id + " deletado com sucesso");
        response.put("status", "OK");

        return ResponseEntity.ok(response);
    }
}
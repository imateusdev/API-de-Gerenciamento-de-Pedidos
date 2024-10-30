# API de Gerenciamento de Pedidos

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-red.svg)](LICENSE)

## 📋 Sobre o Projeto

Sistema de gerenciamento de pedidos desenvolvido com Spring Boot, implementando padrões de projeto como Strategy, Template Method e Factory.

## 🛠 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.5
- H2 Database
- Maven
- Lombok
- JPA/Hibernate

## 🎯 Padrões de Projeto

- **Strategy**: Cálculo de descontos diferentes para clientes VIP e normais
- **Template Method**: Processamento de pedidos com etapas definidas
- **Factory**: Criação de diferentes tipos de pedidos
- **Singleton**: Gerenciamento de configurações globais

 ## 📝 Funcionalidades
- Criação de pedidos com diferentes tipos de clientes
- Cálculo automático de desconto para clientes VIP
- Gerenciamento completo do ciclo de vida do pedido
- Integração com banco de dados H2


## 📦 Instalação

```bash
# Clone o repositório
git clone https://github.com/imateusdev/API-de-Gerenciamento-de-Pedidos.git

# Entre no diretório
cd pedido-api

# Instale as dependências
./mvnw clean install

# Execute o projeto
./mvnw spring-boot:run


🚀 Endpoints
Pedidos
POST /api/pedidos: Criar novo pedido

{
    "descricao": "Pedido teste",
    "valorTotal": 100.0,
    "tipoCliente": "VIP"
}

GET /api/pedidos: Listar todos os pedidos
GET /api/pedidos/{id}: Buscar pedido por ID
PUT /api/pedidos/{id}: Atualizar pedido
DELETE /api/pedidos/{id}: Deletar pedido

💡 Exemplos de Uso
Criar Pedido VIP
curl -X POST http://localhost:8080/api/pedidos \
-H "Content-Type: application/json" \
-d '{
    "descricao": "Pedido VIP",
    "valorTotal": 100.0,
    "tipoCliente": "VIP"
}'

Resposta
{
    "id": 1,
    "dataCriacao": "2024-10-29T18:23:50.3641612",
    "valorTotal": 90.0,
    "status": "PENDENTE",
    "descricao": "Pedido VIP",
    "tipoCliente": "VIP",
    "cliente": {
        "id": 1,
        "nome": "Cliente Padrão",
        "email": "padrao@email.com",
        "telefone": "1234567890"
    }
}

⚙️ Configuração
O arquivo application.properties contém as configurações do banco de dados H2:
spring.datasource.url=jdbc:h2:mem:pedidoapi
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true

Acesse o console H2: http://localhost:8080/h2-console

package com.mateus.pedido.controller;

public class ConfigManager {

    private static ConfigManager instanciaUnica;
    private String configuracaoGlobal;

    private ConfigManager() {
        this.configuracaoGlobal = "Configuração Padrão";
    }

    public static synchronized ConfigManager getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new ConfigManager();
        }
        return instanciaUnica;
    }

    public String getConfiguracaoGlobal() {
        return configuracaoGlobal;
    }

    public void setConfiguracaoGlobal(String configuracaoGlobal) {
        this.configuracaoGlobal = configuracaoGlobal;
    }
}

package com.fwtours.fwalkingtours.enums;

public enum Idioma {
    INGLÉS("Inglés"),
    ESPAÑOL("Español"),
    CHINO("Chino"),
    PORTUGUES("Portugués"),
    FRANCÉS("Francés"),
    ALEMÁN("Alemán"),
    ÁRABE("Árabe");

    private final String label;

    Idioma(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

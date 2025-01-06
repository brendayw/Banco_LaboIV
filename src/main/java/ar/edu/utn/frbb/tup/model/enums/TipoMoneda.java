package ar.edu.utn.frbb.tup.model.enums;

public enum TipoMoneda {
    PESOS("PESOS"),
    DOLARES("DOLARES");

    private final String descripcion;

    TipoMoneda(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoMoneda fromString(String text) {
        for (TipoMoneda tipo : TipoMoneda.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No se pudo encontrar un TipoPersona con la descripción: " + text);
    }

}

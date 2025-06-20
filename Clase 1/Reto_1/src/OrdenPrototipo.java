class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🧪 OrdenPrototipo - Código: " + codigo + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}
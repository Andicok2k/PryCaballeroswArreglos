
public class SistemaCaballeros {

    private Caballero[] caballeros;
    private int cantidad;

    public SistemaCaballeros() {
        caballeros = new Caballero[30];
        cantidad = 0;
        predefinir();
    }

    public void predefinir() {
        registrar(new Caballero(1, "Seiya", "Bronce", "Pegaso",
                8, "Proteger el Santuario", 3, 80000));

        registrar(new Caballero(2, "Shiryu", "Bronce", "Dragon",
                9, "Defender a Atena", 4, 150000));

        registrar(new Caballero(3, "Hyoga", "Bronce", "Cisne",
                8, "Rescatar aldeanos", 3, 200000));

        registrar(new Caballero(4, "Shun", "Bronce", "Andromeda",
                9, "Recuperar la armadura", 4, 300000));

        registrar(new Caballero(5, "Mu", "Oro", "Aries",
                10, "Proteger las doce casas", 5, 500000));
    }

    public int buscarIndicePorId(int id) {

        for (int i = 0; i < cantidad; i++) {
            if (caballeros[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }

    public void validarDatos(Caballero c) {

        if (c == null) {
            throw new IllegalArgumentException(
                    "El caballero no puede ser nulo");
        }

        if (c.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser mayor a cero");
        }

        if (c.getNombre() == null ||
                c.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio");
        }

        if (c.getRango() == null ||
                (!c.getRango().equals("Bronce") &&
                        !c.getRango().equals("Plata") &&
                        !c.getRango().equals("Oro"))) {
            throw new IllegalArgumentException(
                    "El rango no es valido");
        }

        if (c.getConstelacion() == null ||
                c.getConstelacion().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La constelacion es obligatoria");
        }

        if (c.getNivelPoder() < 1 ||
                c.getNivelPoder() > 10) {
            throw new IllegalArgumentException(
                    "El poder debe estar entre 1 y 10");
        }

        if (c.getMision() == null ||
                c.getMision().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La mision es obligatoria");
        }

        if (c.getDificultad() < 1 ||
                c.getDificultad() > 5) {
            throw new IllegalArgumentException(
                    "La dificultad debe estar entre 1 y 5");
        }

        if (!Double.isFinite(c.getRecompensa()) ||
                c.getRecompensa() < 0) {
            throw new IllegalArgumentException(
                    "La recompensa no es valida");
        }
    }

    public void registrar(Caballero c) {

        validarDatos(c);

        if (cantidad == caballeros.length) {
            throw new IllegalArgumentException(
                    "No hay espacio para mas caballeros");
        }

        if (buscarIndicePorId(c.getId()) != -1) {
            throw new IllegalArgumentException(
                    "Ya existe un caballero con ese ID");
        }

        caballeros[cantidad] = c;
        cantidad++;
    }

    public Caballero buscarCaballero(int id) {

        int posicion = buscarIndicePorId(id);

        if (posicion == -1) {
            throw new IllegalArgumentException(
                    "No existe un caballero con ese ID");
        }

        return caballeros[posicion];
    }

    public void modificarCaballero(
            int id, String nombre, String rango,
            String constelacion, int poder,
            String mision, int dificultad,
            double recompensa) {

        Caballero actual = buscarCaballero(id);

        Caballero nuevo = new Caballero(
                id, nombre, rango, constelacion,
                poder, mision, dificultad, recompensa);

        validarDatos(nuevo);

        actual.setNombre(nombre);
        actual.setRango(rango);
        actual.setConstelacion(constelacion);
        actual.setNivelPoder(poder);
        actual.setMision(mision);
        actual.setDificultad(dificultad);
        actual.setRecompensa(recompensa);
    }

    public Caballero[] listarCaballeros() {

        Caballero[] lista = new Caballero[cantidad];

        for (int i = 0; i < cantidad; i++) {
            lista[i] = caballeros[i];
        }

        return lista;
    }

    public int cantidadCaballeros() {
        return cantidad;
    }
}


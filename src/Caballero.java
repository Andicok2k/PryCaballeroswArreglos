
public class Caballero {

    private int id;
    private String nombre;
    private String rango;
    private String constelacion;
    private int nivelPoder;
    private String mision;
    private int dificultad;
    private double recompensa;

    public Caballero(int id, String nombre, String rango,
                     String constelacion, int nivelPoder,
                     String mision, int dificultad,
                     double recompensa) {

        this.id = id;
        this.nombre = nombre;
        this.rango = rango;
        this.constelacion = constelacion;
        this.nivelPoder = nivelPoder;
        this.mision = mision;
        this.dificultad = dificultad;
        this.recompensa = recompensa;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getConstelacion() {
        return constelacion;
    }

    public void setConstelacion(String constelacion) {
        this.constelacion = constelacion;
    }

    public int getNivelPoder() {
        return nivelPoder;
    }

    public void setNivelPoder(int nivelPoder) {
        this.nivelPoder = nivelPoder;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public double getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(double recompensa) {
        this.recompensa = recompensa;
    }

    // APORTE DEL 10% AL FONDO DEL SANTUARIO
    public double calcularFondo() {
        return recompensa * 0.10;
    }

    // IMPUESTO DEL REINO DE ATENA
    public double calcularImpuesto() {

        if (recompensa <= 100000) {
            return 0;
        } else if (recompensa <= 200000) {
            return (recompensa - 100000) * 0.12;
        } else if (recompensa <= 400000) {
            return (recompensa - 200000) * 0.25;
        } else {
            return (recompensa - 400000) * 0.35;
        }
    }

    // RECOMPENSA FINAL
    public double calcularNeto() {
        return recompensa - calcularFondo() - calcularImpuesto();
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Rango: " + rango +
                " | Constelacion: " + constelacion +
                " | Poder: " + nivelPoder +
                " | Mision: " + mision +
                " | Dificultad: " + dificultad +
                " | Recompensa: " + recompensa;
    }
}


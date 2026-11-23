
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {

    private JTabbedPane tabbedPane1;
    private JPanel Ventana;

    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox cmbRango;
    private JTextField txtConstelacion;
    private JTextField txtPoder;
    private JTextField txtRecompensa;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JTextField txtMision;
    private JTextField txtDificultad;

    private JTextField txtBuscarId;
    private JButton btnBuscar;
    private JTextField txtModificarNombre;
    private JComboBox cmbModificarRango;
    private JTextField txtModificarConstelacion;
    private JTextField txtModificarPoder;
    private JTextField txtModificarMision;
    private JTextField txtModificarDificultad;
    private JTextField txtModificarRecompensa;
    private JButton btnModificar;

    private JList listaCaballeros;
    private JButton btnListar;

    private JTextField txtIdInforme;
    private JButton btnCalcular;
    private JTextArea areaInforme;
    private JButton btnLimpiarInforme;

    SistemaCaballeros sistema = new SistemaCaballeros();

    // Guarda el ID que se busco para modificar
    private int idSeleccionado = -1;

    public Ventana() {


        areaInforme.setEditable(false);

        // REGISTRAR
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = leerEntero(txtId, "ID");
                    String nombre = leerTexto(txtNombre, "Nombre");
                    String rango = cmbRango.getSelectedItem().toString();
                    String constelacion = leerTexto(
                            txtConstelacion, "Constelacion");
                    int poder = leerEntero(txtPoder, "Nivel de poder");
                    String mision = leerTexto(txtMision, "Mision");
                    int dificultad = leerEntero(
                            txtDificultad, "Dificultad");
                    double recompensa = leerDecimal(
                            txtRecompensa, "Recompensa");

                    Caballero caballero = new Caballero(
                            id, nombre, rango, constelacion,
                            poder, mision, dificultad, recompensa);

                    sistema.registrar(caballero);

                    JOptionPane.showMessageDialog(null,
                            "Caballero registrado correctamente");

                    limpiarRegistro();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // LIMPIAR REGISTRO
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarRegistro();
            }
        });

        // BUSCAR PARA MODIFICAR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    idSeleccionado = -1;
                    limpiarModificacion();

                    if (sistema.cantidadCaballeros() == 0) {
                        throw new Exception(
                                "No hay caballeros registrados");
                    }

                    int id = leerEntero(txtBuscarId, "ID");

                    Caballero c = sistema.buscarCaballero(id);

                    txtModificarNombre.setText(c.getNombre());
                    cmbModificarRango.setSelectedItem(c.getRango());
                    txtModificarConstelacion.setText(
                            c.getConstelacion());
                    txtModificarPoder.setText(
                            String.valueOf(c.getNivelPoder()));
                    txtModificarMision.setText(c.getMision());
                    txtModificarDificultad.setText(
                            String.valueOf(c.getDificultad()));
                    txtModificarRecompensa.setText(
                            String.valueOf(c.getRecompensa()));

                    idSeleccionado = id;

                    JOptionPane.showMessageDialog(null,
                            "Caballero encontrado");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // MODIFICAR
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (sistema.cantidadCaballeros() == 0) {
                        throw new Exception(
                                "No hay caballeros registrados");
                    }

                    if (idSeleccionado == -1) {
                        throw new Exception(
                                "Primero debes buscar un caballero");
                    }

                    int id = leerEntero(txtBuscarId, "ID");

                    if (id != idSeleccionado) {
                        throw new Exception(
                                "El ID cambio. Presiona Buscar nuevamente");
                    }

                    String nombre = leerTexto(
                            txtModificarNombre, "Nombre");
                    String rango = cmbModificarRango
                            .getSelectedItem().toString();
                    String constelacion = leerTexto(
                            txtModificarConstelacion, "Constelacion");
                    int poder = leerEntero(
                            txtModificarPoder, "Nivel de poder");
                    String mision = leerTexto(
                            txtModificarMision, "Mision");
                    int dificultad = leerEntero(
                            txtModificarDificultad, "Dificultad");
                    double recompensa = leerDecimal(
                            txtModificarRecompensa, "Recompensa");

                    sistema.modificarCaballero(
                            id, nombre, rango, constelacion,
                            poder, mision, dificultad, recompensa);

                    JOptionPane.showMessageDialog(null,
                            "Caballero modificado correctamente");

                    idSeleccionado = -1;
                    txtBuscarId.setText("");
                    limpiarModificacion();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // LISTAR
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel<Caballero> dlm = new DefaultListModel<>();

                if (sistema.cantidadCaballeros() == 0) {
                    listaCaballeros.setModel(dlm);
                    JOptionPane.showMessageDialog(null,
                            "No hay caballeros registrados");
                    return;
                }

                for (Caballero c : sistema.listarCaballeros()) {
                    dlm.addElement(c);
                }
                listaCaballeros.setModel(dlm);
            }
        });

        // CALCULAR INFORME
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    areaInforme.setText("");

                    if (sistema.cantidadCaballeros() == 0) {
                        throw new Exception(
                                "No hay caballeros registrados");
                    }

                    int id = leerEntero(txtIdInforme, "ID");

                    Caballero c = sistema.buscarCaballero(id);

                    String informe =
                            "INFORME DEL CABALLERO\n" +
                                    "----------------------------------\n" +
                                    "Nombre: " + c.getNombre() + "\n" +
                                    "Rango: " + c.getRango() + "\n" +
                                    "Constelacion: " + c.getConstelacion() + "\n" +
                                    "Nivel de poder: " + c.getNivelPoder() + "\n" +
                                    "Mision: " + c.getMision() + "\n" +
                                    "Dificultad: " + c.getDificultad() + "\n" +
                                    "----------------------------------\n" +
                                    String.format("Recompensa: %.2f\n",
                                            c.getRecompensa()) +
                                    String.format("Fondo del Santuario: %.2f\n",
                                            c.calcularFondo()) +
                                    String.format("Impuesto del Reino: %.2f\n",
                                            c.calcularImpuesto()) +
                                    String.format("Recompensa neta: %.2f\n",
                                            c.calcularNeto());

                    areaInforme.setText(informe);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // LIMPIAR INFORME
        btnLimpiarInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdInforme.setText("");
                areaInforme.setText("");
            }
        });
    }

    // VALIDAR CAMPOS DE TEXTO
    public String leerTexto(JTextField campo, String nombre)
            throws Exception {

        String texto = campo.getText().trim();

        if (texto.isEmpty()) {
            throw new Exception(
                    "El campo " + nombre + " es obligatorio");
        }

        return texto;
    }

    // VALIDAR NUMEROS ENTEROS
    public int leerEntero(JTextField campo, String nombre)
            throws Exception {

        String texto = leerTexto(campo, nombre);

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            throw new Exception(
                    "El campo " + nombre +
                            " debe contener un numero entero valido");
        }
    }

    // VALIDAR NUMEROS DECIMALES
    public double leerDecimal(JTextField campo, String nombre)
            throws Exception {

        String texto = leerTexto(campo, nombre);

        try {
            double numero = Double.parseDouble(texto);

            if (Double.isNaN(numero) ||
                    Double.isInfinite(numero)) {
                throw new Exception(
                        "La recompensa debe ser un numero valido");
            }

            return numero;

        } catch (NumberFormatException ex) {
            throw new Exception(
                    "El campo " + nombre +
                            " debe contener un numero valido");
        }
    }

    // LIMPIAR REGISTRO
    public void limpiarRegistro() {
        txtId.setText("");
        txtNombre.setText("");
        cmbRango.setSelectedIndex(0);
        txtConstelacion.setText("");
        txtPoder.setText("");
        txtMision.setText("");
        txtDificultad.setText("");
        txtRecompensa.setText("");
    }

    // LIMPIAR MODIFICACION
    public void limpiarModificacion() {
        txtModificarNombre.setText("");
        cmbModificarRango.setSelectedIndex(0);
        txtModificarConstelacion.setText("");
        txtModificarPoder.setText("");
        txtModificarMision.setText("");
        txtModificarDificultad.setText("");
        txtModificarRecompensa.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}






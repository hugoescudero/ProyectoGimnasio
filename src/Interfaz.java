import Model.Persona;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interfaz {
    private static JFrame frame;
    private static List<Persona> listaUsuarios = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("FITESC");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            //PONER UNA IMAGEN DE FONDO DE PANTALLA
            String imagePath = "C:\\Users\\hugoe\\Pictures\\FITESC (2).png";
            frame.setContentPane(new JLabel(new ImageIcon(imagePath)));
            frame.setLayout(new FlowLayout());

            JPanel panel = new JPanel(new GridBagLayout());
            frame.add(panel);
            placeComponents(panel);

            frame.setVisible(true);
        });
    }

    private static void placeComponents(JPanel panel) {
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());

        JButton registroButton = new JButton("Registrar usuario");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(registroButton, constraints);

        JButton inicioSesionButton = new JButton("Iniciar sesión");
        constraints.gridy = 1;
        panel.add(inicioSesionButton, constraints);

        JButton salirButton = new JButton("Salir");
        constraints.gridy = 2;
        panel.add(salirButton, constraints);

        // Acción para el botón de registrar usuario
        registroButton.addActionListener(e -> registrarUsuario());

        // Acción para el botón de iniciar sesión
        inicioSesionButton.addActionListener(e -> {
            try {
                if (iniciarSesion()) {
                    // Si la sesión se inicia correctamente, mostrar la calculadora de calorías
                    mostrarCalculadoraCalorias();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        // Acción para el botón de salir
        salirButton.addActionListener(e -> System.exit(0));
    }

    private static void registrarUsuario() {
        JPanel registroPanel = new JPanel(new GridLayout(8, 2));
        registroPanel.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        registroPanel.add(nombreField);
        registroPanel.add(new JLabel("Primer Apellido:"));
        JTextField apellido1Field = new JTextField();
        registroPanel.add(apellido1Field);
        registroPanel.add(new JLabel("Segundo Apellido:"));
        JTextField apellido2Field = new JTextField();
        registroPanel.add(apellido2Field);
        registroPanel.add(new JLabel("Teléfono:"));
        JTextField telefonoField = new JTextField();
        registroPanel.add(telefonoField);
        registroPanel.add(new JLabel("DNI:"));
        JTextField dniField = new JTextField();
        registroPanel.add(dniField);
        registroPanel.add(new JLabel("Correo Electrónico:"));
        JTextField correoField = new JTextField();
        registroPanel.add(correoField);
        registroPanel.add(new JLabel("Contraseña:"));
        JPasswordField contrasenaField = new JPasswordField();
        registroPanel.add(contrasenaField);

        int result = JOptionPane.showConfirmDialog(frame, registroPanel,
                "Registrar Usuario", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String apellido1 = apellido1Field.getText();
            String apellido2 = apellido2Field.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String dni = dniField.getText();
            String correo = correoField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            Persona usuario = new Persona(telefono, nombre, apellido1, apellido2, dni, correo, contrasena);

            try {
                if (estaUsuario(usuario.getDNI()) == null && estaUsuario2(usuario.getCorreo()) == null) {
                    listaUsuarios.add(usuario);
                    guardarCuentaEnArchivo(usuario.getCorreo(), usuario.getContrasena());
                    JOptionPane.showMessageDialog(frame, "Usuario añadido con éxito a nuestra base de datos");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se puede agregar porque ya está en nuestra base de datos.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al guardar en el archivo");
            }
        }
    }

    private static boolean iniciarSesion() throws IOException {
        JPanel inicioSesionPanel = new JPanel(new GridLayout(2, 2));
        inicioSesionPanel.add(new JLabel("Correo Electrónico:"));
        JTextField correoField = new JTextField();
        inicioSesionPanel.add(correoField);
        inicioSesionPanel.add(new JLabel("Contraseña:"));
        JPasswordField contrasenaField = new JPasswordField();
        inicioSesionPanel.add(contrasenaField);

        int result = JOptionPane.showConfirmDialog(frame, inicioSesionPanel,
                "Iniciar Sesión", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String correo = correoField.getText();
            String contrasena = new String(contrasenaField.getPassword());

            for (Persona usuario : listaUsuarios) {
                if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getContrasena().equals(contrasena)) {
                    JOptionPane.showMessageDialog(frame, "Inicio de sesión correcto");
                    return true;
                }
            }

            JOptionPane.showMessageDialog(frame, "El correo electrónico o la contraseña son incorrectos");
        }

        return false;
    }

    private static void mostrarCalculadoraCalorias() {
        JPanel calculadoraPanel = new JPanel(new GridLayout(4, 2));
        calculadoraPanel.add(new JLabel("Peso:"));
        JTextField pesoField = new JTextField();
        calculadoraPanel.add(pesoField);
        calculadoraPanel.add(new JLabel("Altura:"));
        JTextField alturaField = new JTextField();
        calculadoraPanel.add(alturaField);
        calculadoraPanel.add(new JLabel("Género (Hombre/Mujer):"));
        JTextField sexoField = new JTextField();
        calculadoraPanel.add(sexoField);
        calculadoraPanel.add(new JLabel("Edad:"));
        JTextField edadField = new JTextField();
        calculadoraPanel.add(edadField);

        int result = JOptionPane.showConfirmDialog(frame, calculadoraPanel,
                "Calculadora de Calorías", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            double peso = Double.parseDouble(pesoField.getText());
            int altura = Integer.parseInt(alturaField.getText());
            String sexo = sexoField.getText();
            int edad = Integer.parseInt(edadField.getText());

            double calorias = 0;

            if (sexo.equalsIgnoreCase("Hombre")) {
                calorias = ((10 * peso) + (6.25 * altura) - (5 * edad) + 5) * 1.5;
            } else if (sexo.equalsIgnoreCase("Mujer")) {
                calorias = ((10 * peso) + (6.25 * altura) - (5 * edad) - 161) * 1.5;
            }

            int opcion;
            do {
                // Utilizando JOptionPane para mostrar las opciones y solicitar la selección del usuario
                String opcionStr = JOptionPane.showInputDialog(frame,
                        "¿Qué quieres saber?\n" +
                                "1: Calorias de mantenimiento\n" +
                                "2: Calorias pérdida de grasa\n" +
                                "3: Calorias ganancia de peso\n" +
                                "4: Salir");
                opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(frame, String.format("Tus calorías de mantenimiento son %.0f:", calorias));
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(frame, String.format("Tus calorías para pérdida de peso son %.0f:", calorias - 300));
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(frame, String.format("Tus calorías para ganancia de peso son %.0f:", calorias + 300));
                        break;
                }
            } while (opcion != 4);
        }
    }

    private static Persona estaUsuario(String dni) {
        for (Persona usuario : listaUsuarios) {
            if (usuario.getDNI().equalsIgnoreCase(dni)) {
                return usuario;
            }
        }
        return null;
    }

    private static Persona estaUsuario2(String correo) {
        for (Persona usuario : listaUsuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                return usuario;
            }
        }
        return null;
    }

    // Método para guardar la cuenta en un archivo
    private static void guardarCuentaEnArchivo(String correo, String contrasena) throws IOException {
        try (FileWriter writer = new FileWriter("cuentasGimnasio.txt", true)) {
            writer.write("Correo: " + correo + ", Contraseña: " + contrasena + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

package Model;

import Model.Aplicacion;
import Model.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Interfaz extends JFrame {

    private Aplicacion fitesc;
    private JTextField nombreField, apellido1Field, apellido2Field, edadField, telefonoField,
            DNIField, pesoField, alturaField, sexoField, correoField, contrasenaField;

    public Interfaz() {
        fitesc = new Aplicacion("Fitesc", 1);

        // Configuración del JFrame
        setTitle("Fitesc");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(13, 2));

        // Campos de entrada
        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Primer Apellido:"));
        apellido1Field = new JTextField();
        add(apellido1Field);

        add(new JLabel("Segundo Apellido:"));
        apellido2Field = new JTextField();
        add(apellido2Field);

        add(new JLabel("Edad:"));
        edadField = new JTextField();
        add(edadField);

        add(new JLabel("Número de Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("DNI:"));
        DNIField = new JTextField();
        add(DNIField);

        add(new JLabel("Peso:"));
        pesoField = new JTextField();
        add(pesoField);

        add(new JLabel("Altura:"));
        alturaField = new JTextField();
        add(alturaField);

        add(new JLabel("Género (Hombre/Mujer):"));
        sexoField = new JTextField();
        add(sexoField);

        add(new JLabel("Correo Electrónico:"));
        correoField = new JTextField();
        add(correoField);

        add(new JLabel("Contraseña:"));
        contrasenaField = new JTextField();
        add(contrasenaField);

        // Botones
        JButton registrarBtn = new JButton("Registrar usuario");
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        add(registrarBtn);

        JButton iniciarSesionBtn = new JButton("Iniciar sesión");
        iniciarSesionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        add(iniciarSesionBtn);

        JButton salirBtn = new JButton("Salir");
        salirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("----HASTA PRONTO----");
                System.exit(0);
            }
        });
        add(salirBtn);

        // Mostrar el JFrame
        setVisible(true);
    }

    private void registrarUsuario() {
        try {
            int edad = Integer.parseInt(edadField.getText());
            int telefono = Integer.parseInt(telefonoField.getText());
            double peso = Double.parseDouble(pesoField.getText());
            int altura = Integer.parseInt(alturaField.getText());

            Persona usuario = new Persona(telefono, nombreField.getText(), apellido1Field.getText(),
                    apellido2Field.getText(), DNIField.getText(), correoField.getText(),
                    contrasenaField.getText());

            fitesc.aniadirUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Introduce valores válidos en los campos numéricos.");
        }
    }

    private void iniciarSesion() {
        String correo = correoField.getText();
        String contrasena = contrasenaField.getText();

        if (fitesc.iniciarSesion(correo, contrasena)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
        } else {
            JOptionPane.showMessageDialog(this, "Inicio de sesión fallido. Verifique sus credenciales.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interfaz();
            }
        });
    }
}

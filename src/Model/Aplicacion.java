package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {
    Scanner sc = new Scanner(System.in);
    private String nombre;
    private int tamanio;
    private ArrayList<Persona> listaUsuarios;

    public Aplicacion() {
        listaUsuarios = new ArrayList<Persona>();
    }

    public Aplicacion(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        listaUsuarios = new ArrayList<Persona>();
    }


    private Persona estaUsuario(String dni) {
        for (Persona persona : listaUsuarios) {
            if (persona.getDNI().equalsIgnoreCase(dni)) {
                // si está retorno la persona
                return persona;
            }
        }
        // si no está la persona retorno null
        return null;
    }

    private Persona estaUsuario2(String correo) {
        for (Persona persona : listaUsuarios) {
            if (persona.getCorreo().equalsIgnoreCase(correo)) {
                // si está retorno la persona
                return persona;
            }
        }
        // si no está la persona retorno null
        return null;
    }

    public void aniadirUsuario(Persona persona) throws IOException {
        if (estaUsuario(persona.getDNI()) == null && persona.getEdad() >= 18 && estaUsuario2(persona.getCorreo())== null) {
            listaUsuarios.add(persona);
            guardarCuentaEnArchivo(persona.getCorreo(), persona.getContrasena());
            System.out.println("Usuario añadido con exito a nuestra base de datos");
        } else {
            System.out.println("No se puede agregar porque ya está en nuestra base de datos o eres menor de edad");
        }
    }

    public void borrarUsuario(String dni) {
        if (estaUsuario(dni) != null) {
            listaUsuarios.remove(estaUsuario(dni));
        } else {
            System.out.println("Ese usuario no se encuentra en nuestra base de datos");
        }
    }

    public void iniciarSesion(String correo, String contrasena) {
        if (estaUsuario2(correo) != null) {
            if (estaUsuario2(correo).getContrasena().equalsIgnoreCase(contrasena)) {
                System.out.println("Inicio de sesión correcto");
            } else {
                System.out.println("El correo electrónico o la contraseña son incorrectos");
            }
        }
    }
    private void guardarCuentaEnArchivo(String correo, String contraseña) throws IOException {
        FileWriter writer = new FileWriter("cuentasGimnasio.txt", true);
        writer.write("Correo: " + correo + ", Contraseña: " + contraseña + "\n");
        writer.close();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}

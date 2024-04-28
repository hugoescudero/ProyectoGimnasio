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
        if (estaUsuario(persona.getDNI()) == null && estaUsuario2(persona.getCorreo())== null) {
            listaUsuarios.add(persona);
            guardarCuentaEnArchivo(persona.getCorreo(), persona.getContrasena());
        }
    }


    public boolean iniciarSesion(String correo, String contrasena) {
        if (estaUsuario2(correo) != null) {
            if (estaUsuario2(correo).getContrasena().equalsIgnoreCase(contrasena)) {
                return true;
            }
        }
        return false;
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

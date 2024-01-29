import Model.Aplicacion;
import Model.Persona;

import java.io.IOException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int edad, telefono, altura, opcion;
        String nombre, apellido1, apellido2, DNI, correo, contrasena, sexo;
        double peso;
        Aplicacion fitesc = new Aplicacion("Fitesc",1);

        do {
            System.out.println("BIENVENIDO A FITESC");
            System.out.println("--------------------");
            System.out.println("1:Registrar usuario");
            System.out.println("2:Iniciar sesión");
            System.out.println("3:Salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Introduce nombre");
                    nombre = sc.next();
                    System.out.println("Introduce primer apellido");
                    apellido1 = sc.next();
                    System.out.println("Introduce segundo apellido");
                    apellido2 = sc.next();
                    System.out.println("Introduce edad");
                    edad = sc.nextInt();
                    System.out.println("Intoduce numero de telefono");
                    telefono = sc.nextInt();
                    System.out.println("Introduce DNI");
                    DNI = sc.next();
                    System.out.println("Introduce peso");
                    peso = sc.nextDouble();
                    System.out.println("Introduce altura en cm");
                    altura = sc.nextInt();
                    System.out.println("Introduce género (Hombre/Mujer)");
                    sexo = sc.next();
                    System.out.println("Introduce correo electronico");
                    correo = sc.next();
                    System.out.println("Introduce contraseña");
                    contrasena = sc.next();
                    Persona usuario = new Persona(edad,telefono,nombre,apellido1,apellido2,DNI,correo,contrasena,sexo,peso,altura);
                    fitesc.aniadirUsuario(usuario);
                    break;
                case 2:
                    System.out.println("Introduce el correo electronico");
                    correo = sc.next();
                    System.out.println("Introduce la contraseña");
                    contrasena = sc.next();
                    fitesc.iniciarSesion(correo,contrasena);
                    break;
                case 3:
                    System.out.println("----HASTA PRONTO----");
                    break;
            }
        }while (opcion!=3);
    }
}

package Model;

import Model.Aplicacion;
import Model.Persona;

import java.io.IOException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int edad, telefono, altura, opcion;
        String nombre, apellido1, apellido2, DNI, correo, contrasena, sexo;
        double peso,calorias = 0;
        Aplicacion fitesc = new Aplicacion("Fitesc",1);

        do {
            System.out.println("--------------------");
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
                    System.out.println("Intoduce numero de telefono");
                    telefono = sc.nextInt();
                    System.out.println("Introduce DNI");
                    DNI = sc.next();
                    System.out.println("Introduce correo electronico");
                    correo = sc.next();
                    System.out.println("Introduce contraseña");
                    contrasena = sc.next();
                    Persona usuario = new Persona(telefono,nombre,apellido1,apellido2,DNI,correo,contrasena);
                    fitesc.aniadirUsuario(usuario);
                    break;
                case 2:
                    System.out.println("Introduce el correo electrónico");
                    correo = sc.next();
                    System.out.println("Introduce la contraseña");
                    contrasena = sc.next();
                    if (fitesc.iniciarSesion(correo,contrasena)){
                        System.out.println("Calculadora de calorias");
                        System.out.println("Introduce tu peso");
                        peso= sc.nextDouble();
                        System.out.println("Introduce tu altura");
                        altura = sc.nextInt();
                        System.out.println("Introduce tu genero (Hombre/Mujer)");
                        sexo = sc.next();
                        System.out.println("Introduce tu edad");
                        edad = sc.nextInt();
                        if (sexo.equalsIgnoreCase("Hombre")){
                            calorias = ((10*peso)+(6.25*altura)-(5*edad)+5)*1.5;
                        } else if (sexo.equalsIgnoreCase("Mujer")) {
                            calorias = ((10*peso)+(6.25*altura)-(5*edad)-161)*1.5;
                        }

                        do {
                            System.out.println("¿Que quieres saber?");
                            System.out.println("1: Calorias de mantenimiento");
                            System.out.println("2: Calorias perdida de gras");
                            System.out.println("3: Calorias ganancia de peso");
                            System.out.println("4: Salir");
                            opcion = sc.nextInt();
                            switch (opcion){
                                case 1:
                                    System.out.printf("Tus calorias de mantenimiento son %.0f:\n",calorias);
                                    break;
                                case 2:
                                    System.out.printf("Tus calorias para perdida de peso son %.0f:\n",calorias-300);
                                    break;
                                case 3:
                                    System.out.printf("Tus calorias para ganancia de peso son %.0f:\n",calorias+300);
                                    break;
                            }
                        }while (opcion != 4);
                    }

                    break;
                case 3:
                    System.out.println("----HASTA PRONTO----");
                    break;
            }
        }while (opcion!=3);
    }
}
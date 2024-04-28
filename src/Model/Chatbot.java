package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chatbot {
    private String nombre, creador;

    public Chatbot() {
    }

    public Chatbot(String nombre, String creador) {
        this.nombre = nombre;
        this.creador = creador;
    }

    Scanner sc = new Scanner(System.in);
    public void calorias(String mensaje){
        if (mensaje.contains("calorias")){
            double calorias = 0;
            System.out.println("Claro, voy a calcular tus calorias de mantenimiento");
            System.out.println("Introduce tu peso");
            double peso = sc.nextDouble();
            System.out.println("Introduce tu altura");
            double altura = sc.nextDouble();
            System.out.println("Introduce tu género (Hombre/Mujer)");
            String sexo = sc.next();
            System.out.println("Introduce tu edad");
            int edad = sc.nextInt();
            if (sexo.equalsIgnoreCase("Hombre")){
                calorias = ((10*peso)+(6.25*altura)-(5*edad)+5)*1.5;
            } else if (sexo.equalsIgnoreCase("Mujer")) {
                calorias = ((10*peso)+(6.25*altura)-(5*edad)-161)*1.5;
            }
            System.out.println("Sus calorias de mantenimiento son: "+calorias);
            System.out.println("¿Quiere que le muestre las calorias de ganancia de peso o pérdida de peso?");
            String contestacion = sc.next();
            if (contestacion.equalsIgnoreCase("Si")){
                System.out.println("¿Calorias de ganancia o de pérdida de peso?");
                String respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("Ganancia")){
                    System.out.println("Las calorías que tiene que consumir para ganar peso son: "+(calorias + 300));
                } else if (respuesta.equalsIgnoreCase("Perdida")) {
                    System.out.println("Las calorías que tiene que consumir para perder peso son: "+(calorias - 300));
                }
            }
        }
    }

    public void entrenador(String mensaje){
        if (mensaje.contains("entrenador")){
            System.out.println("¿Con que entrenador quiere reservar?");
            System.out.println("Javi");
            System.out.println("Pepe");
            System.out.println("Pablo");
            String eleccionEntrenador = sc.nextLine();
            if (eleccionEntrenador.contains("Javi")){
                String entrenador = "Javi";
                System.out.println("¿A nombre de quién?");
                String usuario = sc.next();
                int dia = diaCita();
                double hora = horaCita();
                if (confirmacion(dia,hora)){
                    System.out.println("Cita confirmada");
                    guardarCita(entrenador,dia,hora,usuario);
                } else if (!confirmacion(dia,hora)) {
                    System.out.println("De acuerdo, anulo la cita");
                }
            } else if (eleccionEntrenador.contains("Pepe")) {
                String entrenador = "Pepe";
                System.out.println("¿A nombre de quién?");
                String usuario = sc.next();
                int dia = diaCita();
                double hora = horaCita();
                if (confirmacion(dia,hora)){
                    System.out.println("Cita confirmada");
                    guardarCita(entrenador,dia,hora,usuario);
                } else if (!confirmacion(dia,hora)) {
                    System.out.println("De acuerdo, anulo la cita");
                }
            } else if (eleccionEntrenador.contains("Pablo")) {
                String entrenador = "Pablo";
                System.out.println("¿A nombre de quién?");
                String usuario = sc.next();
                int dia = diaCita();
                double hora = horaCita();
                if (confirmacion(dia,hora)){
                    System.out.println("Cita confirmada");
                    guardarCita(entrenador,dia,hora,usuario);
                } else if (!confirmacion(dia,hora)) {
                    System.out.println("De acuerdo, anulo la cita");
                }
            }
        }
    }

    public void clase(String mensaje){
        if (mensaje.contains("clase")){
            System.out.println("¿En qué clase quieres reservar?");
            System.out.println("Zumba");
            System.out.println("Aquagym.txt");
            System.out.println("Bodycombat");
            String eleccionClase = sc.nextLine();
            if (eleccionClase.contains("Zumba")){
                String clase = "Zumba";
                System.out.println("¿A nombre de quién?");
                String usuario = sc.next();
                int dia = diaCita();
                double hora = horaCita();
                if (confirmacion(dia,hora)){
                    System.out.println("Cita confirmada");
                    guardarCita(clase,dia,hora,usuario);
                } else if (!confirmacion(dia,hora)) {
                    System.out.println("De acuerdo, anulo la cita");
                }
            } else if (eleccionClase.contains("Aquagym.txt")) {
                String clase = "Aquagym";
                System.out.println("¿A nombre de quién?");
                String usuario = sc.next();
                int dia = diaCita();
                double hora = horaCita();
                if (confirmacion(dia,hora)){
                    System.out.println("Cita confirmada");
                    guardarCita(clase,dia,hora,usuario);
                } else if (!confirmacion(dia,hora)) {
                    System.out.println("De acuerdo, anulo la cita");
                }
            } else if (eleccionClase.contains("Bodycombat")) {
                String entrenador = "Bodycombat";
                System.out.println("¿A nombre de quién?");
                String usuario = sc.next();
                int dia = diaCita();
                double hora = horaCita();
                if (confirmacion(dia,hora)){
                    System.out.println("Cita confirmada");
                    guardarCita(entrenador,dia,hora,usuario);
                } else if (!confirmacion(dia,hora)) {
                    System.out.println("De acuerdo, anulo la cita");
                }
            }
        }
    }

    public int diaCita(){
        System.out.println("¿Qué día quiere reservar?");
        int dia = sc.nextInt();
        return dia;
    }
    public double horaCita(){
        System.out.println("¿A qué hora quiere reservar?");
        double hora = sc.nextDouble();
        return hora;
    }
    public boolean confirmacion(int dia, double hora){
        System.out.printf("Va a reservar el día %d a las %.2f horas, ¿está de acuerdo?\n",dia, hora);
        String respuesta = sc.next();
        if (respuesta.equalsIgnoreCase("Si")){
            return true;
        }
        return false;
    }
    public boolean comprobacion(String mensaje){
        if (mensaje.equalsIgnoreCase("Si")){
            return true;
        }
        System.out.println("Hasta pronto!!");
        return false;
    }
    public void guardarCita(String ruta, int dia, double hora, String usuario){
        String path = String.format("src/Resources/%s.txt",ruta);
        File file = new File(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write("\n"+dia+"      "+hora+"     "+usuario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }
}

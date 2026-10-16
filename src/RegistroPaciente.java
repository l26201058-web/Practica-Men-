/*
 * INSITUTO TECNOLOGICO DE PACHUCA
 * FUNDAMENTOS DE PROGRAMACION
 * DIAZ CERON JOSE LUIS   26201058
 * ALVAREZ RODRIGUEZ GADIEL RAMON    26201024
 * 16-10-2026
 * EJERCICIO: Registro Consultorio
 */
import java.util.Scanner; // Importa la clase Scanner para leer la entrada del usuario por consola
public class RegistroPaciente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crea una instancia de Scanner para capturar datos
        int contNinos = 0; // Inicializa el contador de pacientes niños en 0
        int contAdolescentes = 0; // Inicializa el contador de pacientes adolescentes en 0
        int contAdultos = 0; // Inicializa el contador de pacientes adultos en 0
        int contAdultosMayores = 0; // Inicializa el contador de pacientes adultos mayores en 0

        double totalRecaudado = 0.0; // Inicializa el acumulador del dinero total recaudado en 0.0
        double totalDescuentos = 0.0; // Inicializa el acumulador de los descuentos aplicados en 0.0
        int totalPacientes = 0; // Inicializa el contador total de pacientes en 0

        System.out.println("--- Sistema de Registro de Pacientes ---"); // Imprime el título del sistema en pantalla

        while (true) { // Inicia un bucle infinito que se repetirá hasta que se indique lo contrario
            System.out.print("¿Deseas registrar un paciente? (s/n): "); // Pregunta al usuario si desea continuar
            String respuesta = scanner.nextLine().trim().toLowerCase(); // Lee la respuesta, elimina espacios y la pasa a minúsculas

            if (respuesta.equals("n")) { // Comprueba si la respuesta es 'n' para salir del bucle
                break; // Rompe el ciclo while y finaliza el registro
            } else if (!respuesta.equals("s")) { // Comprueba si la respuesta no es 's' ni 'n'
                System.out.println("Opción no válida. Por favor, escribe 's' o 'n'."); // Muestra mensaje de error por opción incorrecta
                continue; // Regresa al inicio del bucle para volver a preguntar
            }

            int edad = 0; // Declara e inicializa la variable local para la edad del paciente
            double costo = 0.0; // Declara e inicializa la variable local para el costo de la consulta

            try { // Inicia un bloque try para manejar posibles errores de formato numérico
                System.out.print("Ingresa la edad del paciente: "); // Solicita la edad en pantalla
                edad = Integer.parseInt(scanner.nextLine()); // Lee la entrada y la convierte a número entero
                if (edad < 0) { // Valida si la edad ingresada es menor a cero
                    System.out.println("La edad no puede ser negativa."); // Muestra mensaje de error
                    continue; // Regresa al inicio del ciclo
                }

                System.out.print("Ingresa el costo de la consulta: "); // Solicita el costo de la consulta
                costo = Double.parseDouble(scanner.nextLine()); // Lee la entrada y la convierte a número decimal
                if (costo < 0) { // Valida si el costo ingresado es menor a cero
                    System.out.println("El costo no puede ser negativo."); // Muestra mensaje de error
                    continue; // Regresa al inicio del ciclo
                }
            } catch (
                    NumberFormatException e) { // Captura el error si el usuario ingresa letras u otros caracteres inválidos
                System.out.println("Por favor, ingresa valores numéricos válidos."); // Muestra mensaje de advertencia
                continue; // Regresa al inicio del ciclo
            }

            String categoria = ""; // Declara la variable para almacenar la categoría del paciente
            double descuento = 0.0; // Declara e inicializa la variable para el descuento del paciente

            if (edad < 12) { // Evalúa si la edad es menor a 12 años
                contNinos++; // Incrementa el contador de niños en 1
                categoria = "Niño"; // Asigna la categoría correspondiente
            } else if (edad >= 12 && edad <= 17) { // Evalúa si la edad está entre 12 y 17 años
                contAdolescentes++; // Incrementa el contador de adolescentes en 1
                categoria = "Adolescente"; // Asigna la categoría correspondiente
            } else if (edad >= 18 && edad <= 59) { // Evalúa si la edad está entre 18 y 59 años
                contAdultos++; // Incrementa el contador de adultos en 1
                categoria = "Adulto"; // Asigna la categoría correspondiente
            } else { // Si no cumple las anteriores, significa que tiene 60 años o más
                contAdultosMayores++; // Incrementa el contador de adultos mayores en 1
                categoria = "Adulto mayor"; // Asigna la categoría correspondiente
                descuento = costo * 0.15; // Calcula el 15% de descuento sobre el costo
            }

            double costoFinal = costo - descuento; // Calcula el costo final restando el descuento al costo original

            totalRecaudado += costoFinal; // Suma el costo final al acumulador de dinero recaudado
            totalDescuentos += descuento; // Suma el descuento actual al acumulador global de descuentos
            totalPacientes++; // Incrementa en 1 el contador total de pacientes atendidos

            System.out.println("-> Categoría: " + categoria); // Muestra la categoría del paciente registrado
            System.out.printf("-> Descuento aplicado: $%.2f%n", descuento); // Muestra el descuento aplicado formateado a dos decimales
            System.out.printf("-> Total a pagar por este paciente: $%.2f%n\n", costoFinal); // Muestra el total a pagar formateado
        }

        System.out.println("\n" + "======================================="); // Imprime una línea separadora estética
        System.out.println("       RESUMEN DEL DÍA"); // Imprime el título del reporte final
        System.out.println("======================================="); // Imprime otra línea separadora estética
        System.out.println("Total de pacientes atendidos: " + totalPacientes); // Muestra el total global de pacientes
        System.out.println(" - Niños: " + contNinos); // Muestra la cantidad total de niños
        System.out.println(" - Adolescentes: " + contAdolescentes); // Muestra la cantidad total de adolescentes
        System.out.println(" - Adultos: " + contAdultos); // Muestra la cantidad total de adultos
        System.out.println(" - Adultos mayores: " + contAdultosMayores); // Muestra la cantidad total de adultos mayores
        System.out.printf("Dinero total recaudado: $%.2f%n", totalRecaudado); // Muestra el dinero total recaudado formateado
        System.out.printf("Total de descuentos aplicados: $%.2f%n", totalDescuentos); // Muestra el total de descuentos formateado

        if (totalPacientes > 0) { // Valida que se haya registrado al menos un paciente para evitar división por cero
            double promedio = totalRecaudado / totalPacientes; // Calcula el promedio pagado por paciente
            System.out.printf("Promedio pagado por paciente: $%.2f%n", promedio); // Muestra el promedio formateado
        } else { // En caso de que no se haya registrado ningún paciente
            System.out.println("No se registraron pacientes en el día."); // Muestra un mensaje informativo
        }
    }
}

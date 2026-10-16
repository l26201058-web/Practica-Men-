/*
 * INSITUTO TECNOLOGICO DE PACHUCA
 * FUNDAMENTOS DE PROGRAMACION
 * DIAZ CERON JOSE LUIS   26201058
 * ALVAREZ RODRIGUEZ GADIEL RAMON    26201024
 * 16-10-2026
 * EJERCICIO: Registro Gasolinera
 */
import java.util.Scanner;
public class RegistroGasolinera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crea una instancia de Scanner para capturar datos

        int totalCargas = 0; // Inicializa el contador del número total de cargas en 0
        int cargasConDescuento = 0; // Inicializa el contador de cargas que recibieron descuento en 0
        double acumLitrosVendidos = 0.0; // Inicializa el acumulador de litros vendidos en 0.0
        double acumImporteSinDescuento = 0.0; // Inicializa el acumulador del importe antes de descuentos en 0.0
        double acumTotalCobrado = 0.0; // Inicializa el acumulador del total cobrado con descuentos en 0.0

        System.out.println("--- Sistema de Registro de Gasolinera ---"); // Imprime el título del sistema en pantalla

        while (true) { // Inicia un bucle infinito que se repetirá hasta que se indique lo contrario
            System.out.print("¿Deseas registrar una carga de combustible? (s/n): "); // Pregunta al usuario si desea continuar
            String respuesta = scanner.nextLine().trim().toLowerCase(); // Lee la respuesta, elimina espacios y la pasa a minúsculas

            if (respuesta.equals("n")) { // Comprueba si la respuesta es 'n' para salir del bucle
                break; // Rompe el ciclo while y finaliza el registro
            } else if (!respuesta.equals("s")) { // Comprueba si la respuesta no es 's' ni 'n'
                System.out.println("Opción no válida. Por favor, escribe 's' o 'n'."); // Muestra mensaje de error por opción incorrecta
                continue; // Regresa al inicio del bucle para volver a preguntar
            }

            double litros = 0.0; // Declara e inicializa la variable local para los litros cargados
            double precioLitro = 0.0; // Declara e inicializa la variable local para el precio por litro

            try { // Inicia un bloque try para manejar posibles errores de formato numérico
                System.out.print("Ingresa los litros cargados: "); // Solicita los litros en pantalla
                litros = Double.parseDouble(scanner.nextLine()); // Lee la entrada y la convierte a número decimal
                if (litros <= 0) { // Valida si los litros son menores o iguales a cero
                    System.out.println("Error: Los litros deben ser mayores que cero."); // Muestra mensaje de error
                    continue; // Regresa al inicio del ciclo
                }

                System.out.print("Ingresa el precio por litro: "); // Solicita el precio por litro
                precioLitro = Double.parseDouble(scanner.nextLine()); // Lee la entrada y la convierte a número decimal
                if (precioLitro <= 0) { // Valida si el precio por litro es menor o igual a cero
                    System.out.println("Error: El precio por litro debe ser mayor que cero."); // Muestra mensaje de error
                    continue; // Regresa al inicio del ciclo
                }
            } catch (NumberFormatException e) { // Captura el error si el usuario ingresa letras u otros caracteres inválidos
                System.out.println("Por favor, ingresa valores numéricos válidos."); // Muestra mensaje de advertencia
                continue; // Regresa al inicio del ciclo
            }

            double importeSinDescuento = litros * precioLitro; // Calcula el importe multiplicando los litros por el precio por litro
            double descuento = 0.0; // Declara e inicializa la variable para el descuento de esta carga

            if (litros >= 40) { // Evalúa si la carga es de 40 litros o más
                descuento = importeSinDescuento * 0.05; // Calcula el 5% de descuento sobre el importe
                cargasConDescuento++; // Incrementa en 1 el contador de cargas que recibieron descuento
            }
            double totalCobrado = importeSinDescuento - descuento; // Calcula el total a cobrar restando el descuento al importe
            totalCargas++; // Incrementa en 1 el contador total de cargas registradas
            acumLitrosVendidos += litros; // Suma los litros actuales al acumulador global de litros vendidos
            acumImporteSinDescuento += importeSinDescuento; // Suma el importe actual al acumulador sin descuentos
            acumTotalCobrado += totalCobrado; // Suma el total cobrado al acumulador global de dinero
            System.out.printf("-> Importe de la carga: $%.2f%n", importeSinDescuento); // Muestra el importe de la carga formateado a dos decimales
            if (descuento > 0) { // Valida si se aplicó un descuento en esta carga
                System.out.printf("-> Descuento aplicado (5%%): $%.2f%n", descuento); // Muestra el descuento aplicado formateado
            }
            System.out.printf("-> Total a cobrar por esta carga: $%.2f%n\n", totalCobrado); // Muestra el total a cobrar formateado
        }
        System.out.println("\n" + "======================================="); // Imprime una línea separadora estética
        System.out.println("       RESUMEN DE VENTAS DEL DÍA"); // Imprime el título del reporte final
        System.out.println("======================================="); // Imprime otra línea separadora estética
        System.out.println("Número total de cargas registradas: " + totalCargas); // Muestra el número total de cargas
        System.out.printf("Total de litros vendidos: %.2f L%n", acumLitrosVendidos); // Muestra el total de litros vendidos formateado
        System.out.printf("Importe total (antes de descuentos): $%.2f%n", acumImporteSinDescuento); // Muestra el importe total sin descuentos formateado
        System.out.printf("Total cobrado (con descuentos): $%.2f%n", acumTotalCobrado); // Muestra el total cobrado global formateado
        System.out.println("Cargas que recibieron descuento (>= 40 L): " + cargasConDescuento); // Muestra cuántas cargas tuvieron descuento
        if (totalCargas > 0) { // Valida que se haya registrado al menos una carga para evitar división por cero
            double promedioPorCarga = acumTotalCobrado / totalCargas; // Calcula el importe promedio por carga
            System.out.printf("Importe promedio por carga: $%.2f%n", promedioPorCarga); // Muestra el promedio formateado
        } else { // En caso de que no se haya registrado ninguna carga
            System.out.println("No se registraron cargas de combustible en el día."); // Muestra un mensaje informativo
        }
    }
}
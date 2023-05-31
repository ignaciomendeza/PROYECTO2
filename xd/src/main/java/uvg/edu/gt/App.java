package uvg.edu.gt;

import uvg.edu.gt.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner teclado = new Scanner(System.in);
        ControladorBD foody = new ControladorBD();
        System.out.println("---Bienvenido a Foody Gt---");
        System.out.println("Seleccione la categoria de precios: (bajo, medio, alto)");
        String precio = teclado.nextLine();
        List<Restaurant> restaurantes = foody.getRestaurantByPrice(precio);
        System.out.println("El listado de restaurantes recomendados son:");
        restaurantes.forEach(System.out::println);

        System.out.println("\nSeleccione la categoria de tipo: (comida rapida, comida formal)");
        String tipo = teclado.nextLine();
        List<Restaurant> restaurantes1 = foody.getRestaurantByTipo(tipo);
        System.out.println("El listado de restaurantes recomendados son:");
        restaurantes1.forEach(System.out::println);

    }
}

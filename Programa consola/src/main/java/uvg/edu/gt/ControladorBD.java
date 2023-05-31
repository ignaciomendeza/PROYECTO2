package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.*;
import org.neo4j.ogm.drivers.bolt.driver.BoltDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import uvg.edu.gt.model.Restaurant;

public class ControladorBD implements AutoCloseable{
    private final Driver _driver;
    private final SessionFactory _sessionFactory;
    private final String MODELS_PACKAGE = "uvg.edu.gt.model";
    private final String CONNECTION_URI = "bolt://54.167.142.225:7687";
    private final String USERNAME = "neo4j";
    private final String PASSWORD = "compression-kettle-print";

    @Override
    public void close() throws Exception {
        _sessionFactory.close();
        _driver.close();
    }

    public ControladorBD(){
        _driver = GraphDatabase.driver("bolt://54.167.142.225:7687",
                AuthTokens.basic("neo4j","compression-kettle-print"));
        _sessionFactory = new SessionFactory(new BoltDriver(_driver), MODELS_PACKAGE);
    }

    public List<Restaurant> getRestaurantByPrice(String precio){

        Session session = _sessionFactory.openSession();
        Iterable<Restaurant> restaurants = session.query(Restaurant.class,
                "MATCH (n:restaurante WHERE n.precio = $precio) RETURN n",
                Map.of("precio", precio));
        return toList(restaurants);
    }

    public List<Restaurant> getRestaurantByTipo(String tipo){

        Session session = _sessionFactory.openSession();
        Iterable<Restaurant> restaurants = session.query(Restaurant.class,
                "MATCH (n:restaurante WHERE n.tipo = $tipo) RETURN n",
                Map.of("tipo", tipo));
        return toList(restaurants);
    }

    public List<Restaurant> getRestaurantByAmbiente(String ambiente){

        Session session = _sessionFactory.openSession();
        Iterable<Restaurant> restaurants = session.query(Restaurant.class,
                "MATCH (n:restaurante WHERE n.ambiente = $ambiente) RETURN n",
                Map.of("ambiente", ambiente));
        return toList(restaurants);
    }

    public List<Restaurant> getRestaurantByPuntuacion(String puntuacion){

        Session session = _sessionFactory.openSession();
        Iterable<Restaurant> restaurants = session.query(Restaurant.class,
                "MATCH (n:restaurante WHERE n.puntuacion = $puntuacion) RETURN n",
                Map.of("puntuacion", puntuacion));
        return toList(restaurants);
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

}

/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.summary.ResultSummary;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public String getTipo(String name)
    {
        try(Session session = driver.session())
        {
            {
                @Override
                public String execute( Transaction tx)
                {
                    javax.naming.spi.DirStateFactory.Result result = tx.run("MATCH (n:persona {name: \"" + name + "\"}) RETURN n.tipo");
                    String tipo = session.readTransaction( new TransactionWork<String>()
                    tipo = result.toString();
                }
            });
            return tipo;
        }
    }


    public String getPrecio(String name)
    {
        try(Session session = driver.session())
        {
            String precio = session.readTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx)
                {
                    javax.naming.spi.DirStateFactory.Result result = tx.run("MATCH (n:persona {name: \"" + name + "\"}) RETURN n.precio");
                    precio = result.toString();
                }
            });
            return precio;
        }
    }


    
    
    public LinkedList<String> getRestaurantsByName(String name)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 

        String tipo = getTipo(name);
        String precio = getPrecio(name);
   		 LinkedList<String> names = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:restaurante WHERE n.tipo = '+ tipo + ') RETURN n.name");
                    Result result1 = tx.run( "MATCH (n:restaurante WHERE n.precio = '" + precio + "' RETURN n.name");
                    LinkedList<String> myUsers = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 myUsers.add(registros.get(i).get("name").asString());
                    }
                    List<Record> registros1 = result1.list();
                    for (int i = 0; i < registros1.size(); i++) {
                   	 myUsers.add(registros1.get(i).get("name").asString());
                    }
                    return myUsers;
                }
            } );
            
            return names;
        }
   }

}

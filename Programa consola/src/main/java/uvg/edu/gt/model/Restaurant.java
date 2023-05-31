package uvg.edu.gt.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity(label = "restaurante")
public class Restaurant {
    @Id
    @Property(name = "name")
    String name;

    @Property(name = "precio")
    String precio;

    public Restaurant(){

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Restaurant other = (Restaurant) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}

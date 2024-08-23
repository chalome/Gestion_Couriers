package Services;

import java.util.List;

public interface IServicesDao<T> {

    int ajouter(T t);

    int modifier(T t);

    T lire(int id);

    int supprimer(int id);

    List<T> list();

}

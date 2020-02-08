/* 
User: Urmi
Date: 12/27/2019 
Time: 8:34 PM
*/

package guru.springframework.sfgpetclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save (T object);

    void delete(T object);

    void deleteById(ID id);
}

/* 
User: Urmi
Date: 12/26/2019 
Time: 4:34 PM
*/

package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}

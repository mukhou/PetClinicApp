package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    public static final String LAST_NAME = "Smith";

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
        Owner owner = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwners = new HashSet<>();
        returnedOwners.add(Owner.builder().id(1L).build());
        returnedOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));
        Owner owner = service.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerRepository.save(any())).thenReturn(returnedOwner);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedOwner);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}
package com.example.petclinic.services.map;

import com.example.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;
    final String ownerLastName = "Glennane";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner ownerSaved = ownerServiceMap.save(owner2);

        assertEquals(id, ownerSaved.getId());
    }

    @Test
    void saveNoId() {
        Owner ownerSaved = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner3 = ownerServiceMap.findByLastName(ownerLastName);
        assertNotNull(owner3);
        assertEquals(ownerId,owner3.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner3 = ownerServiceMap.findByLastName("Foo");
        assertNull(owner3);
    }
}
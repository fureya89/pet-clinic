package com.example.petclinic.bootstrap;

import com.example.petclinic.model.*;
import com.example.petclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiologySpeciality = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgerySpeciality = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistrySpeciality = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddres("123 Narrow");
        owner1.setCity("London");
        owner1.setTelephone("123123123");

        Pet michaelsPet = new Pet();
        michaelsPet.setPetType(savedDogPetType);
        michaelsPet.setOwner(owner1);
        michaelsPet.setBirthDate(LocalDate.now());
        michaelsPet.setName("Max");
        owner1.getPets().add(michaelsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glennane");
        owner2.setAddres("456 Night");
        owner2.setCity("Barcelona");
        owner2.setTelephone("7867864");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(savedCatPetType);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("KitKat");
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);


        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vet1.getSpecialities().add(savedRadiologySpeciality);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Adam");
        vet2.setLastName("Krakowsky");
        vet2.getSpecialities().add(savedSurgerySpeciality);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Mike");
        vet3.setLastName("Zagumny");
        vet3.getSpecialities().add(savedDentistrySpeciality);
        vetService.save(vet3);

        System.out.println("Loaded Vets...");
    }
}

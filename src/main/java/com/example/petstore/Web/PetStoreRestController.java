package com.example.petstore.Web;

import com.example.petstore.Model.*;
import com.example.petstore.Model.Enum.Type;
import com.example.petstore.Service.impl.PetServiceImp;
import com.example.petstore.Service.impl.UserServiceImp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PetStoreRestController {
    private final UserServiceImp userServiceImp;
    private final PetServiceImp petServiceImp;
    private List<Transaction> historyLog;

    public PetStoreRestController(UserServiceImp userServiceImp, PetServiceImp petServiceImp) {
        this.userServiceImp = userServiceImp;
        this.petServiceImp = petServiceImp;
        this.historyLog = new ArrayList<>();
    }

    // USER METHODS
    //Test

    @GetMapping
    private List<User> findAll(){
        return this.userServiceImp.listAll();
    }

    @GetMapping("/createUsers")
    private List<User> createUsers(){
        List<User> users =  new ArrayList<>();
        Random rand= new Random();
        for(int i =0;i<10;i++){
            Collections.shuffle(namesList);
            Collections.shuffle(emailList);
            String name = namesList.get(0);
            String lastName = namesList.get(1);
            String email = emailList.get(0);
            Double  budget = rand.nextDouble(50);
            users.add(this.userServiceImp
                    .create(name,lastName,email,budget));
        }
        return users;
    }
    @GetMapping("/deleteAllUsers")
     public void deleteUsers(){
        this.userServiceImp.deleteAllUsers();
    }
    @GetMapping("/listUsers")
    private List<User> listUsers(){
        return this.userServiceImp.listAll();
    }
    @GetMapping("/buy")
    private List<Pet> buy(){
        List<User> users = this.userServiceImp.listAll();
        List<Pet> pets = this.petServiceImp.listAll();
        Random random = new Random();
        Transaction transaction = new Transaction(LocalDateTime.now());

        users.forEach(user -> {
            try {
                Pet pet = pets.get(random.nextInt(pets.size()));
                this.userServiceImp.buyPet(pet,user);
                pets.add(pet);
                transaction.addSuccessfulTransaction();

            }catch (RuntimeException e){
                transaction.addFailedTransaction();
                System.out.println(e.getMessage());
            }
        });
        historyLog.add(transaction);
        return this.petServiceImp.listAll();
    }

    @GetMapping("/history")
    private String getHistory(){
        StringBuilder sb= new StringBuilder();
        this.historyLog.forEach(transaction -> sb.append(transaction.toString()+"\n"));
        return sb.toString();
    }





    // PET METHODS

    @GetMapping("/createPets")
    private List<Pet> createPets(){
        List<Type> petType = List.of(Type.values());
        List<Pet> pets = new ArrayList<>();
        Random random =  new Random();
        for(int i = 0;i<20;i++){
            LocalDate birthDate = LocalDate.of(random.nextInt(2000,2022)
                    ,random.nextInt(1,12), random.nextInt(1,29));
            Collections.shuffle(petNamesList);
            String name = petNamesList.get(0);
            Type type = petType.get(random.nextInt(petType.size()));

            if(type.equals(Type.Cat)){
                pets.add(this.petServiceImp.
                        create(new Cat(
                                null,name,type,
                                "",birthDate)));
            }else{
                pets.add(this.petServiceImp.
                        create(new Dog(null,name,type,
                                "",birthDate,
                                random.nextInt(0,10))));
            }
        }
        return this.petServiceImp.listAll();
    }
    @GetMapping("/deleteAllPets")
    public void deletePets(){
        this.petServiceImp.deleteAllPets();
    }

    @GetMapping("/listPets")
     private List<Pet> listPets(){
        return this.petServiceImp.listAll();
    }

    List<String> namesList = Arrays.asList(
            "Michael", "Christopher", "Jessica", "Matthew", "Ashley", "Jennifer",
            "Joshua", "Amanda", "Daniel", "David", "James", "Robert", "John", "Joseph", "Andrew"
    );
    List<String> emailList = Arrays.asList("ilial@yahoo.ca", "kmself@optonline.net", "shazow@hotmail.com",
            "slaff@live.com", "gtaylor@yahoo.com", "cosimo@outlook.com", "drjlaw@verizon.net",
            "daveed@sbcglobal.net", "mpiotr@outlook.com", "jemarch@me.com", "jeffcovey@hotmail.com",
            "alastair@outlook.com");
    List<String> petNamesList = Arrays.asList(
            "Aardvark", "Albatross", "Alligator", "Alpaca", "Ant", "Anteater", "Antelope", "Ape",
            "Armadillo", "Donkey", "Baboon", "Badger", "Barracuda", "Bat", "Bear", "Beaver",
            "Bee", "Bison", "Boar", "Buffalo"
    );
}

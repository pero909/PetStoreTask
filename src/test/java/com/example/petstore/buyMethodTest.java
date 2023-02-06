package com.example.petstore;

import com.example.petstore.Model.Cat;
import com.example.petstore.Model.Enum.Type;
import com.example.petstore.Model.Exceptions.InsufficientFundsException;
import com.example.petstore.Model.Exceptions.petHasOwnerException;
import com.example.petstore.Model.Pet;
import com.example.petstore.Model.User;
import com.example.petstore.Repository.PetJpaRepository;
import com.example.petstore.Repository.UserJpaRepository;
import com.example.petstore.Service.impl.PetServiceImp;
import com.example.petstore.Service.impl.UserServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class buyMethodTest {

    @Mock
    private PetJpaRepository petJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;

    private UserServiceImp userServiceImp;
    private PetServiceImp petServiceImp;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);


        this.userServiceImp =
                Mockito.spy(new UserServiceImp(this.userJpaRepository,this.petJpaRepository));
        this.petServiceImp = Mockito.spy(new PetServiceImp(this.petJpaRepository));

      //  Mockito.when(this.userServiceImp.buyPet(pet,user))
        //        .thenCallRealMethod();
    }

    @Test
    public void testNotEnoughFunds(){
        User user = new User("test1","test1","test@test.com",2.1);
        Pet petToBuy = new Cat(user,"test2", Type.Cat,
                "", LocalDate.of(2018,2,3));


        Assert.assertNotNull(user);
        Assert.assertNotNull(petToBuy);

       Assert.assertThrows("InsufficientFundsException expected",
               InsufficientFundsException.class,
               ()-> this.userServiceImp.buyPet(petToBuy,user));
        System.out.println(user.getBudget());
        System.out.println(petToBuy.getPrice());

        Mockito.verify(this.userServiceImp).buyPet(petToBuy,user);



    }
    @Test
    public void testHasOwner(){



       User user = new User("test1","test1","test@test.com",30.2);
       Pet petToBuy = new Cat(user,"test2", Type.Cat,
               "", LocalDate.of(2018,2,3));


        Assert.assertThrows("petHasOwnerException expected",
                petHasOwnerException.class,
                ()-> this.userServiceImp.buyPet(petToBuy,user));
        System.out.println(user.getBudget());
        System.out.println(petToBuy.getPrice());

        Mockito.verify(this.userServiceImp).buyPet(petToBuy,user);



    }
    @Test
    public void successfulBuy(){
        User user = new User("test1","test1","test@test.com",30.2);
        Pet petToBuy = new Cat(null,"test2", Type.Cat,
                "", LocalDate.of(2018,2,3));

        Assert.assertEquals(this.userServiceImp.buyPet(petToBuy,user),petToBuy);
    }
}

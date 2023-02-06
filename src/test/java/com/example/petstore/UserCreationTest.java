package com.example.petstore;

import com.example.petstore.Model.User;
import com.example.petstore.Repository.PetJpaRepository;
import com.example.petstore.Repository.UserJpaRepository;
import com.example.petstore.Service.impl.UserServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserCreationTest {

    @Mock
    private PetJpaRepository petJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;

    private UserServiceImp userServiceImp;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user =  new User("test1","test1","test@Test.com",20.0);
        Mockito.when(this.userJpaRepository.save(Mockito.any(User.class))).thenReturn(user);

        userServiceImp = Mockito.spy(new UserServiceImp(this.userJpaRepository,this.petJpaRepository));
    }

    @Test
    public void testUserCreation(){
        User user = this.userServiceImp
                .create("test1","test1","test@Test.com",20.0);
        Mockito.verify(this.userServiceImp)
                .create("test1","test1","test@Test.com",20.0);

        Assert.assertNotNull(user);
        Assert.assertEquals("test1",user.getFirstName());
        Assert.assertEquals("test1",user.getLastName());
        Assert.assertEquals("test@Test.com",user.getEmail());
        Assert.assertEquals(20.0,user.getBudget(),0.1);
    }
}

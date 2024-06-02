package fr.dreamteam;

import fr.dreamteam.account.bo.UserBo;
import fr.dreamteam.account.exception.LoginException;
import fr.dreamteam.account.mapper.UserMapper;
import fr.dreamteam.account.repository.UserRepository;
import fr.dreamteam.account.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.apache.commons.lang.builder.EqualsBuilder;

@ExtendWith(MockitoExtension.class)
public class AccountApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private AuthService authService;
    

    @Test
    public void registerUser_UserAlreadyExists_ThrowsLoginException() {
        String username = "test";
        String password = "test";
        UserBo existingUser = new UserBo(username, password);
        when(userRepository.findByUsername(username)).thenReturn(existingUser);

        assertThrows(LoginException.class, () -> authService.registerUser(username, password));
        try{
            authService.registerUser(username, password);
        }catch(LoginException e){
            assertTrue(e.getMessage().equals("User already exists"));
        }
    }

    @Test
    public void Login_User_Does_Not_Exist_ThrowsLoginException() {
        String username = "doesNotExist";
        String password = "doesNotExist";
        
        assertThrows(LoginException.class, () -> authService.loginUser(username, password));
        try{
            authService.loginUser(username, password);
        }catch(LoginException e){
            assertTrue(e.getMessage().equals("User does not exist"));
        }
    }

}

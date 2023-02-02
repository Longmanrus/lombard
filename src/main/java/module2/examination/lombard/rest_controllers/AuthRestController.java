package module2.examination.lombard.rest_controllers;

import module2.examination.lombard.models.AuthRequestDTO;
import module2.examination.lombard.models.User;
import module2.examination.lombard.repo.UsersRepository;
import module2.examination.lombard.security.JWTProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final JWTProvider jwtProvider;

    public AuthRestController(AuthenticationManager authenticationManager, UsersRepository usersRepository, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate (@RequestBody AuthRequestDTO request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = usersRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtProvider.createToken(request.getUsername(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", request.getUsername());
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid username/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout (HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response, null);
    }


}

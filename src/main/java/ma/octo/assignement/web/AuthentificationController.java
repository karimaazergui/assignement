package ma.octo.assignement.web;

import ma.octo.assignement.domain.User;
import ma.octo.assignement.security.JwtUtil;
import ma.octo.assignement.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private JwtUtil jwtTokenUtil;
        @Autowired
        private MyUserDetailsService userDetailsService;


        @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
        public ResponseEntity<?> createAuthenticationToken(@RequestBody User user)throws Exception{

            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUsername(),user.getPassword()));
            }
            catch(BadCredentialsException e){
                throw new Exception("incorrect username or password",e);
            }
            final UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt=jwtTokenUtil.generatToken(userDetails);
            return  ResponseEntity.ok(jwt);
        }


    }

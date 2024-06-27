package ewallet.ewallet.controller;

import ewallet.ewallet.config.JwtTokenUtil;
import ewallet.ewallet.constant.MessageResponse;
import ewallet.ewallet.entity.response.SuccesResponse;
import ewallet.ewallet.model.UserModel;
import ewallet.ewallet.repository.UserRepository;
import ewallet.ewallet.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class UserController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtUserDetailService jwtUserDetailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/registrasi")
    public ResponseEntity<String> insertData(@RequestBody UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registrasi Berhasil di Lakukan");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel userModel) throws Exception{
        authenticate(userModel.getUsername(),userModel.getPassword());
        final UserDetails userDetails = jwtUserDetailService
                .loadUserByUsername(userModel.getUsername());
        UserModel userEmail = userRepository.findByUsername(userModel.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails, userEmail.getEmail());
        return ResponseEntity.ok(new SuccesResponse<>(MessageResponse.SUCCESS_GET_DATA.toString(), token));
    }

    @PutMapping("/user/insertSaldo")
    public ResponseEntity<?> insertSaldo(@RequestBody UserModel saldo, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){

        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        UserModel userDetail = userRepository.findByUsername(username);
        userDetail.setSaldo(userDetail.getSaldo()+saldo.getSaldo());
        userRepository.save(userDetail);
        return ResponseEntity.ok(new SuccesResponse<>(MessageResponse.SUCCESS_UPDATE_DATA.toString(), userDetail));
    }

    @GetMapping("/user/checkSaldo")
    public ResponseEntity<?> getMethodName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        UserModel userModel = userRepository.findByUsername(username);
        return ResponseEntity.ok(new SuccesResponse<>(MessageResponse.SUCCESS_UPDATE_DATA.toString(), userModel));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            // user disable
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // invalid credentials
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }
}

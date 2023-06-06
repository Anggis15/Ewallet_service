package ewallet.ewallet.controller;

import ewallet.ewallet.config.JwtTokenUtil;
import ewallet.ewallet.model.TransferModel;
import ewallet.ewallet.model.UserModel;
import ewallet.ewallet.repository.TransferRepository;
import ewallet.ewallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransferRepository transferRepository;



    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferModel transferModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String token ){
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        transferModel.setUsername(username);
        transferRepository.save(transferModel);
        UserModel userPengirim = userRepository.findByUsername(username);
        UserModel userPenerima = userRepository.findByUsername(transferModel.getUsernameTo());
        userPengirim.setSaldo(userPengirim.getSaldo()-transferModel.getAmount());
        userRepository.save(userPengirim);
        userPenerima.setSaldo(userPenerima.getSaldo()+transferModel.getAmount());
        userRepository.save(userPenerima);
        return ResponseEntity.ok("Transfer Berhasil di Lakukan");
    }
}

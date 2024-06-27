package ewallet.ewallet.controller;

import ewallet.ewallet.config.JwtTokenUtil;
import ewallet.ewallet.model.TransferModel;
import ewallet.ewallet.service.TransferService;

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
    TransferService transferService;



    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferModel transferModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String token ){
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        transferService.transfer(username, transferModel);
        return ResponseEntity.ok("Transfer Berhasil di Lakukan");
    }
}

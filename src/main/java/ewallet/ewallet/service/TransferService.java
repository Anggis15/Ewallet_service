package ewallet.ewallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ewallet.ewallet.model.TransferModel;
import ewallet.ewallet.model.UserModel;
import ewallet.ewallet.repository.TransferRepository;
import ewallet.ewallet.repository.UserRepository;

@Service
public class TransferService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransferRepository transferRepository;
    public void transfer(String username, TransferModel transferModel){
        transferModel.setUsername(username);
        UserModel userPengirim = userRepository.findByUsername(username);
        UserModel userPenerima = userRepository.findByUsername(transferModel.getUsernameTo());
        if(userPenerima.getSaldo()<transferModel.getAmount()){
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tidak bisa melalukan transfer karena saldo pengirim kurang");
        }
        userPengirim.setSaldo(userPengirim.getSaldo()-transferModel.getAmount());
        userRepository.save(userPengirim);
        userPenerima.setSaldo(userPenerima.getSaldo()+transferModel.getAmount());
        userRepository.save(userPenerima);
        
        transferRepository.save(transferModel);
        
    }
    
}

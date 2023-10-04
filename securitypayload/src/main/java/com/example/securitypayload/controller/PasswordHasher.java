package com.example.securitypayload.controller;

import com.example.securitypayload.dto.RequestDto;
import com.example.securitypayload.dto.ResponseDto;
import com.example.securitypayload.service.PasswordHashing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passwordHasher")
public class PasswordHasher {

    @Autowired
    private PasswordHashing passwordHashing;
    @PostMapping()
    ResponseEntity<ResponseDto> hashPassword(@RequestBody RequestDto requestDto){
        String password = requestDto.getPassword();
        String hashedPassword= passwordHashing.hashedPassword(password);

        ResponseDto responseDto=new ResponseDto(password,hashedPassword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

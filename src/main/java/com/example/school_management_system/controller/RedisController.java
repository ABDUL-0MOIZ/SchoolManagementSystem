package com.example.school_management_system.controller;



import com.example.school_management_system.service.RedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {
private final RedisService redisService;
public RedisController(RedisService redisService){
    this.redisService=redisService;
}
@PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam String key,@RequestParam String val){
    redisService.saveKeyValue(key,val);
    return new ResponseEntity<>("save successfully", HttpStatus.OK);
}
@GetMapping("/Get/{key}")
    public  ResponseEntity<?> get(@PathVariable String key){
    String v=redisService.getValue(key);
return new ResponseEntity<>(v,HttpStatus.OK);
}
}

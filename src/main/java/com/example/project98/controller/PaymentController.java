package com.example.project98.controller;

import com.example.project98.domain.Payment;
import com.example.project98.exception.ResourceNotFoundException;
import com.example.project98.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {return this.paymentService.findAll();}

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable (value="id") Long id) throws ResourceNotFoundException {
        Payment payment = paymentService.findById(id);
        return ResponseEntity.ok().body(payment);
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) throws Exception {
        Payment savedPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<Payment> updatePerson(@PathVariable(value = "id") Long id, @RequestBody Payment paymentDetails) throws ResourceNotFoundException {
        Payment payment = paymentService.updatePayment(id, paymentDetails);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) throws ResourceNotFoundException {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

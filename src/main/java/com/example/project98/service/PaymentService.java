package com.example.project98.service;

import com.example.project98.domain.Payment;
import com.example.project98.domain.Payment;
import com.example.project98.exception.ConstraintException;
import com.example.project98.exception.ResourceNotFoundException;
import com.example.project98.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.List;


@Service
public class PaymentService {
    @Autowired

    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

//    public PaymentService(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }

    public Payment findById(Long paymentId) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: " + paymentId));
        return payment;
    }

    public Payment createPayment(Payment payment) throws ConstraintException, Exception {
        try {
            return this.paymentRepository.save(payment);
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintException("Constraint Problem - " + e.getMostSpecificCause().getMessage());
        } catch (Exception e) {
            throw new Exception("Unknown error :( but i know some details that could help: " + e.getMessage());
        }
    }

    public Payment updatePayment(Long id, Payment paymentDetails) throws ResourceNotFoundException {

        Payment payment = paymentRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: " + id));

        payment.setCardNumber(paymentDetails.getCardNumber());
        payment.setCardHolderName(paymentDetails.getCardHolderName());
        payment.setExpirationDate(paymentDetails.getExpirationDate());
        payment.setCardCvv(paymentDetails.getCardCvv());
        payment.setCardAmount(paymentDetails.getCardAmount());

        return paymentRepository.save(payment);
    }


    public Payment addPayment(Payment payment) throws Exception {
        // Проверяем, что payment не является пустым
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }

        // Проверяем, что все обязательные поля заполнены
        if (payment.getCardAmount() == null || payment.getCardNumber() == null || payment.getCardHolderName() == null || payment.getExpirationDate() == null || payment.getCardCvv() == null) {
            throw new IllegalArgumentException("Payment must have a non-null amount, card number, card holder name, expiry date and CVV");
        }

        // Проверяем, что номер карты введен корректно
        if (!validateCard(payment.getCardNumber())) {
            throw new IllegalArgumentException("Invalid card number");
        }

        // Шифруем номер карты перед сохранением в БД
        payment.setCardNumber(encrypt(payment.getCardNumber()));

        Payment savedPayment = paymentRepository.save(payment);

        // Расшифровываем номер карты после сохранения в БД
        savedPayment.setCardNumber(decrypt(savedPayment.getCardNumber()));

        return savedPayment;
    }

    private boolean validateCard(String cardNumber) {
        // Проверка длины номера карты
        if (cardNumber.length() != 16) {
            return false;
        }
        // Проверка на наличие только цифр
        if (!cardNumber.matches("[0-9]+")) {
            return false;
        }
        // Проверка на алгоритм Луна
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] KEY = "my-secret-key-16".getBytes();
    private static final byte[] IV = "my-iv-16".getBytes();

    public String encrypt(String data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);
    }


    // Метод в PaymentService для проверки подлинности по CVV Code
//    public boolean verifyCvv(Long id, String cvv) {
//        Optional<Payment> paymentOptional = paymentRepository.findById(id);
//        if (paymentOptional.isPresent()) {
//            Payment payment = paymentOptional.get();
//            return payment.getCardCvv().equals(cvv);
//        }
//        return false;
//    }
    private String decrypt(String data) {
        try {
            final String secretKey = "mySecretKey123";
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = Base64.getDecoder().decode(data.getBytes());
            byte[] decValue = c.doFinal(decodedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt card data", e);
        }
    }



    public void deletePayment(Long id) throws ResourceNotFoundException {
        Payment payment = findById(id);
        paymentRepository.delete(payment);
    }

//    @Transactional
//    public void processPayment(Long id) throws ResourceNotFoundException {
//        Payment payment = getPaymentById(id);
    // Реализация метода processPayment() (обработка транзакции)
    // ...
//    }
//}
}
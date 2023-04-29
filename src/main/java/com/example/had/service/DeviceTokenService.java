package com.example.had.service;

import com.example.had.entity.DeviceToken;
import com.example.had.repository.DeviceTokenRepository;
import com.example.had.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceTokenService {
    private final DeviceTokenRepository deviceTokenRepository;
    private final UserRepository userRepository;

    public DeviceTokenService(DeviceTokenRepository deviceTokenRepository,
                              UserRepository userRepository) {
        this.deviceTokenRepository = deviceTokenRepository;
        this.userRepository = userRepository;
    }

    public boolean addDeviceToken(UUID patientId, String token) {
        try {
            DeviceToken deviceToken = deviceTokenRepository.findByUser_Id(patientId);
            if (deviceToken==null){
                deviceTokenRepository.save(new DeviceToken(
                        userRepository.findById(patientId).get(),
                        token
                ));
            }
            else {
                deviceTokenRepository.updateTokenByUser(token,userRepository.findById(patientId).get());
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getDeviceToken(UUID patientId) {
        try {
            return deviceTokenRepository.findByUser_Id(patientId).getToken();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

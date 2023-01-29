package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.exceptions.*;
import com.example.fooddeliveryapp.exceptions.EmptyFieldException;
import com.example.fooddeliveryapp.payload.request.SignUpRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    boolean checkEmailExists(String email);

    UserDTO findUserByEmail(String email);

    AddressDTO getUserDefaultAddress(String email);

    AddressDTO addNewAddressForUserEmail(String email, AddressDTO address) throws UnableToAddAddressException, UnableToAddAddressException, UnableToAddAddressException, UnableToAddAddressException;

    void updateDefaultAddress(String email, int id) throws NoAddressFoundException, NoAddressFoundException, NoAddressFoundException, NoAddressFoundException;

    List<AddressDTO> getAddressesByUserEmail(String email);

    UserSignUpDTO addNewUser(SignUpRequest request);

    UserDTO showUserInfo(String email);
    UserDTO updateUserInfo(String email, String fullName, String phoneNumber) throws EmptyFieldException;
    String updateProfilePic(MultipartFile profilePic,String email);
    boolean deleteProfilePic(String email);
}

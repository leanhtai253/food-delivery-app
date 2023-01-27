package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.exceptions.EmptyFieldException;
import com.example.fooddeliveryapp.mapper.exceptions.NoAddressFoundException;
import com.example.fooddeliveryapp.mapper.exceptions.UnableToAddAddressException;
import com.example.fooddeliveryapp.mapper.AddressMapper;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.payload.request.SignUpRequest;
import com.example.fooddeliveryapp.repositories.UserRepository;
import com.example.fooddeliveryapp.utils.Constant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fooddeliveryapp.repositories.AddressRepository;
import com.example.fooddeliveryapp.utils.AddressUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final Path avatarRoot = Paths.get("127.0.0.1:5500/askbootstrap.com/preview/osahan-eat/admin/img/user");
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressUtils addressUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean checkEmailExists(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public UserDTO findUserByEmail(String email){
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO = userMapper.userToUserDTO(user);
            return userDTO;
        }
        else return null;
    }

    @Override
    public List<AddressDTO> getAddressesByUserEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        List<AddressDTO> addresses = new ArrayList<>();
        if (user != null) {
            user.getUserAddresses().forEach((address) -> {
                addresses.add(addressMapper.addressToAddressDto(address));
            });
            return addresses;
        }
        return null;
    }

    @Override
    public UserSignUpDTO addNewUser(SignUpRequest request) {
        UserEntity user = new UserEntity();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.userSignUpToUserSignUpDTO(userRepository.save(user));
    }

    @Override
    public AddressDTO getUserDefaultAddress(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            for (UserAddressEntity address : user.getUserAddresses()) {
                if (address.isDefault()) {
                    return addressMapper.addressToAddressDto(address);
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public AddressDTO addNewAddressForUserEmail(String email, AddressDTO address) throws UnableToAddAddressException {
        UserEntity user = userRepository.findByEmail(email);
        UserAddressEntity newAddress = addressMapper.addressDtoToAddress(address, user);
        UserAddressEntity result =  addressRepository.save(newAddress);
        if (result != null) {
            AddressDTO resultDto = addressMapper.addressToAddressDto(result);
            return resultDto;
        }
        else throw new UnableToAddAddressException(addressUtils.MSG_UNABLE_TO_ADD_ADDRESS());
    }

    @Override
    public void updateDefaultAddress(String email, int id) throws NoAddressFoundException {
        if (addressService.checkAddressExistsById(id)) {
            UserEntity user = userRepository.findByEmail(email);
            Set<UserAddressEntity> new_addresses = new HashSet<>();
            for (UserAddressEntity addr : user.getUserAddresses()) {
                if (addr.getId() == id) {
                    for (UserAddressEntity address : user.getUserAddresses()) {
                        if (address.getId() != id && address.isDefault()) {
                            address.setDefault(false);
                        } else if (address.getId() == id) {
                            address.setDefault(true);
                        }
                        new_addresses.add(address);
                    }
                    user.setUserAddresses(new_addresses);
                    UserEntity result = userRepository.save(user);
                    if (result != null) return;
                    else throw new EmptyResultDataAccessException("Unable to update default address", 1);
                }
            }
        } else {
            throw new NoAddressFoundException(addressUtils.MSG_NO_ADDRESS_FOUND_ID(id));
        }
    }

    @Override
    public UserDTO showUserInfo(String email){
        UserDTO userDTO = userMapper.userToUserDTO(userRepository.findByEmail(email));
        return new UserDTO();
    }

    @Override
    public UserDTO updateUserInfo(String email, String fullName, String phoneNumber) throws EmptyFieldException{
        // user is not allowed to change email
        UserEntity user = userRepository.findByEmail(email);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        UserDTO userDTO = userMapper.userToUserDTO(user);
        if(userDTO.getEmail() != null && userDTO.getFullName() != null && userDTO.getPhoneNumber() != null){
            return userDTO;
        } else throw new EmptyFieldException(Constant.MSG_EMPTY_FIELD);
    }

    @Override
    public boolean updateProfilePic(MultipartFile avatar, String email){
        // update profile pic under the name of: user[i].(contentType), store avatar URL in database.user.avatar
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(avatar.getOriginalFilename()));
        String contentType = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        int id = (userRepository.findByEmail(email)).getId();
        String savedFileName = "user" + Integer.toString(id).concat("." + contentType);

        try{
            Files.copy(avatar.getInputStream(),avatarRoot.resolve(savedFileName), StandardCopyOption.REPLACE_EXISTING);
            userRepository.findByEmail(email).setAvatar(savedFileName);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteProfilePic(String email){
        //todo: if delete profile pic, use user0.png in folder
        userRepository.findByEmail(email).setAvatar("user0.png");
        return true;
    }
}

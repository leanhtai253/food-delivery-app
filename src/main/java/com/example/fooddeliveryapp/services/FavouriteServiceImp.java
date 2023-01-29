package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserFoodFavDTO;
import com.example.fooddeliveryapp.dto.UserResFavDTO;
import com.example.fooddeliveryapp.entities.UserFoodFavEntity;
import com.example.fooddeliveryapp.entities.UserResFavEntity;
import com.example.fooddeliveryapp.mapper.UserFoodFavMapper;
import com.example.fooddeliveryapp.mapper.UserResFavMapper;
import com.example.fooddeliveryapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteServiceImp implements FavouriteService{
    @Autowired
    UserFoodFavMapper userFoodFavMapper;
    @Autowired
    UserResFavMapper userResFavMapper;
    @Autowired
    UserFoodFavRepository userFoodFavRepository;
    @Autowired
    UserResFavRepository userResFavRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<UserFoodFavDTO> modifyFavFoodList(UserFoodFavEntity modification){
        modification.setUser(userRepository.findById(modification.getIdUser()));
        modification.setFood(foodRepository.findById(modification.getIdFood()));
        List<UserFoodFavEntity> foodFavEntityList = showUserFavFoodList(modification);
            if(!foodFavEntityList.contains(modification)){
                userFoodFavRepository.save(modification);
                foodFavEntityList.add(modification);
            } else {
                userFoodFavRepository.delete(modification);
                foodFavEntityList.remove(modification);
            }
        List<UserFoodFavDTO> foodFavDTOList = new ArrayList<>();
        for(UserFoodFavEntity userFoodFavEntity:foodFavEntityList){
            foodFavDTOList.add(userFoodFavMapper.userFoodFav2UserFoodFavDTO(userFoodFavEntity));
        }
        return foodFavDTOList;
    }

    @Override
    public List<UserFoodFavEntity> showUserFavFoodList(UserFoodFavEntity modification) {
        List<UserFoodFavEntity> foodFavEntityList = new ArrayList<>();
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        int userId = (userRepository.findByEmail(userEmail)).getId();
        foodFavEntityList.addAll(userFoodFavRepository.findAllByIdUser(modification.getIdUser()));
        return foodFavEntityList;
    }

    @Override
    public List<UserFoodFavDTO> showUserFavFoodList(String email){
        List<UserFoodFavDTO> foodFavDTOList = new ArrayList<>();
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = (userRepository.findByEmail(email)).getId();
        for(UserFoodFavEntity userFoodFavEntity: userFoodFavRepository.findAllByIdUser(userId)){
            foodFavDTOList.add(userFoodFavMapper.userFoodFav2UserFoodFavDTO(userFoodFavEntity));
        }
        return foodFavDTOList;
    }

    @Override
    public List<UserResFavDTO> modifyFavRestaurantList(UserResFavEntity modification) {
        modification.setUser(userRepository.findById(modification.getIdUser()));
        modification.setRestaurant(restaurantRepository.findById(modification.getIdRestaurant()));
        List<UserResFavEntity> resFavEntityList = showUserFavRestaurantList(modification);
        if(!resFavEntityList.contains(modification)){
            userResFavRepository.save(modification);
            resFavEntityList.add(modification);
        } else {
            userResFavRepository.delete(modification);
            resFavEntityList.remove(modification);
        }
        List<UserResFavDTO> resFavDTOList = new ArrayList<>();
        for(UserResFavEntity userResFavEntity:resFavEntityList){
            resFavDTOList.add(userResFavMapper.userResFav2UserResFavDTO(userResFavEntity));
        }
        return resFavDTOList;
    }

    @Override
    public List<UserResFavEntity> showUserFavRestaurantList(UserResFavEntity modification) {
        List<UserResFavEntity> resFavEntityList = new ArrayList<>();
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        int userId = (userRepository.findByEmail(userEmail)).getId();
        resFavEntityList.addAll(userResFavRepository.findAllByIdUser(modification.getIdUser()));
        return resFavEntityList;
    }

    @Override
    public List<UserResFavDTO> showUserFavRestaurantList(String email) {
        List<UserResFavDTO> resFavDTOList = new ArrayList<>();
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = (userRepository.findByEmail(email)).getId();
        for(UserResFavEntity userResFavEntity: userResFavRepository.findAllByIdUser(userId)){
            resFavDTOList.add(userResFavMapper.userResFav2UserResFavDTO(userResFavEntity));
        }
        return resFavDTOList;
    }
}

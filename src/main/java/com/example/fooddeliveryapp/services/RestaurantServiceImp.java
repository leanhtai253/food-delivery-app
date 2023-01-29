package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.dto.RestaurantDetailDto;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.exceptions.FoodNotExistException;
import com.example.fooddeliveryapp.exceptions.RestaurantNotExistException;
import com.example.fooddeliveryapp.mapper.CategoryMapper;
import com.example.fooddeliveryapp.mapper.FoodMapper;
import com.example.fooddeliveryapp.mapper.RestaurantMapper;
import com.example.fooddeliveryapp.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<RestaurantDTO> getTop6Restaurants() throws RestaurantNotExistException {
        List<RestaurantEntity> restaurantEntities = restaurantRepository.getTop6Restaurants();
        if (restaurantEntities != null) {
            List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
            for (RestaurantEntity restaurant : restaurantEntities) {
                restaurantDTOS.add(restaurantMapper.restaurantToRestaurantDTO(restaurant));
            }
            return restaurantDTOS;
        } else {
            throw new RestaurantNotExistException();
        }
    }

    @Override
    public List<RestaurantDTO> findAll() throws RestaurantNotExistException {
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        if (restaurantEntities != null) {
            List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
            for (RestaurantEntity restaurant : restaurantEntities) {
                restaurantDTOS.add(restaurantMapper.restaurantToRestaurantDTO(restaurant));
            }
            return restaurantDTOS;
        } else {
            throw new RestaurantNotExistException();
        }
    }

    @Override
    public RestaurantDetailDto getRestaurantDetail(int id) {
        try {
            RestaurantEntity entity = restaurantRepository.findById(id);
            return restaurantMapper.restaurantToRestaurantDetailDto(entity);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
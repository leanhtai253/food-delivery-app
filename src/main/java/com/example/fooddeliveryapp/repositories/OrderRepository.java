package com.example.fooddeliveryapp.repositories;


import com.example.fooddeliveryapp.entities.TOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TOrderEntity, Integer> {
    @Query(value = "select * \n" +
            "from t_order t right join order_status os \n" +
            "on t.id = os.id_order\n" +
            "group by t.id\n" +
            "having max(id_status) < 4;", nativeQuery = true)
    List<TOrderEntity> getOrderUpComing();
}

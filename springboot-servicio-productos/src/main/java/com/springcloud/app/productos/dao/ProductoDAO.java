package com.springcloud.app.productos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcloud.app.commons.models.entity.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Long> {

}

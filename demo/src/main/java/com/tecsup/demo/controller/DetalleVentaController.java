package com.tecsup.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.demo.model.DetalleVenta;
import com.tecsup.demo.service.DetalleVentaService;

@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService service;

    @GetMapping
    public List<DetalleVenta> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalle) {
        return ResponseEntity.status(201).body(service.guardar(detalle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtener(@PathVariable Long id) {
        DetalleVenta d = service.obtener(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(@PathVariable Long id, @RequestBody DetalleVenta d) {
        DetalleVenta existente = service.obtener(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setVenta(d.getVenta());
        existente.setProducto(d.getProducto());
        existente.setCantidad(d.getCantidad());
        existente.setPrecio(d.getPrecio());
        existente.setSubtotal(d.getCantidad() * d.getPrecio());
        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        DetalleVenta d = service.obtener(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

package com.tecsup.demo.controller;

import com.tecsup.demo.model.*;
import com.tecsup.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired private CategoriaService categoriaService;
    @Autowired private ProductoService productoService;
    @Autowired private ClienteService clienteService;
    @Autowired private EmpleadoService empleadoService;
    @Autowired private VentaService ventaService;
    @Autowired private DetalleVentaService detalleVentaService;

    // Dashboard
    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("activePage", "dashboard");
        model.addAttribute("categoriasCount", categoriaService.listar().size());
        model.addAttribute("productosCount", productoService.listar().size());
        model.addAttribute("clientesCount", clienteService.listar().size());
        model.addAttribute("empleadosCount", empleadoService.listar().size());
        model.addAttribute("ventasCount", ventaService.listar().size());
        model.addAttribute("detallesCount", detalleVentaService.listar().size());
        return "index";
    }

    // ===== CATEGORÍAS =====
    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("activePage", "categorias");
        model.addAttribute("categorias", categoriaService.listar());
        return "categorias/lista";
    }

    @GetMapping("/categorias/nuevo")
    public String nuevaCategoriaForm(Model model) {
        model.addAttribute("activePage", "categorias");
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria, RedirectAttributes redirect) {
        categoriaService.guardar(categoria);
        redirect.addFlashAttribute("message", "Categoría guardada correctamente");
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/editar/{id}")
    public String editarCategoriaForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Categoria categoria = categoriaService.obtener(id);
        if (categoria == null) {
            redirect.addFlashAttribute("error", "Categoría no encontrada");
            return "redirect:/categorias";
        }
        model.addAttribute("activePage", "categorias");
        model.addAttribute("categoria", categoria);
        return "categorias/formulario";
    }

    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirect) {
        categoriaService.eliminar(id);
        redirect.addFlashAttribute("message", "Categoría eliminada correctamente");
        return "redirect:/categorias";
    }

    // ===== PRODUCTOS =====
    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("activePage", "productos");
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/lista";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProductoForm(Model model) {
        model.addAttribute("activePage", "productos");
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/formulario";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, RedirectAttributes redirect) {
        productoService.guardar(producto);
        redirect.addFlashAttribute("message", "Producto guardado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProductoForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Producto producto = productoService.obtener(id);
        if (producto == null) {
            redirect.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/productos";
        }
        model.addAttribute("activePage", "productos");
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/formulario";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirect) {
        productoService.eliminar(id);
        redirect.addFlashAttribute("message", "Producto eliminado correctamente");
        return "redirect:/productos";
    }

    // ===== CLIENTES =====
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("activePage", "clientes");
        model.addAttribute("clientes", clienteService.listar());
        return "clientes/lista";
    }

    @GetMapping("/clientes/nuevo")
    public String nuevoClienteForm(Model model) {
        model.addAttribute("activePage", "clientes");
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirect) {
        clienteService.guardar(cliente);
        redirect.addFlashAttribute("message", "Cliente guardado correctamente");
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String editarClienteForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Cliente cliente = clienteService.obtener(id);
        if (cliente == null) {
            redirect.addFlashAttribute("error", "Cliente no encontrado");
            return "redirect:/clientes";
        }
        model.addAttribute("activePage", "clientes");
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirect) {
        clienteService.eliminar(id);
        redirect.addFlashAttribute("message", "Cliente eliminado correctamente");
        return "redirect:/clientes";
    }

    // ===== EMPLEADOS =====
    @GetMapping("/empleados")
    public String listarEmpleados(Model model) {
        model.addAttribute("activePage", "empleados");
        model.addAttribute("empleados", empleadoService.listar());
        return "empleados/lista";
    }

    @GetMapping("/empleados/nuevo")
    public String nuevoEmpleadoForm(Model model) {
        model.addAttribute("activePage", "empleados");
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    @PostMapping("/empleados/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado, RedirectAttributes redirect) {
        empleadoService.guardar(empleado);
        redirect.addFlashAttribute("message", "Empleado guardado correctamente");
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/editar/{id}")
    public String editarEmpleadoForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Empleado empleado = empleadoService.obtener(id);
        if (empleado == null) {
            redirect.addFlashAttribute("error", "Empleado no encontrado");
            return "redirect:/empleados";
        }
        model.addAttribute("activePage", "empleados");
        model.addAttribute("empleado", empleado);
        return "empleados/formulario";
    }

    @GetMapping("/empleados/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id, RedirectAttributes redirect) {
        empleadoService.eliminar(id);
        redirect.addFlashAttribute("message", "Empleado eliminado correctamente");
        return "redirect:/empleados";
    }

    // ===== VENTAS =====
    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        model.addAttribute("activePage", "ventas");
        model.addAttribute("ventas", ventaService.listar());
        return "ventas/lista";
    }

    @GetMapping("/ventas/nuevo")
    public String nuevaVentaForm(Model model) {
        model.addAttribute("activePage", "ventas");
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("empleados", empleadoService.listar());
        model.addAttribute("productos", productoService.listar());
        return "ventas/formulario";
    }

    @PostMapping("/ventas/guardar")
    public String guardarVenta(@ModelAttribute Venta venta, 
                               @RequestParam(value = "productoIds", required = false) List<Long> productoIds,
                               @RequestParam(value = "cantidades", required = false) List<Integer> cantidades,
                               RedirectAttributes redirect) {
        
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDate.now());
        }
        
        // Calcular total y crear detalles
        double total = 0;
        if (productoIds != null && cantidades != null) {
            for (int i = 0; i < productoIds.size(); i++) {
                Long pid = productoIds.get(i);
                Integer cant = cantidades.get(i);
                if (pid != null && cant != null && cant > 0) {
                    Producto p = productoService.obtener(pid);
                    if (p != null) {
                        DetalleVenta dv = new DetalleVenta();
                        dv.setProducto(p);
                        dv.setCantidad(cant);
                        dv.setPrecio(p.getPrecio());
                        dv.calcularSubtotal();
                        venta.getDetalles().add(dv);
                        total += dv.getSubtotal();
                        
                        // Actualizar stock
                        p.setStock(p.getStock() - cant);
                        productoService.guardar(p);
                    }
                }
            }
        }
        venta.setTotal(total);
        ventaService.guardar(venta);
        redirect.addFlashAttribute("message", "Venta registrada correctamente");
        return "redirect:/ventas";
    }

    @GetMapping("/ventas/ver/{id}")
    public String verVenta(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Venta venta = ventaService.obtener(id);
        if (venta == null) {
            redirect.addFlashAttribute("error", "Venta no encontrada");
            return "redirect:/ventas";
        }
        model.addAttribute("activePage", "ventas");
        model.addAttribute("venta", venta);
        return "ventas/detalle";
    }

    @GetMapping("/ventas/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id, RedirectAttributes redirect) {
        ventaService.eliminar(id);
        redirect.addFlashAttribute("message", "Venta eliminada correctamente");
        return "redirect:/ventas";
    }

    // ===== DETALLES VENTA =====
    @GetMapping("/detalles")
    public String listarDetalles(Model model) {
        model.addAttribute("activePage", "detalles");
        model.addAttribute("detalles", detalleVentaService.listar());
        return "detalles/lista";
    }
}
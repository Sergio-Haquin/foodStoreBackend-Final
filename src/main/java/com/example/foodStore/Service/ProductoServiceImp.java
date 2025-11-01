package com.example.foodStore.Service;

import com.example.foodStore.Entity.Categoria;
import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Entity.Dto.ProductoDto;
import com.example.foodStore.Entity.Mapper.ProductoMapper;
import com.example.foodStore.Entity.Producto;
import com.example.foodStore.Repository.CategoriaRepository;
import com.example.foodStore.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

   @Autowired
   ProductoRepository productoRepository;

   @Autowired
   CategoriaRepository categoriaRepository;

   @Autowired
   ProductoMapper productoMapper;

   /**
    * Método para guardar un nuevo producto.
    * Se asume que este endpoint es SÓLO para CREACIÓN, ya que la edición
    * se maneja en el Frontend con los métodos editPrice y editCategory.
    */
   @Override
   public ProductoDto save(ProductoCreate productoCreate) {

      // 🛑 CORRECCIÓN DE LÓGICA: Si el DTO trae ID, significa que se usó incorrectamente
      // para crear. Esto previene que se mezclen flujos.
      if (productoCreate.getId() != null) {
         throw new IllegalArgumentException("No se puede crear un producto con ID. Use los endpoints de edición.");
      }

      Producto producto = productoMapper.toEntity(productoCreate);

      // 🛑 La validación de nombre único es correcta para la CREACIÓN.
      if (productoRepository.existsByNombre(producto.getNombre())) {
         throw new RuntimeException("El producto ya esta registrado");
      }

      producto = productoRepository.save(producto);
      return productoMapper.toDto(producto);
   }

   @Override
   public ProductoDto findByName(String nombre) {
      Producto p = productoRepository.findByNombre(nombre).orElseThrow(() -> new NullPointerException("Producto no encontrado"));
      return productoMapper.toDto(p);
   }

   @Override
   public List<ProductoDto> findByCategory(Long idCategoria) {
      Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + idCategoria));
      // Se mantiene tu lógica de filtro, aunque usar un método findByCategoria en el Repository sería más eficiente.
      return productoRepository.findAll().stream().filter(p -> p.getCategoria().equals(categoria)).map(productoMapper::toDto).collect(Collectors.toList());
   }

   @Override
   public List<ProductoDto> findAll() {
      // 🚨 CORRECCIÓN IMPLÍCITA: Ahora que el ProductoMapper incluye el ID,
      // la lista de productos devuelta por este método tendrá IDs válidos.
      return productoRepository.findAll().stream().map(productoMapper::toDto).collect(Collectors.toList());
   }

   // =============================================================
   // MÉTODOS DE EDICIÓN Y ELIMINACIÓN (Usando Long id)
   // Estos métodos están bien.
   // =============================================================

   @Override
   public void editPrice(Long id, double precio) {
      Producto p = productoRepository.findById(id).orElseThrow(() -> new NullPointerException("Producto no encontrado con ID: " + id));
      p.setPrecio(precio);
      productoRepository.save(p);
   }

   @Override
   public void editCategory(Long id, Long idCategoria) {
      Producto p = productoRepository.findById(id).orElseThrow(() -> new NullPointerException("Producto no encontrado con ID: " + id));
      Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + idCategoria));
      p.setCategoria(categoria);
      productoRepository.save(p);
   }

   @Override
   public void delete(Long id) {
      Producto p = productoRepository.findById(id).orElseThrow(() -> new NullPointerException("Producto no encontrado con ID: " + id));
      productoRepository.delete(p);
   }
}
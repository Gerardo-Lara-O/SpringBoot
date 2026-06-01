package com.gerardo.curso.java.springboot.backend.controllers;

import com.gerardo.curso.java.springboot.backend.entities.Product;
import com.gerardo.curso.java.springboot.backend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// 1. @RestController es la suma de @Controller + @ResponseBody.
// A diferencia de MVC (donde devuelves un String con el nombre de la plantilla HTML),
// aquí le dices a Spring: "Todo lo que devuelvan estos métodos, conviértelo
// automáticamente a JSON y mándalo directamente al cuerpo (body) de la respuesta HTTP".
@RestController
// *TIP PROFESIONAL:* Te sugiero agregar aquí un @RequestMapping("/api/products")
// para que todas las rutas nazcan desde ahí y no desde la raíz de tu proyecto.
public class ProductController {

    final private ProductService service;

    // Inyección de dependencias por constructor (¡La mejor práctica!)
    public ProductController(ProductService service) {
        this.service = service;
    }

    // ------------------------------------------------------------------
    // GET (Listar todos)
    // ------------------------------------------------------------------
    @GetMapping
    // 2. ResponseEntity: Es una clase envoltorio (wrapper) de Spring.
    // Te permite controlar TODA la respuesta HTTP: el código de estado (200, 404, 500),
    // los encabezados (Headers) y el cuerpo (Body) que en este caso es la lista.
    public ResponseEntity<List<Product>> list(){
        // ResponseEntity.ok(...) es un atajo para decir:
        // "Manda un status 200 OK y pon esta lista en el cuerpo en formato JSON".
        return ResponseEntity.ok(service.findAll());
    }

    // ------------------------------------------------------------------
    // GET (Detalle por ID)
    // ------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id){
        Optional<Product> optionalProduct = service.findById(id);

        // Si el producto existe en la base de datos...
        if (optionalProduct.isPresent()){
            // Retornamos 200 OK y el producto.
            // optionalProduct.orElseThrow() extrae el objeto Product de adentro del Optional.
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        // Si no existe, devolvemos un código de estado 404 Not Found sin cuerpo (.build()).
        // Esto es crucial para que los clientes (Front-end) sepan que hubo un error y por qué.
        return ResponseEntity.notFound().build();
    }

    // ------------------------------------------------------------------
    // POST (Crear nuevo)
    // ------------------------------------------------------------------
    @PostMapping
    // 3. @RequestBody: ¡Esta es la estrella de las APIs REST!
    // En MVC usábamos el Model para recibir datos de un formulario (application/x-www-form-urlencoded).
    // Aquí, @RequestBody le dice a Spring: "El cliente me está mandando un JSON puro
    // en el cuerpo de la petición. Toma ese JSON y conviértelo en este objeto Product de Java".
    public ResponseEntity<Product> create(@RequestBody Product product){

        // Cuando creamos un recurso nuevo, el estándar HTTP dicta que no debemos
        // devolver un 200 OK, sino un 201 CREATED.
        // Aquí personalizamos la respuesta: Status 201 y en el cuerpo devolvemos
        // el producto que acabamos de guardar (que ahora ya trae el ID generado por la BD).
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    // ------------------------------------------------------------------
    // PUT (Actualizar existente)
    // ------------------------------------------------------------------
    @PutMapping("/{id}")
    // En un PUT, necesitamos dos cosas:
    // 1. El JSON con los datos nuevos (@RequestBody)
    // 2. A quién se los vamos a aplicar (@PathVariable id)
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id){
        // Primero buscamos si el producto a actualizar realmente existe.
        Optional<Product> optionalProduct = service.findById(id);

        if (optionalProduct.isPresent()){
            // Si existe, lo sacamos del envoltorio Optional
            Product productDb = optionalProduct.orElseThrow();

            // Actualizamos SUS PROPIEDADES con los datos que llegaron en el JSON.
            // OJO: No cambiamos el ID, porque el ID es intocable.
            productDb.setDescription(product.getDescription());
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());

            // Guardamos el objeto modificado y devolvemos 201 CREATED
            // (algunos prefieren devolver 200 OK para actualizaciones).
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productDb));
        }
        // Si intentan actualizar un ID que no existe, devolvemos 404.
        return ResponseEntity.notFound().build();
    }

    // ------------------------------------------------------------------
    // DELETE (Eliminar por ID)
    // ------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        // Buscamos si existe antes de intentar borrarlo.
        Optional<Product> optionalProduct = service.deleteById(id);

        if (optionalProduct.isPresent()){
            // Usamos tu método del servicio (que devuelve un Optional).
            Product productDeleted = optionalProduct.orElseThrow();
            // Retornamos 200 OK y, de forma opcional (pero buena práctica),
            // devolvemos el objeto que acabamos de borrar por si el Front-end lo necesita.
            return ResponseEntity.status(HttpStatus.OK).body(productDeleted);
        }
        // Si no existe, devolvemos 404.
        return ResponseEntity.notFound().build();
    }
}
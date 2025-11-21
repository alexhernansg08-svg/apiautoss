package com.agencia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.agencia.api.entities.Auto; 
import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, String> {

    /**
     * Autos por nombre de la marca
     */
    @Query("SELECT a FROM Auto a JOIN a.marca m WHERE m.nombre = :nombre")
    List<Auto> findAutosByMarcaNombre(@Param("nombre") String nombre);

    /**
     * Autos por precio mayor al indicado
     */
    @Query("SELECT a FROM Auto a WHERE a.precio > :precio")
    List<Auto> findAutosByPrecioMayorA(@Param("precio") Double precio);
    
    /**
     * Autos por modelo exacto
     */
    List<Auto> findByModelo(Integer modelo);

    /**
     * Autos donde modelo sea menor a un valor
     */
    List<Auto> findByModeloLessThan(Integer anio);

    /**
     * Autos según país de la marca
     */
    @Query("SELECT a FROM Auto a JOIN a.marca m WHERE m.pais = :pais")
    List<Auto> findAutosByMarcaPais(@Param("pais") String pais);

}

package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalidadTest {

    Provincia provinciaTest = Provincia.builder().id(1L).nombre("Buenos Aires").build();
    Localidad localidadTest = Localidad.builder()
            .id(1L)
            .nombre("La Plata")
            .codigoPostal(1900)
            .provincia(provinciaTest)
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = localidadTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "La Plata";
        // when
        String nombreTest = localidadTest.getNombre();
        // then
        assertEquals(nombre, nombreTest);
    }

    @Test
    void getCodigoPostal() {
        // given
        int codigoPostal = 1900;
        // when
        int codigoPostalTest = localidadTest.getCodigoPostal();
        // then
        assertEquals(codigoPostal, codigoPostalTest);
    }

    @Test
    void getProvincia() {
        // given
        Provincia provincia = provinciaTest;
        // when
        Provincia provinciaTest = localidadTest.getProvincia();
        // then
        assertEquals(provincia, provinciaTest);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        localidadTest.setId(id);
        // then
        assertEquals(id, localidadTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Berazategui";
        // when
        localidadTest.setNombre(nombre);
        // then
        assertEquals(nombre, localidadTest.getNombre());
    }

    @Test
    void setCodigoPostal() {
        // given
        int codigoPostal = 1874;
        // when
        localidadTest.setCodigoPostal(codigoPostal);
        // then
        assertEquals(codigoPostal, localidadTest.getCodigoPostal());
    }

    @Test
    void setProvincia() {
        // given
        Provincia provincia = Provincia.builder().id(2L).nombre("Buenos Aires").build();
        // when
        localidadTest.setProvincia(provincia);
        // then
        assertEquals(provincia, localidadTest.getProvincia());
    }

    @Test
    void builder() {
        // given
        Localidad localidad = new Localidad();
        localidad.setId(1L);
        localidad.setNombre("La Plata");
        localidad.setCodigoPostal(1900);
        localidad.setProvincia(provinciaTest);
        // when
        Localidad localidadTest = Localidad.builder()
                .id(1L)
                .nombre("La Plata")
                .codigoPostal(1900)
                .provincia(provinciaTest)
                .build();
        // then
        assertEquals(localidad.getId(), localidadTest.getId());
        assertEquals(localidad.getNombre(), localidadTest.getNombre());
        assertEquals(localidad.getCodigoPostal(), localidadTest.getCodigoPostal());
        assertEquals(localidad.getProvincia(), localidadTest.getProvincia());
    }

    @Test
    void noArgsConstructor() {
        // given
        Localidad localidad = new Localidad();
        // when
        Localidad localidadTest = new Localidad();
        // then
        assertEquals(localidad.getId(), localidadTest.getId());
        assertEquals(localidad.getNombre(), localidadTest.getNombre());
        assertEquals(localidad.getCodigoPostal(), localidadTest.getCodigoPostal());
        assertEquals(localidad.getProvincia(), localidadTest.getProvincia());
    }

}
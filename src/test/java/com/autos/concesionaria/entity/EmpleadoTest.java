package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {

    Rol rolTest = Rol.builder().id(1L).nombre("Vendedor").build();
    Direccion direccionTest = Direccion.builder().id(1L).calle("Av. Siempre Viva").numero(742).build();

    Empleado empleadoTest = Empleado.builder()
            .id(1L)
            .nombre("Juan")
            .apellido("Perez")
            .telefono("123456789")
            .dni(12345678)
            .email("juanperez@gmail.com")
            .fechaIngreso(LocalDate.of(2020, 1, 1))
            .fechaEgreso(LocalDate.of(2020, 5, 5))
            .salario(10000.0)
            .rol(rolTest)
            .direccion(direccionTest)
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = empleadoTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "Juan";
        // when
        String nombreTest = empleadoTest.getNombre();
        // then
        assertEquals(nombre, nombreTest);
    }

    @Test
    void getApellido() {
        // given
        String apellido = "Perez";
        // when
        String apellidoTest = empleadoTest.getApellido();
        // then
        assertEquals(apellido, apellidoTest);
    }

    @Test
    void getTelefono() {
        // given
        String telefono = "123456789";
        // when
        String telefonoTest = empleadoTest.getTelefono();
        // then
        assertEquals(telefono, telefonoTest);
    }

    @Test
    void getDni() {
        // given
        Integer dni = 12345678;
        // when
        Integer dniTest = empleadoTest.getDni();
        // then
        assertEquals(dni, dniTest);
    }

    @Test
    void getEmail() {
        // given
        String email = "juanperez@gmail.com";
        // when
        String emailTest = empleadoTest.getEmail();
        // then
        assertEquals(email, emailTest);
    }

    @Test
    void getFechaIngreso() {
        // given
        LocalDate fechaIngreso = LocalDate.of(2020, 1, 1);
        // when
        LocalDate fechaIngresoTest = empleadoTest.getFechaIngreso();
        // then
        assertEquals(fechaIngreso.getDayOfMonth(), fechaIngresoTest.getDayOfMonth());
        assertEquals(fechaIngreso.getMonthValue(), fechaIngresoTest.getMonthValue());
        assertEquals(fechaIngreso.getYear(), fechaIngresoTest.getYear());
    }

    @Test
    void getFechaEgreso() {
        // given
        LocalDate fechaEgreso = LocalDate.of(2020, 5, 5);
        // when
        LocalDate fechaEgresoTest = empleadoTest.getFechaEgreso();
        // then
        assertEquals(fechaEgreso.getDayOfMonth(), fechaEgresoTest.getDayOfMonth());
        assertEquals(fechaEgreso.getMonthValue(), fechaEgresoTest.getMonthValue());
        assertEquals(fechaEgreso.getYear(), fechaEgresoTest.getYear());
    }

    @Test
    void getSalario() {
        // given
        Double salario = 10000.0;
        // when
        Double salarioTest = empleadoTest.getSalario();
        // then
        assertEquals(salario, salarioTest);
    }

    @Test
    void getRol() {
        // given
        Rol rol = Rol.builder().id(1L).nombre("Vendedor").build();
        // when
        Rol rolTest = empleadoTest.getRol();
        // then
        assertEquals(rol.getId(), rolTest.getId());
        assertEquals(rol.getNombre(), rolTest.getNombre());
    }

    @Test
    void getDireccion() {
        // given
        Direccion direccion = Direccion.builder().id(1L).calle("Av. Siempre Viva").numero(742).build();
        // when
        Direccion direccionTest = empleadoTest.getDireccion();
        // then
        assertEquals(direccion.getId(), direccionTest.getId());
        assertEquals(direccion.getCalle(), direccionTest.getCalle());
        assertEquals(direccion.getNumero(), direccionTest.getNumero());
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        empleadoTest.setId(id);
        // then
        assertEquals(id, empleadoTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Pedro";
        // when
        empleadoTest.setNombre(nombre);
        // then
        assertEquals(nombre, empleadoTest.getNombre());
    }

    @Test
    void setApellido() {
        // given
        String apellido = "Gomez";
        // when
        empleadoTest.setApellido(apellido);
        // then
        assertEquals(apellido, empleadoTest.getApellido());
    }

    @Test
    void setTelefono() {
        // given
        String telefono = "987654321";
        // when
        empleadoTest.setTelefono(telefono);
        // then
        assertEquals(telefono, empleadoTest.getTelefono());
    }

    @Test
    void setDni() {
        // given
        Integer dni = 87654321;
        // when
        empleadoTest.setDni(dni);
        // then
        assertEquals(dni, empleadoTest.getDni());
    }

    @Test
    void setEmail() {
        // given
        String email = "juanperez901@gmail.com";
        // when
        empleadoTest.setEmail(email);
        // then
        assertEquals(email, empleadoTest.getEmail());
    }

    @Test
    void setFechaIngreso() {
        // given
        LocalDate fechaIngreso = LocalDate.of(2020, 2, 2);
        // when
        empleadoTest.setFechaIngreso(fechaIngreso);
        // then
        assertEquals(fechaIngreso.getDayOfMonth(), empleadoTest.getFechaIngreso().getDayOfMonth());
        assertEquals(fechaIngreso.getMonthValue(), empleadoTest.getFechaIngreso().getMonthValue());
        assertEquals(fechaIngreso.getYear(), empleadoTest.getFechaIngreso().getYear());
    }

    @Test
    void setFechaEgreso() {
        // given
        LocalDate fechaEgreso = LocalDate.of(2020, 6, 6);
        // when
        empleadoTest.setFechaEgreso(fechaEgreso);
        // then
        assertEquals(fechaEgreso.getDayOfMonth(), empleadoTest.getFechaEgreso().getDayOfMonth());
        assertEquals(fechaEgreso.getMonthValue(), empleadoTest.getFechaEgreso().getMonthValue());
        assertEquals(fechaEgreso.getYear(), empleadoTest.getFechaEgreso().getYear());
    }

    @Test
    void setSalario() {
        // given
        Double salario = 20000.0;
        // when
        empleadoTest.setSalario(salario);
        // then
        assertEquals(salario, empleadoTest.getSalario());
    }

    @Test
    void setRol() {
        // given
        Rol rol = Rol.builder().id(2L).nombre("Gerente").build();
        // when
        empleadoTest.setRol(rol);
        // then
        assertEquals(rol.getId(), empleadoTest.getRol().getId());
        assertEquals(rol.getNombre(), empleadoTest.getRol().getNombre());
    }

    @Test
    void setDireccion() {
        // given
        Direccion direccion = Direccion.builder().id(2L).calle("Av. Siempre Viva").numero(742).build();
        // when
        empleadoTest.setDireccion(direccion);
        // then
        assertEquals(direccion.getId(), empleadoTest.getDireccion().getId());
        assertEquals(direccion.getCalle(), empleadoTest.getDireccion().getCalle());
        assertEquals(direccion.getNumero(), empleadoTest.getDireccion().getNumero());
    }

    @Test
    void builder() {
        // given
        Empleado empleado;
        // when
        empleado = Empleado.builder().id(1L).nombre("Juan").apellido("Perez").telefono("123456789").dni(12345678).email("juanperez@gmail.com").fechaIngreso(LocalDate.of(2020, 1, 1)).fechaEgreso(LocalDate.of(2020, 5, 5)).salario(10000.0).rol(Rol.builder().id(1L).nombre("Vendedor").build()).direccion(Direccion.builder().id(1L).calle("Av. Siempre Viva").numero(742).build()).build();
        // then
        assertEquals(empleado.getId(), empleadoTest.getId());
        assertEquals(empleado.getNombre(), empleadoTest.getNombre());
        assertEquals(empleado.getApellido(), empleadoTest.getApellido());
        assertEquals(empleado.getTelefono(), empleadoTest.getTelefono());
        assertEquals(empleado.getDni(), empleadoTest.getDni());
        assertEquals(empleado.getEmail(), empleadoTest.getEmail());
        assertEquals(empleado.getFechaIngreso().getDayOfMonth(), empleadoTest.getFechaIngreso().getDayOfMonth());
        assertEquals(empleado.getFechaIngreso().getMonthValue(), empleadoTest.getFechaIngreso().getMonthValue());
        assertEquals(empleado.getFechaIngreso().getYear(), empleadoTest.getFechaIngreso().getYear());
        assertEquals(empleado.getFechaEgreso().getDayOfMonth(), empleadoTest.getFechaEgreso().getDayOfMonth());
        assertEquals(empleado.getFechaEgreso().getMonthValue(), empleadoTest.getFechaEgreso().getMonthValue());
        assertEquals(empleado.getFechaEgreso().getYear(), empleadoTest.getFechaEgreso().getYear());
        assertEquals(empleado.getSalario(), empleadoTest.getSalario());
        assertEquals(empleado.getRol().getId(), empleadoTest.getRol().getId());
        assertEquals(empleado.getRol().getNombre(), empleadoTest.getRol().getNombre());
        assertEquals(empleado.getDireccion().getId(), empleadoTest.getDireccion().getId());
        assertEquals(empleado.getDireccion().getCalle(), empleadoTest.getDireccion().getCalle());
        assertEquals(empleado.getDireccion().getNumero(), empleadoTest.getDireccion().getNumero());
    }

    @Test
    void noArgsConstructor() {
        // given
        Empleado empleado;
        // when
        empleado = new Empleado();
        // then
        assertNotNull(empleado);
    }
}
package org.global.usuarios;

public class Admin implements Metodos{ //Clase con metodo "decirQnEntro para confirmar la entrada del administrador
    @Override
    public void decirQnEntro(String usuario) {
        System.out.println(usuario);
    }
}

package org.global.utilerias;

public class ConectorBD { //Clase donde se definen las variables para usarlas cuando se requiera conectarse a la base de datos
    public final static String url = "jdbc:mysql://localhost:3306/desarrollo";
    public final static String username = "root";
    public final static String password = "Xenoblade04";
    public String paraConectarUrl(){String mandar = url+"?useSSL=false";return mandar;
    }
    public String paraConectarUsername(){return username;
    }
    public String paraConectarPass(){
        return password;
    }
}

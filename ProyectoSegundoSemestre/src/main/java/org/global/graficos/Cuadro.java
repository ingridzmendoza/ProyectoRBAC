package org.global.graficos;

import org.global.usuarios.Admin;
import org.global.usuarios.Cuidadores;
import org.global.usuarios.Visitantes;
import org.global.utilerias.ConectorBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cuadro extends JFrame { //clase de gráficos donde esta toda la interfaz gráfica
    Admin admin = new Admin(); //Instancia clase Admin
    Cuidadores cuidadores = new Cuidadores(); //Instancia Clase Cuidadores
    Visitantes visitantes = new Visitantes(); //Instancia Clase Visitantes
    ConectorBD conectorBD = new ConectorBD(); //Instancia clase ConectorBD para conectar a la base de datos
    //------------------------
    //Toda esta sección para la primera ventana que te sale cuando corres el programa
    JPanel panelPrincipal;
    ImageIcon userFoto;
    JLabel fotoUsu;
    JButton empezarBoton;
    JButton salirBoton;
    //-------------------------
    //Segundo frame, aquí es donde ingresas poniendo el usuario y contraseña
    JPanel panelIngresaAdmin;
    JFrame frameIngresaAdmin;
    ImageIcon imagenAdmin;
    JLabel fotoImagenAdmin;
    JLabel userAdmin;
    JLabel contraAdmin;
    JTextField userFieldAdmin;
    JPasswordField contraFieldAdmin;
    JButton ingresarAdmin;
    //------------------------
    String usu; //Usuario - Variable que es la que va a tomar valor cuando se ingresa con este usuario
    String pass; //Contraseña - Variable que es la que va a tomar valor cuando se ingresa con este usuario
    Connection con;//Con esto se creará una conexión hacia la base de datos


    public Cuadro() { //Primer frame de la aplicación
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);//Layout null
        add(panelPrincipal);
        setSize(720,720);//720x720
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        primeraParteDelFrame();//Contenido que tiene este primer frame
        setVisible(true);
    }

    public void primeraParteDelFrame(){//Contenido que va a tener el primer frame
        fotoUsu = new JLabel();
        userFoto = new ImageIcon("src\\main\\java\\org\\global\\fotos\\user.png");
        fotoUsu.setLocation(266,0);
        fotoUsu.setSize(300,200);
        fotoUsu.setIcon(new ImageIcon(userFoto.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH)));
        fotoUsu.setVisible(true);
        panelPrincipal.add(fotoUsu);

        empezarBoton = new JButton();
        empezarBoton.setBounds(270,260,150,50);
        empezarBoton.setVisible(true);
        empezarBoton.setText("Empezar");
        empezarBoton.setFont(new Font("Times new roman",Font.BOLD,20));
        ActionListener cuandoEmpezar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ingresaAdmin();//lo que se hace cuando clickeas a empezar

            }
        };empezarBoton.addActionListener(cuandoEmpezar);

        panelPrincipal.add(empezarBoton);

        salirBoton = new JButton();
        salirBoton.setBounds(270,460,150,50);
        salirBoton.setVisible(true);
        salirBoton.setText("Salir");
        salirBoton.setFont(new Font("Times new roman",Font.BOLD,20));
        ActionListener darSalir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Lo que hace cuando clickeas "Salir"
                String[] adios = {"Salir","Cancelar"};
                int salir = JOptionPane.showOptionDialog(null,"¿Quiere salir?","SALIR",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("src\\main\\java\\org\\global\\fotos\\pensando.png"),adios,adios[0]);
                if (salir == 0) {
                    dispose();
                }
            }
        };salirBoton.addActionListener(darSalir);

        panelPrincipal.add(salirBoton);
    }

    public void ingresaAdmin(){ //Segundo frame - aquí se inicia con usuario y contraseña
        panelIngresaAdmin = new JPanel();
        frameIngresaAdmin = new JFrame();
        panelIngresaAdmin.setLayout(null);
        frameIngresaAdmin.add(panelIngresaAdmin);
        frameIngresaAdmin.setSize(720,720);
        frameIngresaAdmin.setResizable(false);
        frameIngresaAdmin.setLocationRelativeTo(null);
        frameIngresaAdmin.setDefaultCloseOperation(3);
        elementosIngresaAdmin(); //Llamada a función de los elementos que tendrá este segundo frame
        frameIngresaAdmin.setVisible(true);
    }

    public void elementosIngresaAdmin(){ //Elementos que tendrá el segundo frame
        imagenAdmin = new ImageIcon("src\\main\\java\\org\\global\\fotos\\los3G.png");
        fotoImagenAdmin = new JLabel();
        fotoImagenAdmin.setBounds(180,30,300,200);
        fotoImagenAdmin.setIcon(new ImageIcon(imagenAdmin.getImage().getScaledInstance(350,350, Image.SCALE_SMOOTH)));
        fotoImagenAdmin.setVisible(true);

        userFieldAdmin = new JTextField();
        userFieldAdmin.setBounds(270,230,300,50);
        userFieldAdmin.setVisible(true);

        contraFieldAdmin = new JPasswordField();
        contraFieldAdmin.setBounds(270,360,300,50);
        contraFieldAdmin.setVisible(true);

        userAdmin = new JLabel("User: ");
        userAdmin.setBounds(200,230,150,50);
        userAdmin.setFont(new Font("New Times Roman",Font.BOLD,20));
        userAdmin.setVisible(true);

        contraAdmin = new JLabel("Password: ");
        contraAdmin.setBounds(150,360,150,50);
        contraAdmin.setFont(new Font("New Times Roman",Font.BOLD,20));
        contraAdmin.setVisible(true);

        ingresarAdmin = new JButton("Ingresar");
        ingresarAdmin.setBounds(270,490,150,50);
        ingresarAdmin.setFont(new Font("Times New Roman",Font.BOLD,20));
        ActionListener actionIngr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(conectorBD.paraConectarUrl(),
                            conectorBD.paraConectarUsername(),conectorBD.paraConectarPass()); //Connection a la base de datos

                    usu = userFieldAdmin.getText();
                    pass = contraFieldAdmin.getText();

                    //Entrará en base si ingresaste como un admin, un cuidador o un visitante
                    Statement sta = con.createStatement();
                    String sql = "select * from administradores where user='"+usu+"' and password='"+pass+"'";
                    ResultSet rs = sta.executeQuery(sql);

                    Statement sta1 = con.createStatement();
                    String sql1 = "select * from cuidadores where user='"+usu+"' and password='"+pass+"'";
                    ResultSet rs1 = sta1.executeQuery(sql1);

                    Statement sta2 = con.createStatement();
                    String sql2 = "select * from visitantes where user='"+usu+"' and password='"+pass+"'";
                    ResultSet rs2 = sta2.executeQuery(sql2);

                    if (rs.next()){ //Si todo bien, se ingresará como admin
                        frameIngresaAdmin.dispose();
                        admin.decirQnEntro("Entró Admin "+usu);//Admin - si es que se conecta a la base de datos, esta es la acción que realizará
                        cuadroFinalAdmin();

                    } else if (rs1.next()) {//Si todo bien, se ingresará como cuidador
                        frameIngresaAdmin.dispose();
                        cuidadores.decirQnEntro("Entró Cuidador "+ usu);//Cuidadores - si es que se conecta a la base de datos, esta es la acción que realizará
                        cuadroFinalCuidadores();

                    } else if (rs2.next()) {//Si todo bien, se ingresará como visitante
                        frameIngresaAdmin.dispose();
                        visitantes.decirQnEntro("Entró visitante "+usu);//Visitantes - si es que se conecta a la base de datos, esta es la acción que realizará
                        cuadroFinalVisitantes();

                    }else {
                        JOptionPane.showMessageDialog(null,"MAL...");
                        userFieldAdmin.setText("");
                        contraFieldAdmin.setText("");
                    }

                }catch (Exception exe){
                    System.out.println(exe.getMessage());
                }
            }
        };ingresarAdmin.addActionListener(actionIngr);

        ingresarAdmin.setVisible(true);
        panelIngresaAdmin.add(fotoImagenAdmin);
        panelIngresaAdmin.add(userFieldAdmin);
        panelIngresaAdmin.add(contraFieldAdmin);
        panelIngresaAdmin.add(userAdmin);
        panelIngresaAdmin.add(contraAdmin);
        panelIngresaAdmin.add(ingresarAdmin);
    }

    JPanel panelFinalVisi;
    JFrame frameFinalVisi;
    JLabel holaVisit;
    JButton verAnimalesVisit;
    JTextArea dondeVerAnimVisi;
    public void cuadroFinalVisitantes(){ //Frame que entrará si entramos como visitante
        panelFinalVisi = new JPanel();
        frameFinalVisi = new JFrame();
        panelFinalVisi.setLayout(null);
        frameFinalVisi.add(panelFinalVisi);
        frameFinalVisi.setSize(720,720);
        frameFinalVisi.setResizable(false);
        frameFinalVisi.setLocationRelativeTo(null);
        frameFinalVisi.setDefaultCloseOperation(3);
        elementosFinalVisitante();//Llamado al metodo de los elementos que tendrá el frame de visitantes
        frameFinalVisi.setVisible(true);
    }

    public void elementosFinalVisitante(){//Elementos del frame de visitantes
        holaVisit = new JLabel("Hola Visitante "+ usu);
        holaVisit.setBounds(95,0,800,200);
        holaVisit.setFont(new Font("Times New Roman",Font.BOLD,40));

        dondeVerAnimVisi = new JTextArea();
        dondeVerAnimVisi.setLineWrap(true);
        dondeVerAnimVisi.setEditable(false);
        JScrollPane scroll = new JScrollPane(dondeVerAnimVisi,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(110,300,500,300);

        verAnimalesVisit = new JButton("Ver Animales");
        verAnimalesVisit.setBounds(250,200,200,50);
        verAnimalesVisit.setFont(new Font("times new roman",Font.ITALIC,30));
        ActionListener darBotonVerAnimales = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Cuando de le da a este botón realizará la acción de ver los elementos de la base de datos de los animales
                dondeVerAnimVisi.setText("");
                Statement sta = null;
                        try {
                            sta = con.createStatement();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        String sql = "SELECT nombre from animales"; //Nomas podrá ver los animales de la base de datos
                        try {
                            ResultSet rs = sta.executeQuery(sql);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int columnas = rsmd.getColumnCount();

                            while (rs.next()) {
                                for (int i = 1; i <= columnas; i++) {
                                    dondeVerAnimVisi.append(rs.getString(i) + "\t");
                                }
                                dondeVerAnimVisi.append("\n");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };verAnimalesVisit.addActionListener(darBotonVerAnimales);
        panelFinalVisi.add(holaVisit);
        panelFinalVisi.add(verAnimalesVisit);
        panelFinalVisi.add(scroll);
    }

    JPanel panelFinalCuid;
    JFrame frameFinalCuid;
    JLabel holaCuid;
    JButton verComidaCuid;
    JButton agregarAnimal;
    JButton eliminarAnimal;
    JTextArea dondeVerComidaCuid;

    public void cuadroFinalCuidadores(){//Frame que entrará si entramos como cuidadores
        panelFinalCuid = new JPanel();
        frameFinalCuid = new JFrame();
        panelFinalCuid.setLayout(null);
        frameFinalCuid.add(panelFinalCuid);
        frameFinalCuid.setSize(720,720);
        frameFinalCuid.setResizable(false);
        frameFinalCuid.setLocationRelativeTo(null);
        frameFinalCuid.setDefaultCloseOperation(3);
        elementosFinalCuidador();//Llamado al metodo de los elementos que tendrá el frame de cuidadores
        frameFinalCuid.setVisible(true);
    }

    public void elementosFinalCuidador(){//Elementos que tendrá el frame de cuidadores
        dondeVerComidaCuid = new JTextArea();
        dondeVerComidaCuid.setLineWrap(true);
        dondeVerComidaCuid.setEditable(false);
        JScrollPane scroll = new JScrollPane(dondeVerComidaCuid,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(110,300,500,300);


        holaCuid = new JLabel("Hola Cuidador "+ usu);
        holaCuid.setBounds(95,0,800,200);
        holaCuid.setFont(new Font("Times New Roman",Font.BOLD,40));

        verComidaCuid = new JButton("Ver Comida");
        verComidaCuid.setBounds(50,200,200,50);
        verComidaCuid.setFont(new Font("times new roman",Font.ITALIC,20));

        agregarAnimal = new JButton("Agregar Animal");
        agregarAnimal.setBounds(250,200,200,50);
        agregarAnimal.setFont(new Font("times new roman",Font.ITALIC,20));

        eliminarAnimal = new JButton("Eliminar Animal");
        eliminarAnimal.setBounds(450,200,200,50);
        eliminarAnimal.setFont(new Font("times new roman",Font.ITALIC,20));


        ActionListener botonesCuidador = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == verComidaCuid){//Boton de "ver" para ver la comida de los animales
                    dondeVerComidaCuid.setText("");
                    Statement sta = null;
                    try {
                        sta = con.createStatement();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    String sql = "SELECT nombre, comida from animales";
                    try {
                        ResultSet rs = sta.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnas = rsmd.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnas; i++) {
                                dondeVerComidaCuid.append(rs.getString(i) + "\t");
                            }
                            dondeVerComidaCuid.append("\n");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (e.getSource() == agregarAnimal){//Botón para ingresar a un nuevo animal a la base de datos
                    try {
                        String agregarAni = "INSERT INTO animales (nombre, comida) VALUES (?, ?)";

                        PreparedStatement sta = con.prepareStatement(agregarAni); //Statement para ir haciendo una conexión con la variable "AgregarAni" que tiene el comando de sql

                        String nombreAni = "";
                        String comidaAni = "";

                        boolean parar = false;
                        boolean parar1 = false;


                            do {
                                nombreAni = JOptionPane.showInputDialog("Nombre del nuevo animal: ");
                                if (nombreAni == null){
                                    nombreAni = "";
                                }
                                if (nombreAni.equals("")) {
                                    JOptionPane.showMessageDialog(null, "MAL...Agrega un animal bien");
                                    parar = false;
                                } else {
                                    parar = true;
                                }
                            } while (!parar);

                            do {
                                comidaAni = JOptionPane.showInputDialog("Comida del nuevo animal");
                                if (comidaAni == null){
                                    comidaAni = "";
                                }
                                if (comidaAni.equals("")){
                                    JOptionPane.showMessageDialog(null,"MAL...Agrega una comida bien");
                                }else{
                                    JOptionPane.showMessageDialog(null, "Se agregó " + nombreAni);
                                    parar1 = true;
                                }
                            }while (!parar1);

                        //Se coloca la información dentro de las variables del statement
                        sta.setString(1, nombreAni);
                        sta.setString(2, comidaAni);

                        sta.executeUpdate();//Se ejecuta y se actualiza en la base de datos

                        sta.close();//Se cierra el statement
                    } catch (SQLException exe) {
                        exe.printStackTrace();
                    }
                }

                if (e.getSource() == eliminarAnimal){//Boton para eliminar a un animal de la base de datos
                    try {
                        String eliminarAni = "DELETE FROM animales WHERE nombre = ?";

                        PreparedStatement sta = con.prepareStatement(eliminarAni);

                        String eliminarNombreAni = "";

                        boolean parar = false;

                        try {
                            do {// el do while funciona para poder realizar la acción entre los parentesis del 'do' hasta que la condición inicial cambie
                                eliminarNombreAni = JOptionPane.showInputDialog("Nombre del animal que eliminar: ");
                                if (eliminarNombreAni.equals("")) {
                                    JOptionPane.showMessageDialog(null, "MAL...No se eliminó nada porque no pusiste nada");
                                    parar = false;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Eliminaste a " + eliminarNombreAni);
                                    parar = true;
                                }
                            } while (!parar);
                        }catch (NullPointerException npe){
                            JOptionPane.showMessageDialog(null,"Se canceló la operación");
                        }

                        sta.setString(1,eliminarNombreAni);

                        sta.executeUpdate();

                        sta.close();
                    } catch (SQLException exe) {
                        exe.printStackTrace();
                    }
                }
            }
        };verComidaCuid.addActionListener(botonesCuidador);agregarAnimal.addActionListener(botonesCuidador);
        eliminarAnimal.addActionListener(botonesCuidador);

        panelFinalCuid.add(holaCuid);
        panelFinalCuid.add(verComidaCuid);
        panelFinalCuid.add(agregarAnimal);
        panelFinalCuid.add(eliminarAnimal);
        panelFinalCuid.add(scroll);

    }

    JPanel panelFinalAdmin;
    JFrame frameFInalAdmin;
    JLabel holaAdmin;
    JButton verCuidAdmin;
    JButton agregarCuidAdmin;
    JButton eliminarCuidAdmin;
    JTextArea dondeVerCuidAdmin;

    public void cuadroFinalAdmin(){ //Frame si se ingresa como admin
        panelFinalAdmin = new JPanel();
        frameFInalAdmin = new JFrame();
        panelFinalAdmin.setLayout(null);
        frameFInalAdmin.add(panelFinalAdmin);
        frameFInalAdmin.setSize(720,720);
        frameFInalAdmin.setResizable(false);
        frameFInalAdmin.setLocationRelativeTo(null);
        frameFInalAdmin.setDefaultCloseOperation(3);
        elementosFinalAdmin();//Llamado al metodo de los elementos que tendrá el frame de administrador
        frameFInalAdmin.setVisible(true);
    }

    public void elementosFinalAdmin(){//Elementos que tendrá el frame de administrador
        holaAdmin = new JLabel("Hola Admin "+usu);
        holaAdmin.setBounds(95,0,800,200);
        holaAdmin.setFont(new Font("Times New Roman",Font.BOLD,40));

        dondeVerCuidAdmin = new JTextArea();
        dondeVerCuidAdmin.setLineWrap(true);
        dondeVerCuidAdmin.setEditable(false);
        JScrollPane scroll = new JScrollPane(dondeVerCuidAdmin,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(110,300,500,300);

        verCuidAdmin = new JButton("Ver Cuid");
        verCuidAdmin.setBounds(50,200,200,50);
        verCuidAdmin.setFont(new Font("times new roman",Font.ITALIC,20));

        agregarCuidAdmin = new JButton("Agregar Cuid");
        agregarCuidAdmin.setBounds(250,200,200,50);
        agregarCuidAdmin.setFont(new Font("times new roman",Font.ITALIC,20));

        eliminarCuidAdmin = new JButton("Eliminar Cuid");
        eliminarCuidAdmin.setBounds(450,200,200,50);
        eliminarCuidAdmin.setFont(new Font("times new roman",Font.ITALIC,20));

        ActionListener botonesAdmin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == verCuidAdmin){ //Botón para ver los cuidadores de la base de datos
                    dondeVerCuidAdmin.setText("");
                    Statement sta = null;
                    try {
                        sta = con.createStatement();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    String sql = "SELECT user, password FROM cuidadores";
                    try {
                        ResultSet rs = sta.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnas = rsmd.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnas; i++) {
                                dondeVerCuidAdmin.append(rs.getString(i) + "\t");
                            }
                            dondeVerCuidAdmin.append("\n");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (e.getSource() == agregarCuidAdmin){//Botón para poder ingresar cuidadores a la base de datos

                    try {
                        String agregarCuidador = "INSERT INTO cuidadores (user, password) VALUES (?, ?)";

                        PreparedStatement sta = con.prepareStatement(agregarCuidador);

                        JDialog cerrar = new JDialog();
                        String nombreCuid = "";

                        boolean parar = false;

                            do {
                                nombreCuid = JOptionPane.showInputDialog(cerrar,"Nombre del nuevo cuidador: ");
                                if (nombreCuid == null){
                                    nombreCuid = "";
                                }
                                if (nombreCuid.equals("")) {
                                    JOptionPane.showMessageDialog(null, "MAL...Agrega un nombre bien");
                                    parar = false;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Se agregó " + nombreCuid);
                                    parar = true;
                                }

                            } while (!parar);

                        String contrasena = "Xenoblade05";

                        sta.setString(1, nombreCuid);
                        sta.setString(2, contrasena);

                        sta.executeUpdate();

                        sta.close();
                    } catch (SQLException exe) {
                        exe.printStackTrace();
                    }
                }
                if (e.getSource() == eliminarCuidAdmin){ //Botón para eliminar a un cuidador de la base de datos
                    try {
                        String eliminarCuidador = "DELETE FROM cuidadores WHERE user = ?";

                        PreparedStatement sta = con.prepareStatement(eliminarCuidador);

                        String eliminarCuid = "";

                        boolean parar = false;

                        try {
                            do {// el do while funciona para poder realizar la acción entre los parentesis del 'do' hasta que la condición inicial cambie
                                eliminarCuid = JOptionPane.showInputDialog("Nombre del cuidador que eliminar: ");
                                if (eliminarCuid.equals("")) {
                                    JOptionPane.showMessageDialog(null, "MAL...No se eliminó nada porque no pusiste nada");
                                    parar = false;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Eliminaste a " + eliminarCuid);
                                    parar = true;
                                }
                            } while (!parar);
                        }catch (NullPointerException npe){
                            JOptionPane.showMessageDialog(null,"Se canceló la operación");
                        }

                        sta.setString(1,eliminarCuid);

                        sta.executeUpdate();

                        sta.close();
                    } catch (SQLException exe) {
                        exe.printStackTrace();
                    }
                }
            }
        };verCuidAdmin.addActionListener(botonesAdmin);agregarCuidAdmin.addActionListener(botonesAdmin);
        eliminarCuidAdmin.addActionListener(botonesAdmin);

        panelFinalAdmin.add(holaAdmin);
        panelFinalAdmin.add(verCuidAdmin);
        panelFinalAdmin.add(agregarCuidAdmin);
        panelFinalAdmin.add(eliminarCuidAdmin);
        panelFinalAdmin.add(scroll);
    }
}

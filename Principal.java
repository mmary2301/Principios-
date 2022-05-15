import java.util.Scanner;
import java.io.*;
public class Principal {
    public static Producto[] productos = new Producto[10];
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        productos[0] = new Producto(20000, "Camisa", "S", "Amarillo", "CSA1");
        productos[1] = new Producto(40000, "Camisa", "M", "Rojo", "CMR2");
        productos[2] = new Producto(20000, "Buso", "l", "Negro", "BLN3");
        productos[3] = new Producto(20000, "Jean", "32", "Azul", "J32A4");
        productos[4] = new Producto(20000, "Buso", "S", "Gris", "BSG5");
        productos[5] = new Producto(20000, "Camisa", "S", "Negra", "CSN6");
        productos[6] = new Producto(20000, "Jean", "30", "Gris", "J30G7");
        productos[7] = new Producto(20000, "Camisa", "l", "Verde", "ClV8");
        productos[8] = new Producto(20000, "Jean", "34", "Negro", "J34N9");
        productos[9] = new Producto(20000, "Camisa", "S", "Morado", "CSM10");
        Scanner sc = new Scanner(System.in);
        //Nuevo codigo
        System.out.println("Ingrese 1 para Iniciar sesión y 2 para crear cuenta");
        String IniciarPrograma = sc.next();
        if(IniciarPrograma.equals("1")){
            System.out.println("Ingrese su nombre de Usuario");
            String NombredeUsuario= sc.next();
            System.out.println("Ingrese su contraseña");
            String contraseñadeUsuario= sc.next();
            File usuarios3= new File("Usuarios.txt");
            Usuario u1 = new Usuario();
            try {
                Scanner nombre = new Scanner(usuarios3);
                String informacionDeUsuarios;


                while(nombre.hasNextLine()){
                    informacionDeUsuarios=nombre.nextLine();
                    String[] infoUsuario= informacionDeUsuarios.split(",");
                    String N=infoUsuario[0];
                    String C=infoUsuario[1];
                    if((NombredeUsuario.equals(N))&&contraseñadeUsuario.equals(C)){
                        while (true) {
                            System.out.println("Ingrese numero de referencia");
                            System.out.println("Ingrese numero 2 para quitar un Producto de carrito de compras");
                            System.out.println("Ingrese 3 para comprar");
                            System.out.println("Ingrese 4 para Mostrar el estado actual de la compra");

                            String numeroReferencia = sc.next().toUpperCase();
                            if (numeroReferencia.equals("3")) {
                                while(true){
                                    System.out.println("Ingresar Dinero para la compra");
                                    int dinero = sc.nextInt();
                                    u1.setSaldo(dinero);
                                    if(u1.getSaldo()>=(Producto.calcularPrecio(Producto.CarritodeCompras))){
                                        System.out.println("Productos Pagados");
                                        Principal.mostrarInformacion();
                                        break;
                                    }else{
                                        System.out.println("Saldo para la compra insuficiente");
                                    }

                                }break;
                            } else if (numeroReferencia.equals("2")) {
                                Principal.eliminarProducto();
                            } else if (numeroReferencia.equals("4")) {
                                Principal.mostrarInformacion();
                            } else {
                                Principal.buscaryAgregarProducto(numeroReferencia);
                            }
                        }
                    }else{
                        System.out.println("Usuario y contraseña invalidos");;
                    }

                }




            }catch (Exception e){
            }



        }else if(IniciarPrograma.equals("2")) {
            File file = new File("Usuarios.txt");
            PrintWriter outpout = null;
            try{
                outpout = new PrintWriter(new FileWriter(file,true));
                System.out.println("Ingrese su nombre Usuario");
                String nombre = sc.next();
                System.out.println("Ingrese contraseña");
                String contraseña1 = sc.next();
                System.out.println("Confirmar contraseña");
                String contraseña2 = sc.next();
                while (true) {
                    if (contraseña1.equals(contraseña2)) {
                        System.out.println("A creado su cuenta correctamente");
                        outpout.println(nombre+","+contraseña1);
                        break;

                    } else {
                        System.out.println("Vuelva a escribir su contraseña");
                        contraseña2 = sc.next();
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                if (outpout!=null){
                    outpout.close();
                }
            }

        }



    }

    public static void buscaryAgregarProducto (String numeroReferencia){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < productos.length; i++) {
            if (numeroReferencia.equals(productos[i].getNumeroReferencia())) {
                System.out.println(productos[i].toString());
                System.out.println("Ingrese 1 para agregar producto o ingrese 2 para descartarlo");
                int comprar = sc.nextInt();
                while (true) {
                    if (comprar == 1) {
                        Producto.CarritodeCompras.add(productos[i]);
                        break;
                    } else if (comprar == 2) {
                        break;
                    } else if (comprar != 1 && comprar != 2) {
                        System.out.println("Ingrese un numero Correcto");
                        comprar = sc.nextInt();
                    }
                }
            }
        }
    }
    public static void eliminarProducto () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el numero que desea eliminar del carrito de compras");
        int eliminar = sc.nextInt();
        Producto.CarritodeCompras.remove(eliminar - 1);
    }
    public static void mostrarInformacion () {
        for (int i = 0; i < Producto.CarritodeCompras.size(); i++) {
            System.out.println("Producto " + (i + 1) + "    ");
            System.out.println(Producto.CarritodeCompras.get(i).toString());
        }
        System.out.println("El precio total es: " + Producto.calcularPrecio(Producto.CarritodeCompras));
    }
}
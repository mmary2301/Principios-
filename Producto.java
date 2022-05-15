
import java.util.ArrayList;
import java.util.Scanner;

public class Producto{
    public static ArrayList<Producto> CarritodeCompras = new ArrayList<>();
    private double precio;
    private String tipo;
    private String talla;
    private String color;
    private String numeroReferencia;

    public Producto(double precio, String tipo, String talla, String color, String numeroReferencia) {
        this.precio = precio;
        this.tipo = tipo;
        this.talla = talla;
        this.color = color;
        this.numeroReferencia = numeroReferencia;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getNumeroReferencia() {
        return this.numeroReferencia;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Precio: " + precio + " Producto: " + tipo + ", Talla: " + talla + ", Color: " + color;
    }

    public static double calcularPrecio(ArrayList<Producto> productos) {
        double precio = 0;
        for (int i = 0; i < productos.size(); i++) {
            precio = precio + productos.get(i).getPrecio();
        }
        return precio;
    }
}
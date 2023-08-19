/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jqaguilera.ordenycount;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jqagu
 */
public class OrdenYCount {
    
    

    public static void main(String[] args) {
        
        String[] datos_entrada = {"Yungay",
            "Calbuco",
            "Taltal",
            "Iquique",
            "Los Vilos",
            "Algarrobo",
            "Iquique",
            "Yungay",
            "Calbuco",
            "Palena",
            "Yungay"};
        
        ordenarLista(datos_entrada);

        Ciudad[] ciudadList = contarCiudad(datos_entrada);
        
        System.out.println("+ + + + + + + + + + +");
        for (Ciudad cl : ciudadList) {
            System.out.println("+   " + cl.nombre + ": " + cl.cantidad);
        }
        System.out.println("+ + + + + + + + + + +");
    }
    
    public static void ordenarLista(String[] datos) {

        for (int i = 0; i < datos.length - 1; i++) {
            
            for (int j = 0; j < datos.length - i - 1; j++) {
                if (datos[j].compareTo(datos[j + 1]) > 0) {
                    String temp = datos[j];
                    datos[j] = datos[j + 1];
                    datos[j + 1] = temp;
                    
                }
            }
  
        }
    }

    public static Ciudad[] contarCiudad(String[] datos) {
        List<Ciudad> ciudadList = new ArrayList<>();

        for (String dts : datos) {
            boolean bool = false;
            for (Ciudad cl : ciudadList) {
                if (cl.nombre.equals(dts)) {
                    cl.cantidad++;
                    bool = true;
                    break;
                }
            }
            if (!bool) {
                ciudadList.add(new Ciudad(dts, 1));
            }
        }

        return ciudadList.toArray(new Ciudad[0]);
    }
}

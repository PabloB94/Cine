package cine;



import anotacion.Programacion2;

@Programacion2(
        nombreAutor1 = "Pablo",
        apellidoAutor1 = "Beltran de Casso",
        emailUPMAutor1 = "p.beltran@alumnos.upm.es",
        nombreAutor2 = "Inigo",
        apellidoAutor2 = "Aranguren Redondo",
        emailUPMAutor2 = "i.aranguren@alumnos.upm.es"
)


public class Sesion {


    private String hora;
    private int asientosDisponibles;
    private int sigIdCompra;
    private int[][] estadoAsientos;

    public Sesion (String hora, int filas, int columnas){

        this.hora = hora;
        this.estadoAsientos = new int[filas][columnas];
        this.asientosDisponibles = filas * columnas;
        this.sigIdCompra = 1;
    } //Constructor Sesion

    public void comprarEntrada (int fila, int columna){ //Funcion para comprar una entrada
        estadoAsientos[fila-1][columna-1] = sigIdCompra;
        sigIdCompra++;
        asientosDisponibles--;

    }//comprarEntrada

    public int getIdEntrada (int fila, int columna){ //Funcion que devuelve el id de la entrada
        return estadoAsientos[fila-1][columna-1];

    }//getIdEntrada

    public int getButacasDisponiblesSesion (){ //Funcion que devuelve el numero de butacas disponibles en una sesion
        return asientosDisponibles;

    }//getButacasDisponiblesSesion

    public String getHora (){ //Funcion que devuelve la hora de una sesion
        return hora;

    }//getHora

    public char[][] getEstadoSesion (){ //Funcion que devuelve una matriz que representa el estado de la sesion
        char[][] estadoSesion = new char[estadoAsientos.length][estadoAsientos[0].length];
        for (int i = 0; i < estadoAsientos.length; i++){
            for (int j = 0; j < estadoAsientos[0].length; j++){
                if (estadoAsientos[i][j] != 0){
                    estadoSesion[i][j] = '#';
                }
                else {
                    estadoSesion[i][j] = 'O';
                }
            }
        }
        return estadoSesion;

    }//getEstadoSesion

    public String recogerEntradas (int id){ //Funcion que devuelve la informacion de una entrada
        String entrada = new String(hora + "+");
        String otro = "";
        for (int i = 0; i < estadoAsientos.length; i++){
            for (int j = 0; j < estadoAsientos[0].length; j++){
                if (estadoAsientos[i][j] == id){
                    String asientos = (i+1) + "," + (j+1) + "+";
                    otro = otro + asientos;
                }
            }
        }

        if (otro.equals("")){
            return null;
        }

        else{
            return entrada + otro;
        }

    }//recogerEntradas

    public ButacasContiguas recomendarButacasContiguas (int noButacas){ //Funcion que recomienda un número dado de butacas según las especificaciones dadas
        boolean finalizado = false;//Variable para evitar recorrer ambos bucles innecesariamente
        int[] butacasEscogidas = new int[3]; //Por las especificaciones del lenguaje, este array está por defecto inicializado con todos sus elementos a 0.
        ButacasContiguas butacasContiguas = null;
        for (int filaActual = (estadoAsientos.length + 1) / 2 + 1; filaActual <= estadoAsientos.length  && !finalizado; filaActual++){//Bucle para buscar en la mitad posterior de la sala
            butacasEscogidas = buscarButacas(noButacas, filaActual);
            if (butacasEscogidas[2] == noButacas){
                finalizado = !finalizado;
            }
        }
        for (int filaActual = (estadoAsientos.length + 1) / 2; filaActual > 0  && !finalizado; filaActual--){//Bucle para buscar en la mitad anterior de la sala
            butacasEscogidas = buscarButacas(noButacas, filaActual);
            if (butacasEscogidas[2] == noButacas){
                finalizado = !finalizado;
            }
        }
        if(finalizado){
            ButacasContiguas aux = new ButacasContiguas(butacasEscogidas[0],butacasEscogidas[1],butacasEscogidas[2]);
            butacasContiguas = aux;
        }
        return butacasContiguas;
    }//recomendarButacasContiguas

    private int[] buscarButacas (int noButacas, int filaActual){
        int libres = 0;
        boolean asientosJuntos = true;
        int[] escogidas = new int[3];
        for (int columnaActual = estadoAsientos[0].length - 1; columnaActual >= 1 && libres != noButacas; columnaActual--){
            if (estadoAsientos[filaActual - 1][columnaActual] == 0){
                for (int asientosContiguos = 0; asientosContiguos < noButacas && asientosJuntos; asientosContiguos++){
                    if (columnaActual - asientosContiguos >= 0 && estadoAsientos[filaActual - 1][columnaActual - asientosContiguos] == 0){
                        libres++;
                        asientosJuntos = true;
                    }else{
                        libres = 0;
                        asientosJuntos = false;
                    }
                    escogidas[0] = filaActual;
                    escogidas[1] = columnaActual - asientosContiguos + 1;
                    escogidas[2] = libres;
                }
            }
        }
        return escogidas;
    }

    public void comprarEntradasRecomendadas (ButacasContiguas butacas){ //Compra las entradas recomendadas por el método recomendarButacasContiguas
        for (int i = 0; i < butacas.getNoButacas(); i++){
            estadoAsientos[butacas.getFila()-1][butacas.getColumna()-1+i] = sigIdCompra;
        }
        sigIdCompra++;
        asientosDisponibles = asientosDisponibles - butacas.getNoButacas();
    }//comprarEntradasRecomendadas

    public boolean equals (Object sesion){ //Funcion para comparar dos sesiones
    	Sesion aux = null;
    	if (sesion instanceof Sesion && sesion != null){
    		aux = (Sesion) sesion;
    		return this.getHora().equals(aux.getHora());
    	}else{
    		return false;
    	}
    } //equals
}//Clase Sesion

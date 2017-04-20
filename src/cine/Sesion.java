package cine;

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

    public ButacasContiguas recomendarButacasContiguas (int noButacas){ //Método que dado un numero de butacas, recomienda la mejor posición dentro de la sala, primero buscando en la mitad trasera y luego en la mitad delantera
        int butacasDisponibles = 0;
        int vaux = 0;
        boolean done = false;
        ButacasContiguas butacasContiguas = null;
        for (int i = (estadoAsientos.length+1)/2+1; i <= estadoAsientos.length && butacasDisponibles != noButacas && !done; i++){
            for (int j = estadoAsientos[0].length-1; j >= 1; j--){
                if (estadoAsientos[i-1][j] == 0){
                    for (int k = 0; k < noButacas ;k++){
                        if (j-k >= 0 && estadoAsientos[i-1][j-k] == 0){
                            butacasDisponibles++;
                        }else{
                            butacasDisponibles=0;
                        }
                        vaux = j-k+1;
                    }
                }
                if (butacasDisponibles == noButacas){
                    ButacasContiguas aux = new ButacasContiguas(i,vaux,noButacas);
                    butacasContiguas = aux;
                    done = !done;
                }
            } //Búsqueda en la mitad trasera de la sala
        }
        for (int i = (estadoAsientos.length+1)/2; i > 0 && butacasDisponibles != noButacas && !done; i--){
            for (int j = estadoAsientos[0].length-1; j >= 1; j--){
                if (estadoAsientos[i-1][j] == 0){
                    for (int k = 0; k < noButacas ;k++){
                        if (j-k >= 0 && estadoAsientos[i-1][j-k] == 0){
                            butacasDisponibles++;
                        }else{
                            butacasDisponibles=0;
                        }
                        vaux = j-k+1;
                    }
                }
                if (butacasDisponibles == noButacas){
                    ButacasContiguas aux = new ButacasContiguas(i,vaux,noButacas);
                    butacasContiguas = aux;
                    done = !done;
                }
            }//Búsqueda en la mitad delantera de la sala;
        }
        return butacasContiguas;
    }//recomendarButacasContiguas

    public void comprarEntradasRecomendadas (ButacasContiguas butacas){ //Funcion para comprar entradas recomendadas por la funcion recomendarButacasContiguas
        for (int i = 0; i < butacas.getNoButacas(); i++){
            estadoAsientos[butacas.getFila()-1][butacas.getColumna()-1+i] = sigIdCompra;
        }
        sigIdCompra++;
        asientosDisponibles = asientosDisponibles - butacas.getNoButacas();
    }//comprarEntradasRecomendadas

    public boolean equals (Sesion obj){ //Funcion para comparar dos sesiones
        return this.hora.equals(obj.hora);

    } //equals
}//Class Sesion

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

public class Cine {

    private String nombre;
    private Sala[] salas;

    public Cine(String nombre, Sala[] salas){

        this.nombre = nombre;
        this.salas =  salas;

    }//Constructor Cine

    public void comprarEntrada (int sala, int sesion, int fila, int columna){ //Funcion para comprar una entrada
    	this.salas[sala-1].comprarEntrada(sesion, fila, columna);
    }//comprarEntrada

    public int getIdEntrada (int sala, int sesion, int fila, int columna){ //Funcion que devuelve el id de la entrada
    	return this.salas[sala-1].getIdEntrada(sesion, fila, columna);
    }//getIdEntrada

    public char[][] getEstadoSesion (int sala, int sesion){ //Funcion que devuelve una matriz que representa el estado de la sesion
        return this.salas[sala-1].getEstadoSesion(sesion);

    }//getEstadoSesion

    public String[] getPeliculas (){ //Funcion que devuelve las peliculas que hay en el cine
        String[] Peliculas = new String[salas.length];
        for (int i = 0; i < salas.length; i++){
            Peliculas[i] = salas[i].getPelicula();
        }
        return Peliculas;

    }//getPeliculas
    
    public String[] getHorasDeSesionesDeSala (int sala){ //Funcion que devuelve las horas de las sesiones en una sala
    	return this.salas[sala-1].getHorasDeSesionesDeSala();
    }//getHorasDeSesionesDeSala

    public String recogerEntradas (int id, int sala, int sesion){ //Funcion que devuelve la informacion de una entrada
        if (this.salas[sala-1].recogerEntradas(id, sesion) == null){
            return null;
        }
        else {
            return this.nombre + "@" + this.salas[sala - 1].recogerEntradas(id, sesion - 1);
        }
    }//recogerEntradas

    public int getButacasDisponiblesSesion (int sala, int sesion){ //Funcion que devuelve el numero de butacas disponibles en una sesion
    	return this.salas[sala-1].getButacasDisponiblesSesion(sesion) ;
    }//getButacasDisponiblesSesion

    public ButacasContiguas recomendarButacasContiguas (int noButacas, int sala, int sesion){ //Funcion para recomendar butacas contiguas
    	return this.salas[sala-1].recomendarButacasContiguas(noButacas, sesion);

    }//recomendarButacasContiguas

    public void comprarEntradasRecomendadas (int sala, int sesion, ButacasContiguas butacas){ //Funcion para comprar entradas recomendadas por la funcion recomendarButacasContiguas
    	this.salas[sala-1].comprarEntradasRecomendadas(sesion, butacas);
    }//comprarEntradasRecomendadas

    public void incluirSesion (int sala, String horaSesion){ //Funcion para añadir una nueva sesion
    	this.salas[sala-1].incluirSesion(horaSesion);
    }//incluirSesion

    public void borrarSesion (int sala, String horaSesion){ //Funcion para eliminar una sesion 
        this.salas[sala-1].borrarSesion(horaSesion);
    }//borrarSesion
}//Class Cine

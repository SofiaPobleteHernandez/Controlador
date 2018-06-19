import java.util.Scanner;
import java.util.ArrayList;

public class Controlador {
    private String opcion;
    private int longitud_espacio;
    private int altura_espacio;
    private int ancho_espacio;
    private Valkyrie valkyrie;
    private int numero_de_pistas;
    ArrayList<Pistas> pistas;
    ArrayList<Enemigo> enemigos;
    Pistas[]rep_pistas;
    String[][][] matriz;
    int[][]suelo;

    public Controlador(){
        valkyrie=new Valkyrie();
        rep_pistas=new Pistas[numero_de_pistas];
        pistas=new ArrayList<Pistas>();
        enemigos=new ArrayList<Enemigo>();
        matriz=new String[longitud_espacio][ancho_espacio][altura_espacio];
    }

    public Controlador(String opcion, int longitud_espacio, int altura_espacio, int ancho_espacio, Valkyrie valkyrie, int numero_de_pistas){
        this.opcion=opcion;
        this.longitud_espacio=longitud_espacio;
        this.altura_espacio=altura_espacio;
        this.ancho_espacio=ancho_espacio;
        this.valkyrie=valkyrie;
        this.numero_de_pistas=numero_de_pistas;
    }

    Scanner leer_opcion = new Scanner(System.in);
    int veces=0;

    public int Establecer_Longitud(){
        longitud_espacio=(int)(Math.random() * 10000)+300;
        return this.longitud_espacio;
    }

    public int Establecer_Altura(){
        altura_espacio=(int)(Math.random() * 10000)+300;
        return this.altura_espacio;
    }

    public int Establecer_Ancho() {
        ancho_espacio = (int)(Math.random() * 10000) + 300;
        return this.ancho_espacio;
    }

    public int Cantidad_de_Pistas(){
        if(ancho_espacio>longitud_espacio){
            numero_de_pistas = (int) (Math.random() * (ancho_espacio/longitud_espacio))+1;
        }

        if(ancho_espacio<longitud_espacio){
            numero_de_pistas = (int) (Math.random() * (longitud_espacio/ancho_espacio))+1;
        }

        if(ancho_espacio == longitud_espacio){
            numero_de_pistas = (int) (Math.random() * ancho_espacio) +1;
        }
        return this.numero_de_pistas;
    }


    public ArrayList Establecer_Pistas(){

        int azar = 0;

        suelo=new int[ancho_espacio][longitud_espacio];

        for(int i = 0; i<ancho_espacio; i++){
            for(int j = 0; j<longitud_espacio; j++){

                suelo[i][j]=0;
            }
        }

        System.out.println("Se llenó suelo");

        for(int i = 0; i<ancho_espacio; i++){
            for(int j = 0; j<longitud_espacio; j++){

                if(i==0){

                    azar = (int) (Math.random() * 10)+1;

                    if(azar>5){
                        suelo[i][j]=1;
                    }

                }

                if(i>0){

                    if(j>0) {
                        if ((suelo[i - 1][j] == 1) && (suelo[i][j - 1]) == 1) {
                            suelo[i][j] = 1;
                        }

                        if ((suelo[i - 1][j] == 0) && (suelo[i][j - 1] == 1)) {
                            azar = (int) (Math.random() * 10) + 1;
                            if (azar > 5) {
                                suelo[i][j] = 1;
                            }
                        }

                        if ((suelo[i - 1][j] == 1) && (suelo[i][j - 1] == 0)) {
                            azar = (int) (Math.random() * 10) + 1;
                            if (azar > 5) {
                                suelo[i][j] = 1;
                            }
                        }

                        if ((suelo[i - 1][j] == 0) && (suelo[i][j - 1] == 0)) {
                            azar = (int) (Math.random() * 10) + 1;
                            if (azar > 5) {
                                suelo[i][j] = 1;
                            }
                        }
                    }

                    if(j==0){

                        azar = (int) (Math.random() * 10)+1;
                        if(azar > 5){
                            suelo[i][j] = 1;
                        }
                    }

                }

            }
        }

        System.out.println("Se creó pistas");



        return this.pistas;
    }



    public int Imprimir_Pistas(){
        System.out.println("");
        System.out.println("PISTAS DISPONIBLES : ");
        for (int i=0; i<numero_de_pistas; i++){
            System.out.println("");
            System.out.println("-Pista " + (i+1) + " : ");
            System.out.println("-Comienza en las coordenadas : (" + pistas.get(i).getPosicion_x_pista() + "," + pistas.get(i).getPosicion_y_pista() + ")");
            System.out.println("-Tiene una longitud de       : " + pistas.get(i).getLongitud_pista());
            System.out.println("-Tiene un ancho de           : " + pistas.get(i).getAncho_pista());
            System.out.println("-----------------------------------------------------------------");
        }
        return 0;
    }

    public ArrayList Crear_Enemigos(){
        int numero_de_enemigos=0;
        int creacion;

        for(int i=0; i<longitud_espacio; i++){
            for(int j=0; j<ancho_espacio; j++){
                for(int k=0; k<altura_espacio; k++){

                    creacion = (int)(Math.random() * 10)+1;

                    if(creacion==5){
                        numero_de_enemigos++;
                        Enemigo enemy = new Enemigo(numero_de_enemigos,i,j,k,30);
                    }
                }
            }
        }
        return this.enemigos;
    }

    public int Mostrar_Enemigos(){

        System.out.println("LISTADO DE ENEMIGOS : ");
        System.out.println("");
        for(int i=0; i<enemigos.size(); i++){
            System.out.println("-Enemigo N°" + (i+1) + " : (" + enemigos.get(i).getCoordenada_x() + " , " + enemigos.get(i).getCoordenada_y() + " , " + enemigos.get(i).getCoordenada_z() + ").");
        }
        return 0;
    }

    public String[][][] Crear_Matriz(){
        for (int posicion=0; posicion<enemigos.size(); posicion++) {
            for (int i = 0; i < longitud_espacio; i++){
                for (int j = 0; j < ancho_espacio; j++) {
                    for (int k = 0; k < altura_espacio; k++) {

                        if((i==enemigos.get(posicion).getCoordenada_x()) && (j==enemigos.get(posicion).getCoordenada_y()) && (k==enemigos.get(posicion).getCoordenada_z())){
                            matriz[i][j][k]="E";
                        }
                    }
                }
            }
        }
        return matriz;

    }

    public int[][] Dibujar_Suelo(){
        for(int i=0; i<longitud_espacio; i++){
            for (int j=0; j<ancho_espacio; j++){
                suelo[i][j]=0;
            }
        }

        for(int a=0; a<pistas.size(); a++){
            for(int x=pistas.get(a).getPosicion_x_pista(); x<(pistas.get(a).getLongitud_pista()+pistas.get(a).getPosicion_x_pista()); x++){
                for(int y=pistas.get(a).getPosicion_y_pista(); y<(pistas.get(a).getAncho_pista()+pistas.get(a).getPosicion_y_pista()); y++){
                    suelo[x][y]=1;
                }
            }
        }
        return suelo;
    }

    public String Menu(){
        System.out.println("");
        System.out.println("");
        System.out.println("======================================================");
        System.out.println("======================================================");
        System.out.println("Teclas y Su Uso: ");
        System.out.println("-E : Empieza el Juego.");
        System.out.println("-T : Termina el Juego.");
        System.out.println("-H : Aumenta la Altitud de vuelo del VF-1.");
        System.out.println("-L : Disminuye la Altitud de vuelo del VF-1.");
        System.out.println("-Q : Aumenta la Veocidad del VF-1");
        System.out.println("-S : Disminuye la Velocidad del VF-1");
        System.out.println("-R : El VF-1 retrocede.");
        System.out.println("-G : El VF-1 avanza.");
        System.out.println("-F : Libera disparo.");
        System.out.println("-1 : Transforma el VF-1 en Modo Fighter.");
        System.out.println("-2 : Transforma el VF-1 en Modo Gerwalk.");
        System.out.println("-3 : Transforma el VF-1 en Modo Battloid");
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.println("");
        opcion=leer_opcion.next();
        return opcion;
    }

    public int Resolver() {

        if ((opcion.equalsIgnoreCase("E")) && (veces == 0)) {
            System.out.println("¡A jugar! ¡Ya puedes dirigir al VF-1!");
        }

        if (opcion.equalsIgnoreCase("H")) {
            if (!valkyrie.getEstado().equalsIgnoreCase("3")) {
                if ((valkyrie.getPosicion_y() + valkyrie.getVelocidad() < altura_espacio) || (valkyrie.getPosicion_y() + valkyrie.getVelocidad() == altura_espacio)) {

                    if (valkyrie.getEstado().equalsIgnoreCase("2")) {
                        valkyrie.setPosicion_y(valkyrie.getPosicion_y() + valkyrie.getVelocidad());
                        valkyrie.setOrden(opcion);
                    }

                    if (valkyrie.getEstado().equalsIgnoreCase("1")) {

                        if(valkyrie.getPosicion_y()==0) {
                            int posicion = 0;
                            int tamano = 0;

                            for (int i = 0; i < numero_de_pistas; i++) {
                                if (pistas.get(i).getPosicion_x_pista() < valkyrie.getPosicion_x()) {
                                    if ((pistas.get(i).getPosicion_x_pista() + pistas.get(i).getLongitud_pista()) > valkyrie.getPosicion_x()) {
                                        tamano = pistas.get(i).getLongitud_pista();
                                        posicion = pistas.get(i).getPosicion_x_pista();
                                    }
                                }
                            }


                            if ((tamano > 99) && (valkyrie.getVelocidad() > 349)) {
                                valkyrie.setPosicion_y(valkyrie.getVelocidad());
                                valkyrie.setOrden(opcion);
                            }
                        }

                        if(valkyrie.getPosicion_y()>0){
                            valkyrie.setPosicion_y(valkyrie.getVelocidad()+valkyrie.getPosicion_y());
                            valkyrie.setOrden(opcion);
                        }
                    }
                }

                if (valkyrie.getPosicion_y() + valkyrie.getVelocidad() > altura_espacio) {
                    valkyrie.setPosicion_y(altura_espacio);
                    valkyrie.setOrden(opcion);
                }
            }
        }

        if (opcion.equalsIgnoreCase("L")) {
            if (!valkyrie.getEstado().equalsIgnoreCase("3")) {
                if (valkyrie.getPosicion_y() - valkyrie.getVelocidad() > -1) {
                    valkyrie.setPosicion_y(valkyrie.getPosicion_y() - valkyrie.getVelocidad());
                    valkyrie.setOrden(opcion);
                }
                if (valkyrie.getPosicion_y() - valkyrie.getVelocidad() < 0) {
                    valkyrie.setPosicion_y(0);
                    valkyrie.setOrden(opcion);
                }
            }
        }

        if (opcion.equalsIgnoreCase("Q")) {
            int incremento=0;
            if (valkyrie.getVelocidad() < 750) {
                incremento=(int)(Math.random()*100)+1;
                while(valkyrie.getVelocidad()+incremento>750){
                    incremento=(int)(Math.random()*100)+1;
                }
                valkyrie.setVelocidad(valkyrie.getVelocidad() + incremento);
                valkyrie.setOrden(opcion);
            }
        }

        if (opcion.equalsIgnoreCase("S")) {
            int disminucion=0;
            disminucion=(int)(Math.random()*100)+1;
            while(valkyrie.getVelocidad()-disminucion<0){
                disminucion=(int)(Math.random()*100)+1;
            }
            valkyrie.setVelocidad(valkyrie.getVelocidad()-disminucion);
            valkyrie.setOrden(opcion);
        }

        if (opcion.equalsIgnoreCase("R")) {
            if (valkyrie.getPosicion_x() - valkyrie.getVelocidad() > -1) {
                valkyrie.setPosicion_x(valkyrie.getPosicion_x() - valkyrie.getVelocidad());
                valkyrie.setOrden(opcion);
            }
            if (valkyrie.getPosicion_x() - valkyrie.getVelocidad() < 0) {
                valkyrie.setPosicion_x(0);
                valkyrie.setOrden(opcion);
            }
        }

        if (opcion.equalsIgnoreCase("G")) {
            if (valkyrie.getPosicion_x() + valkyrie.getVelocidad() < longitud_espacio + 1) {
                valkyrie.setPosicion_x(valkyrie.getPosicion_x() + valkyrie.getVelocidad());
                valkyrie.setOrden(opcion);
            }
            if (valkyrie.getPosicion_x() + valkyrie.getVelocidad() > longitud_espacio) {
                valkyrie.setPosicion_x(longitud_espacio);
                valkyrie.setOrden(opcion);
            }
        }

        if (opcion.equalsIgnoreCase("3")) {

            if (valkyrie.getEstado().equalsIgnoreCase("1")) {
                if (valkyrie.getPosicion_y() < 201) {
                    valkyrie.setEstado("3");
                    valkyrie.setOrden(opcion);
                }
            }

            if (valkyrie.getEstado().equalsIgnoreCase("2")) {
                if (valkyrie.getPosicion_y() < 201) {
                    valkyrie.setEstado("3");
                    valkyrie.setOrden(opcion);
                }
            }
        }

        if (opcion.equalsIgnoreCase("2")) {
            valkyrie.setEstado("2");
            valkyrie.setOrden(opcion);
        }

        if (opcion.equalsIgnoreCase("1")) {
            if (valkyrie.getEstado().equalsIgnoreCase("2")) {

                int longitud = 0;

                for (int i = 0; i < numero_de_pistas; i++) {
                    if (pistas.get(i).getPosicion_x_pista() < valkyrie.getPosicion_x()) {
                        if ((pistas.get(i).getPosicion_x_pista() + pistas.get(i).getLongitud_pista()) > valkyrie.getPosicion_x()) {
                            longitud = pistas.get(i).getLongitud_pista();
                        }
                    }
                }

                if ((valkyrie.getPosicion_y() > 0) && (longitud>99)){
                    valkyrie.setEstado("1");
                    valkyrie.setOrden(opcion);
                }
            }
            if (valkyrie.getEstado().equalsIgnoreCase("3")) {
                valkyrie.setEstado("2");
                valkyrie.setOrden("2");
            }
        }

        if(opcion.equalsIgnoreCase("F")){
            valkyrie.setOrden(opcion);
        }

        veces++;
        return veces;
    }

    public int Informe(){
        System.out.println("");
        System.out.println("REPORTE : ");
        System.out.println("");
        System.out.println("El VF-1 se encuentra en las siguientes condiciones : ");
        if(valkyrie.getEstado().equalsIgnoreCase("1")){
            System.out.println("-Modo   : Fighter.");
            if(valkyrie.getPosicion_y()==0){
                System.out.println("-Transporte  : Se desliza.");
            }
            if(valkyrie.getPosicion_y()>0){
                System.out.println("-Transporte  : Vuela.");
            }
        }
        if(valkyrie.getEstado().equalsIgnoreCase("2")){
            System.out.println("-Modo   : Gerwalk.");
            if(valkyrie.getPosicion_y()==0){
                System.out.println("-Transporte  : Camina.");
            }
            if(valkyrie.getPosicion_y()>0){
                System.out.println("-Transporte  : Vuela.");
            }
        }
        if(valkyrie.getEstado().equalsIgnoreCase("3")){
            System.out.println("-Modo   : Battloid.");
            System.out.println("-Transporte  : Camina.");
        }
        System.out.println("-Coordenadas  : (" + valkyrie.getPosicion_x() + " , " + valkyrie.getPosicion_y() + ") metros.");
        System.out.println("-Velocidad    : " + valkyrie.getVelocidad() + " km/h.");

        int largo = 0;

        for (int i = 0; i < numero_de_pistas; i++) {
            if (pistas.get(i).getPosicion_x_pista() < valkyrie.getPosicion_x()) {
                if ((pistas.get(i).getPosicion_x_pista() + pistas.get(i).getLongitud_pista()) > valkyrie.getPosicion_x()) {
                    largo = pistas.get(i).getLongitud_pista();
                    System.out.println("-El VF-1 está sobre una pista de longitud : "+ largo);
                }
            }
        }

        valkyrie.Imprimir_Estado_de_Piezas();

        return 0;
    }
}
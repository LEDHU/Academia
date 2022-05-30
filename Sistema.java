import java.util.Scanner;
//get para ter acesso
//set para modificar

public class Sistema {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Cliente[] clientes = new Cliente[20];

        int parada_do_sistema = 1;
        int aux = 0;
        int[] delete = new int [2];

        for (int i = 0; i < clientes.length; i++){
            clientes[i] = new Cliente();
        }

        imprimirLinhas();

        while (parada_do_sistema != 0){

            menu();

            parada_do_sistema = teclado.nextInt();

            if (parada_do_sistema == 1){

                if(delete[1] == 0 && aux < 20){
                    adicionarAluno(clientes, aux);
                    aux = aux + 1;
                    imprimirCadastrado();
                }
                else if (aux < 20){
                    adicionarAluno(clientes, delete[0]);
                    delete[1] = 0;
                    imprimirCadastrado();
                }else
                    System.out.println("\n\nLista cheia!!\n\n");

            }else if (parada_do_sistema == 2){

                if(aux != 0)
                    delete = excluiAluno(clientes, aux);
                else
                    imprimirListaVazia();

            }else if (parada_do_sistema == 3){

                if(aux != 0)
                    imprimirAluno(clientes, aux);
                else
                    imprimirListaVazia();

            }else if (parada_do_sistema == 4){

                if(aux != 0)
                    imprimirLista(clientes, aux);
                else
                    imprimirListaVazia();

            }else if (parada_do_sistema == 5){

                if (aux != 0)
                    imprimirDevedores(clientes, aux);
                else
                    imprimirListaVazia();

            }else if (parada_do_sistema == 6){

                if(aux != 0)
                    imprimirValor(clientes, aux);
                else
                    imprimirListaVazia();

            }else if (parada_do_sistema == 7){

                if (aux != 0)
                    atualizaAluno(clientes, aux);
                else
                    imprimirListaVazia();

            }else if (parada_do_sistema == 0)
                encerrandoSistema();

            else
                imprimirNumeroInvalido();
        }
    }

    /////////////MENU////////////////////
    public static void menu(){

        System.out.println("Digite 1 para adicionar um novo aluno");
        System.out.println("Digite 2 para excluir um aluno");
        System.out.println("Digite 3 para buscar dado de aluno");
        System.out.println("Digite 4 para ver a lista de alunos");
        System.out.println("Digite 5 para ver a quantidade dos alunos devedores");
        System.out.println("Digite 6 para ver lista de valores a receber");
        System.out.println("Digite 7 para alterar dados de aluno");
        System.out.println("Digite 0 para sair");

    }

    //////////////////////um e sete//////////////////////////////
    public static void adicionarAluno(Cliente[] listinha, int num){

        Scanner teclado = new Scanner(System.in);
        //matricula
        listinha[num].setMatricula(num);

        //nome
        System.out.println("Qual o nome do aluno? ");
        listinha[num].setNome(teclado.next());

        //atividade
        System.out.println("Qual atividade do aluno? ");
        listinha[num].setAtividade(teclado.next());

        //Devedor?
        System.out.println("O aluno estar devendo? ");
        listinha[num].setPag(teclado.nextBoolean());

        //mensalidade
        if (listinha[num].getPag()){

            System.out.println("O aluno estar devendo quanto? ");
            listinha[num].setMensalidade(teclado.nextDouble());

        }else
            listinha[num].setMensalidade(0);
    }

    /////////////////////////////////dois/////////////////
    public static int[] excluiAluno(Cliente[] lista_de_aluno, int auxiliar) {

        int matri = matricula();
        int[] exclui = new int[2];

        if (matri > -1 && matri < auxiliar){
            exclui[0] = matri;
            exclui[1] = 1;

            lista_de_aluno[matri].setMatricula(matri);
            lista_de_aluno[matri].setNome(null);
            lista_de_aluno[matri].setAtividade(null);
            lista_de_aluno[matri].setPag(false);
            lista_de_aluno[matri].setMensalidade(0);

            imprimirLinhas();
            System.out.println("\n\nAluno excluido com sucesso!!\n\n");
            imprimirLinhas();

        }else
            imprimirDesaparecido();
        return exclui;
    }

    ////////////////tres//////////////////////////////////
    public static void imprimirAluno(Cliente[] cliente, int tam){

        int matri = matricula();

        if (matri > -1 && matri < tam){

            imprimirLinhas();
            System.out.println("\n\nA matricula: " + cliente[matri].getMatricula());
            System.out.println("Nome do aluno: " + cliente[matri].getNome());
            System.out.println("Atividade do aluno: " + cliente[matri].getAtividade());
            System.out.println("Devendo?: " + cliente[matri].getPag());
            System.out.println("Valor: R$" + cliente[matri].getMensalidade() + "\n\n");
            imprimirLinhas();

        }else
            imprimirDesaparecido();
    }

    /////////////////////quatro////////////////////////////
    public static void imprimirLista(Cliente[] lista, int auxiliar){

        for (int i = 0; i < auxiliar; i++){

            imprimirLinhas();
            System.out.println("Aluno: " + lista[i].getMatricula());
            System.out.println("Nome aluno: " + lista[i].getNome());
            System.out.println("Atividade do aluno: " + lista[i].getAtividade());
            imprimirLinhas();

        }
    }

    //////////////////////cinco//////////////////////////
    public static void imprimirDevedores(Cliente[] dev, int tam){

        int devedores = 0;
        int nao_devedores = 0;

        for (int i = 0; i < tam; i++){

            if (dev[i].getMensalidade() > 0)
                devedores = devedores + 1;

            else
                nao_devedores = nao_devedores + 1;

        }

        imprimirLinhas();
        System.out.println("\n\nA quantidade de alunos devendo: " + devedores);
        System.out.println("A quantidade de alunos em dia: " + nao_devedores + "\n\n");
        imprimirLinhas();

    }

    //////////////////////////seis//////////////////////////
    public static void imprimirValor(Cliente[] list, int tam){

        int controle = 1;
        double auxiliarNum;
        double[] vetNum = new double[tam];
        String auxiliarNome;
        String[] vetNome = new String[tam];

        for (int i = 0; i < tam; i++){
            if(list[i].getMensalidade() != 0) {
                vetNum[i] = list[i].getMensalidade();
                vetNome[i] = list[i].getNome();
            }
        }

        while (controle != 0){

            controle = 0;

            for (int i = 0; i < tam - 1; i++){

                if (vetNum[i] < vetNum[i+1]){

                    auxiliarNum = vetNum[i];
                    auxiliarNome = vetNome[i];

                    vetNum[i] = vetNum[i+1];
                    vetNome[i] = vetNome[i+1];

                    vetNum[i+1] = auxiliarNum;
                    vetNome[i+1] = auxiliarNome;

                    controle++;

                }
            }
        }

        imprimirLinhas();
        double total = 0;

        for (int q = 0; q < tam; q++){

            if (vetNum[q] != 0) {
                System.out.println(vetNome[q] + " estar devendo R$ " + vetNum[q]);
                total = total + vetNum[q];
            }
        }

        System.out.println("O total a receber é de R$" + total);
        imprimirLinhas();

    }

    /////////////////////////sete////////////////
    public static void atualizaAluno(Cliente[] clientinho, int tam){

        int matri = matricula();
        if (matri > -1 && matri < tam){
            adicionarAluno(clientinho, matri);
            imprimirLinhas();
            System.out.println("\n\nAluno atualizado com sucesso!!!\n\n");
            imprimirLinhas();
        }else
            imprimirDesaparecido();

    }

    public static int matricula(){

        Scanner teclado = new Scanner(System.in);
        System.out.println("Qual é a matricula do aluno? ");
        return teclado.nextInt();

    }

    public static void imprimirDesaparecido(){

        imprimirLinhas();
        System.out.println("\n\nAluno não encontrado!!!\n\n");
        imprimirLinhas();

    }
    public static void imprimirCadastrado(){

        imprimirLinhas();
        System.out.println("\n\nAluno cadastrado com sucesso!!!\n\n");
        imprimirLinhas();

    }

    public static void imprimirLinhas(){

        System.out.println("--------------------------------------------");

    }

    public static void imprimirListaVazia(){

        imprimirLinhas();
        System.out.println("\n\nLista vazia!!\n\n");
        imprimirLinhas();

    }

    public static void imprimirNumeroInvalido(){

        imprimirLinhas();
        System.out.println("\n\nNúmero inválido!!\n\n");
        imprimirLinhas();

    }

    public static void encerrandoSistema(){

        imprimirLinhas();
        System.out.println("\n\nAté mais e bom treino!!!\n\n");
        imprimirLinhas();

    }

}
package src;

/* Calculadora.java
 * Copyright 2000-2012 Prof. Rosvelter J. Coelho da Costa
 *
 * Observar correspondencia com o diagrama de estados (cf. aula/transparencias).
 */
import javax.swing.*;
import java.awt.event.*;

class Main {

    public static void main(String[] args) {
        new Calculadora();
    }

}

interface OpBin {

    double bin(double x, double y);
}

interface OpUn {
    double un(double x);
}

interface Estado {

    void eval();//?'='

    void eval(char x);//?x='0'|..|'9'|','

    void eval(OpBin op);//?op='+'|'-'|'*'|'/'

    void eval(OpUn opu);
}//

public class Calculadora implements ActionListener {

    private JTextField visor;
    String digs = "";
    double acc = 0.0, val = 0.0, mem = 0.0;
    OpBin op = null;
    OpUn opu = null;
    Estado[] estado;
    int atual = 0;
    boolean decimal = false;
    boolean isBin = true;

    private final OpBin soma = new OpBin() {
        public double bin(double x, double y) {
            return x + y;
        }
    },
            menos = new OpBin() {
                public double bin(double x, double y) {
                    return x - y;
                }
            },
            produto = new OpBin() {
                public double bin(double x, double y) {
                    return x * y;
                }
            },
            divisao = new OpBin() {
                public double bin(double x, double y) {
                    return x / y;
                }
            },
            inicial = new OpBin() {
                public double bin(double x, double y) {
                    return 0;
                }
            };

    private final OpUn umsobrex = new OpUn() {
        public double un(double x) {
            return 1 / x;
        }
    }, raiz = new OpUn(){
        public double un(double x) {
            return Math.sqrt(x);
        }
    }, ms = new OpUn(){
        public double un(double x) {
            mem = x;
            return mem;
        }
    }, madd = new OpUn(){
        public double un(double x) {
            mem += x;
            return x;
        }
    }, msub = new OpUn(){
        public double un(double x) {
            mem -= x;
            return x;
        }
    }, percentage = new OpUn(){
        public double un(double x) {
            val = ((acc)*(x/100));
            return val;
        }
    }
            ;

    Calculadora() {
        visor = new GUI(this).visor;
        op = inicial;
        opu = umsobrex;

        estado = new Estado[]{
            new CalculandoResultado(),
            new DigitandoNumero(),
            new EscolhendoOperacao()//, ...
        };

    }

    void exe() {
        if(isBin){
            acc = op.bin(acc, val);
        }else{
            acc = opu.un(val);
            val = acc;
        }
        
        show(acc);
        decimal = false;
    }

    void show(double val) {
        //visor.setText("["+ estado + "] " + val);
        visor.setText("" + val);
    }

    void show(String val) {
        //visor.setText("["+ estado + "] " + val);
        visor.setText(val);
    }

//estado 0
    class /*interna*/ CalculandoResultado implements Estado {

        public void eval() {//?'='
            //execute com os mesmos operando e operacao  ...
            exe();
        }

        public void eval(char x) {//?x='0'|..|'9'|','
            acc = 0.0;
            op = soma;
            digs = "" + x;
            show(digs);
            atual = 1;            
        }

        public void eval(OpBin s) {//?s='+'|'-'|'*'|'/'
            val = acc;
            op = s;
            atual = 2;            
        }

        public void eval(OpUn un) {
            opu = un;
            atual = 2;
            exe();
        }
    }//interna

//estado 1
    class /*interna*/ DigitandoNumero implements Estado {

        public void eval() {//?'='
            try {
                val = Double.parseDouble(digs);
            } catch (Exception ignorada) {
            }
            exe();
            atual = 0;
        }

        public void eval(char x) {//?x='0'|..|'9'|','
            if(!digs.equals("0") || x != '0')
            {
                if(digs.equals("0"))
                {
                   digs = ""; 
                }
                
                if(!decimal || x != '.')
                {
                    if(x == '.')
                        decimal = true;
                    digs += x;
                    show(digs);
                }
            }            
        }

        public void eval(OpBin s) {//?s='+'|'-'|'*'|'/'
            try {
                val = Double.parseDouble(digs);
            } catch (Exception ignorada) {
            }//experimente tratar! (exercicio)
            exe();
            op = s;
            atual = 2;
        }

        public void eval(OpUn un) {
            try {
                val = Double.parseDouble(digs);
            } catch (Exception ignorada) {
            }//experimente tratar! (exercicio)
            //exeUn();
            opu = un;
            atual = 2;
            exe();
        }
    }//interna

//estado 2
    class /*interna*/ EscolhendoOperacao implements Estado {

        public void eval() {//?'='
            exe();
            atual = 0;
        }

        public void eval(char x) {//?x='0'|..|'9'|','
            digs = "" + x;
            show(digs);
            atual = 1;
        }

        public void eval(OpBin s) {//?s='+'|'-'|'*'|'/'
            //proxima operacao ...
            op = s;
        }

        public void eval(OpUn un) {
            opu = un;
            exe();
        }
    }//interna

    //Definindo o metodo actionPerformed da interface 'ActionListener' ...
    //Obs. Mesmo os condicionais deste metodo podem ser evitados.
    public void actionPerformed(ActionEvent e) {
        String tecla = e.getActionCommand();
        if (!tecla.equals("RESULTADO"))
            isBin = true;            
        
        if (tecla.charAt(0) == '$') {//se eh um digito ...
            estado[atual].eval(tecla.charAt(1));
        } else if (tecla.equals("SEPARADOR_DECIMAL")) {
            estado[atual].eval('.');
        } else if (tecla.equals("SOMA")) {
            estado[atual].eval(soma);
        } else if (tecla.equals("MENOS")) {
            estado[atual].eval(menos);
        } else if (tecla.equals("PRODUTO")) {
            estado[atual].eval(produto);
        } else if (tecla.equals("DIVISAO")) {
            estado[atual].eval(divisao);
        } else if (tecla.equals("RESULTADO")) {
            estado[atual].eval();
        } else if (tecla.equals("CE")) {
            acc = 0;
            digs = "";
            show(acc);
            decimal = false;
        } else if (tecla.equals("C")) {
            acc = 0;
            val = 0;
            digs = "";
            show(val);
            decimal = false;
        } else if (tecla.equals("SINAL")) {
            String s = visor.getText();
            digs = "" + (-1*Double.parseDouble(s));
            visor.setText(digs);
        } else if (tecla.equals("RAIZ")) {
            isBin = false;
            estado[atual].eval(raiz);   
        } else if (tecla.equals("UMSOBREX")){
            isBin = false;
            estado[atual].eval(umsobrex);
        } else if (tecla.equals("MC")) {
            mem = 0.0;
        } else if (tecla.equals("MR")) {
            val = mem;
            show(val);   
        } else if (tecla.equals("MS")){
            isBin = false;
            estado[atual].eval(ms);
        } else if (tecla.equals("M+")) {
            isBin = false;
            estado[atual].eval(madd);   
        } else if (tecla.equals("M-")){
            isBin = false;
            estado[atual].eval(msub);
        } else if (tecla.equals("PORCENTAGEM")){
            isBin = false;
            estado[atual].eval(percentage);
        }

    }

}//Calculadora


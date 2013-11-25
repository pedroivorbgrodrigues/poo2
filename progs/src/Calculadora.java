package src;

/* Calculadora.java
 * Copyright 2000-2012 Prof. Rosvelter J. Coelho da Costa
 *
 * Observar correspondencia com o diagrama de estados (cf. aula/transparencias).
 */
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    void clear();

    void clearEntry();

    void percentage();
    
    void PI();
}//

public class Calculadora implements ActionListener {

    private JTextField visor;
    String operationLog = "";
    String digs = "";
    double acc = 0.0, val = 0.0, mem = 0.0;
    OpBin op = null;
    OpUn opu = null;
    Estado[] estado;
    int atual = 0;
    boolean decimal = false;
    boolean isBin = true;
    PrintWriter printer;

    private final OpBin soma = new OpBin() {
        public double bin(double x, double y) {
            return x + y;
        }
    }, menos = new OpBin() {
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
            raizEnesima = new OpBin() {
                public double bin(double x,double y) {
                    double fraction = 1.0 / Math.abs(y);                    
                    double raiz = 0;
                    if(Math.abs(y)%2==1)
                    {
                        raiz = Math.pow(Math.abs(x), fraction);
                        raiz =  x > 0 ? raiz : -raiz;
                    }
                    else
                    {
                        raiz = Math.pow(x, fraction);                        
                    }
                    return y < 0 ? 1/raiz : raiz;
                }

            },
            inicial = new OpBin() {
                public double bin(double x, double y) {
                    return y;
                }
            },
            potencia = new OpBin() {
                public double bin(double x, double y) {
                    return Math.pow(x, y);
                }
            };

    private final OpUn umsobrex = new OpUn() {
        public double un(double x) {
            return 1 / x;
        }
    }, raiz = new OpUn() {
        public double un(double x) {
            return Math.sqrt(x);
        }
    }, raizCubica = new OpUn() {
        public double un(double x) {
            double fraction = 1.0 / 3.0;
            double raiz = Math.pow(Math.abs(x), fraction);
            return x > 0 ? raiz : -raiz;
        }

    }, quadrado = new OpUn() {
        public double un(double x) {
            return Math.pow(x, 2);
        }

    }, fatorial = new OpUn() {
        public double un(double x) {
            int xf = (int)Math.floor(x);
            if(x % xf == 0){
                int result = 1;
                for(int i = 2; i <= x;i++)
                {
                        result *= i;
                }
                
                return result;
            }else{
                return Math.sqrt(-1);
            }
        }

    }, ms = new OpUn() {
        public double un(double x) {
            operationLog += "MS: " + x + "\r\n";
            mem = x;
            return mem;
        }
    }, madd = new OpUn() {
        public double un(double x) {
            operationLog += "M+: " + mem + " + " + x + "\r\n";
            mem += x;
            operationLog += " = " + mem;
            return x;
        }
    }, msub = new OpUn() {
        public double un(double x) {
            operationLog += "M-: " + mem + " - " + x + "\r\n";
            mem -= x;
            operationLog += " = " + mem;
            return x;
        }
    }, inicialUn = new OpUn() {
        public double un(double x) {
            return acc;
        }
    }, sin = new OpUn() {
        public double un(double x) {
            return Math.sin(x);
        }
    }, cos = new OpUn() {
        public double un(double x) {
            return Math.cos(x);
        }
    }, tan = new OpUn() {
        public double un(double x) {
            return Math.tan(x);
        }
    }, sinh = new OpUn() {
        public double un(double x) {
            return Math.sinh(x);
        }
    }, cosh = new OpUn() {
        public double un(double x) {
            return Math.cosh(x);
        }
    }, tanh = new OpUn() {
        public double un(double x) {
            return Math.tanh(x);
        }
    }, floor = new OpUn() {
        public double un(double x) {
            return Math.floor(x);
        }
    }, cubo = new OpUn() {
        public double un(double x) {
            return Math.pow(x, 3);
        }
    }, pow10 = new OpUn() {
        public double un(double x) {
            return Math.pow(10, x);
        }
    }, ln = new OpUn() {
        public double un(double x) {
            return Math.log(x);
        }
    }, log = new OpUn() {
        public double un(double x) {
            return Math.log10(x);
        }
    };

    Calculadora() {
        visor = new GUI(this).visor;
        op = inicial;
        opu = inicialUn;

        estado = new Estado[]{
            new CalculandoResultado(),
            new DigitandoNumero(),
            new EscolhendoOperacao()//, ...
        };

    }

    /*void clearEntry()
     {
     val = 0;
     digs = "";
     show(val);
     decimal = false;
     atual = 0;
     }
     */
    void pi(){
        val = Math.PI;
        show(val);
    }
    
    void clearAll() {
        digs = "";
        acc = 0.0;
        val = 0.0;
        mem = 0.0;
        op = inicial;
        opu = inicialUn;
        atual = 0;
        decimal = false;
        isBin = true;
        show(val);
    }

    void inverterSinal() {
        String s = visor.getText();
        double d = -1 * Double.parseDouble(s);
        show(d);
    }

    void porcentagem() {
        val = Double.parseDouble(digs);
        val = acc * (val / 100);
        show(val);
    }

    void exportarLogParaArquivoTxt() {
        try {
            printer = new PrintWriter("filename.txt");
            printer.print(operationLog);
            printer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void exe() {
        if (isBin) {
            acc = op.bin(acc, val);
        } else {
            acc = opu.un(val);
            opu = inicialUn;
            isBin = true;
            val = acc;
        }
        show(acc);
        decimal = false;
    }

    void show(double val) {
        //visor.setText("["+ estado + "] " + val);
        visor.setText("" + val);
        digs = "" + val;
    }

    void show(String val) {
        //visor.setText("["+ estado + "] " + val);
        visor.setText(val);
        digs = "" + val;
    }

//estado 0
    class /*interna*/ CalculandoResultado implements Estado {

        public void eval() {//?'='
            //execute com os mesmos operando e operacao  ...
            exe();
        }

        public void eval(char x) {//?x='0'|..|'9'|','
            acc = 0.0;
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

        public void clear() {
            clearAll();
        }

        public void clearEntry() {
            acc = 0.0;
            digs = "";
            show(acc);
        }

        public void percentage() {
            double x = Double.parseDouble(digs);
            val = ((acc) * (x / 100));
            show(val);
        }
        
        public void PI(){
            pi();
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
            if (!digs.equals("0") || digs.equals("0.0") || x != '0') {
                if (digs.equals("0") || digs.equals("0.0")) {
                    digs = "";
                }

                if (!decimal || x != '.') {
                    if (x == '.') {
                        decimal = true;
                    }
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
            opu = un;
            atual = 2;
            exe();
        }

        public void clear() {
            clearAll();
        }

        public void clearEntry() {
            val = 0.0;
            digs = "";
            show(val);
        }

        public void percentage() {
            double x = Double.parseDouble(digs);
            val = ((acc) * (x / 100));
            show(val);
            atual = 2;
        }
        
        public void PI(){
            pi();
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

        public void clear() {
            clearAll();
        }

        public void clearEntry() {
            val = 0.0;
            digs = "";
            show(val);
        }

        public void percentage() {
            val = ((acc) * (acc / 100));
            show(val);
            atual = 0;
        }
        
        public void PI(){
            pi();
        }
    }//interna

    //Definindo o metodo actionPerformed da interface 'ActionListener' ...
    //Obs. Mesmo os condicionais deste metodo podem ser evitados.
    public void actionPerformed(ActionEvent e) {
        String tecla = e.getActionCommand();
        if (!tecla.equals("=")) {
            isBin = true;
        }

        if (tecla.charAt(0) == '$') {//se eh um digito ...
            estado[atual].eval(tecla.charAt(1));
        } else if (tecla.equals("SEPARADOR_DECIMAL")) {
            estado[atual].eval('.');
        } else if (tecla.equals("+")) {
            estado[atual].eval(soma);
        } else if (tecla.equals("-")) {
            estado[atual].eval(menos);
        } else if (tecla.equals("*")) {
            estado[atual].eval(produto);
        } else if (tecla.equals("/")) {
            estado[atual].eval(divisao);
        } else if (tecla.equals("=")) {
            estado[atual].eval();
        } else if(tecla.equals("QUADRADO"))
        {
            isBin = false;
            estado[atual].eval(quadrado);
        } else if (tecla.equals("CE")) {
            estado[atual].clearEntry();
        } else if (tecla.equals("C")) {
            estado[atual].clear();
        } else if (tecla.equals("SINAL")) {
            inverterSinal();
        } else if (tecla.equals("RAIZ")) {
            isBin = false;
            estado[atual].eval(raiz);
        } else if (tecla.equals("NRAIZ"))
        {
          estado[atual].eval(raizEnesima);
        } else if (tecla.equals("RAIZCUBO")) {
            isBin = false;
            estado[atual].eval(raizCubica);
        } else if (tecla.equals("UMSOBREX")) {
            isBin = false;
            estado[atual].eval(umsobrex);
        } else if (tecla.equals("MC")) {
            mem = 0.0;
        } else if (tecla.equals("MR")) {
            val = mem;
            show(val);
        } else if (tecla.equals("MS")) {
            isBin = false;
            estado[atual].eval(ms);
        } else if (tecla.equals("M+")) {
            isBin = false;
            estado[atual].eval(madd);
        } else if (tecla.equals("M-")) {
            isBin = false;
            estado[atual].eval(msub);
        } else if (tecla.equals("%")) {
            estado[atual].percentage();
        } else if (tecla.equals(">")) {
            exportarLogParaArquivoTxt();
        } else if(tecla.equals("FATORIAL")){
            isBin = false;
            estado[atual].eval(fatorial);
        } else if(tecla.equals("COS")){
            isBin = false;
            estado[atual].eval(cos);
        } else if(tecla.equals("SIN")){
            isBin = false;
            estado[atual].eval(sin);
        } else if(tecla.equals("TAN")){
            isBin = false;
            estado[atual].eval(tan);
        } else if(tecla.equals("COSH")){
            isBin = false;
            estado[atual].eval(cosh);
        } else if(tecla.equals("SINH")){
            isBin = false;
            estado[atual].eval(sinh);
        } else if(tecla.equals("TANH")){
            isBin = false;
            estado[atual].eval(tanh);
        } else if(tecla.equals("FLOOR")){
            isBin = false;
            estado[atual].eval(floor);
        }else if(tecla.equals("CUBO")){
            isBin = false;
            estado[atual].eval(cubo);
        } else if(tecla.equals("POTENCIA")){
            estado[atual].eval(potencia);
        } else if(tecla.equals("10POW")){
            isBin = false;
            estado[atual].eval(pow10);
        } else if(tecla.equals("LOG")){
            isBin = false;
            estado[atual].eval(log);
        } else if(tecla.equals("LN")){
            isBin = false;
            estado[atual].eval(ln);
        } else if(tecla.equals("PI")){
            estado[atual].PI();
        }
    }

}//Calculadora


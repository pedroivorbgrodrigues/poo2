package src;

/* GUI.java
 * Copyright 2000 Prof. Rosvelter J. Coelho da Costa
 *
 * Projeto Calculadora
 * Cf. Calculadora.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUI extends JFrame {

    protected String Msg_Inicial = "0.0";
    protected String Separador_Decimal = ".";
    protected JTextField visor;
    protected JTextField historico;
    
    GUI(ActionListener Ouvinte) {

    //Utilizando o 'ContentPane' default ...
        //Obs. getContentPane() fornece uma referencia ao ContentPane default.
        Container pane = getContentPane();
        //Define layout do 'ContentPane' ...
        pane.setLayout(new BorderLayout(2, 2));
        //Criando um visor ...
        visor = new JTextField(Msg_Inicial);
        //... de somente leitura ...
        visor.setEditable(false);
        //... com alinhamento a direita ...
        visor.setHorizontalAlignment(JTextField.RIGHT);
        //... com border linha cinza escuro ...
        visor.setBorder(new javax.swing.border.LineBorder(java.awt.Color.gray, 0));
        visor.setMargin(null);
    //Obs. 'setBorder(tipo:Border,espessura:int)' eh um metodo aplicado a qualquer 'JComponent'. 
        //'gray' eh um atributo estatico da classe 'java.awt.Color'.
        //Experimente outras cores.
        visor.setBackground(java.awt.Color.white);
        
        //Criando um visor ...
        historico = new JTextField();
        //... de somente leitura ...
        historico.setEditable(false);
        //... com alinhamento a direita ...
        historico.setHorizontalAlignment(JTextField.RIGHT);
        //... com border linha cinza escuro ...
        historico.setBorder(new javax.swing.border.LineBorder(java.awt.Color.gray, 0));
        historico.setMargin(null);
    //Obs. 'setBorder(tipo:Border,espessura:int)' eh um metodo aplicado a qualquer 'JComponent'. 
        //'gray' eh um atributo estatico da classe 'java.awt.Color'.
        //Experimente outras cores.
        historico.setBackground(new java.awt.Color(235,235,235));

        //Adicionando o componente ao 'Contentpane' ...
        pane.add(historico, BorderLayout.NORTH);
        pane.add(visor);

        //Criando um sub-recipiente para as teclas ...
        JPanel Teclado = new JPanel(new GridBagLayout()); // 4 linhas X 4 colunas ...
        //... + espacamento de 8pt tanto na horizontal quanto na vertical. 
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(3,3,3,3);

        //Criando as teclas ...
        JButton Tecla;
        
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 0;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 0;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 0;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 0;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 5;
        c.gridy = 0;
        Tecla = new JButton("MC");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 6;
        c.gridy = 0;
        Tecla = new JButton("MR");
        Tecla.setActionCommand("MR");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 7;
        c.gridy = 0;
        Tecla = new JButton("MS");
        Tecla.setActionCommand("MS");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 8;
        c.gridy = 0;
        Tecla = new JButton("M+");
        Tecla.setActionCommand("M+");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 9;
        c.gridy = 0;
        Tecla = new JButton("M-");
        Tecla.setActionCommand("M-");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 0;
        c.gridy = 1;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 1;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 1;
        Tecla = new JButton("ln");
        Tecla.setActionCommand("LN");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 1;
        Tecla = new JButton("(");
        Tecla.setActionCommand("LEFTPAR");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 1;
        Tecla = new JButton(")");
        Tecla.setActionCommand("RIGHTPAR");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 5;
        c.gridy = 1;
        Tecla = new JButton("←");
        Tecla.setActionCommand("BACKSPACE");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 6;
        c.gridy = 1;
        Tecla = new JButton("CE");
        Tecla.setActionCommand("CE");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 7;
        c.gridy = 1;
        Tecla = new JButton("C");
        Tecla.setActionCommand("C");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 8;
        c.gridy = 1;
        Tecla = new JButton("±");
        Tecla.setActionCommand("SINAL");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 9;
        c.gridy = 1;
        Tecla = new JButton("√");
        Tecla.setActionCommand("RAIZ");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 0;
        c.gridy = 2;
        Tecla = new JButton("floor");
        Tecla.setActionCommand("FLOOR");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 2;
        Tecla = new JButton("sinh");
        Tecla.setActionCommand("SINH");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 2;
        Tecla = new JButton("sin");
        Tecla.setActionCommand("SIN");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 2;
        Tecla = new JButton("x²");
        Tecla.setActionCommand("QUADRADO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 2;
        Tecla = new JButton("n!");
        Tecla.setActionCommand("FATORIAL");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 5;
        c.gridy = 2;
        Tecla = new JButton("7");
        Tecla.setActionCommand("$7");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)

        c.gridx = 6;
        c.gridy = 2;
        Tecla = new JButton("8");
        Tecla.setActionCommand("$8");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 7;
        c.gridy = 2;
        Tecla = new JButton("9");
        Tecla.setActionCommand("$9");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 8;
        c.gridy = 2;
        Tecla = new JButton("/");
        Tecla.setActionCommand("DIVISAO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 9;
        c.gridy = 2;
        Tecla = new JButton("%");
        Tecla.setActionCommand("PORCENTAGEM");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 0;
        c.gridy = 3;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 3;
        Tecla = new JButton("cosh");
        Tecla.setActionCommand("COSH");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 3;
        Tecla = new JButton("cos");
        Tecla.setActionCommand("cosh");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 3;
        Tecla = new JButton("x^y");
        Tecla.setActionCommand("POTENCIA");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 3;
        Tecla = new JButton("raiz n");
        Tecla.setActionCommand("NRAIZ");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 5;
        c.gridy = 3;
        Tecla = new JButton("4");
        Tecla.setActionCommand("$4");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 6;
        c.gridy = 3;
        Tecla = new JButton("5");
        Tecla.setActionCommand("$5");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 7;
        c.gridy = 3;
        Tecla = new JButton("6");
        Tecla.setActionCommand("$6");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 8;
        c.gridy = 3;
        Tecla = new JButton("*");
        Tecla.setActionCommand("PRODUTO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 9;
        c.gridy = 3;
        Tecla = new JButton("1/x");
        Tecla.setActionCommand("UMSOBREX");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);        

        c.gridx = 0;
        c.gridy = 4;
        Tecla = new JButton("pi");
        Tecla.setActionCommand("PI");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 4;
        Tecla = new JButton("tanh");
        Tecla.setActionCommand("TANH");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 4;
        Tecla = new JButton("tan");
        Tecla.setActionCommand("TAN");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 4;
        Tecla = new JButton("x³");
        Tecla.setActionCommand("CUBO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 4;
        Tecla = new JButton("raiz 3");
        Tecla.setActionCommand("RAIZCUBO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 5;
        c.gridy = 4;
        Tecla = new JButton("1");
        Tecla.setActionCommand("$1");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 6;
        c.gridy = 4;
        Tecla = new JButton("2");
        Tecla.setActionCommand("$2");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 7;
        c.gridy = 4;
        Tecla = new JButton("3");
        Tecla.setActionCommand("$3");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 8;
        c.gridy = 4;
        Tecla = new JButton("-");
        Tecla.setActionCommand("MENOS");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 9;
        c.gridy = 4;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        Tecla = new JButton("=");
        Tecla.setActionCommand("RESULTADO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 0;
        c.gridy = 5;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 5;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 5;
        Tecla = new JButton("");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 5;
        Tecla = new JButton("log");
        Tecla.setActionCommand("LOG");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 5;
        Tecla = new JButton("10^x");
        Tecla.setActionCommand("10POW");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 5;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;        
        Tecla = new JButton("0");
        Tecla.setActionCommand("$0");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 7;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        Tecla = new JButton(Separador_Decimal);
        Tecla.setActionCommand("SEPARADOR_DECIMAL");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 8;
        c.gridy = 5;
        Tecla = new JButton("+");
        Tecla.setActionCommand("SOMA");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);        

        //Adicionando o componente ao 'Contentpane' ...
        pane.add(Teclado, BorderLayout.SOUTH);

        //Ouvindo 'window closing' ...
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        //Inicializando parametros ...
        setTitle("Calculadora"); //... do 'JFrame'.
        //setSize(200,250); //... do 'JFrame' em pixels.
        pack(); //... para juntar os componentes.
        setVisible(true); //... este JFrame e todos os seus componentes.
    }//GUI()

}//GUI

//Teste para GUI 
class TesteGUI implements ActionListener {

    static GUI gui;

    public void actionPerformed(ActionEvent e) {
        show(e.getActionCommand());
    }

    void show(String val) {
        gui.visor.setText(val);
    }

    public static void main(String[] args) {
        gui = new GUI(new TesteGUI());
        //Inicializando parametros ... 
        gui.setTitle("Calculadora"); //... do 'JFrame'. 
        //gui.setSize(200,250); //... do 'JFrame' em pixels. 
        gui.pack(); //... para juntar os componentes. 
        //gui.setLocationRelativeTo(null);//... por no meio da tela
        gui.setLocation(new Point(100, 100));//.. 
        gui.setVisible(true); //... este JFrame e todos os seus componentes.
    }
}//Teste 


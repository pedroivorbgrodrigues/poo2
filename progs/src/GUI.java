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
        visor.setBorder(new javax.swing.border.LineBorder(java.awt.Color.gray, 2));
    //Obs. 'setBorder(tipo:Border,espessura:int)' eh um metodo aplicado a qualquer 'JComponent'. 
        //'gray' eh um atributo estatico da classe 'java.awt.Color'.
        //Experimente outras cores.
        visor.setBackground(java.awt.Color.white);

        //Adicionando o componente ao 'Contentpane' ...
        pane.add(visor, BorderLayout.NORTH);

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
        Tecla = new JButton("MC");
        Tecla.setActionCommand("MC");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 0;
        Tecla = new JButton("MR");
        Tecla.setActionCommand("MR");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 0;
        Tecla = new JButton("MS");
        Tecla.setActionCommand("MS");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 0;
        Tecla = new JButton("M+");
        Tecla.setActionCommand("M+");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 0;
        Tecla = new JButton("M-");
        Tecla.setActionCommand("M-");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 0;
        c.gridy = 1;
        Tecla = new JButton("←");
        Tecla.setActionCommand("BACKSPACE");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 1;
        c.gridy = 1;
        Tecla = new JButton("CE");
        Tecla.setActionCommand("CE");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 2;
        c.gridy = 1;
        Tecla = new JButton("C");
        Tecla.setActionCommand("C");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 3;
        c.gridy = 1;
        Tecla = new JButton("±");
        Tecla.setActionCommand("SINAL");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)
        
        c.gridx = 4;
        c.gridy = 1;
        Tecla = new JButton("√");
        Tecla.setActionCommand("RAIZ");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 0;
        c.gridy = 2;
        Tecla = new JButton("7");
        Tecla.setActionCommand("$7");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla, c);
        Tecla.setFocusable(false);//estetica (entre outras possiveis)

        c.gridx = 1;
        c.gridy = 2;
        Tecla = new JButton("8");
        Tecla.setActionCommand("$8");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 2;
        c.gridy = 2;
        Tecla = new JButton("9");
        Tecla.setActionCommand("$9");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 3;
        c.gridy = 2;
        Tecla = new JButton("/");
        Tecla.setActionCommand("DIVISAO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 4;
        c.gridy = 2;
        Tecla = new JButton("%");
        Tecla.setActionCommand("PORCENTAGEM");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 0;
        c.gridy = 3;
        Tecla = new JButton("4");
        Tecla.setActionCommand("$4");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 1;
        c.gridy = 3;
        Tecla = new JButton("5");
        Tecla.setActionCommand("$5");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 2;
        c.gridy = 3;
        Tecla = new JButton("6");
        Tecla.setActionCommand("$6");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 3;
        c.gridy = 3;
        Tecla = new JButton("*");
        Tecla.setActionCommand("PRODUTO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 4;
        c.gridy = 3;
        Tecla = new JButton("1/x");
        Tecla.setActionCommand("UMSOBREX");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);        

        c.gridx = 0;
        c.gridy = 4;
        Tecla = new JButton("1");
        Tecla.setActionCommand("$1");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 1;
        c.gridy = 4;
        Tecla = new JButton("2");
        Tecla.setActionCommand("$2");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 2;
        c.gridy = 4;
        Tecla = new JButton("3");
        Tecla.setActionCommand("$3");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 3;
        c.gridy = 4;
        Tecla = new JButton("-");
        Tecla.setActionCommand("MENOS");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 4;
        c.gridy = 4;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        Tecla = new JButton("=");
        Tecla.setActionCommand("RESULTADO");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;        
        Tecla = new JButton("0");
        Tecla.setActionCommand("$0");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);

        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        Tecla = new JButton(Separador_Decimal);
        Tecla.setActionCommand("SEPARADOR_DECIMAL");
        Tecla.addActionListener(Ouvinte);
        Teclado.add(Tecla,c);
        
        c.gridx = 3;
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


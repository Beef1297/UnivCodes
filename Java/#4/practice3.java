JFrame のadd(引数が二つのadd)
    *引数が二つのaddは,クラスjava.awt.Containerから継承されたメソッド
    また,addはオーバーロードされていて,add自体5つ定義されていた.
JFrame の setTitle
    *これは,クラスjava.awt.Frameで定義され継承されたメソッドである
JFrame の setVisible
    *これは,クラスjava.awt.Windowから継承されたメソッドである
JLabel の setText
    *これは,クラスjavax.swing.JLabelで定義されたメソッドである
JButton の addActionListener
    *これは,クラスjavax.swing.AbstractButtonから継承されたメソッドである

 次に,JLabelの祖先クラスは
    java.lang.Object
         java.awt.Component
              java.awt.Container
                   javax.swing.JComponent
    である.
 そして,JButtonの祖先クラスは
    java.lang.Object
         java.awt.Component
              java.awt.Container
                   javax.swing.JComponent
                        javax.swing.AbstractButton
    である.

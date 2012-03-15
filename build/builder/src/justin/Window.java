package justin;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Window extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton b1 = new JButton("GO"),
                    b2 = new JButton("Add Data");
    private JTextField textField = new JTextField(50);
    static String path = "http://www.baidu.com";
    static final Display display = Display.getDefault();
    static Canvas canvas = null;
    static Shell shell;
    static Browser browser = null;

    public Window() {
        System.setProperty("sun.awt.xembedserver", "true");
        canvas = new Canvas() {
            private static final long serialVersionUID = 1L;
            @Override
            public void addNotify() {
                super.addNotify();
                final Canvas canvas_ = this;
                display.asyncExec(new Runnable() {
                    public void run() {
                        shell = SWT_AWT.new_Shell(display, canvas);
                        shell.setText("Slashdot");
                        shell.setLayout(new FillLayout());
                        browser = new Browser(shell, SWT.NONE);
                        browser.addProgressListener(new ProgressListener() {
                            public void changed(ProgressEvent event) {
                            }

                            public void completed(ProgressEvent event) {
                                /*String script = "var a = new Array();" +
                                                "a = document.getElementsByTagName(\"a\");" +
                                                "for(i = 0; i < a.length; i++) {" +
                                                "a[i].target = '_self';" +
                                                "}";
                                browser.execute(script);*/
                                System.out.println("网页加载完成！");
                            }
                        });
                        browser.addOpenWindowListener(new OpenWindowListener() {
                            @Override
                            public void open(WindowEvent event) {
                                final Browser browser2 = new Browser(shell, SWT.NONE);
                                event.browser = browser2;
                                event.display.asyncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        browser.setUrl(browser2.getUrl());
                                    }
                                });
                            }

                        });
                        browser.setUrl(path);
                        browser.setVisible(true);
                        shell.setSize(canvas_.getWidth(), canvas_.getHeight());
                        shell.open();
                        while(!shell.isDisposed()){
                            if(!display.readAndDispatch()){
                                display.sleep();
                            }
                        }
                    }
                });
            }
        };
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(textField);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = textField.getText();
                display.asyncExec(new Runnable() {
                    public void run() {
                        browser.setUrl(path);
                    }
                });
            }

        });
        panel.add(b1);
        add(BorderLayout.NORTH, panel);
        add(BorderLayout.CENTER, canvas);
        setSize(800, 600);
        //ImageIcon ii = new ImageIcon(this.getClass().getResource("thu.jpg"));
        //setIconImage(ii.getImage());
        setTitle("swt嵌入swing");
        setBounds(200, 200, 800, 600);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static Window window;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                window = new Window();
            }
        });
        while(true){
            if(!display.readAndDispatch()){
                display.sleep();
            }
        }

    }
}
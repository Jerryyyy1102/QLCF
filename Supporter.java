/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import javax.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Admin
 */
public class Supporter {

    public static void ganPhim(int phimTat, String tenAction, JButton btn, Runnable runnable) {
        // Tạo một KeyStroke với phím tắt từ tham số
        KeyStroke keyStroke = KeyStroke.getKeyStroke(phimTat, 0);

        // Lấy InputMap của button
        InputMap inputMap = btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Gán KeyStroke cho Action Name "Enter"
        inputMap.put(keyStroke, tenAction);

        // Lấy ActionMap của button
        ActionMap actionMap = btn.getActionMap();

        // Đăng ký một AbstractAction với Action Name "Enter"
        actionMap.put(tenAction, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runnable.run();
            }
        });
    }

    public void ganPhim(int phimTat, int phimTat2, String tenAction, JButton btn, Runnable runnable) {
        // Tạo một KeyStroke với phím tắt từ tham số
        KeyStroke keyStroke = KeyStroke.getKeyStroke(phimTat, phimTat2);

        // Lấy InputMap của button
        InputMap inputMap = btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Gán KeyStroke cho Action Name "Enter"
        inputMap.put(keyStroke, tenAction);

        // Lấy ActionMap của button
        ActionMap actionMap = btn.getActionMap();

        // Đăng ký một AbstractAction với Action Name "Enter"
        actionMap.put(tenAction, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runnable.run();
            }
        });
    }
}

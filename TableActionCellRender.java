package Assets.Custom_Item;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    
    private Map<Integer,Integer> alignment = new HashMap<>();
    public void setAlignment( int colum, int align)
    {
        alignment.put(colum,align);
    }
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelAction action = new PanelAction();
        if (isSeleted == false && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        
        if(alignment.containsKey(column))
        {
            setHorizontalAlignment(alignment.get(column));
        }
        else{
            setHorizontalAlignment(JLabel.LEFT);
        }
        return action;
    }
}

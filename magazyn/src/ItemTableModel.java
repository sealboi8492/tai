import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {

    private final List<Item> items = new ArrayList<>();

    private final String[] columns = {
            "ID",
            "Nazwa",
            "Ilość",
            "Kategoria"
    };

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Item item = items.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getName();
            case 2 -> item.getQuantity();
            case 3 -> item.getCategory();
            default -> null;
        };
    }

    public void addItem(Item item) {
        items.add(item);

        fireTableRowsInserted(
                items.size() - 1,
                items.size() - 1
        );
    }

    public void removeItem(int row) {

        if (row >= 0 && row < items.size()) {
            items.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
}
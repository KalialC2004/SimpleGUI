/**
 * An interface for objects that can be represented as a row in a Table.
 * This acts as a contract, ensuring that any object destined for a Table
 * knows how to convert itself into a simple array of its data.
 */
public interface Rowable {
    /**
     * Returns the object's data as an array of Objects.
     * The order of the elements in the array should match the order
     * of the columns in the Table.
     *
     * @return An array of Objects representing a single table row.
     */
    Object[] getRowData();
}

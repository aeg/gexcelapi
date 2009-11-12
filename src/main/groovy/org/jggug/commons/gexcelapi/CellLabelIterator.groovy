package org.jggug.commons.gexcelapi

class CellLabelIterator implements Iterator<String> {

    private final int beginRow
    private final int beginColumn
    private final int endRow // inclusive
    private final int endColumn // inclusive

    private int nextRow
    private int nextColumn

    CellLabelIterator(beginRow, beginColumn, endRow, endColumn) {
        this.beginRow = beginRow
        this.beginColumn = beginColumn
        this.endRow = endRow
        this.endColumn = endColumn
        (nextRow, nextColumn) = [beginRow, beginColumn]
    }

    CellLabelIterator(beginLabel, endLabel) {
        this(
            Util.rowIndex(beginLabel), 
            Util.colIndex(beginLabel),
            Util.rowIndex(endLabel), 
            Util.colIndex(endLabel)
        )
    }

    boolean hasNext() {
        nextRow <= endRow && nextColumn <= endColumn
    }

    String next() {
        if (!hasNext()) { throw new NoSuchElementException("out of range") }
        def (row, column) = [nextRow, nextColumn]
        nextRow++
        if (nextRow > endRow) {
            nextColumn++
            nextRow = beginRow
        }
        return Util.cellLabel(row, column) // if null, return value is null
    }

    void remove() {
        throw new UnsupportedOperationException('read only')
    }

}


package fr.enedis.grafana.dsl.panels

class LegendBuilder {

    var alignAsTable: Boolean = false
    var avg: Boolean = false
    var current: Boolean = false
    var hideEmpty: Boolean = false
    var hideZero: Boolean = false
    var max: Boolean = false
    var min: Boolean = false
    var rightSide: Boolean = false
    var show: Boolean = false
    var sort: Sort? = Sort.AVG
    var sortDesc: Boolean = false
    var total: Boolean = false
    var values: Boolean = false
    var sideWidth: Int? = null

    fun createLegend() = Legend(
        alignAsTable = alignAsTable,
        avg = avg,
        current = current,
        hideEmpty = hideEmpty,
        hideZero = hideZero,
        max = max,
        min = min,
        rightSide = rightSide,
        show = show,
        sort = sort,
        sortDesc = sortDesc,
        total = total,
        values = values,
        sideWidth = sideWidth,
    )
}
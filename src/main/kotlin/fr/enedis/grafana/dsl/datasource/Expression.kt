/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

import fr.enedis.grafana.dsl.datasource.Datasource

/**
 * Elastic datasource type
 */
interface ExprDataSource : Datasource

object Expr : ExprDataSource {
    override fun asDatasourceName() = "__expr__"
}

/**
 * Create elastic datasource
 */
fun exprDataSource(name: String): ExprDataSource = SimpleExprDataSource(name)

internal class SimpleExprDataSource(private val name: String) : ExprDataSource {
    override fun asDatasourceName(): String = name
}
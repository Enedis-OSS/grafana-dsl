/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.annotations

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Mutable builder for building annotation collection.
 */
@DashboardElement
class AnnotationsBuilder {

    internal val annotations: MutableList<Annotation> = mutableListOf()

    /**
     * Adds new annotation to [annotations] with given [name].
     */
    fun annotation(name: String, build: AnnotationBuilder.() -> Annotation) {
        val builder = AnnotationBuilder(name)
        annotations += builder.build()
    }
}
/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.folder

data class Folder(
    val id: Long? = null,
    val uid: String? = null,
    val title: String
) {
    init {
        require(!(id == null && uid.isNullOrBlank())) {
            "Either 'id' or 'uid' must be provided."
        }
    }
}
/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.folder

data class Folder(
    val uid: String? = null,
    val title: String? = null,
    val parentUid: String? = null
) {
    init {
        require(!(title.isNullOrBlank() && uid.isNullOrBlank())) {
            "Either 'uid' or 'title' must be provided."
        }
    }
}
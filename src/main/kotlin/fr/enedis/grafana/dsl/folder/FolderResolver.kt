/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.folder

import fr.enedis.grafana.dsl.dashboard.Dashboard

interface FolderResolver {
    fun resolve(dashboard: Dashboard): Folder?
}
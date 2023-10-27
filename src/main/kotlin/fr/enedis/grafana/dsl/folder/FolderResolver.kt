package fr.enedis.grafana.dsl.folder

import fr.enedis.grafana.dsl.dashboard.Dashboard

interface FolderResolver {
    fun resolve(dashboard: Dashboard): Folder?
}
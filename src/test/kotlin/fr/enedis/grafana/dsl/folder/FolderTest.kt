/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.folder

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FolderInstantiationTest {

    @Test
    fun `should throw exception if both id or uid are null`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Folder(title = "Test Folder")
        }
    }
}
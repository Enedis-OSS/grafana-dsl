package fr.enedis.grafana.dsl.panels

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Тестирует [Color]
 *
 * @author dupavlov
 */
class ColorTest {

    @Test
    fun `should create Color instance via constructor`() {
        val color = Color(126, 178, 109)
        color.red shouldBeEqualTo 126
        color.green shouldBeEqualTo 178
        color.blue shouldBeEqualTo 109
        color.asString() shouldBeEqualTo "#7eb26d"
    }

    @Test
    fun `should create Color instance via factory`() {
        val color = Color.of(0x7EB26D)
        color.red shouldBeEqualTo 126
        color.green shouldBeEqualTo 178
        color.blue shouldBeEqualTo 109
        color.asString() shouldBeEqualTo "#7eb26d"
    }

    @ParameterizedTest
    @MethodSource("dataForExceptionHandlingTest")
    fun `should throw exception if one pf chanel values id out of bounds`(red: Int, green: Int, blue: Int) {
        val illegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Color(red, green, blue)
        }
        Assertions.assertEquals(true, illegalArgumentException.message?.matches(Regex(".*must be between 0 and 255.*")))
    }

    @ParameterizedTest
    @MethodSource("dataForTestingConstants")
    fun `should return appropriate value for each constant`(color: Color, value: String) {
        color.asString() shouldBeEqualTo value
    }

    companion object {
        @JvmStatic
        fun dataForExceptionHandlingTest() = arrayOf(
            arrayOf(280, 0, 0),
            arrayOf(128, -172, 0),
            arrayOf(128, 255, 400)
        )

        @JvmStatic
        fun dataForTestingConstants() = arrayOf(
            arrayOf(Color.GREEN, "#7eb26d"),
            arrayOf(Color.YELLOW, "#eab839"),
            arrayOf(Color.RED, "#bf1b00"),
            arrayOf(Color.PURPLE, "#3f2b5b"),
            arrayOf(Color.DARK_GREEN, "#3f6833"),
            arrayOf(Color.ORANGE, "#ef843c"),
            arrayOf(Color.DARK_RED, "#890f02"),
            arrayOf(Color.BLUE, "#64b0c8"),
            arrayOf(Color.CORAL, "#e24d42"),
            arrayOf(Color.WHITE, "#fce2de")
        )
    }
}
